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

-- 异常结束充电发送短信标志
Delimiter ;
Drop Procedure If Exists `spUpdateDatabase`;
Delimiter ;;
Create Procedure `spUpdateDatabase`(
) Begin
	-- 异常结束充电发送短信id
	If fnColumnExists('tbAgentAccountInfo','UcpassExceptionId') = 0 Then
		Alter Table `tbAgentAccountInfo` Add `UcpassExceptionId` Varchar(11) Default Null Comment '异常结束充电短信模板Id';
	End If;
	
	-- 桩是否启用短信通知配置表
	If fnTableExists('tbExceptionMsgCfg') = 0 Then
		Create Table `tbExceptionMsgCfg` (
			`Id` Int(10) Unsigned Not Null Auto_Increment Comment '主键',
			`ChargePointId` Int(10) Unsigned Default Null Comment '充电点Id',
			`ObjectUnitId` Int(10) Unsigned Default Null Comment '离散充电桩Id',
			`IsValid` Bit(1) Not Null Default 1 Comment '是否有效，0：无效，1：有效',
			Primary Key(`Id`)
		) Comment '异常结束充电短信通知配置表';
	End If;

End ;;

Delimiter ;

Call spUpdateDatabase();

-- 易电桩短信ID：262899

