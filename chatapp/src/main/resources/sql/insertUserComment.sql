INSERT INTO USER_COMMENT(NAME, USER_ID,MAILADDRESS, TEXT) VALUES (/* INSERTはSQLにおいてデータの保存を担当する。*/
/*[# mb:p="dto.name"]*/ 'name' /*[/]*/,
/*[# mb:p="dto.userId"]*/ 'user' /*[/]*/,
/*[# mb:p="dto.mailAddress"]*/ 'mailaddress' /*[/]*/,
/*[# mb:p="dto.comment"]*/ 'text' /*[/]*/
);
-- 構文はDB毎に微妙に異なり、今回はH2の構文に従う。　https://www.tutorialspoint.com/h2_database/h2_database_insert.html
-- 初期値を挿入。