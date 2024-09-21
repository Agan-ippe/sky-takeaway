package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/10  17:57
 * @description 负责菜品分类表相关的数据操作
 */
@Mapper
public interface CategoryMapper {

    /**
     * 新增菜品类型
     * @param category
     */

    @Insert("INSERT INTO t_category(`name`, `type`, sort, status, create_time, update_time, create_user, update_user) " +
            "VALUES " +
            "(#{name}, #{type}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insertCategory(Category category);

    /**
     * 分页查询菜品分类
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> queryCategoryPage(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除菜品分类
     * @param id ID
     */
    @Delete("DELETE FROM t_category WHERE id = #{id}")
    void deleteCategoryByID(Long id);

    /**
     * 根据id修改菜品分类
     * @param category
     */
    @AutoFill(value = OperationType.UPDATE)
    void updateCategoryByID(Category category);

    /**
     * 根据id修改当前分类状态
     */
    @AutoFill(value = OperationType.UPDATE)
    void updateStatusByID(Category category);

    /**
     * 根据type查询菜品分类
     * @param type 类型：1 菜品分类 2 套餐分类
     */
    List<Category> listCategoryByType(Integer type);
}
