CREATE DATABASE `sfc` CHARACTER SET utf8 COLLATE utf8_general_ci;

/*学生信息表*/
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `student_info_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '学生信息自增id',
  `qtalk` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '学生qtalk',
  `student_name` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '学生姓名',
  `password` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '密码',
  `student_sex` VARCHAR(5) NOT NULL DEFAULT '' COMMENT '学生性别',
  `student_content` varchar(127) NOT NULL DEFAULT '' COMMENT '学生宣言',
  `student_phone` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '学生手机号',
  `period_id` int(10) NOT NULL DEFAULT '0' COMMENT '学生期次id',
  `student_class` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '学生班级',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`student_info_id`),
  UNIQUE KEY `uniq_idx_student_qtalk_period_id` (`qtalk`,`period_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';

/*导师信息表*/
DROP TABLE IF EXISTS `tutor_info`;
CREATE TABLE `tutor_info` (
   `tutor_info_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '导师信息自增id',
   `qtalk` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '导师qtalk',
   `tutor_name` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '导师姓名',
   `password` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '密码',
   `tutor_sex` VARCHAR(5) NOT NULL DEFAULT '' COMMENT '导师性别',
   `tutor_content` varchar(127) NOT NULL DEFAULT '' COMMENT '导师宣言',
   `tutor_phone` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '导师手机号',
   `period_id` int(10) NOT NULL DEFAULT '0' COMMENT '导师期次id',
   `tutor_subject` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '导师科目',
   `tutor_homework` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '导师作业',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`tutor_info_id`),
   UNIQUE KEY `uniq_idx_tutor_qtalk_period_id` (`qtalk`,`period_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='导师信息表';

/*学生导师关系表*/
DROP TABLE IF EXISTS `student_tutor_relation`;
CREATE TABLE `student_tutor_relation` (
  `relation_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '导师学生关系自增id',
  `student_qtalk` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '学生qtalk',
  `period_id` int(10) NOT NULL DEFAULT '0' COMMENT '学生期次id',
  `tutor_qtalk` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '导师qtalk',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`relation_id`),
  UNIQUE KEY `uniq_idx_student_qtalk_period_id` (`student_qtalk`,`period_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='学生导师关系表表';

/*课程表*/
DROP TABLE IF EXISTS `subject_info`;
CREATE TABLE `subject_info` (
  `subject_info_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程表自增id',
  `period_id` int(10) NOT NULL DEFAULT '0' COMMENT '课程期次id',
  `subject_order` int(10) NOT NULL DEFAULT '0' COMMENT '课程顺序，按时间顺序排列',
  `subject_name` VARCHAR(15) NOT NULL DEFAULT '' COMMENT '课程名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`subject_info_id`),
  UNIQUE KEY `uniq_idx_student_qtalk_period_id` (`subject_order`,`period_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

/*学生作业表*/
DROP TABLE IF EXISTS `student_homework_info`;
CREATE TABLE `student_homework_info` (
 `homework_info_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程表自增id',
 `student_qtalk` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '学生qtalk',
 `period_id` int(10) NOT NULL DEFAULT '0' COMMENT '学生期次id',
 `subject_time` int(10) NOT NULL DEFAULT '0' COMMENT '课程顺序，用来确定一个作业,与课程表中的order相同',
 `homework_url` VARCHAR(126) NOT NULL DEFAULT '' COMMENT '学生作业路径',
 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY (`homework_info_id`),
 UNIQUE KEY `uniq_idx_student_qtalk_period_id_subject_time` (`student_qtalk`,`period_id`, subject_time)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='学生作业表';

/*期次*/
DROP TABLE IF EXISTS `sfc_period`;
CREATE TABLE `sfc_period` (
  `sfc_period_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id 期次id',
  `period_name` varchar(10) NOT NULL DEFAULT '' COMMENT '期次名字',
  `period_id` int(10) NOT NULL DEFAULT '0' COMMENT '期次id',
  `graduate_year` varchar(5) NOT NULL DEFAULT '' COMMENT '毕业年份',
  `educate_year` varchar(5) NOT NULL DEFAULT '' COMMENT '培训年份',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '辅导开始时间',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '辅导结束时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 0 :禁用;1：默认启用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`sfc_period_id`),
  UNIQUE KEY `period_id` (`period_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='期次表';

/*期次表，预插入数据*/
insert into sfc_period(period_name, period_id) values ("1", 1);
insert into sfc_period(period_name, period_id) values ("2", 2);
insert into sfc_period(period_name, period_id) values ("3", 3);

/*导师信息预插入数据*/
insert into tutor_info(
  qtalk, tutor_name, password, tutor_sex, tutor_content, tutor_phone, period_id,tutor_subject,tutor_homework
  ) values (
  "520", "3", "520", "男", "3", "3","3", "3", "3"
  );
insert into tutor_info(
  qtalk, tutor_name, password, tutor_sex, tutor_content, tutor_phone, period_id,tutor_subject,tutor_homework
) values (
           "1", "1", "1", "男", "1", "1","3", "1", "1"
         );
insert into tutor_info(qtalk, tutor_name, password, tutor_sex, tutor_content, tutor_phone, period_id, tutor_subject,
                       tutor_homework)
values ("1", "1", "1", "男", "1", "1", "1", "1", "1");
insert into tutor_info(
  qtalk, tutor_name, password, tutor_sex, tutor_content, tutor_phone, period_id,tutor_subject
) values (
           "5", "5", "5", "男", "5", "5","5", "5"
         );

/*科目信息预插入*/
insert into subject_info(period_id, subject_order, subject_name)
values (3, 1, "math");
insert into subject_info(period_id, subject_order, subject_name) values (3, 2, "Chinese");
insert into subject_info(period_id, subject_order, subject_name) values (1, 2, "Chinese");
/*作业信息预插入*/
insert into student_homework_info(student_qtalk, period_id, subject_time, homework_url)
values ("1", 3, 2, "Chinese");
insert into student_homework_info(student_qtalk, period_id, subject_time, homework_url)
values ("1", 3, 1, "aaa");
insert into student_homework_info(student_qtalk, period_id, subject_time, homework_url)
values ("2", 3, 2, "Chinese");
insert into student_homework_info(student_qtalk, period_id, subject_time, homework_url)
values ("2", 1, 2, "Chinese");