package zijie.zeng.homework.study.model;

import lombok.Data;

@Data
public class SfcTeacherInfoModel {
    /**
     * 导师信息自增id
     */
    private int id;

    /**
     * 导师qtalk
     */
    private String qtalk;

    /**
     * 导师姓名
     */
    private String tutorName;

    /**
     * 导师密码
     */
    private String password;

    /**
     * 导师性别
     */
    private String tutorSex;

    /**
     * 导师个性签名
     */
    private String tutorContent;

    /**
     * 导师手机号
     */
    private String tutorPhone;

    /**
     * 导师期次ID
     */
    private int periodId;

    /**
     * 导师教学科目
     */
    private String tutorSubject;
    /**
     * 导师教学科目作业
     */
    private String tutorHomework;
}
