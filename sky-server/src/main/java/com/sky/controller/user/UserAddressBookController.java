package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/17   17:43
 * @Version 1.0
 * @Description 用户端地址簿相关功能
 */

@Slf4j
@RestController
@Api(tags = "用户端地址簿相关功能")
@RequestMapping("/user/addressBook")
public class UserAddressBookController {

    @Autowired
    private AddressBookService addressService;

    /**
     * 新增收货地址
     * @author Aip
     * @param addressBook 地址簿对象
     * @return com.sky.result.Result
     */
    @PostMapping
    @ApiOperation("新增收货地址")
    public Result addAddress(@RequestBody AddressBook addressBook){
        log.info("UserAddressBookController.addAddress-->当前的地址对象是: {}",addressBook);
        addressService.addAddress(addressBook);
        return Result.success();
    }


    /**
     * 查询当前用户的所有收获地址
     * @author Aip
     * @return com.sky.result.Result<java.util.List<com.sky.entity.AddressBook>>
     */
    @GetMapping("/list")
    @ApiOperation("查询当前登录用户的所有地址信息")
    public Result<List<AddressBook>> queryAllUsingAddress(){
        log.info("UserAddressBookController.queryAllUsingAddress-->查询当前用户的所有收货地址");
        List<AddressBook> addressBookList = addressService.queryAllUsingAddress();
        return Result.success(addressBookList);
    }


    /**
     * 查询默认收货地址
     * @author Aip
     * @return com.sky.result.Result<com.sky.entity.AddressBook>
     */
    @GetMapping("/default")
    @ApiOperation("查询默认地址")
    public Result<AddressBook> queryDefaultAddress(){
        log.info("UserAddressBookController.queryDefaultAddress-->查询默认收货地址");
        AddressBook address = addressService.queryDefaultAddress();
        if (address != null) {
            return Result.success(address);
        }
        return Result.error("没有找到默认地址");
    }


    /**
     * 设置默认收货地址
     * @author Aip
     * @param addressBook 地址簿对象
     * @return com.sky.result.Result
     */
    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result updateDefaultAddress(@RequestBody AddressBook addressBook){
        log.info("UserAddressBookController.updateDefaultAddress-->设置默认收货地址: {}",addressBook);
        addressService.setDefaultAddress(addressBook);
        return Result.success();
    }


    /**
     * 根据ID查询地址，属于修改地址的附属功能
     * @author Aip
     * @param AddressID 地址id
     * @return com.sky.result.Result<com.sky.entity.AddressBook>
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址")
    public Result<AddressBook> queryAddressById(@PathVariable(value = "id") Long AddressID){
        log.info("UserAddressBookController.queryAddressById-->根据id查询地址: {}",AddressID);
        AddressBook address = addressService.queryAddressById(AddressID);
        return Result.success(address);
    }


    /**
     * 根据ID修改地址
     * @author Aip
     * @param addressBook 地址簿对象
     * @return com.sky.result.Result
     */
    @PutMapping
    @ApiOperation("根据id修改地址")
    public Result updateAddressByID(@RequestBody AddressBook addressBook){
        log.info("UserAddressBookController.updateAddressByID-->根据id修改地址: {}",addressBook);
        addressService.updateAddress(addressBook);
        return Result.success();
    }


    /**
     * 根据ID删除地址
     * @author Aip
     * @param id 地址ID
     * @return com.sky.result.Result
     */
    @DeleteMapping
    @ApiOperation("根据id删除地址")
    public Result deleteAddressByID(Long id){
        log.info("UserAddressBookController.deleteAddressByID-->根据id删除地址: {}",id);
        addressService.deleteAddressByID(id);
        return Result.success();
    }
}
