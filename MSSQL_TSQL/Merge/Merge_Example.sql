/*
 * MERGE Example
 */

USE DevTest;

DECLARE @sourceData TABLE (
	 ID int IDENTITY
    ,[KEY] VARCHAR(64)
	,[VALUE] VARCHAR(64)
);

DECLARE @targetData TABLE (
	 ID int IDENTITY
    ,[KEY] VARCHAR(64)
	,[VALUE] VARCHAR(64)
);

INSERT INTO @targetData ([KEY], [VALUE]) VALUES ('Key1','Value1');
INSERT INTO @targetData ([KEY], [VALUE]) VALUES ('Key2','Value2');
INSERT INTO @targetData ([KEY], [VALUE]) VALUES ('Key3','Value3');

/* In Source 'Key2' is missing and 'KeyNew' was added. */
INSERT INTO @sourceData SELECT [KEY], [VALUE] FROM @targetData WHERE [KEY] <> 'Key2'; 
INSERT INTO @sourceData ([KEY], [VALUE]) VALUES ('KeyNew','ValueNew');

SELECT ID, [KEY], [VALUE] FROM @sourceData;
/*
ID	KEY	VALUE
========================
1	Key1	Value1
2	Key3	Value3
3	KeyNew	ValueNew
*/

SELECT ID, [KEY], [VALUE] FROM @targetData;
/*
ID	KEY	VALUE
========================
1	Key1	Value1
2	Key2	Value2
3	Key3	Value3
*/

MERGE @targetData AS TARGET
USING @sourceData AS SOURCE 
	ON (TARGET.[KEY] = SOURCE.[KEY]) 
	WHEN MATCHED AND TARGET.[KEY] <> SOURCE.[KEY] 
		THEN UPDATE SET TARGET.[VALUE] = SOURCE.[VALUE] 
	WHEN NOT MATCHED BY TARGET 
		THEN INSERT ([KEY], [VALUE]) VALUES (SOURCE.[KEY], SOURCE.[VALUE])
	WHEN NOT MATCHED BY SOURCE 
		THEN DELETE;

SELECT ID, [KEY], [VALUE] FROM @targetData;
/*
ID	KEY	VALUE
========================
1	Key1	Value1
3	Key3	Value3
4	KeyNew	ValueNew
*/
