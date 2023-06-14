DROP DATABASE myBlog;
create database myBlog default char set utf8;
use myBlog;
DROP TABLE `user`;
CREATE TABLE `user`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `first_name`   VARCHAR(255)    NOT NULL,
    `last_name`    VARCHAR(255)    NOT NULL,
    `avatar`       VARCHAR(255)    NOT NULL,
    `login`        VARCHAR(255)    NOT NULL    unique,
    `password`     VARCHAR(255)    NOT NULL    unique,
    `role_id`      INT             NOT NULL
);

CREATE TABLE `role`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `mean`         VARCHAR(255)    NOT NULL    unique
);

alter table user
    add constraint user_role_fk
    foreign key (role_id) references role (id);

CREATE TABLE `post`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `title`        VARCHAR(255)    NOT NULL,
    `published`    VARCHAR(255)    NOT NULL,
    `author_id`    INT             NOT NULL,
    `image_path`   VARCHAR(255)    NOT NULL,
    `content`      TEXT            NOT NULL,
    `draft_id`     INT             NOT NULL
);

CREATE TABLE `draft`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `mean`         BOOLEAN         NOT NULL    unique
);

alter table post
    add constraint post_draft_fk
        foreign key (draft_id) references draft (id);

alter table post
    add constraint post_user_fk
        foreign key (author_id) references user (id);


DROP TABLE `post`;
DROP TABLE `draft`;

CREATE TABLE `post`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `title`        VARCHAR(255)    NOT NULL,
    `published`    DATETIME        NOT NULL,
    `user_id`      INT             NOT NULL,
    `image_path`   VARCHAR(255)    NOT NULL,
    `content`      TEXT            NOT NULL,
    `draft_id`     INT             NOT NULL
);

alter table post
    add constraint post_user_fk
        foreign key (user_id) references user (id);