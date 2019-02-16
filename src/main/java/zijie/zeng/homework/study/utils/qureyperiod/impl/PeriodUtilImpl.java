package zijie.zeng.homework.study.utils.qureyperiod.impl;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zijie.zeng.homework.study.dao.SfcHomeworkPeriodDao;
import zijie.zeng.homework.study.model.SfcHomeworkPeriodModel;
import zijie.zeng.homework.study.utils.qureyperiod.PeriodUtil;

@Service
public class PeriodUtilImpl implements PeriodUtil {
    @Autowired
    private SfcHomeworkPeriodDao sfcHomeworkPeriodDao;

    @Override
    public int queryLastPeriodId() {
        int periodId = sfcHomeworkPeriodDao.queryLastPeriodId();
        Preconditions.checkNotNull(periodId, "参数:期次错误");
        return periodId;
    }

    @Override
    public SfcHomeworkPeriodModel queryPeriodByPeriodId(int periodId) {
        SfcHomeworkPeriodModel sfcHomeworkPeriodModel = sfcHomeworkPeriodDao.queryPeriodByPeriodId(periodId);
        return sfcHomeworkPeriodModel;
    }
}
