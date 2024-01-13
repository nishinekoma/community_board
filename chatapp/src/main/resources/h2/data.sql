INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) VALUES
('admin','{bcrypt}$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG',true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_USER');
-- board内のもの
INSERT INTO USER_COMMENT(NAME,USER_ID, MAILADDRESS, TEXT) VALUES
('User１', 'admin','example1@example.com', 'kome1'),
('User２', 'admin','example1example.com', 'kome2');
-- !!!このユーザにはID: admin, PW: adminでログインすることができます!!!
--adminユーザでログイン後 localhost://localhost:8080/h2-console  に行くと　このdata.sql基準になっている。
INSERT INTO RELATION(USER_ID, MAILADDRESS) VALUES
('admin','admin@example.com');

