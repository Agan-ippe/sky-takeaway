package com.sky.service;

import com.sky.dto.EmpChangePwdDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.vo.QueryEmpVO;

/**
 * 员工相关业务接口
 * @author Aganippe
 * @version v1.0
 * @date 2024/9/3
 */
public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO 员工登录的数据模型
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO 员工操作的数据模型
     */
    void add(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工
     * @param employeePageQueryDTO 员工分页查询的数据模型
     * @return 分页结果
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工账号状态
     * @param id 员工ID
     * @param status 账号状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 根据ID查询员工
     * @param id id
     * @return 员工信息
     */
    QueryEmpVO getEmpByID(Long id);

    /**
     * 修改员工信息
     * @param employee 员工信息
     */
    void updateEmp(EmployeeDTO employee);

    /**
     * 修改密码
     * @param empChangePwdDTO
     */
    void changePassword(EmpChangePwdDTO empChangePwdDTO);
}
