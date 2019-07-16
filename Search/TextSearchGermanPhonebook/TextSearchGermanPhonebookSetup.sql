USE [DevTest]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TextSearchGermanPhonebook](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Text] [varchar](255) NULL,
 CONSTRAINT [PK_TextSearchGermanPhonebook] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Muller');
INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Müller');
INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Mueller');
INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Armelkanal');
INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Ärmelkanal');
INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Aermelkanal');
INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Rep Österreich');
INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Rep Osterreich');
INSERT INTO [dbo].[TextSearchGermanPhonebook] ([Text]) VALUES ('Rep Oesterreich');
