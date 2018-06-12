-- 检查表是否存在
Delimiter ;
Drop Function If Exists `fnTableExists`;
Delimiter ;;
Create Function `fnTableExists`(
    p_TableName Varchar(50)
) Returns Bit
Begin 
	If Exists(Select * From information_schema.`TABLES` Where TABLE_SCHEMA = Database() And Lower(Table_Name) = Lower(p_TableName)) Then 
		Return 1;
	Else 
		Return 0;
  End If;
End ;;

-- 检查字段是否存在
Delimiter ;
Drop Function If Exists `fnColumnExists`;
Delimiter ;;
Create Function `fnColumnExists` (
	p_TableName Varchar(50), 
	p_ColumnName Varchar(50)
) Returns Bit
Begin
	If Exists(Select * From information_schema.`COLUMNS` Where TABLE_SCHEMA = Database() And Lower(TABLE_NAME) = Lower(p_TableName) And Lower(COLUMN_NAME) = Lower(p_ColumnName)) Then 
		Return 1;
	Else
		Return 0;
	End If;
End ;;

-- CEC平台参数
Delimiter ;
Drop Procedure If Exists `spUpdateDatabase`;
Delimiter ;;
Create Procedure `spUpdateDatabase`(
) Begin

	If fnTableExists('tbOperatorParams') = 0 Then
		Create Table `tbOperatorParams` (
			`Id` Int(10) Unsigned Not Null Auto_Increment Comment '主键',
			`OperatorID` Varchar(100) Not Null Comment '运营商组织机构代码',
			`OperatorMobile` Varchar(11) Not Null Comment '运营商手机号',
			`OperatorSecret` Varchar(1000) Not Null Comment '运营商秘钥',
			`SigKey` Varchar(1000) Not Null Comment '参数签名秘钥',
			`AESKey` Varchar(1000) Not Null Comment '数据AES加解密秘钥',
			`AESIv` Varchar(1000) Not Null Comment '数据AES加解密向量',
			`Url` Varchar(1000) Not Null Comment '对接平台url',
			`OwnerID` Varchar(100) Not Null Comment '自己平台Id',
			`OwnerSecret` Varchar(1000) Not Null Comment '自己平台的运营商秘钥',
			`OwnerSigKey` Varchar(1000) Not Null Comment '自己平台的参数签名秘钥',
			`OwnerAESKey` Varchar(1000) Not Null Comment '自己平台的数据AES加解密秘钥',
			`OwnerAESIv` Varchar(1000) Not Null Comment '自己平台的数据AES加解密向量',
			Primary Key(`Id`)
		) Comment '运营商参数配置表';
	End If;
	
	
	If fnTableExists('tbCECOrderLog') = 0 Then
		Create Table `tbCECOrderLog` (
		`Id` Int(10) Unsigned Not Null Auto_Increment Comment '主键',
		`OperatorID` Varchar(100) Not Null Comment '运营商组织机构代码',
		`StartChargeSeq` Varchar(100) Not Null Comment '充电订单号',
		`TradingSn` Bigint(20) Not Null Comment '交易流水号',
		`ClientId` int(10)  Default Null COMMENT '客户Id',
		`CreatedDateTime` Datetime Not Null COMMENT '数据创建时间',
		`OrderType` Int(10) Default Null Comment '订单类型，1：平台内车主消费，2：平台外车主消费',
		`ConnectorId` Varchar(100) Not Null Comment '充电设备接口编码',
		`StartTime` DateTime Default Null Comment '开始充电时间',
		`EndTime` DateTime Default Null Comment '结束充电时间',
		`StopReason` Int(10) Default Null Comment '充电结束原因，0：用户手动停止充电，1：客户归属地运营商平台停止充电，2：BMS停止充电，3：充电机设备故障，4：连接器断开，5~99：自定义',
		`TotalPower` Float(20) Default Null Comment '累计充电量，单位：度，小数点后2位',
		`TotalElecMoney` Float(20) Default Null Comment '累计电费，单位：元，小数点后2位',
		`TotalServiceMoney` Float(20) Default Null Comment '累计服务费，单位：元，小数点后2位',
		`TotalMoney` Float(20) Default Null Comment '累计总金额，单位：元，小数点后2位',
		`ConfirmResult` Int(10) Default Null Comment '订单推送确认结果，0：成功， 1：争议订单，2~99：自定义',
		Primary Key(`Id`),
		KEY `idx_CECOrderLog_SCS` (`StartChargeSeq`),
		KEY `idx_CECOrderLog_CDT` (`CreatedDateTime`),
		KEY `idx_CECOrderLog_TS` (`TradingSn`),
		KEY `idx_CECOrderLog_CId` (`ClientId`)
		) Comment 'CEC订单记录表';
	End If;
	
	-- 添加App合作企业关联
	If fnColumnExists('tbOperator','AppCooperatorId') = 0 Then
		Alter Table `tbOperator` Add `AppCooperatorId` Int(10) unsigned Default Null Comment 'App合作企业Id';
	End If;
	
	-- 添加运营商配置信息关联
	If fnColumnExists('tbOperator','OperatorParamsId') = 0 Then
		Alter Table `tbOperator` Add `OperatorParamsId` Int(10) unsigned Default Null Comment '运营商参数配置Id',
		Add Key `fk_Operator_OPId` (`OperatorParamsId`),
		Add Constraint `fk_Operator_OPId` Foreign Key (`OperatorParamsId`) References `tbOperatorParams` (`Id`);
	End If;
	
	-- 添加App合作企业标记
	If fnColumnExists('tbTradingLog','AppCooperatorId') = 0 Then
		Alter Table `tbTradingLog` Add `AppCooperatorId` Int(10) unsigned Default Null Comment 'App合作企业Id',
		Add Key `fk_TradingLog_ACId` (`AppCooperatorId`);
	End If;
	
	-- 添加合作运营商是否有开放电站标记
	If fnColumnExists('tbOperatorParams','HasOpenStation') = 0 Then
		Alter Table `tbOperatorParams` Add `HasOpenStation` Bit(1) Default 0 Comment '是否有开放电站，0：没有，1：有';
	End If;
	
	-- 添加订单状态标记
	If fnColumnExists('tbCECOrderLog','ChargeStatus') = 0 Then
		Alter Table `tbCECOrderLog` Add `ChargeStatus` Int(10) Default Null Comment '订单状态，1：启动中，2：充电中，3：停止中，4：已结束，5：未知';
	End If;
	
	-- 添加订单支付状态标记
	If fnColumnExists('tbCECOrderLog','TradeStatus') = 0 Then
		Alter Table `tbCECOrderLog` Add `TradeStatus` Int(10) Not Null Default 2 Comment '支付状态，0：支付成功，1：支付失败，2：未支付，3：交易关闭';
	End If;
	
	-- 添加订单支付状态推送状态标记
	If fnColumnExists('tbCECOrderLog','PayResultPushStatus') = 0 Then
		Alter Table `tbCECOrderLog` Add `PayResultPushStatus` Int(10) Default Null Comment '支付结果推送状态，0：失败，1：成功';
	End If;
	
	-- 添加订单对账状态标记
	If fnColumnExists('tbCECOrderLog','CheckedStatus') = 0 Then
		Alter Table `tbCECOrderLog` Add `CheckedStatus` Int(10) Not Null Default 1 Comment '对账状态，0：对账通过，1：还未对账，2：对账未通过';
	End If;
	
	-- 添加订单对账失败原因记录
	If fnColumnExists('tbCECOrderLog','DisputeReason') = 0 Then
		Alter Table `tbCECOrderLog` Add `DisputeReason` Int(10) Default Null Comment '争议原因，1:交易不存在；2：交易金额错误；3：交易电量错误；4～99：自定义';
	End If;
	
	-- 添加订单对账失败争议数据记录
	If fnColumnExists('tbCECOrderLog','DisputeValue') = 0 Then
		Alter Table `tbCECOrderLog` Add `DisputeValue` Float(20) Default Null Comment '争议数值，具体争议原因匹配';
	End If;
	
	-- 添加合作企业结算方式
	If fnColumnExists('tbOperatorParams','IsOnlinePay') = 0 Then
		Alter Table `tbOperatorParams` Add `IsOnlinePay` Bit(1) Not Null Default 0 Comment '是否是线上支付，0：线下支付（统一结算），1：线上支付（跳转支付）';
	End If;
	
	-- 添加合作企业结算方式
	If fnColumnExists('tbOperatorParams','PayResultNotifyUrl') = 0 Then
		Alter Table `tbOperatorParams` Add `PayResultNotifyUrl` Varchar(1000) Default Null Comment '支付结果通知Url（选择线上支付才有）';
	End If;
End ;;

Delimiter ;

Call spUpdateDatabase();