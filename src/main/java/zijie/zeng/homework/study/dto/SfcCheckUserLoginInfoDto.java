package zijie.zeng.homework.study.dto;

import lombok.Data;

@Data
public class SfcCheckUserLoginInfoDto {
    /**
     * 学生Qtalk
     */
    private String qtalk;

    /**
     * 学生密码
     */
    private String password;

    /**
     * 学生期次
     */
    private Integer periodId;
}
