package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * @Author Aip
  * @Date 2025/01/19   12:42
  * @Version 1.0
  * @Description 地址数据模型
  */
@Data
@Builder
@ApiModel("地址DTO")
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @ApiModelProperty(value = "地址ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "是否是默认地址，0为否，1为是")
    private Integer isDefault;


}
