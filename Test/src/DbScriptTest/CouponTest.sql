Drop Table If Exists `tbCoupon`;
Crecate Table `tbCoupon`(
	`Id` Int Unsigned Auto_Increment Not Null Comment '主键',
	`Name` Varchar(50) Not Null Comment '优惠券名称',
	`CreateDateTime` DateTime Default Null Comment '创建时间', 
	`FaceValue` Int Ubsigned Not Null Comment '面值',
	`TotalQuantity` Int Ubsigned Not Null Comment '数量',
	`PerferentialId` Int Ubsigned Not Null Comment '优惠活动Id',
	`ActionStartTime` DateTime Not Null Comment '活动开始时间',
	`ActionEndTime` DateTime Not Null Comment '活动结束时间',
	`ActionScope` Varchar(200) Not Null Comment '活动范围',
	`LimitQuantity` Int Unsigned  Comment '限制条件，充值送就为金额，积分兑券就为积分',
	`UseLimit` Int Unsigned  Comment '使用限制，满足消费金额使用',
	`UsedQuantity` Int Ubsigned Not Null Comment '已使用数量',
	`State` Bit(1) Default 0 Not Null Comment '状态：0-未使用，1-使用',
	Primary Key(`Id`),
	Key `AppPerferential-PerferentialId` ("PerferentialId"),
	Constraint `AppPerferential-PerferentialId` Foreign Key("PerferentialId") References `tbPerferential`(`Id`),
	Constraint `AppPerferential-StartTime` Foreign Key("ActionStartTime") References `tbPerferential`(`StartTime`),
	Constraint `AppPerferential-EndTime` Foreign Key("ActionEndTime") References `tbPerferential`(`EndTime`),
	Constraint `AppPerferential-Scope` Foreign Key("ActionScope") References `tbPerferential`(`Scope`),
)Comment '优惠券表';

Drop Table If Exists `tbGetCouponLog`
Create Table `tbGetCouponLog`(
	`Id` Int Unsigned Auto_Increment Not Null Comment `主键`,
	``
)