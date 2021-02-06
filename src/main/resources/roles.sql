INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(id_role, id_team, password, status, user_name) VALUES (1, 1, '$2a$10$lJoHkrohURewJ4qqPLU7lO0uUkxUoM1G4PTRwRCAUmuP3jUPvplmm"', true, 'user111');
INSERT INTO teams(id_leader, name) VALUES (1, 'TEAM A');
