package com.sky.service.impl;

import com.sky.constant.AddressConstant;
import com.sky.context.BaseContext;
import com.sky.dto.AddressDTO;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressMapper;
import com.sky.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/18   16:15
 * @Version 1.0
 * @Description 收货地址服务接口实现类
 */

@Slf4j
@Service
public class AddressBookServiceImpl implements AddressBookService {
    // TODO 优化方向 ---> 像地址信息这些量少且几乎不会改变的数据，可以存入缓存中

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 新增地址
     *
     * @param addressBook 地址对象
     */
    @Override
    public void addAddress(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(AddressConstant.NOT_DEFAULT);
        log.info("AddressBookServiceImpl.addAddress():::{}", addressBook);
        addressMapper.insertAddress(addressBook);
    }

    /**
     * 查询当前用户所有地址
     *
     * @return java.util.List<com.sky.entity.AddressBook>
     */
    @Override
    public List<AddressBook> queryAllUsingAddress() {
        Long userId = BaseContext.getCurrentId();
        log.info("AddressBookServiceImpl.queryAllUsingAddress():::当前是{}号用户", userId);
        return addressMapper.selectAllUsingAddressByUserID(userId);
    }

    /**
     * 查询默认地址
     *
     * @return com.sky.entity.AddressBook
     */
    @Override
    public AddressBook queryDefaultAddress() {
        log.info("AddressBookServiceImpl.queryDefaultAddress():::查询默认地址");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUserId(BaseContext.getCurrentId());
        addressDTO.setIsDefault(AddressConstant.DEFAULT);
        return addressMapper.queryDefaultAddressOfTheCurrentUser(addressDTO);
    }

    /**
     * 设置默认地址
     *
     * @param newAddress 新的默认地址
     * @return com.sky.entity.AddressBook
     * @author Aip
     */
    @Override
    public void setDefaultAddress(AddressBook newAddress) {
        // 找到旧的默认地址，将其更新为非默认地址
        AddressDTO addressDTO = AddressDTO.builder()
                .userId(BaseContext.getCurrentId())
                .isDefault(AddressConstant.DEFAULT)
                .build();
        AddressBook OldAddress = addressMapper.queryDefaultAddressOfTheCurrentUser(addressDTO);
        OldAddress.setIsDefault(AddressConstant.NOT_DEFAULT);
        addressMapper.updateAddress(OldAddress);

        // 设置新的默认地址
        newAddress.setIsDefault(AddressConstant.DEFAULT);
        addressMapper.updateAddress(newAddress);
    }

    /**
     * 修改地址
     * @param addressBook 地址对象
     */
    @Override
    public void updateAddress(AddressBook addressBook) {
        log.info("AddressBookServiceImpl.updateAddress():::即将更新的地址是{}", addressBook);
        addressMapper.updateAddress(addressBook);
    }

    @Override
    public AddressBook queryAddressById(Long addressID) {
        log.info("AddressBookServiceImpl.queryAddressById():::即将查询的地址ID是{}", addressID);
        return addressMapper.queryAddressByAddreID(addressID);
    }

    @Override
    public void deleteAddressByID(Long addressID) {
        log.info("AddressBookServiceImpl.deleteAddressByID():::即将删除的地址ID是{}", addressID);
        addressMapper.deleteAddressByAddressID(addressID);
    }
}
