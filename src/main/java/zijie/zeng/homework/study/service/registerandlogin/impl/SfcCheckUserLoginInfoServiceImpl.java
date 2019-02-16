package zijie.zeng.homework.study.service.registerandlogin.impl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zijie.zeng.homework.study.constants.SfcConstants;
import zijie.zeng.homework.study.dao.SfcStudentInfoDao;
import zijie.zeng.homework.study.dao.SfcTeacherInfoDao;
import zijie.zeng.homework.study.dto.SfcCheckUserLoginInfoDto;
import zijie.zeng.homework.study.exceptions.SfcExtException;
import zijie.zeng.homework.study.model.SfcStudentInfoModel;
import zijie.zeng.homework.study.model.SfcTeacherInfoModel;
import zijie.zeng.homework.study.service.registerandlogin.SfcCheckUserLoginInfoService;
import zijie.zeng.homework.study.utils.qureyperiod.PeriodUtil;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class SfcCheckUserLoginInfoServiceImpl implements SfcCheckUserLoginInfoService {
    //查找当前最大期次使用
    @Autowired
    private PeriodUtil periodUtil;
    //判断该用户是否在学生信息表中
    @Autowired
    private SfcStudentInfoDao sfcStudentInfoDao;
    //判断该用户是否在教师表中
    @Autowired
    private SfcTeacherInfoDao sfcTeacherInfoDao;

    @Override
    public Map queryOneUserInfo(SfcCheckUserLoginInfoDto sfcCheckUserLoginInfoDto) {
        log.info("方法：queryOneUserInfo");
        //判断是否选择期次，如果没选择期次，默认为最近期次
        if(Objects.isNull(sfcCheckUserLoginInfoDto.getPeriodId())) {
            int periodId = periodUtil.queryLastPeriodId();
            sfcCheckUserLoginInfoDto.setPeriodId(periodId);
        }

        //使用map返回值，包括期次，qtalk，权限
        Map map = Maps.newHashMap();

        //根据期次和qtalk查询身份，学生表
        SfcStudentInfoModel ssiModel = sfcStudentInfoDao.queryStudentInfoByPeriodIdAndQtalk(sfcCheckUserLoginInfoDto.getQtalk(), sfcCheckUserLoginInfoDto.getPeriodId());

        if(!Objects.isNull(ssiModel)) {
            if (!Objects.equals(sfcCheckUserLoginInfoDto.getPassword(), ssiModel.getPassword())) {
                throw new SfcExtException("密码不正确");
            }
            map.put("permissions", SfcConstants.STUDENT_PERMISSIONS);
            map.put("qtalk", sfcCheckUserLoginInfoDto.getQtalk());
            map.put("periodId", sfcCheckUserLoginInfoDto.getPeriodId());

            return map;
        }

        //根据期次和qtalk查询身份，教师表
        SfcTeacherInfoModel sfcTeacherInfoModel = sfcTeacherInfoDao.queryTeacherInfoByQtalkAndPeriodId(sfcCheckUserLoginInfoDto.getQtalk(), sfcCheckUserLoginInfoDto.getPeriodId());

        if(!Objects.isNull(sfcTeacherInfoModel)) {
            if (!Objects.equals(sfcCheckUserLoginInfoDto.getPassword(), sfcTeacherInfoModel.getPassword())) {
                throw new SfcExtException("密码不正确");
            }
            map.put("qtalk", sfcCheckUserLoginInfoDto.getQtalk());
            map.put("permissions", SfcConstants.TEACHER_PERMISSIONS);
            map.put("periodId", sfcCheckUserLoginInfoDto.getPeriodId());

            return map;
        }

        throw new SfcExtException("qtalk或者密码错误");

    }
}
