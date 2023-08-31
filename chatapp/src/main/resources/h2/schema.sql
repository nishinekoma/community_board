CREATE TABLE IF NOT EXISTS USER_COMMENT ( -- CREATE TABLE テーブルの作成　IF NOT EXISTS USER_COMMENT 　もしテーブルが存在していなければ作成するという意味
	ID NUMBER(10) AUTO_INCREMENT, -- 主キーとしてふさわしくないものでIDを採番（番号を採用すること）　AUTO_INCREMENT 自動採番　１ずつIDをすすめていくという指定。
	NAME VARCHAR2(20),-- NAMEという列を作成　最大２０文字の文字列を保持できる。
	MAILADDRESS VARCHAR2(100),
	TEXT VARCHAR2(400) NOT NULL, -- NULLを許容しない
	CONSTRAINT ID_PKC PRIMARY KEY(ID) -- IDをプライマリキーとする（プライマリキーの名前をID_PKCとする）　IDカラムに主キー制約を与えています。
);