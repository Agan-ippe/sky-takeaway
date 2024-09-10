package com.sky.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/9  10:13
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("查询员工返回的数据格式")
public class QueryEmpVO {

    @ApiModelProperty("员工id")
    private Long id;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身份证号")
    private String idNumber;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("更新人")
    private Long updateUser;
}
