package com.sky.mapper;

import com.sky.dto.AddressDTO;
import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/18   16:35
 * @Version 1.0
 * @Description
 */
@Mapper
public interface AddressMapper {


    /**
     * 新增地址
     * @param addressBook 地址对象
     */
    @Insert("INSERT INTO t_address (user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default) " +
            "VALUES (#{userId}, #{consignee}, #{sex}, #{phone}, #{provinceCode}, #{provinceName}, #{cityCode}, #{cityName}, #{districtCode}, #{districtName}, #{detail}, #{label}, #{isDefault})")
    void insertAddress(AddressBook addressBook);


    /**
     * 查询当前登录用户的所有地址
     * @param userId 当前登录用户id
     * @return java.util.List<com.sky.entity.AddressBook>
     */
    @Select("SELECT " +
            "id," +
            "consignee," +
            "sex," +
            "phone," +
            "province_name," +
            "city_name," +
            "district_name," +
            "detail," +
            "label, " +
            "is_default FROM t_address WHERE user_id = #{userId}")
    List<AddressBook> selectAllUsingAddressByUserID(Long userId);

    /**
     * 查询当前用户的地址
     * @param addressDTO 地址数据模型
     * @return com.sky.dto.AddressDTO
     */
    AddressBook queryDefaultAddressOfTheCurrentUser(AddressDTO addressDTO);

    /**
     * 修改地址
     * @param addressBook 地址对象
     */
    void updateAddress(AddressBook addressBook);

    /**
     * 根据地址id查询地址
     * @author Aip
     * @param addressID 地址id
     * @return com.sky.entity.AddressBook
     */
    @Select("SELECT " +
            "id, " +
            "consignee, " +
            "sex, " +
            "phone, " +
            "province_name, " +
            "city_name, " +
            "district_name, " +
            "detail, " +
            "label " +
            "FROM t_address WHERE id = #{addressID}")
    AddressBook queryAddressByAddreID(Long addressID);

    @Delete("DELETE FROM t_address WHERE id = #{addressID}")
    void deleteAddressByAddressID(Long addressID);
}
