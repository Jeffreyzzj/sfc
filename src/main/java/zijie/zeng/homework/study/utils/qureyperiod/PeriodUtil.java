package zijie.zeng.homework.study.utils.qureyperiod;

import zijie.zeng.homework.study.model.SfcHomeworkPeriodModel;

public interface PeriodUtil {

    /**
     * 获得当前最新期次
     * zijie.zeng
     *
     * @return int
     */
    int queryLastPeriodId();

    /**
     * 查询该期次是否在期次表中
     * zijie.zeng
     *
     * @return SfcHomeworkPeriodModel
     */
    SfcHomeworkPeriodModel queryPeriodByPeriodId(int periodId);
}
