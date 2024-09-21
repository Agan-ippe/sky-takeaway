package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmpChangePwdDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import com.sky.utils.StringUtil;
import com.sky.vo.QueryEmpVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //密码进行 md5 加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {

            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus().equals(StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     *
     * @param employeeDTO 员工操作的数据模型
     * @return
     */
    @Override
    public void insertEmp(EmployeeDTO employeeDTO) {
        Employee emp = new Employee();
        //将数据模型中的数据拷贝到实体对象中
        BeanUtils.copyProperties(employeeDTO, emp);

        emp.setStatus(StatusConstant.ENABLE);
        emp.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employeeMapper.addUser(emp);
    }

    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        Page<Employee> page = employeeMapper.queryEmpPage(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> result = page.getResult();
        return new PageResult(total, result);
    }

    /**
     * 修改员工账号状态
     *
     * @param id     员工ID
     * @param status 账号状态
     */
    @Override
    public void updateStatus(Long id, Integer status) {
        Employee emp = Employee.builder()
                .status(status)
                .id(id)
                .build();

        employeeMapper.updateStatus(emp);
    }

    /**
     * 根据ID查询员工信息
     *
     * @param id id
     * @return QueryEmpVO
     */
    @Override
    public QueryEmpVO getEmpByID(Long id) {
        return employeeMapper.queryEmpByID(id);
    }

    /**
     * 修改员工信息
     *
     * @param employeeDTO 员工信息
     */
    @Override
    public void updateEmp(EmployeeDTO employeeDTO) {
        Employee emp = new Employee();
        //将数据模型中的数据拷贝到实体对象中
        BeanUtils.copyProperties(employeeDTO, emp);
        employeeMapper.updateUser(emp);
    }

    /**
     * 重置密码
     * @param empChangePwdDTO
     */
    @Override
    public void changePassword(EmpChangePwdDTO empChangePwdDTO) {
        String oldPassword = empChangePwdDTO.getOldPassword();
        String password = employeeMapper.getPassword(BaseContext.getCurrentId());

        if (!password.equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            throw new PasswordErrorException(MessageConstant.OLD_PASSWORD_ERROR);
        }
        if (empChangePwdDTO.getNewPassword().equals(oldPassword)) {
            throw new PasswordErrorException(MessageConstant.NEW_PASSWORD_CANNOT_SAME_AS_OLD);
        }
        if (StringUtil.isEmpty(empChangePwdDTO.getNewPassword()) || StringUtil.isEmpty(oldPassword)) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_IS_NULL);
        }

        String newPassword = empChangePwdDTO.getNewPassword();
        //md5加密
        empChangePwdDTO.setNewPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        employeeMapper.updatePassword(empChangePwdDTO);
    }

}
