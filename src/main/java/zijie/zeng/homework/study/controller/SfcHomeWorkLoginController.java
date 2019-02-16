package zijie.zeng.homework.study.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zijie.zeng.homework.study.constants.SfcConstants;
import zijie.zeng.homework.study.dto.SfcCheckUserLoginInfoDto;
import zijie.zeng.homework.study.dto.SfcUserForRegesiterDto;
import zijie.zeng.homework.study.exceptions.SfcExtException;
import zijie.zeng.homework.study.service.registerandlogin.SfcCheckUserLoginInfoService;
import zijie.zeng.homework.study.service.registerandlogin.SfcHomeWorkLoginService;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class SfcHomeWorkLoginController {

    //登录系统
    @Autowired
    private SfcCheckUserLoginInfoService sfcCheckUserLoginInfoService;

    //注册系统
    @Autowired
    private SfcHomeWorkLoginService sfcHomeWorkLoginService;

    //用于登录后再次登录系统时使用
    @RequestMapping("/studentindex")
    public String studentHomeWorkIndex() {
        log.info("访问学生学习系统。url:/studentindex");
        return ("/html/studentindex.html");
    }

    @RequestMapping("/tutorindex")
    public String tutorindex() {
        log.info("访问学生学习系统。url:/tutorindex");
        return ("/html/tutorindex.html");
    }

    //登录界面
    @RequestMapping("/login")
    public String loginStudentStudy() {
        log.info("登录学生学习系统学生权限。url:/login");
        return "html/Login.html";
    }

    //用于密码和账户的确认,学生权限
    @ResponseBody
    @RequestMapping("/CheckUserLoginInfo")
    public Map checkUserLoginInfo(@Validated SfcCheckUserLoginInfoDto sfcCheckUserLoginInfoDto, BindingResult result) {
        log.info("登录系统，用于确认身份。url:/CheckUserLoginInfo");

        //检查传参是否正确
        checkSfcUserForRegesiterArgs(sfcCheckUserLoginInfoDto);

        //进行逻辑判断
        Map map = sfcCheckUserLoginInfoService.queryOneUserInfo(sfcCheckUserLoginInfoDto);
        return map;
    }

    //注册用户，只能学生注册，老师需要管理员添加
    @RequestMapping("/RegisterStudentInfoPage")
    public String registerStudentStudy() {
        log.info("进入注册界面。url:/RegisterStudentInfoPage");
        return "/html/RegisterStudentInfoPage.html";
    }

    //注册信息填入数据库
    @RequestMapping("/insertUserForRegesiter")
    @ResponseBody
    public Map insertUserForRegesiterToStudentInfo(@Validated SfcUserForRegesiterDto sfcUserForRegesiterDto, BindingResult result) {
        log.info("用户注册信息处理。url:/insertUserForRegesiter");
        Map map = Maps.newHashMap();

        //传过来的值中，qtalk、手机号、密码不能为空
        checkSfcUserForRegesiterArgs(sfcUserForRegesiterDto);

        int res = sfcHomeWorkLoginService.insertUserForRegesiterToStudentInfo(sfcUserForRegesiterDto);
        map.put("res", res);
        return map;
    }

    /**
     * 检验传参
     * 注册时检验传参
     * @param userForRegesiterDto
     * @return
     */
    private void checkSfcUserForRegesiterArgs(SfcUserForRegesiterDto userForRegesiterDto) {
        Preconditions.checkNotNull(userForRegesiterDto.getStudentQtalk(), "参数:学生qtalk错误");
        Preconditions.checkNotNull(userForRegesiterDto.getStudentPassword(), "参数:学生密码不能为空");
        Preconditions.checkNotNull(userForRegesiterDto.getStudentPhone(), "参数:学生手机为空");

        if(Objects.equals(userForRegesiterDto.getStudentQtalk(), SfcConstants.QTALK_VALUE_IS_NULL)) {
            throw new SfcExtException("qtalk值为空");
        }
        if(Objects.equals(userForRegesiterDto.getStudentPhone(), SfcConstants.PHONE_VALUE_IS_NULL)) {
            throw new SfcExtException("手机号为空");
        }
        if(Objects.equals(userForRegesiterDto.getStudentPassword(), SfcConstants.PASSWORD_VALUE_IS_NULL)) {
            throw new SfcExtException("密码为空");
        }
    }

    /**
     * 检验传参
     * 登录时检验传参
     * @param sfcCheckUserLoginInfoDto
     * @return
     */
    private void checkSfcUserForRegesiterArgs(SfcCheckUserLoginInfoDto sfcCheckUserLoginInfoDto) {
        Preconditions.checkNotNull(sfcCheckUserLoginInfoDto.getQtalk(), "参数:学生qtalk错误");
        Preconditions.checkNotNull(sfcCheckUserLoginInfoDto.getPassword(), "参数:学生密码不能为空");

        if(Objects.equals(sfcCheckUserLoginInfoDto.getQtalk(), SfcConstants.QTALK_VALUE_IS_NULL)) {
            throw new SfcExtException("qtalk值为空");
        }
        if(Objects.equals(sfcCheckUserLoginInfoDto.getPassword(), SfcConstants.PASSWORD_VALUE_IS_NULL)) {
            throw new SfcExtException("密码为空");
        }
    }
}
