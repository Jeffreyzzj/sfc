package zijie.zeng.homework.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zijie.zeng.homework.study.model.SfcHomeworkPeriodModel;

@Mapper
public interface SfcHomeworkPeriodDao {
    /**
     * 获得当前期次，即表中期次数值最大的
     * @return
     */
    int queryLastPeriodId();

    /**
     * 查询该期次是否在期次表中
     * zijie.zeng
     *
     * @return int
     */
    SfcHomeworkPeriodModel queryPeriodByPeriodId(@Param("periodId") int periodId);

}
