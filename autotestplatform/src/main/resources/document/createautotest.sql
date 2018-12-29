/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/2 16:46:57                            */
/*==============================================================*/


DROP TABLE IF EXISTS sys_log;

DROP TABLE IF EXISTS rule;

DROP TABLE IF EXISTS EXAMPLE;

DROP TABLE IF EXISTS plan_result;

DROP TABLE IF EXISTS model;

DROP TABLE IF EXISTS message;

DROP TABLE IF EXISTS use_case;

DROP TABLE IF EXISTS use_case_plan_relation;

DROP TABLE IF EXISTS USER;

DROP TABLE IF EXISTS script;

DROP TABLE IF EXISTS menu;

DROP TABLE IF EXISTS plan;

DROP TABLE IF EXISTS identity_rule_relation;

DROP TABLE IF EXISTS identity;

DROP TABLE IF EXISTS config_content;

DROP TABLE IF EXISTS config;

DROP TABLE IF EXISTS project;

/*==============================================================*/
/* Table: 日志表Log                                                */
/*==============================================================*/
CREATE TABLE sys_log
(
   sys_log_id           INT NOT NULL auto_increment,
   use_case_id          INT NOT NULL,
   plan_id              INT NOT NULL,
   plan_result_id       INT NOT NULL,
   log_name             VARCHAR(8000),
   log_url              VARCHAR(200),
   log_type             INT,
   log_status           INT,
   create_time          DATETIME,
   update_time          DATETIME,
   PRIMARY KEY (sys_log_id)
);

/*==============================================================*/
/* Table: 权限表Rule                                               */
/*==============================================================*/
CREATE TABLE rule
(
   rule_id               INT NOT NULL auto_increment,
   rule_name             VARCHAR(50),
   rule_type             INT,
   foreign_id            INT NOT NULL,
   rule_status           INT,
   create_time           DATETIME,
   update_time           DATETIME,
   create_user_id         INT,
   update_user_id         INT,
   PRIMARY KEY (rule_id)
);

/*==============================================================*/
/* Table: 案例表example                                            */
/*==============================================================*/
CREATE TABLE EXAMPLE
(
   example_id           INT NOT NULL auto_increment,
   use_case_id          INT NOT NULL,
   example_name         VARCHAR(100),
   example_url          VARCHAR(200),
   example_status       INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id       INT,
   update_user_id       INT,
   PRIMARY KEY (example_id)
);

/*==============================================================*/
/* Table: 执行结果表planResult                                         */
/*==============================================================*/
CREATE TABLE plan_result
(
   plan_result_id           INT NOT NULL auto_increment,
   plan_id          INT NOT NULL,
   plan_result_name         VARCHAR(50),
   plan_result_log_url      VARCHAR(200),
   sum_num                  INT,
   success_num              INT,
   warn_flag                VARCHAR(200),
   plan_result_status       INT,
   create_time              DATETIME,
   PRIMARY KEY (plan_result_id)
);

/*==============================================================*/
/* Table: 模块表model                                              */
/*==============================================================*/
CREATE TABLE model
(
   model_id             INT NOT NULL auto_increment,
   project_id           INT NOT NULL,
   model_name           VARCHAR(50),
   model_url            VARCHAR(200),
   model_status         INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id       INT,
   update_user_id       INT,
   PRIMARY KEY (model_id)
);

/*==============================================================*/
/* Table: 消息表Message                                            */
/*==============================================================*/
CREATE TABLE message
(
   message_id           INT NOT NULL auto_increment,
   message_name         VARCHAR(50),
   message_content      VARCHAR(200),
   message_type         INT,
   foreign_id           INT NOT NULL,
   message_status       INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id       INT,
   update_user_id       INT,
   PRIMARY KEY (message_id)
);

/*==============================================================*/
/* Table: 用例表useCase                                            */
/*==============================================================*/
CREATE TABLE use_case
(
   use_case_id          INT NOT NULL auto_increment,
   project_id           INT NOT NULL,
   model_id             INT NOT NULL,
   config_id            INT,
   use_case_name        VARCHAR(50),
   use_case_script_url  VARCHAR(200),
   use_case_example_url VARCHAR(200),
   use_case_type        INT,
   use_case_status      INT,
   use_case_content     VARCHAR(500),
   use_case_num         INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id       INT,
   update_user_id       INT,
   PRIMARY KEY (use_case_id)
);

