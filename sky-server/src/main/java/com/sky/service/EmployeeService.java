package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;

/**
 * 员工相关业务接口
 * @author Aganippe
 * @version v1.0
 * @date 2024/9/3
 */
public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO 员工操作的数据模型
     * @return
     */
    void add(EmployeeDTO employeeDTO);

}
