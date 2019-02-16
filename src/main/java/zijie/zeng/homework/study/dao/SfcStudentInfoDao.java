package zijie.zeng.homework.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zijie.zeng.homework.study.model.SfcStudentInfoModel;

import java.util.List;

@Mapper
public interface SfcStudentInfoDao {
    /**
     * 将信息插入学生信息表
     * @return
     */
    int insertStudentInfoByModel(SfcStudentInfoModel sfcStudentInfoModel);

    /**
     * 修改个人信息
     * @param sfcStudentInfoModel，
     * @return int
     */
    int updateStudentInfoByPeriodIdAndQtalk(SfcStudentInfoModel sfcStudentInfoModel);

    /**
     * 查询某一个学生，根据期次和qtalk
     *
     * @param qtalk,
     * @param periodId
     * @return int
     */
    SfcStudentInfoModel queryStudentInfoByPeriodIdAndQtalk(@Param("qtalk") String qtalk, @Param("periodId") int periodId);

    /**
     * 查询所有期次的学生
     * @return
     */
    List<SfcStudentInfoModel> queryStudentInfoAll();

    /**
     * 查询最新期次的学生
     * @return
     */
    List<SfcStudentInfoModel> queryStudentInfoByPeriodId(@Param("periodId") int periodId);

    /**
     * 根据qtalk查询学生
     * @return
     */
    List<SfcStudentInfoModel> queryStudentInfoByQtalk(@Param("qtalk") String qtalk);

}
