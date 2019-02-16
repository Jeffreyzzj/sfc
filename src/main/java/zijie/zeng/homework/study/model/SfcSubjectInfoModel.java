package zijie.zeng.homework.study.model;

import lombok.Data;

@Data
public class SfcSubjectInfoModel {
    /**
     * 自增id
     */
    private int id;

    /**
     * 期次id
     */
    private int periodId;

    /**
     * 课程顺序
     */
    private int subjectOrder;

    /**
     * 课程名称
     */
    private String subjectName;

}
