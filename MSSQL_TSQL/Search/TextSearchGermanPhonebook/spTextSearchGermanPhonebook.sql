USE [DevTest]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
	exec spTextSearchGermanPhonebook
*/
ALTER PROCEDURE [dbo].[spTextSearchGermanPhonebook]
AS
BEGIN
	SET NOCOUNT ON;

    	--List all supported SQL Server Collations
	/*
		select name, COLLATIONPROPERTY(name, 'CodePage') as Code_Page, description
		from sys.fn_HelpCollations() where name like '%german_phonebook%'
	*/

	select * from dbo.TextSearchGermanPhonebook where Text like '%Müller%'						--> Müller
	select * from dbo.TextSearchGermanPhonebook where Text like '%Müller%' collate German_PhoneBook_CI_AI		--> Müller, Mueller
	select * from dbo.TextSearchGermanPhonebook where Text like '%Mueller%' collate German_PhoneBook_CI_AI		--> Müller, Mueller
	
	select * from dbo.TextSearchGermanPhonebook where Text like '%Muller%' collate German_PhoneBook_CI_AI		--> Muller
	select * from dbo.TextSearchGermanPhonebook where Text like '%M[uü]ller%' collate German_PhoneBook_CI_AI	--> Müller, Muller
	select * from dbo.TextSearchGermanPhonebook where Text like '%M[uü]ller%' 					--> Müller, Muller

	select * from dbo.TextSearchGermanPhonebook where Text like '%M_ller%' collate German_PhoneBook_CI_AI		--> Müller, Muller
	select * from dbo.TextSearchGermanPhonebook where Text like '%M_ller%' 						--> Müller, Muller

	select * from dbo.TextSearch where Text like '%M%ller%'								--> Muller, Müller, Mueller

	
END
