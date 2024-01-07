# 数据库初始化
-- 创建库
create database if not exists my_db;

-- 切换库
use my_db;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_userAccount (userAccount)
    ) comment '用户' collate = utf8mb4_unicode_ci;


-- 代码生成器表
create table if not exists generator
(
    id          bigint auto_increment comment 'id' primary key,
    name        varchar(128)                       null comment '名称',
    description text                               null comment '描述',
    basePackage varchar(128)                       null comment '基础包',
    version     varchar(128)                       null comment '版本',
    author      varchar(128)                       null comment '作者',
    tags        varchar(1024)                      null comment '标签列表（json 数组）',
    picture     varchar(256)                       null comment '图片',
    fileConfig  text                               null comment '文件配置（json字符串）',
    modelConfig text                               null comment '模型配置（json字符串）',
    distPath    text                               null comment '代码生成器产物路径',
    status      int      default 0                 not null comment '状态',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
    ) comment '代码生成器' collate = utf8mb4_unicode_ci;

-- 模拟用户数据
INSERT INTO my_db.user (id, userAccount, userPassword, userName, userAvatar, userProfile, userRole) VALUES (1, 'yovvis', 'b0dd3697a192885d7c055db46155b26a', '程序员Yovvis', 'https://img.yovvis.top/avatar.png', '我有一头小毛驴我从来也不骑', 'admin');
INSERT INTO my_db.user (id, userAccount, userPassword, userName, userAvatar, userProfile, userRole) VALUES (2, 'yovvis2', 'b0dd3697a192885d7c055db46155b26a', '普通Yovvis', 'https://img.yovvis.top/avatar.png', '我有一头小毛驴我从来也不骑', 'user');

-- 模拟代码生成器数据
INSERT INTO my_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig, modelConfig, distPath, status, userId) VALUES (1, 'ACM 模板项目', 'ACM 模板项目生成器', 'com.yovvis', '1.0', '程序员Yovvis', '["Java"]', 'https://img.yovvis.top/avatar.png', '{}', '{}', null, 0, 1);
INSERT INTO my_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig, modelConfig, distPath, status, userId) VALUES (2, 'Spring Boot 初始化模板', 'Spring Boot 初始化模板项目生成器', 'com.yovvis', '1.0', '程序员Yovvis', '["Java"]', 'https://img.yovvis.top/avatar.png', '{}', '{}', null, 0, 1);
INSERT INTO my_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig, modelConfig, distPath, status, userId) VALUES (3, 'Yovvis外卖', 'Yovvis外卖项目生成器', 'com.yovvis', '1.0', '程序员Yovvis', '["Java", "前端"]', 'https://img.yovvis.top/avatar.png', '{}', '{}', null, 0, 1);
INSERT INTO my_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig, modelConfig, distPath, status, userId) VALUES (4, 'Yovvis用户中心', 'Yovvis用户中心项目生成器', 'com.yovvis', '1.0', '程序员Yovvis', '["Java", "前端"]', 'https://img.yovvis.top/avatar.png', '{}', '{}', null, 0, 1);
INSERT INTO my_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig, modelConfig, distPath, status, userId) VALUES (5, 'Yovvis商城', 'Yovvis商城项目生成器', 'com.yovvis', '1.0', '程序员Yovvis', '["Java", "前端"]', 'https://img.yovvis.top/avatar.png', '{}', '{}', null, 0, 1);
