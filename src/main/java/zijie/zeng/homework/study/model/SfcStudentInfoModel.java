package zijie.zeng.homework.study.model;

import lombok.Data;

@Data
public class SfcStudentInfoModel {
    /**
     * 学生信息自增id
     */
    private int id;

    /**
     * 学生qtalk
     */
    private String qtalk;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生密码
     */
    private String password;

    /**
     * 学生性别
     */
    private String studentSex;

    /**
     * 学生个性签名
     */
    private String studentContent;

    /**
     * 学生手机号
     */
    private String studentPhone;

    /**
     * 学生期次ID
     */
    private int periodId;

    /**
     * 学生班级
     */
    private String studentClass;

}
