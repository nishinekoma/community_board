CREATE TABLE IF NOT EXISTS USER_COMMENT ( -- CREATE TABLE テーブルの作成　IF NOT EXISTS USER_COMMENT 　もしテーブルが存在していなければ作成するという意味
	ID NUMBER(4) AUTO_INCREMENT, -- 最大桁数は4桁　9999まで主キーとしてふさわしくないものでIDを採番（番号を採用すること）　AUTO_INCREMENT 自動採番　１ずつIDをすすめていくという指定。
	USER_ID VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(20),-- NAMEという列を作成　最大２０文字の文字列を保持できる。
	MAILADDRESS VARCHAR2(100),
	TEXT VARCHAR2(400) NOT NULL, -- NULLを許容しない
	CREATED_AT DATETIME DEFAULT NOW() NOT NULL, -- 日時型のCREATED_AT列を作成する
	CONSTRAINT ID_PKC PRIMARY KEY(ID) -- IDをプライマリキーとする（プライマリキーの名前をID_PKCとする）　IDカラムに主キー制約を与えています。
);
-- DATETIMEは日付と時刻を保持する型。
-- DEFAULT NOW()は新しい行追加されたとき現在の日時が自動的に更新・挿入
-- CONSTAINT 制約でありIDを一意の主キーとする。重複不可。
