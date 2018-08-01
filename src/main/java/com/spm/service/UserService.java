package com.spm.service;

import com.spm.model.User;

import java.util.List;

public interface UserService {
    /**
     * 获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    User get(long id);

    /**
     * 根据条件获取用户列表
     * @param param 查询条件
     * @return 用户列表
     */
    List<User> getList(User param);

    /**
     * 分页获取用户列表
     * @param pageIndex 当前页码
     * @param pageSize 每页显示条数
     * @param param 查询条件
     * @return 用户列表
     */
    List<User> getPageList(int pageIndex, int pageSize, User param);

    /**
     * 添加用户信息
     * @param data 用户信息
     * @return 用户id
     */
    long add(User data);

    /**
     * 修改用户信息
     * @param data 用户信息
     * @return 修改后用户信息
     */
    User updateById(User data);

    /**
     * 根据id删除用户信息
     * @param id 用户id
     * @return 影响行数
     */
    int removeById(long id);

    /**
     * 登录
     * @param data
     * @return
     */
    User login(User data);
}
