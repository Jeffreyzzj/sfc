package zijie.zeng.homework.study.service.registerandlogin.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zijie.zeng.homework.study.dao.SfcStudentInfoDao;
import zijie.zeng.homework.study.dto.SfcUserForRegesiterDto;
import zijie.zeng.homework.study.exceptions.SfcExtException;
import zijie.zeng.homework.study.model.SfcStudentInfoModel;
import zijie.zeng.homework.study.service.registerandlogin.SfcHomeWorkLoginService;
import zijie.zeng.homework.study.utils.qtalkandpermissions.QtalkOperation;
import zijie.zeng.homework.study.utils.qureyperiod.PeriodUtil;

import java.util.Objects;

@Slf4j
@Service
public class SfcHomeWorkLoginServiceImpl implements SfcHomeWorkLoginService {

    //注入关于期次单表dao层,用于获得当前期次
    @Autowired
    private PeriodUtil periodUtil;

    //注入关于学习信息表dao层，用于插入数据
    @Autowired
    private SfcStudentInfoDao sfcStudentInfoDao;

    //判断qtalk是否使用过
    @Autowired
    private QtalkOperation qtalkOperation;


    /**
     * 将学生信息插入数据库
     * zijie.zeng
     *
     * @return int
     */
    @Override
    @Transactional
    public int insertUserForRegesiterToStudentInfo(SfcUserForRegesiterDto sufrDto) {
        log.info("方法：insertUserForRegesiterToStudentInfo");
        //判断该qtalk是否使用过
        Integer permissionsMid = qtalkOperation.searchPermissionsByQtalk(sufrDto.getStudentQtalk());
        if(!Objects.equals(permissionsMid, -1)) {
            throw new SfcExtException("该qtalk被占用");
        }

        //获得最新期次
        int periodId = periodUtil.queryLastPeriodId();

        //判断数据库中是否有相同(期次和qtalk)的数据
        SfcStudentInfoModel studentInfoModelMid = sfcStudentInfoDao.queryStudentInfoByPeriodIdAndQtalk(sufrDto.getStudentQtalk(), periodId);
        if(Objects.isNull(studentInfoModelMid)) {
            studentInfoModelMid = new SfcStudentInfoModel();
        } else {
            throw new SfcExtException("qtalk重复，请更换后再试");
        }

        //将dto转化为model
        studentInfoModelMid.setQtalk(sufrDto.getStudentQtalk());
        studentInfoModelMid.setStudentName(sufrDto.getStudentName());
        studentInfoModelMid.setPassword(sufrDto.getStudentPassword());
        studentInfoModelMid.setStudentSex(sufrDto.getStudentSex());
        studentInfoModelMid.setStudentContent(sufrDto.getStudentContent());
        studentInfoModelMid.setStudentPhone(sufrDto.getStudentPhone());
        studentInfoModelMid.setPeriodId(periodId);
        studentInfoModelMid.setStudentClass(sufrDto.getStudentClass());

        int res = sfcStudentInfoDao.insertStudentInfoByModel(studentInfoModelMid);

        return res;
    }

}
