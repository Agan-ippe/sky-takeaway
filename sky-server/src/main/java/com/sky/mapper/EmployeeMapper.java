package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmpChangePwdDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.vo.QueryEmpVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from `t_emp` where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 添加员工
     * @param employee
     */
    @Insert("insert into `t_emp` (username, `name`,password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "values " +
            "(#{username}, #{name}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void addUser(Employee employee);

    /**
     * 查询所有员工
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据id修改员工账号
     * @param employee emp
     */
    void updateStatus(Employee employee);

    @Select("select " +
            "`id`, `name`, `username`, `phone`, `sex`, `id_number`, `status`, `create_time`, `update_time`, `create_user`, `update_user` " +
            "from `t_emp` where id = #{id}")
    QueryEmpVO queryEmpByID(Long id);

    /**
     * 更新员工信息
     * @param emp 员工实体类
     */
    void updateUser(Employee emp);


    /**
     * 修改密码
     * @param empChangePwdDTO
     */
    void changePassword(EmpChangePwdDTO empChangePwdDTO);

    /**
     * 根据id查询密码
     * @param id
     * @return 返回用户密码
     */
    @Select("select password from t_emp where id = #{id}")
    String getPassword(Long id);
}
