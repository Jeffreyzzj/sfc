package zijie.zeng.homework.study.service.tutorpermissions;

import zijie.zeng.homework.study.dto.tutorpermissions.SfcTutorPermissionDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SfcTutorPermissionsService {
    /**
     * 教师权限权限，修改个人信息,返回一个值，用于判断修改成功与否
     *
     * @param sfcTutorPermissionDto
     * @return int
     */
    int changeStudentMyselfInfo(SfcTutorPermissionDto sfcTutorPermissionDto);

    /**
     * 教师权限权限，下载文件---学生作业
     *
     * @param sfcTutorPermissionDto
     * @return
     */
    void downloadFileForStudentHomework(HttpServletRequest request, HttpServletResponse response, SfcTutorPermissionDto sfcTutorPermissionDto);

}