/*==============================================================*/
/* Table: 用例计划关系表UseCasePlanRelation                            */
/*==============================================================*/
CREATE TABLE use_case_plan_relation
(
   use_case_plan_relation_id INT NOT NULL auto_increment,
   use_case_id          INT NOT NULL,
   plan_id              INT NOT NULL,
   plan_status          INT,
   PRIMARY KEY (use_case_plan_relation_id)
);

/*==============================================================*/
/* Table: 用户表User                                               */
/*==============================================================*/
CREATE TABLE USER
(
   user_id              INT NOT NULL auto_increment,
   nick_name            VARCHAR(50),
   user_name            VARCHAR(50) NOT NULL,
   PASSWORD             VARCHAR(50) NOT NULL,
   salt VARCHAR(50),
   user_identity_id     INT,
   user_status          INT,
   create_time          DATETIME,
   update_time          DATETIME,
   PRIMARY KEY (user_id)
);

/*==============================================================*/
/* Table: 脚本表script                                             */
/*==============================================================*/
CREATE TABLE script
(
   script_id            INT NOT NULL auto_increment,
   use_case_id          INT NOT NULL,
   script_name          VARCHAR(100),
   script_status        INT,
   script_url           VARCHAR(200),
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id       INT,
   update_user_id       INT,
   PRIMARY KEY (script_id)
);

/*==============================================================*/
/* Table: 菜单表Menu                                               */
/*==============================================================*/
CREATE TABLE menu
(
   menu_id               INT NOT NULL auto_increment,
   menu_name             VARCHAR(100),
   menu_level            INT,
   menu_status           INT,
   create_time           DATETIME,
   update_time           DATETIME,
   create_user_id         INT,
   update_user_id         INT,
   PRIMARY KEY (menu_id)
);

/*==============================================================*/
/* Table: 计划表Plan                                               */
/*==============================================================*/
CREATE TABLE plan
(
   plan_id              INT NOT NULL auto_increment,
   plan_name            VARCHAR(100),
   plan_intro           VARCHAR(200),
   plan_type            INT,
   plan_status          INT,
   plan_start_time      DATETIME,
   plan_sum_times       DATETIME,
   plan_interval        INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id       INT,
   update_user_id       INT,
   PRIMARY KEY (plan_id)
);

/*==============================================================*/
/* Table: 身份权限关系表IdentityRuleRelation                           */
/*==============================================================*/
CREATE TABLE identity_rule_relation
(
   identity_rule_relation_id INT NOT NULL auto_increment,
   rule_id              INT NOT NULL,
   identity_id          INT NOT NULL,
   rule_status          INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id       INT,
   update_user_id       INT,
   PRIMARY KEY (identity_rule_relation_id)
);

/*==============================================================*/
/* Table: 身份表Identity                                           */
/*==============================================================*/
CREATE TABLE identity
(
   identity_id          INT NOT NULL auto_increment,
   identity_name        VARCHAR(50),
   identity_status      INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id       INT,
   update_user_id       INT,
   PRIMARY KEY (identity_id)
);

/*==============================================================*/
/* Table: 配置内容表configContent                                    */
/*==============================================================*/
CREATE TABLE config_content
(
   config_content_id    INT NOT NULL auto_increment,
   config_id            INT NOT NULL,
   config_name          VARCHAR(50),
   config_type          VARCHAR(50),
   config_content       VARCHAR(200),
   content_status       INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id        INT,
   update_user_id        INT,
   PRIMARY KEY (config_content_id)
);

/*==============================================================*/
/* Table: 配置表config                                             */
/*==============================================================*/
CREATE TABLE config
(
   config_id            INT NOT NULL auto_increment,
   config_name          VARCHAR(50),
   config_status        INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id        INT,
   update_user_id        INT,
   PRIMARY KEY (config_id)
);

/*==============================================================*/
/* Table: 项目表project                                            */
/*==============================================================*/
CREATE TABLE project
(
   project_id           INT NOT NULL auto_increment,
   project_name         VARCHAR(50),
   project_url          VARCHAR(200),
   project_mode         INT,
   create_time          DATETIME,
   update_time          DATETIME,
   create_user_id        INT,
   update_user_id        INT,
   PRIMARY KEY (project_id)
);




