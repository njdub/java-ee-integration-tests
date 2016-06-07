# CREATE TABLE roles (
#   id    INT          NOT NULL,
#   title VARCHAR(100) NOT NULL,
#
#   PRIMARY KEY AUTO_INCREMENT (id)
#
# );
#
# CREATE TABLE groups (
#   id    INT          NOT NULL,
#   title VARCHAR(100) NOT NULL,
#
#   PRIMARY KEY AUTO_INCREMENT (id)
# );
#
#
# CREATE TABLE users (
#   id       INT          NOT NULL,
#   username VARCHAR(100) NOT NULL,
#   password VARCHAR(100) NOT NULL,
#   group_id INT          NOT NULL,
#   role_id  INT          NOT NULL,
#
#   PRIMARY KEY AUTO_INCREMENT (id),
#   FOREIGN KEY (group_id) REFERENCES groups (id),
#   FOREIGN KEY (role_id) REFERENCES roles (id)
# );
#
# # SELECT r.title, 'Roles' FROM users u JOIN roles r ON  u.role_id = r.id WHERE u.username='ivan'
#
# INSERT INTO roles (id, title) VALUES (1, 'ADMIN');
#
# INSERT INTO groups (id, title) VALUES (1, 'ADMIN');
#
# INSERT INTO users (id, username, password, group_id, role_id) VALUES (1, 'admin', 'admin', 1, 1);

CREATE TABLE accounts (
  id       INT          NOT NULL,
  login    VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role     VARCHAR(100) NOT NULL,

  PRIMARY KEY (id)
);


INSERT INTO accounts VALUES (1, 'manager', 'manager', 'MANAGER');
INSERT INTO accounts VALUES (2, 'admin', 'admin', 'ADMIN');
INSERT INTO accounts VALUES (3, 'user', 'user', 'USER');

