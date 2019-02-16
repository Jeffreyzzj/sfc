package zijie.zeng.homework.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zijie.zeng.homework.study.model.SfcTeacherInfoModel;

import java.util.List;

@Mapper
public interface SfcTeacherInfoDao {

    /**
     * 修改老师信息
     * @return List
     */
    int updateTeacherInfoBySfcTeacherInfoModel(SfcTeacherInfoModel sfcTeacherInfoModel);

    /**
     * 查询所有期次的老师
     * @return List
     */
    List<SfcTeacherInfoModel> queryTeacherInfoAll();

    /**
     * 查询最新期次的老师
     * @return List
     */
    List<SfcTeacherInfoModel> queryTeacherInfoByPeriodId(@Param("periodId") int periodId);

    /**
     * 根据qtalk查询老师
     * @return List
     */
    List<SfcTeacherInfoModel> queryTeacherInfoByQtalk(@Param("qtalk") String qtalk);

    /**
     * 根据qtalk查询老师
     * @return SfcTeacherInfoModel
     */
    SfcTeacherInfoModel queryTeacherInfoByQtalkAndPeriodId(@Param("qtalk") String qtalk, @Param("periodId") int periodId);
}
