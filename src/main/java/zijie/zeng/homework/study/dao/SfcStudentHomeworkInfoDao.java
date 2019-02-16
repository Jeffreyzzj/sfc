package zijie.zeng.homework.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zijie.zeng.homework.study.model.SfcStudentHomeworkInfoModel;

import java.util.List;

@Mapper
public interface SfcStudentHomeworkInfoDao {
    /**
     * 插入一个作业
     * @return
     */
    int insertHomeworkByQtalkAndPeriodAndSubjectTime(SfcStudentHomeworkInfoModel sfcStudentHomeworkInfoModel);

    /**
     * 插入一个作业
     * @return int
     */
    int updateHomeworkByQtalkAndPeriodAndSubjectTime(SfcStudentHomeworkInfoModel sfcStudentHomeworkInfoModel);

    /**
     * 查询所有期次的作业
     * @return
     */
    List<SfcStudentHomeworkInfoModel> queryHomeworkInfoAll();

    /**
     * 查询某一期次的作业
     * @return
     */
    List<SfcStudentHomeworkInfoModel> queryHWInfoByPeriodId(@Param("periodId") int periodId);

    /**
     * 查询某个人所有的作业，根据Qtalk和期次
     * @return
     */
    List<SfcStudentHomeworkInfoModel> queryHWInfoByPeriodIdAndQtalk(@Param("periodId") int periodId, @Param("qtalk") String qtalk);
    /**
     * 查询某个人所有的作业，根据Qtalk和期次
     * @return
     */
    SfcStudentHomeworkInfoModel queryHWInfoByPeriodIdAndQtalkAndOrder(@Param("periodId") int periodId, @Param("qtalk") String qtalk, @Param("subjectTime") int subjectTime);
}
