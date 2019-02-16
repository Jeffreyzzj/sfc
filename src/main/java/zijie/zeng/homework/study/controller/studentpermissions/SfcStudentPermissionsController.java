package zijie.zeng.homework.study.controller.studentpermissions;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zijie.zeng.homework.study.constants.SfcConstants;
import zijie.zeng.homework.study.dto.studentpermissions.SfcStudentPermissionDto;
import zijie.zeng.homework.study.exceptions.SfcExtException;
import zijie.zeng.homework.study.service.studentpermissions.SfcStudentPermissionService;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class SfcStudentPermissionsController {
    //学生权限相关操作
    @Autowired
    private SfcStudentPermissionService sfcStudentPermissionService;

    @ResponseBody
    @RequestMapping("/queryStudentSearchAllStudent")
    public List queryStudentSearchAllStudent(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询所有学生。url:/queryStudentSearchAllStudent");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.querytAllStudentToStudentInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryStudentSearchLastPeriod")
    public List queryStudentSearchLastPeriod(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询最新期次学生。url:/queryStudentSearchLastPeriod");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.queryStudentSearchLastPeriod(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/changeStudentMyselfInfo")
    public int changeStudentMyselfInfo(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("学生修改个人信息。url：changeStudentMyselfInfo");

        if(Objects.equals(sfcStudentPermissionDto.getPermissions(), SfcConstants.MANAGER_PERMISSIONS) ||
        Objects.equals(sfcStudentPermissionDto.getPermissions(), SfcConstants.ROOT_PERMISSIONS)) {
            throw new SfcExtException("您是管理员，无法修改身份信息");
        }

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        int res = sfcStudentPermissionService.changeStudentMyselfInfo(sfcStudentPermissionDto);

        return res;
    }

    //老师板块
    @ResponseBody
    @RequestMapping("/queryStudentSearchAllTeacher")
    public List queryStudentSearchAllTeacher(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询所有老师。url:/queryStudentSearchAllTeacher");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.querytAllStudentToTeacherInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryStudentLastPeriodTeacher")
    public List queryStudentLastPeriodTeacher(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询最新期次老师。url:/queryStudentLastPeriodTeacher");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.queryLastPTeacherInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryCourseAll")
    public List queryCourseAll(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询所有课程。url:/queryCourseAll");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.querytAllCourseToCourseInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryStudentLastPeriodCourse")
    public List queryStudentLastPeriodCourse(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询最新期次课程。url:/queryStudentLastPeriodCourse");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.queryLastPCourseInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryHomeWorkAll")
    public List queryHomeWorkAll(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询所有作业。url:/queryHomeWorkAll");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.querytAllHomeworkInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryStudentLastPeriodHomeWork")
    public List queryStudentLastPeriodHomeWork(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询最新期次作业。url:/queryStudentLastPeriodHomeWork");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.queryLastPHomeworkInfo(sfcStudentPermissionDto);

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryStudentCompleteHomeWork")
    public List queryStudentCompleteHomeWork(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, BindingResult result) {
        log.info("查询学生自己已经完成的作业。url:/queryStudentCompleteHomeWork");

        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        //进入service进行逻辑处理
        List list = sfcStudentPermissionService.queryStudentCompleteHomeworkInfo(sfcStudentPermissionDto);

        return list;
    }

    /**
     * 学生权限，学生上传作业
     *
     * @param sfcStudentPermissionDto
     * @return List<SfcStudentHomeworkInfoModel>
     */
    @ResponseBody
    @RequestMapping("/studentUploadFileHomework")
    public Integer studentUploadFileHomework(@Validated SfcStudentPermissionDto sfcStudentPermissionDto, @RequestParam("homewrokFile") MultipartFile file,BindingResult result) {
        log.info("学生上传作业。url:/queryStudentCompleteHomeWork");
        Preconditions.checkNotNull(file, "文件为空");
        //判断身份
        if(Objects.equals(sfcStudentPermissionDto.getPermissions(), SfcConstants.MANAGER_PERMISSIONS) ||
                Objects.equals(sfcStudentPermissionDto.getPermissions(), SfcConstants.ROOT_PERMISSIONS)) {
            throw new SfcExtException("您是管理员，无法上传作业");
        }
        //判断传参是否有错误
        checkSfcUserLoginArgs(sfcStudentPermissionDto);

        int res = sfcStudentPermissionService.uploadFileHomework(sfcStudentPermissionDto, file);

        return res;
    }

    /**
     * 检验传参
     * 学生权限，检查登录状态
     * @param sfcStudentPermissionDto
     * @return
     */
    private void checkSfcUserLoginArgs(SfcStudentPermissionDto sfcStudentPermissionDto) {
        Preconditions.checkNotNull(sfcStudentPermissionDto.getQtalk(), "参数:学生qtalk错误");
        Preconditions.checkNotNull(sfcStudentPermissionDto.getPermissions(), "参数：权限错误");
        Preconditions.checkNotNull(sfcStudentPermissionDto.getPeriodId(), "参数:学生期次错误");

        if(Objects.equals(sfcStudentPermissionDto.getQtalk(), SfcConstants.QTALK_VALUE_IS_NULL)) {
            throw new SfcExtException("errorQtalk");
        }
        if(Objects.equals(sfcStudentPermissionDto.getPermissions(), SfcConstants.STUDENT_PERMISSIONS)) {

        } else if(Objects.equals(sfcStudentPermissionDto.getPermissions(), SfcConstants.MANAGER_PERMISSIONS)) {

        } else if(!Objects.equals(sfcStudentPermissionDto.getPermissions(), SfcConstants.ROOT_PERMISSIONS)) {
            throw new SfcExtException("errorPermission");
        }
    }
}
