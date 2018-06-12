
Delimiter;
Drop Procedure If Exists `spExistsDeviceModel`;
Create Procedure `spExistsDeviceModel`(){
	p_ModelCode Int
}Proc_Start:

Select * from tbDeviceModel Where ModelCodel = p_ModelCode;
End;;


Delimiter;
Drop Procedure If Exists `spAddAlarmCategory`;
Delimiter;;
Create Procedure `spAddAlarmCategory`(
	p_ModelCode Int,
	
	p_AlarmCategory Varchar(50)
)Proc_Start:Begin
	Declare v_DeviceModelId Int;

	Select Id Into v_DeviceModelId From tbDeviceModel Where ModelCode = p_ModelCode;
	If (v_DeviceModelId Is Not Null)Then
		If Not Exists(Select * From tbAlarmCategory Where DeviceModelId = v_DeviceModelId And CategoryName=p_AlarmCategory)Then
			Insert Into tbAlarmCategory(AlarmSourceEnum,CategoryName,DeviceModelId) 
				Values(11,p_AlarmCategory,v_DeviceModelId);
		Else
			Update tbAlarmCategory Set CategoryName = p_AlarmCategory;
		End If;
	Else
		Select 'Please Input Right ModelCode';
	End If;
End;;

Delimiter;
Drop Procedure If Exists `spAddAlarm`;
Delimiter;;
Create Procedure `spAddAlarm`(
	p_ModelCode Int,
	p_AlarmCategory Varchar(50),
	p_AlarmLevel Int,
	p_AlarmCode Int,
	p_AlarmContent Varchar(100)
)Proc_Start:Begin
	Declare v_DeviceModelId Int;
	Declare v_AlarmCategoryId Int;

	Select Id Into v_DeviceModelId From tbDeviceModel Where ModelCode = p_ModelCode;

	If(v_DeviceModelId Is Not Null)Then
		Select Id Into v_AlarmCategoryId From tbAlarmCategory Where CategoryName = p_AlarmCategory And DeviceModelId = v_DeviceModelId Order By `Id` Desc Limit 0,1;
		If Not Exists(Select * From tbDeviceAlarmDefine Where AlarmCode=p_AlarmCode And DeviceModelId = v_DeviceModelId)then
			Insert Into tbDeviceAlarmDefine(AlarmCategoryId,AlarmCode,AlarmContent,AlarmSourceEnum,DeviceTypeEnum,AlarmLevelEnum,DeviceModelId)
				Values(v_AlarmCategoryId,p_AlarmCode,p_AlarmContent,11,1,p_AlarmLevel,v_DeviceModelId);
		Else
			Update tbDeviceAlarmDefine Set  AlarmCategoryId = v_AlarmCategoryId,
							AlarmContent = p_AlarmContent,
							AlarmLevelEnum = p_AlarmLevel;
		End If;
	Else	
		Select 'Please Into Right Model Code';
	End If;

End;;