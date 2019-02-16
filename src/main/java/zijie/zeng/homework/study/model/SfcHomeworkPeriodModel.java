package zijie.zeng.homework.study.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class SfcHomeworkPeriodModel {
    /**
     * 表自增id
     */
    private int id;

    /**
     * 期次名称
     */
    private String periodName;

    /**
     * 期次id
     */
    @NotNull(message = "期次值不能为空")
    private int periodId;

    /**
     * 毕业年份
     */
    private String graduateYear;

    /**
     * 培训年份
     */
    private String educateYear;

    /**
     * 辅导开始时间
     */
    private Timestamp startTime;

    /**
     * 辅导结束时间
     */
    private Timestamp endTime;

    /**
     * 期次状态   0禁用状态  1启用状态（默认）
     */
    private Integer status;
}
