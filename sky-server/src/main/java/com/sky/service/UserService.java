package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @Author Aip
 * @Date 2024/12/18   15:32
 * @Version 1.0
 * @Description 用户服务接口
 */

public interface UserService {

    /**
     * 用户登录，(微信登录)
     * @param userLoginDTO
     * @return sky.entity.User
     */
    User wechatLogin(UserLoginDTO userLoginDTO) throws WxErrorException;
}
