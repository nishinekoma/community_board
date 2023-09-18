INSERT INTO USER_COMMENT(NAME, MAILADDRESS, TEXT) VALUES
('TUIS１', 'example1@example.com', '分かりみが深すぎる'),
('TUIS２', 'example2@example.com', 'いやだお＾＾');
INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) VALUES
('admin','{bcrypt}$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG',true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_USER');
-- !!!このユーザにはID: admin, PW: adminでログインすることができます!!!