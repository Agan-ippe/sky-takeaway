package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/18   16:13
 * @Version 1.0
 * @Description 收货地址服务接口
 */
public interface AddressBookService {

    /**
     * 新增地址
     * @param addressBook 地址对象
     */
    void addAddress(AddressBook addressBook);

    /**
     * 查询当前用户的所有地址
     * @return java.util.List<com.sky.entity.AddressBook>
     */
    List<AddressBook> queryAllUsingAddress();

    /**
     * 查询当前用户的默认地址
     * @return com.sky.entity.AddressBook
     */
    AddressBook queryDefaultAddress();


    /**
     * 设置默认地址
     * @author Aip
     * @param newAddress 新的默认地址
     */
    void setDefaultAddress(AddressBook newAddress);

    /**
     * 修改地址
     * @param addressBook 地址对象
     */
    void updateAddress(AddressBook addressBook);

    /**
     * 通过ID查询地址，修改地址的前置功能
     * @param addressID 地址ID
     * @return com.sky.entity.AddressBook
     */
    AddressBook queryAddressById(Long addressID);


    /**
     * 通过ID删除地址
     * @param addressID 地址ID
     */
    void deleteAddressByID(Long addressID);
}
