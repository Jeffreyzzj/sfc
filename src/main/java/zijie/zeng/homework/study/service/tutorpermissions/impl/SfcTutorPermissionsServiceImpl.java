package zijie.zeng.homework.study.service.tutorpermissions.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zijie.zeng.homework.study.constants.SfcConstants;
import zijie.zeng.homework.study.dao.SfcTeacherInfoDao;
import zijie.zeng.homework.study.dto.tutorpermissions.SfcTutorPermissionDto;
import zijie.zeng.homework.study.exceptions.SfcExtException;
import zijie.zeng.homework.study.model.SfcHomeworkPeriodModel;
import zijie.zeng.homework.study.model.SfcTeacherInfoModel;
import zijie.zeng.homework.study.service.tutorpermissions.SfcTutorPermissionsService;
import zijie.zeng.homework.study.utils.FileUtil;
import zijie.zeng.homework.study.utils.qtalkandpermissions.QtalkOperation;
import zijie.zeng.homework.study.utils.qureyperiod.PeriodUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Service
public class SfcTutorPermissionsServiceImpl implements SfcTutorPermissionsService {
    //用于期次操作
    @Autowired
    private PeriodUtil periodUtil;

    //用于判断是否有qtalk
    @Autowired
    private QtalkOperation qtalkOperation;

    //用于老师数据表的操作
    @Autowired
    private SfcTeacherInfoDao sfcTeacherInfoDao;

    @Override
    @Transactional
    public int changeStudentMyselfInfo(SfcTutorPermissionDto sfcTutorPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcTutorPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriodId");
        }

        //查看原来是否有此人
        SfcTeacherInfoModel teacherInfoModel = sfcTeacherInfoDao.queryTeacherInfoByQtalkAndPeriodId(sfcTutorPermissionDto.getQtalk(), sfcTutorPermissionDto.getPeriodId());
        if(Objects.isNull(teacherInfoModel)) {
            throw new SfcExtException("没有此人");
        }

        String password = null;
        if(Objects.isNull(sfcTutorPermissionDto.getPassword())) {
            password = sfcTutorPermissionDto.getPassword();
        } else {
            password = teacherInfoModel.getPassword();
        }

        //拼成老师信息
        SfcTeacherInfoModel sfcTeacherInfoModel = new SfcTeacherInfoModel();
        sfcTeacherInfoModel.setQtalk(sfcTutorPermissionDto.getQtalk());
        sfcTeacherInfoModel.setPassword(password);
        sfcTeacherInfoModel.setTutorContent(sfcTutorPermissionDto.getTutorContent());
        sfcTeacherInfoModel.setTutorName(sfcTutorPermissionDto.getTutorName());
        sfcTeacherInfoModel.setPeriodId(sfcTutorPermissionDto.getPeriodId());
        sfcTeacherInfoModel.setTutorSex(sfcTutorPermissionDto.getTutorSex());
        sfcTeacherInfoModel.setTutorPhone(sfcTutorPermissionDto.getTutorPhone());
        sfcTeacherInfoModel.setTutorSubject(sfcTutorPermissionDto.getTutorSubject());
        sfcTeacherInfoModel.setTutorHomework(sfcTutorPermissionDto.getTutorHomework());

        //进行数据库处理
        int res = sfcTeacherInfoDao.updateTeacherInfoBySfcTeacherInfoModel(sfcTeacherInfoModel);

        return res;
    }

    @Override
    public void downloadFileForStudentHomework(HttpServletRequest request, HttpServletResponse response, SfcTutorPermissionDto sfcTutorPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcTutorPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriodId");
        }

        //判断是否有该Qtalk
        int res = qtalkOperation.searchPermissionsByQtalk(sfcTutorPermissionDto.getQtalk());

        if (Objects.equals(res, SfcConstants.SFC_ERROR)) {
            throw new SfcExtException("系统判断您身份信息异常，建议重新登录");
        }

        log.info("方法：downloadFileForStudentHomework");
        FileUtil.downloadFile(request, response, sfcTutorPermissionDto.getHomeworkUrl());
    }
}
