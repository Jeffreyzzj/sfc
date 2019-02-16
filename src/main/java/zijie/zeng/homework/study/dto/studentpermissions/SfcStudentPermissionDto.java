package zijie.zeng.homework.study.dto.studentpermissions;

import lombok.Data;

@Data
public class SfcStudentPermissionDto {
    /**
     * 学生Qtalk
     */
    private String qtalk;

    /**
     * 学生期次
     */
    private Integer periodId;

    /**
     * 权限代号
     */
    private Integer permissions;

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
     * 学生班级
     */
    private String studentClass;

    /**
     * 课程次序
     */
    private Integer subjectOrder;
}
