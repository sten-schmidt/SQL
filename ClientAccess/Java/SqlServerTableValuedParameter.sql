USE [DevTest]
GO

/* Setup */

CREATE TABLE [dbo].[TableB](
	[ID] [int] NOT NULL,
	[DirPath] [varchar](1024) NOT NULL,
	[FileCount] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TYPE [dbo].[TabTypeB] AS TABLE(
	[ID] [int] NOT NULL,
	[DirPath] [varchar](1024) NOT NULL,
	[FileCount] [int] NOT NULL,
	PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (IGNORE_DUP_KEY = OFF)
)
GO

CREATE PROCEDURE [dbo].[spInsertToTableB]
	@tab TabTypeB READONLY
AS
BEGIN
	SET NOCOUNT ON;

	DECLARE @result INT = 0;

	INSERT INTO dbo.TableB
	SELECT t.ID, t.DirPath, t.FileCount FROM @tab t

	SELECT @result = COUNT(*) FROM dbo.TableB;

	RETURN @result;

END

GO


/* Cleanup */

/*
	DROP PROCEDURE [dbo].[spInsertToTableB]
	GO

	DROP TYPE [dbo].[TabTypeB]
	GO

	DROP TABLE [dbo].[TableB]
	GO

*/