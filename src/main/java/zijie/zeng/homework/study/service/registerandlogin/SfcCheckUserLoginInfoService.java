package zijie.zeng.homework.study.service.registerandlogin;

import zijie.zeng.homework.study.dto.SfcCheckUserLoginInfoDto;

import java.util.Map;

public interface SfcCheckUserLoginInfoService {
    /**
     * 登录系统，判断是否系统中
     * 最新其次
     * @param sfcCheckUserLoginInfoDto
     * @return int
     */
    Map queryOneUserInfo(SfcCheckUserLoginInfoDto sfcCheckUserLoginInfoDto);
}
