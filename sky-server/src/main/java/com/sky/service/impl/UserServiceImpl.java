package com.sky.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.hutool.core.util.StrUtil;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.service.UserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author Aip
 * @Date 2024/12/18   15:32
 * @Version 1.0
 * @Description C端用户服务实现类
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WxMaService wxMaService;

    /**
     * 微信登录
     *
     * @param userLoginDTO
     * @return com.sky.entity.User
     * @author Aip
     */
    @Override
    public User wechatLogin(UserLoginDTO userLoginDTO) throws WxErrorException {
        // 获取当前用户的openid
        String openid = wxMaService.getUserService().getSessionInfo(userLoginDTO.getCode()).getOpenid();
        // 判空openid
        if (StrUtil.isEmpty(openid)) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        // 根据openid查询用户信息，判断是否为新用户，如果是就自动完成注册
        User user = userMapper.getUserByOpenId(openid);
        if (user == null) {
            user = user.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        WxMaConfigHolder.remove();
        return user;
    }
}
