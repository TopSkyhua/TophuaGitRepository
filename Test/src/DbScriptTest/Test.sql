Delimiter;
Drop Procedure If Exists `spUpdateDeviceAlarmDefine4Unify`;
Delimiter;;
Create Procedure `spUpdateDeviceAlarmDefine4Unify`(
	p_UnifyDeviceModelId Int
)Begin
	Declare v_AlarmDefineId Int;
	Declare v_AlarmCode Int;
	Declare v_Done Bit Default 0;
	Declare c_DeviceAlarmDefines Cursor For
			Select Id,AlarmCode From tbDeviceAlarmDefine Where DeviceModelId <> p_UnifyDeviceModelId;
	Declare Continue Handler For NOT Found Set v_Done = 1;
	
	Open c_DeviceAlarmDefines;
	Loop1:Loop
		Fetch c_DeviceAlarmDefines Into v_AlarmDefineId,v_AlarmCode;
		If v_Done = 1 Then
			leave Loop1; 
		End If;
		Delete From tbDeviceAlarmDefineTitle Where DeviceAlarmDefineId = V_AlarmDefineId;
		
		Update tbDeviceAlarmEvent Set DeviceAlarmDefineId = (Select Id From tbDeviceAlarmDefine 
		       Where AlarmCode = v_AlarmCode And DeviceModelId = p_UnifyDeviceModelId) 
		       Where DeviceAlarmDefineId = v_AlarmDefineId;
		       
		Update tbDeviceAlarmEventForm Set DeviceAlarmDefineId = (Select Id From tbDeviceAlarmDefine 
		       Where AlarmCode = v_AlarmCode And DeviceModelId = p_UnifyDeviceModelId) 
		       Where DeviceAlarmDefineId = v_AlarmDefineId;
	End Loop;
	Close c_DeviceAlarmDefines;	
End;;
Delimiter;

Delimiter;
Drop Procedure If Exists `spAddDefaultDeviceAlarmDefineTitle`;
Delimiter;;
Create Procedure `spAddDefaultDeviceAlarmDefineTitle`(
	p_DeviceModelId Int
)Begin
	Declare v_DeviceAlarmDefineId Int;
	Declare v_AlarmContent Varchar(1000);
	Declare v_Done Bit Default 0;
	Declare c_DeviceAlarmDefines Cursor For
			Select Id,AlarmContent from tbDeviceAlarmDefine Where DeviceModelId = p_DeviceModelId;
	Declare Continue Handler For NOT Found Set v_Done = 1;
	
	Open c_DeviceAlarmDefines;
	Loop1:loop
	Fetch c_DeviceAlarmDefines Into v_DeviceAlarmDefineId,v_AlarmContent;
	If v_Done = 1 Then
		leave Loop1; 
	End If;
	
	If Not Exists (Select * From tbDeviceAlarmDefineTitle Where DeviceAlarmDefineId = v_DeviceAlarmDefineId And LanguageId = 1) Then
		Insert Into tbDeviceAlarmDefineTitle(DeviceAlarmDefineId,LanguageId,Title) Values(v_DeviceAlarmDefineId,1,v_AlarmContent);
	Else
		Update tbDeviceAlarmDefineTitle Set Title = v_AlarmContent Where DeviceAlarmDefineId = v_DeviceAlarmDefineId And LanguageId = 1;
	End If;
	
	End Loop;
	
	Close c_DeviceAlarmDefines;
End;;
Delimiter;

Delimiter;
Drop Procedure If Exists `spAddChineseTitle`;
Delimiter;;
CREATE PROCEDURE `spAddChineseTitle`(p_alarmCode INT,
	p_fName VARCHAR(50)
)BEGIN
	DECLARE v_DeviceAlarmDefineId INT;
	SELECT Id INTO v_DeviceAlarmDefineId FROM tbDeviceAlarmDefine WHERE AlarmCode = p_alarmCode;
	IF NOT EXISTS(SELECT * FROM tbDeviceAlarmDefineTitle WHERE DeviceAlarmDefineId = v_DeviceAlarmDefineId AND LanguageId = 4)THEN
		INSERT INTO tbDeviceAlarmDefineTitle(DeviceAlarmDefineId,LanguageId,Title) VALUES(v_DeviceAlarmDefineId,4,p_fName);
	ELSE
		UPDATE tbDeviceAlarmDefineTitle SET Title = p_fName WHERE DeviceAlarmDefineId = v_DeviceAlarmDefineId AND LanguageId = 4;
	END IF;

END;;

/*删除重复的*/
Delimiter;
Drop Procedure If Exists `spDeleteRepetitionLanguageKeyword`;
Delimiter;;
Create Procedure `spDeleteRepetitionLanguageKeyword`(
) BEGIN
	Declare v_Keyword varchar(50);
	Declare v_num int;
	Declare v_Done Bit Default 0;
	Declare c_RepetitionKeyword Cursor For
		SELECT Keyword,COUNT(*) AS count FROM tbLanguageKeyword GROUP BY Keyword HAVING count > 1;
	Declare Continue Handler For Not Found Set v_Done = 1;
	
	Open c_RepetitionKeyword;
	Loop1:LOOP
		Fetch c_RepetitionKeyword into v_Keyword,v_num;
		If v_Done = 1 Then
			Leave Loop1;
		End If;
		
		If v_num >= 2 Then
			Begin
				Declare v_id int;
				Declare v_Stop Bit Default 0;
				Declare v_Count Bit Default 1;
				Declare c_Keyword Cursor For select Id from tbLanguageKeyword where Keyword = v_Keyword;
				Declare Continue Handler For Not Found Set v_Stop = 1;
				
				Open c_Keyword;
				Loop2:loop
					Fetch c_Keyword into v_id;
					If v_Stop = 1 then
						Leave Loop2;
					End If;
					
					if v_Count > 1 Then
						Delete from tbLanguageKeyword Where Id = v_id;
					End if;
					Set v_Count = v_Count + 1;
				End loop;
				Close c_Keyword;
			End;
		End If;
	END LOOP;
	Close c_RepetitionKeyword;
END;;


/*删除重复的*/
Delimiter;
Drop Procedure If Exists `spDeleteRepetitionLanguageKeyword`;
Delimiter;;
Create Procedure `spDeleteRepetitionLanguageKeyword`(
) BEGIN
	Declare v_Keyword varchar(1000);
	Declare v_num int;
	Declare v_Id int;
	Declare v_Done Bit Default 0;
	Declare c_RepetitionKeyword Cursor For
		SELECT Keyword,COUNT(*) AS count FROM tbLanguageKeyword GROUP BY Keyword HAVING count > 1;
	Declare Continue Handler For Not Found Set v_Done = 1;
	
	Open c_RepetitionKeyword;
	Loop1:loop
		Fetch c_RepetitionKeyword into v_Keyword,v_num;
		If v_Done = 1 Then
			Leave Loop1;
		End If;
		
		If v_num >= 2 Then
			Select Id into v_Id from tbLanguageKeyword where Keyword = v_Keyword limit 1;
			Delete from tbLanguageKeyword where Id <> v_Id And Keyword = v_Keyword;
		End If;
	End loop;
	Close c_RepetitionKeyword;
END;;

call `spDeleteRepetitionLanguageKeyword()`;