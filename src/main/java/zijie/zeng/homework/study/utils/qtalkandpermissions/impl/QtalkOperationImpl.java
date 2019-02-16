package zijie.zeng.homework.study.utils.qtalkandpermissions.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zijie.zeng.homework.study.constants.SfcConstants;
import zijie.zeng.homework.study.dao.SfcStudentInfoDao;
import zijie.zeng.homework.study.dao.SfcTeacherInfoDao;
import zijie.zeng.homework.study.model.SfcStudentInfoModel;
import zijie.zeng.homework.study.model.SfcTeacherInfoModel;
import zijie.zeng.homework.study.utils.qtalkandpermissions.QtalkOperation;

import java.util.List;

@Service
public class QtalkOperationImpl implements QtalkOperation {
    //查询根据qtalk查询权限，1.学生表
    @Autowired
    private SfcStudentInfoDao sfcStudentInfoDao;
    //查询老师表
    @Autowired
    private SfcTeacherInfoDao sfcTeacherInfoDao;

    @Override
    public int searchPermissionsByQtalk(String qtalkOne) {
        List<SfcStudentInfoModel> listStudent = sfcStudentInfoDao.queryStudentInfoByQtalk(qtalkOne);
        if(listStudent.size()> SfcConstants.QUERY_ZERO) {
            return SfcConstants.STUDENT_PERMISSIONS;
        }

        List<SfcTeacherInfoModel> listTeacher = sfcTeacherInfoDao.queryTeacherInfoByQtalk(qtalkOne);
        if(listTeacher.size()> SfcConstants.QUERY_ZERO) {
            return SfcConstants.TEACHER_PERMISSIONS;
        }

        return SfcConstants.SFC_ERROR;
    }
}
