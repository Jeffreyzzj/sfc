package zijie.zeng.homework.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zijie.zeng.homework.study.model.SfcSubjectInfoModel;

import java.util.List;

@Mapper
public interface SfcSubjectInfoDao {
    /**
     * 查询所有期次的科目
     * @return
     */
    List<SfcSubjectInfoModel> querySubjectInfoAll();

    /**
     * 查询最新期次科目
     * @return
     */
    List<SfcSubjectInfoModel> querySubjectInfoByPeriodId(@Param("periodId") int periodId);

    /**
     * 查询某个科目信息
     * @return
     */
    SfcSubjectInfoModel querySubjectInfoByPeriodIdAndSubjectOrder(@Param("periodId") int periodId, @Param("subjectOrder") int subjectOrder);
}
