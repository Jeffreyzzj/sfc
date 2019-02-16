package zijie.zeng.homework.study.service.studentpermissions;

import org.springframework.web.multipart.MultipartFile;
import zijie.zeng.homework.study.dto.studentpermissions.SfcStudentPermissionDto;
import zijie.zeng.homework.study.model.SfcStudentHomeworkInfoModel;
import zijie.zeng.homework.study.model.SfcStudentInfoModel;
import zijie.zeng.homework.study.model.SfcSubjectInfoModel;
import zijie.zeng.homework.study.model.SfcTeacherInfoModel;

import java.util.List;

public interface SfcStudentPermissionService {
    /**
     * 学生权限，查询所有学生
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcStudentInfoModel>
     */
    List<SfcStudentInfoModel> querytAllStudentToStudentInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，查询最新期次的学生
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcStudentInfoModel>
     */
    List<SfcStudentInfoModel> queryStudentSearchLastPeriod(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，修改个人信息,返回一个值，用于判断修改成功与否
     *
     * @param sfcStudentPermissionDto
     * @return int
     */
    int changeStudentMyselfInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，查询所有老师
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcStudentInfoModel>
     */
    List<SfcTeacherInfoModel> querytAllStudentToTeacherInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，查询最后期次老师
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcStudentInfoModel>
     */
    List<SfcTeacherInfoModel> queryLastPTeacherInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，查询所有课程
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcSubjectInfoModel>
     */
    List<SfcSubjectInfoModel> querytAllCourseToCourseInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，查询当前期次课程
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcSubjectInfoModel>
     */
    List<SfcSubjectInfoModel> queryLastPCourseInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，查询所有作业
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcStudentHomeworkInfoModel>
     */
    List<SfcStudentHomeworkInfoModel> querytAllHomeworkInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，查询当前期次作业
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcStudentHomeworkInfoModel>
     */
    List<SfcStudentHomeworkInfoModel> queryLastPHomeworkInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，查询学生自己完成的作业
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcStudentHomeworkInfoModel>
     */
    List<SfcStudentHomeworkInfoModel> queryStudentCompleteHomeworkInfo(SfcStudentPermissionDto sfcStudentPermissionDto);

    /**
     * 学生权限，上传作业
     *
     * @param sfcStudentPermissionDto
     * @param file
     * @return int
     */
    int uploadFileHomework(SfcStudentPermissionDto sfcStudentPermissionDto, MultipartFile file);

}
