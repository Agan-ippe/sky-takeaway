package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Aganippe
 * @version v1.0
 */
@Data
@ApiModel("菜品种类分页查询提交的数据格式")
public class CategoryPageQueryDTO implements Serializable {

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("分类类型: 1菜品分类、2套餐分类")
    private Integer type;

}
