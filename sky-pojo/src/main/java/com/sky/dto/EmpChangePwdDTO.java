package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/13  15:12
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("员工修改密码提交的数据格式")
public class EmpChangePwdDTO {

    @ApiModelProperty("员工id")
    private Integer empId;

    @ApiModelProperty("旧密码")
    private String oldPassword;

    @ApiModelProperty("新密码")
    private String newPassword;
}
