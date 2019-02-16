package zijie.zeng.homework.study.service.registerandlogin;

import zijie.zeng.homework.study.dto.SfcUserForRegesiterDto;

public interface SfcHomeWorkLoginService {
    /**
     * 注册信息填入数据库，将学生注册信息填入数据库中
     *
     * @param sfcUserForRegesiterDto
     * @return int
     */
    int insertUserForRegesiterToStudentInfo(SfcUserForRegesiterDto sfcUserForRegesiterDto);
}
