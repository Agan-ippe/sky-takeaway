package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author Aip
 * @Date 2024/12/20   17:34
 * @Version 1.0
 * @Description
 */
@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户信息
     * @author Aip
     * @param openId openid
     * @return com.sky.entity.User
     */
    @Select("SELECT * FROM  t_user WHERE openid = #{openId}")
    User getUserByOpenId(String openId);


    void insert(User user);
}
