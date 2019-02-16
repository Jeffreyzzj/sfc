package zijie.zeng.homework.study.dto;

import lombok.Data;

@Data
public class SfcUserForRegesiterDto {
    /**
     * 学生Qtalk
     */
    private String studentQtalk;

    /**
     * 学生name
     */
    private String studentName;

    /**
     * 学生密码
     */
    private String studentPassword;

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
     * 学生班级
     */
    private String studentClass;
}
