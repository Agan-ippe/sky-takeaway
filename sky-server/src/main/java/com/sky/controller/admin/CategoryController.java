package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/16  17:52
 * @description 菜品分类相关接口
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "菜品分类相关接口")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result<PageResult> categoryByPageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询: {}",categoryPageQueryDTO);
        PageResult pageResult = categoryService.queryCategoryPage(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据类型分类查询分类")
    public Result<List<Category>> queryCategoryByType(Integer type){
        log.info("根据类型分类查询分类");
        List<Category> categoryList = categoryService.listCategoryByType(type);
        return Result.success(categoryList);
    }

    @PostMapping("/status/{status}")
    @ApiOperation(value = "修改分类状态")
    public Result changeStatus(@PathVariable Integer status, Long id){
        log.info("修改分类状态: {}, {}",status,id);
        categoryService.updateCategoryStatus(status, id);
        return Result.success();
    }

    @PostMapping
    @ApiOperation(value = "新增分类")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类: {}",categoryDTO);
        categoryService.insertCategory(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除分类")
    public Result deleteCategory(Long id){
        log.info("删除分类: {}",id);
        categoryService.deleteCategoryById(id);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "修改分类")
    public Result updateCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类: {}",categoryDTO);
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }
}
