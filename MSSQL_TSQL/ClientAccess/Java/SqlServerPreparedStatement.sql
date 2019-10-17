USE [DevTest]
GO

DROP TABLE IF EXISTS dbo.TableC;
GO
CREATE TABLE [dbo].[TableC](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[DirPath] [varchar](1024) NULL,
	[FileCount] [int] NULL,
	[ChangeDate] [datetime] NULL,
 CONSTRAINT [PK__TableC__3214EC2772A462E2] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

CREATE OR ALTER PROCEDURE [dbo].[spInsertToTableC]
	@DirPath nvarchar(1024) = null,
	@FileCount int = null,
	@ChangeDate datetime = null
AS
BEGIN
	SET NOCOUNT ON;

	DECLARE @result INT = 0;

	INSERT INTO dbo.TableC (DirPath, FileCount, ChangeDate)
		VALUES (@DirPath, @FileCount, @ChangeDate);

	SELECT @result = COUNT(*) FROM dbo.TableC;

	SELECT @result AS Result, @DirPath AS DirPath, @FileCount AS FileCount, @ChangeDate AS ChangeDate
	
END

GO

/*
	select *  from [dbo].[TableC]
*/
