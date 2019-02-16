package zijie.zeng.homework.study.model;

import lombok.Data;

@Data
public class SfcStudentHomeworkInfoModel {
    /**
     * 自增id
     */
    private int id;

    /**
     * 学生Qtalk
     */
    private String studentQtalk;

    /**
     * 期次Id
     */
    private int periodId;

    /**
     * 课程顺序
     */
    private int subjectTime;

    /**
     * 学生作业
     */
    private String homeworkUrl;
}
