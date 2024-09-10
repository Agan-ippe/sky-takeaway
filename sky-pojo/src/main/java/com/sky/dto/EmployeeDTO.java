package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel("员工操作的数据模型")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    @ApiModelProperty(value = "员工id")
    private Long id;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "职务名", required = true)
    private String name;

    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份证号", required = true)
    private String idNumber;

    public EmployeeDTO(String username, String name, String phone, String sex, String idNumber) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.idNumber = idNumber;
    }
}
