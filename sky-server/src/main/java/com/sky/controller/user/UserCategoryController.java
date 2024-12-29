package com.sky.controller.user;

import com.sky.entity.Category;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Aip
 * @Date 2024/12/25   17:59
 * @Version 1.0
 * @Description
 */
@Slf4j
@RestController
@Api(tags = "用户端-菜品分类")
@RequestMapping("/user/category")
public class UserCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据类型查询分类
     * @author Aip
     * @param type 类型
     * @return com.sky.result.Result<java.util.List<com.sky.entity.Category>>
     */
    @GetMapping("/list")
    @ApiOperation(value = "根据类型分类查询分类")
    public Result<List<Category>> queryCategoryByType(Integer type){
        List<Category> categoryList = categoryService.listCategoryByType(type);
        return Result.success(categoryList);
    }
}
