package zijie.zeng.homework.study.dto.tutorpermissions;

import lombok.Data;

@Data
public class SfcTutorPermissionDto {
    /**
     * 导师Qtalk
     */
    private String qtalk;

    /**
     * 导师期次
     */
    private Integer periodId;

    /**
     * 导师代号
     */
    private Integer permissions;

    /**
     * 导师密码
     */
    private String password;

    /**
     * 导师姓名
     */
    private String tutorName;

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
     * 导师教学科目
     */
    private String tutorSubject;
    /**
     * 导师教学科目作业
     */
    private String tutorHomework;

    /**
     * 学生作业地址
     */
    private String homeworkUrl;

}
