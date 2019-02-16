package zijie.zeng.homework.study.service.studentpermissions.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zijie.zeng.homework.study.constants.SfcConstants;
import zijie.zeng.homework.study.dao.SfcStudentHomeworkInfoDao;
import zijie.zeng.homework.study.dao.SfcStudentInfoDao;
import zijie.zeng.homework.study.dao.SfcSubjectInfoDao;
import zijie.zeng.homework.study.dao.SfcTeacherInfoDao;
import zijie.zeng.homework.study.dto.studentpermissions.SfcStudentPermissionDto;
import zijie.zeng.homework.study.exceptions.SfcExtException;
import zijie.zeng.homework.study.model.SfcHomeworkPeriodModel;
import zijie.zeng.homework.study.model.SfcStudentHomeworkInfoModel;
import zijie.zeng.homework.study.model.SfcStudentInfoModel;
import zijie.zeng.homework.study.model.SfcSubjectInfoModel;
import zijie.zeng.homework.study.model.SfcTeacherInfoModel;
import zijie.zeng.homework.study.service.studentpermissions.SfcStudentPermissionService;
import zijie.zeng.homework.study.utils.FileUtil;
import zijie.zeng.homework.study.utils.qureyperiod.PeriodUtil;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SfcStudentPermissionServiceImpl implements SfcStudentPermissionService {
    //对期次进行操作
    @Autowired
    private PeriodUtil periodUtil;
    //用于查询所有学生
    @Autowired
    private SfcStudentInfoDao sfcStudentInfoDao;
    //用于查询老师
    @Autowired
    private SfcTeacherInfoDao sfcTeacherInfoDao;
    //用于查询所有科目
    @Autowired
    private SfcSubjectInfoDao sfcSubjectInfoDao;
    //用于处理作业
    @Autowired
    private SfcStudentHomeworkInfoDao sfcStudentHomeworkInfoDao;

    @Override
    public List<SfcStudentInfoModel> querytAllStudentToStudentInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriodId");
        }

        //获得所有学生
        List<SfcStudentInfoModel> list = sfcStudentInfoDao.queryStudentInfoAll();


        return list;
    }

    @Override
    public List<SfcStudentInfoModel> queryStudentSearchLastPeriod(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriodId");
        }

        //获得当前最新期次
        int periodIdLast = periodUtil.queryLastPeriodId();

        //获得当前期次学生
        List<SfcStudentInfoModel> list = sfcStudentInfoDao.queryStudentInfoByPeriodId(periodIdLast);

        return list;
    }

    @Override
    @Transactional
    public int changeStudentMyselfInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriodId");
        }

        String passwordMid = null;

        //检查是否有此人
        SfcStudentInfoModel sfcStudentInfoModel = sfcStudentInfoDao.queryStudentInfoByPeriodIdAndQtalk(sfcStudentPermissionDto.getQtalk(), sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(sfcStudentInfoModel)) {
            throw new SfcExtException("没有此人");
        }


        if(Objects.isNull(sfcStudentPermissionDto.getPassword())) {
            passwordMid = sfcStudentPermissionDto.getPassword();
        } else {
            passwordMid = sfcStudentInfoModel.getPassword();
        }

        SfcStudentInfoModel studentInfoModel = new SfcStudentInfoModel();
        studentInfoModel.setQtalk(sfcStudentPermissionDto.getQtalk());
        studentInfoModel.setPeriodId(sfcStudentPermissionDto.getPeriodId());
        studentInfoModel.setStudentPhone(sfcStudentPermissionDto.getStudentPhone());
        studentInfoModel.setStudentContent(sfcStudentPermissionDto.getStudentContent());
        studentInfoModel.setStudentSex(sfcStudentPermissionDto.getStudentSex());
        studentInfoModel.setStudentClass(sfcStudentPermissionDto.getStudentClass());
        studentInfoModel.setStudentName(sfcStudentPermissionDto.getStudentName());
        studentInfoModel.setPassword(passwordMid);
        int res = sfcStudentInfoDao.updateStudentInfoByPeriodIdAndQtalk(studentInfoModel);

        return res;
    }

    @Override
    public List<SfcTeacherInfoModel> querytAllStudentToTeacherInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriodId");
        }

        //获得所有学生
        List<SfcTeacherInfoModel> list = sfcTeacherInfoDao.queryTeacherInfoAll();

        return list;
    }

    @Override
    public List<SfcTeacherInfoModel> queryLastPTeacherInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriod");
        }

        //获得当前期次学生
        List<SfcTeacherInfoModel> list = sfcTeacherInfoDao.queryTeacherInfoByPeriodId(periodUtil.queryLastPeriodId());

        return list;
    }

    @Override
    public List<SfcSubjectInfoModel> querytAllCourseToCourseInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriod");
        }

        //获得所有科目
        List<SfcSubjectInfoModel> list = sfcSubjectInfoDao.querySubjectInfoAll();

        return list;

    }

    @Override
    public List<SfcSubjectInfoModel> queryLastPCourseInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriod");
        }

        //获得当前最新期次
        int periodIdLast = periodUtil.queryLastPeriodId();

        //获得当前期次科目
        List<SfcSubjectInfoModel> list = sfcSubjectInfoDao.querySubjectInfoByPeriodId(periodIdLast);
        return list;
    }

    @Override
    public List<SfcStudentHomeworkInfoModel> querytAllHomeworkInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriod");
        }

        //获得所有科目
        List<SfcStudentHomeworkInfoModel> list = sfcStudentHomeworkInfoDao.queryHomeworkInfoAll();

        return list;
    }

    @Override
    public List<SfcStudentHomeworkInfoModel> queryLastPHomeworkInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriod");
        }

        List<SfcStudentHomeworkInfoModel> list = sfcStudentHomeworkInfoDao.queryHWInfoByPeriodId(periodUtil.queryLastPeriodId());

        return list;
    }

    @Override
    public List<SfcStudentHomeworkInfoModel> queryStudentCompleteHomeworkInfo(SfcStudentPermissionDto sfcStudentPermissionDto) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriod");
        }

        //获得当前最新期次
        int periodIdLast = periodUtil.queryLastPeriodId();

        List<SfcStudentHomeworkInfoModel> list = sfcStudentHomeworkInfoDao.queryHWInfoByPeriodIdAndQtalk(periodIdLast, sfcStudentPermissionDto.getQtalk());
        return list;
    }

    @Override
    public int uploadFileHomework(SfcStudentPermissionDto sfcStudentPermissionDto, MultipartFile file) {
        //检查期次是否正确
        SfcHomeworkPeriodModel shpModel = periodUtil.queryPeriodByPeriodId(sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(shpModel)) {
            throw new SfcExtException("errorPeriod");
        }

        //查到该学生
        SfcStudentInfoModel studentInfoModel = sfcStudentInfoDao.queryStudentInfoByPeriodIdAndQtalk(sfcStudentPermissionDto.getQtalk(), sfcStudentPermissionDto.getPeriodId());
        if(Objects.isNull(studentInfoModel)) {
            throw new SfcExtException("学生错误");
        }

        //查到该课程
        SfcSubjectInfoModel sfcSubjectInfoModel = sfcSubjectInfoDao.querySubjectInfoByPeriodIdAndSubjectOrder(sfcStudentPermissionDto.getPeriodId(), sfcStudentPermissionDto.getSubjectOrder());
        if(Objects.isNull(sfcSubjectInfoModel)) {
            throw new SfcExtException("科目错误");
        }

        //给文件命名 ：姓名+Qtalk+科目+随机数
        String fileName = studentInfoModel.getStudentName()+studentInfoModel.getQtalk()+sfcSubjectInfoModel.getSubjectName()+
                Math.random()+ "." + FileUtil.getFileSuffixName(file);
        String url = null;
        try {
            url = FileUtil.saveFile(file.getInputStream(), fileName );
        } catch (IOException e) {
            log.info("文件处理出错");
        }

        int res = SfcConstants.SFC_ERROR;

        //查看作业表中是否有该数据,如果有执行更新操作，如果没有执行添加操作。
        SfcStudentHomeworkInfoModel sfcStudentHomeworkInfoModel = sfcStudentHomeworkInfoDao.queryHWInfoByPeriodIdAndQtalkAndOrder(sfcStudentPermissionDto.getPeriodId(), sfcStudentPermissionDto.getQtalk(), sfcSubjectInfoModel.getSubjectOrder());
        if(Objects.isNull(sfcStudentHomeworkInfoModel)) {
            sfcStudentHomeworkInfoModel = new SfcStudentHomeworkInfoModel();
        } else {
            sfcStudentHomeworkInfoModel.setHomeworkUrl(url);
            res = sfcStudentHomeworkInfoDao.updateHomeworkByQtalkAndPeriodAndSubjectTime(sfcStudentHomeworkInfoModel);
            return res;
        }

        //设计出一个作业model
        sfcStudentHomeworkInfoModel.setPeriodId(studentInfoModel.getPeriodId());
        sfcStudentHomeworkInfoModel.setStudentQtalk(studentInfoModel.getQtalk());
        sfcStudentHomeworkInfoModel.setHomeworkUrl(url);
        sfcStudentHomeworkInfoModel.setSubjectTime(sfcSubjectInfoModel.getSubjectOrder());
        res = sfcStudentHomeworkInfoDao.insertHomeworkByQtalkAndPeriodAndSubjectTime(sfcStudentHomeworkInfoModel);

        return res;
    }

}
