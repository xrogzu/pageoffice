DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id       VARCHAR(32)  NOT NULL PRIMARY KEY
  COMMENT 'UUID 唯一主键',
  username VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '用户名，登陆唯一凭证',
  nickname VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '用户昵称，用于页面显示',
  password VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '用户密码'
);

DROP TABLE IF EXISTS documents;
CREATE TABLE documents (
  id          VARCHAR(32)  NOT NULL PRIMARY KEY
  COMMENT 'UUID 唯一主键',
  doc_name    VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '文档展示名称',
  doc_name_en VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '为避免中文乱码，将doc_name修改为机器可读的',
  doc_path    VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '相对于 webapp 的路径'
);

DROP TABLE IF EXISTS user_doc;
CREATE TABLE user_doc (
  user_id  VARCHAR(32) NOT NULL DEFAULT ''
  COMMENT '用户ID',
  doc_id   VARCHAR(32) NOT NULL DEFAULT ''
  COMMENT '文档ID',
  editable TINYINT(1)  NOT NULL DEFAULT 0
  COMMENT '用户对该文档是否拥有可编辑的权限，0代表不可编辑，1代表可编辑'
);

DROP TABLE IF EXISTS doc_history;
CREATE TABLE doc_history (
  doc_id        VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '文档ID',
  modifier      VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '文档修改者的ID',
  modifier_name VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '文档修改者的名称',
  modified_time DATETIME     NOT NULL DEFAULT now()
  COMMENT '文档修改时间',
  bak_path      VARCHAR(500) NOT NULL DEFAULT ''
  COMMENT '备份文档的绝对路径'
);


INSERT INTO users VALUES ('a2d8c63e79d84459a648e2c370c0db1e', 'JunbinZhong', '钟俊滨', '!@#123');
INSERT INTO users VALUES ('a3c5b1fc2c2c4fa3ba6e17051ec0f148', 'admin', '狗管理', '123456');
INSERT INTO users VALUES ('53f7ee732b3b419eba0e29291866f1d7', 'Rowling', '罗琳', '123456');
INSERT INTO users VALUES ('65b2b3cad9e54691b2776d48f2775af5', 'Jilin', '吉林', '123456');
INSERT INTO users VALUES ('c8b23feaa5284e67a5d4d2404e484324', 'Jerkin', 'jerkin', '123456');

INSERT INTO documents
VALUES ('90e17c46870e43fea7b872bb9ec66620', '数据源.xlsx', '539a4e41ca334e0b81249f2d5df42c8e.xlsx',
        '/office/539a4e41ca334e0b81249f2d5df42c8e.xlsx');
INSERT INTO documents
VALUES ('b63e1939995543cc93139e5c8bcf92f1', '参考模板.xlsx', 'd707d6a17c064e9aa6d13f13c42cca7b.xlsx',
        '/office/d707d6a17c064e9aa6d13f13c42cca7b.xlsx');
INSERT INTO documents
VALUES ('c7a66521bc644dc4b15b68293f142db5', 'Excel文档关联.xlsx', 'b1d768068d614ee3890dcf457d575981.xlsx',
        '/office/b1d768068d614ee3890dcf457d575981.xlsx');

INSERT INTO user_doc VALUES ('a2d8c63e79d84459a648e2c370c0db1e', '90e17c46870e43fea7b872bb9ec66620', FALSE);
INSERT INTO user_doc VALUES ('a3c5b1fc2c2c4fa3ba6e17051ec0f148', 'b63e1939995543cc93139e5c8bcf92f1', TRUE);
INSERT INTO user_doc VALUES ('53f7ee732b3b419eba0e29291866f1d7', 'b63e1939995543cc93139e5c8bcf92f1', TRUE);
INSERT INTO user_doc VALUES ('65b2b3cad9e54691b2776d48f2775af5', 'b63e1939995543cc93139e5c8bcf92f1', FALSE);
INSERT INTO user_doc VALUES ('c8b23feaa5284e67a5d4d2404e484324', 'c7a66521bc644dc4b15b68293f142db5', TRUE);

SELECT *
FROM users;

SELECT *
FROM users
  LEFT JOIN user_doc ON (users.id = user_doc.user_id)
  LEFT JOIN documents ON (user_doc.doc_id = documents.id);
