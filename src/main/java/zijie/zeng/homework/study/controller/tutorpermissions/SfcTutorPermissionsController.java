package zijie.zeng.homework.study.controller.tutorpermissions;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zijie.zeng.homework.study.constants.SfcConstants;
import zijie.zeng.homework.study.dto.studentpermissions.SfcStudentPermissionDto;
import zijie.zeng.homework.study.dto.tutorpermissions.SfcTutorPermissionDto;
import zijie.zeng.homework.study.exceptions.SfcExtException;
import zijie.zeng.homework.study.service.studentpermissions.SfcStudentPermissionService;
import zijie.zeng.homework.study.service.tutorpermissions.SfcTutorPermissionsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class SfcTutorPermissionsController {
    //学生权限相关操作
    @Autowired
    private SfcStudentPermissionService sfcStudentPermissionService;
    //老师相关操作
    @Autowired
    private SfcTutorPermissionsService sfcTutorPermissionsService;

    @ResponseBody
    @RequestMapping("/queryTeacherSearchAllStudent")
    public List queryTeacherSearchAllStudent(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询所有学生。url:/queryTeacherSearchAllStudent");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //进入service进行逻辑处理,使用学生权限的service，将老师包装为一个学生
        SfcStudentPermissionDto sfcStudentPermissionDto = new SfcStudentPermissionDto();
        sfcStudentPermissionDto.setPeriodId(sfcTutorPermissionDto.getPeriodId());

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.querytAllStudentToStudentInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryTeacherSearchLastPeriod")
    public List queryTeacherSearchLastPeriod(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询最新期次学生。url:/queryStudentSearchLastPeriod");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //创建studentDto
        SfcStudentPermissionDto sfcStudentPermissionDto = new SfcStudentPermissionDto();
        sfcStudentPermissionDto.setPeriodId(sfcTutorPermissionDto.getPeriodId());

        //进入service进行逻辑处理,使用学生权限的service，将老师包装为一个学生
        List list = sfcStudentPermissionService.queryStudentSearchLastPeriod(sfcStudentPermissionDto);

        return list;
    }

    //老师板块
    @ResponseBody
    @RequestMapping("/queryTeacherSearchAllTeacher")
    public List queryTeacherSearchAllTeacher(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询所有老师。url:/queryStudentSearchAllTeacher");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //创建studentDto
        SfcStudentPermissionDto sfcStudentPermissionDto = new SfcStudentPermissionDto();
        sfcStudentPermissionDto.setPeriodId(sfcTutorPermissionDto.getPeriodId());

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.querytAllStudentToTeacherInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryTeacherLastPeriodTeacher")
    public List queryTeacherLastPeriodTeacher(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询最新期次老师。url:/queryStudentLastPeriodTeacher");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //创建studentDto
        SfcStudentPermissionDto sfcStudentPermissionDto = new SfcStudentPermissionDto();
        sfcStudentPermissionDto.setPeriodId(sfcTutorPermissionDto.getPeriodId());

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.queryLastPTeacherInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/changeTeacherMyselfInfo")
    public int changeTeacherMyselfInfo(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("老师修改个人信息。url：changeTeacherMyselfInfo");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        if(Objects.equals(sfcTutorPermissionDto.getPermissions(), SfcConstants.ROOT_PERMISSIONS) ||
                Objects.equals(sfcTutorPermissionDto.getPermissions(), SfcConstants.MANAGER_PERMISSIONS)) {
            throw new SfcExtException("您是管理员，无法修改身份信息");
        }

        //到service进行处理
        int res = sfcTutorPermissionsService.changeStudentMyselfInfo(sfcTutorPermissionDto);
        return res;
    }

    @ResponseBody
    @RequestMapping("/queryTeacherCourseAll")
    public List queryTeacherCourseAll(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询所有课程。url:/queryTeacherCourseAll");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);
        //创建studentDto
        SfcStudentPermissionDto sfcStudentPermissionDto = new SfcStudentPermissionDto();
        sfcStudentPermissionDto.setPeriodId(sfcTutorPermissionDto.getPeriodId());

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.querytAllCourseToCourseInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryTeacherLastPeriodCourse")
    public List queryTeacherLastPeriodCourse(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询最新期次课程。url:/queryTeacherLastPeriodCourse");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //创建studentDto
        SfcStudentPermissionDto sfcStudentPermissionDto = new SfcStudentPermissionDto();
        sfcStudentPermissionDto.setPeriodId(sfcTutorPermissionDto.getPeriodId());

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.queryLastPCourseInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryTeacherHomeWorkAll")
    public List queryTeacherHomeWorkAll(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询所有作业。url:/queryTeacherHomeWorkAll");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //创建studentDto
        SfcStudentPermissionDto sfcStudentPermissionDto = new SfcStudentPermissionDto();
        sfcStudentPermissionDto.setPeriodId(sfcTutorPermissionDto.getPeriodId());

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.querytAllHomeworkInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryTeacherLastPeriodHomeWork")
    public List queryTeacherLastPeriodHomeWork(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询最新期次作业。url:/queryTeacherLastPeriodHomeWork");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //创建studentDto
        SfcStudentPermissionDto sfcStudentPermissionDto = new SfcStudentPermissionDto();
        sfcStudentPermissionDto.setPeriodId(sfcTutorPermissionDto.getPeriodId());

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.queryLastPHomeworkInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/teacherSureHomework")
    public Integer teacherSureHomework(@Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询最新期次作业。url:/teacherSureHomework");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //将老师作业存到老师表中。
        int res = sfcTutorPermissionsService.changeStudentMyselfInfo(sfcTutorPermissionDto);

        return res;
    }

    /**
     * 老师下载学生作业
     * 老师权限，下载学生作业
     * @param sfcTutorPermissionDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/downloadFileTeacher")
    public void downloadFileTeacher(HttpServletRequest request, HttpServletResponse response, @Validated SfcTutorPermissionDto sfcTutorPermissionDto, BindingResult result) {
        log.info("查询最新期次作业。url:/downloadFileTeacher");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcTutorPermissionDto);

        //下载文件
        sfcTutorPermissionsService.downloadFileForStudentHomework(request, response, sfcTutorPermissionDto);

        return;
    }

    /**
     * 检验传参
     * 学生权限，检查登录状态
     * @param sfcTutorPermissionDto
     * @return
     */
    private void checkSfcUserLoginArgs(SfcTutorPermissionDto sfcTutorPermissionDto) {
        Preconditions.checkNotNull(sfcTutorPermissionDto.getQtalk(), "参数:导师qtalk错误");
        Preconditions.checkNotNull(sfcTutorPermissionDto.getPeriodId(), "参数:导师期次错误");
        Preconditions.checkNotNull(sfcTutorPermissionDto.getPermissions(), "参数：权限错误");

        if(Objects.equals(sfcTutorPermissionDto.getQtalk(), SfcConstants.QTALK_VALUE_IS_NULL)) {
            throw new SfcExtException("errorQtalk");
        }
        if(Objects.equals(sfcTutorPermissionDto.getPermissions(), SfcConstants.TEACHER_PERMISSIONS)) {

        } else if(Objects.equals(sfcTutorPermissionDto.getPermissions(), SfcConstants.MANAGER_PERMISSIONS)) {

        } else if(!Objects.equals(sfcTutorPermissionDto.getPermissions(), SfcConstants.ROOT_PERMISSIONS)) {
            throw new SfcExtException("errorPermission");
        }
    }
}
