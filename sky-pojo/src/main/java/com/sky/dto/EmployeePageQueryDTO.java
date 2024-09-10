package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Aganippe
 * @version v1.0
 */
@Data
@ApiModel(description = "员工分页查询数据模型")
public class EmployeePageQueryDTO implements Serializable {

    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页大小")
    private Integer pageSize;

}
