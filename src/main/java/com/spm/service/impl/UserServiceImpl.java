package com.spm.service.impl;

import com.alibaba.fastjson.JSON;
import com.spm.dao.UserMapper;
import com.spm.model.User;
import com.spm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(long id) {
        User entity = userMapper.get(id);
        return entity;
    }

    @Override
    public List<User> getList(User param) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        List<User> list = userMapper.getList(map);
        return list;
    }

    @Override
    public List<User> getPageList(int pageIndex, int pageSize, User param) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        pageIndex = pageIndex > 0 ? pageIndex - 1 : 0;
        map.put("pageIndex", pageIndex * pageSize);
        map.put("pageSize", pageSize);
        List<User> list = userMapper.getPageList(map);
        return list;
    }

    @Override
    public long add(User data) {
        long id = 0l;
        if (data != null) {
            int num = userMapper.addAndId(data);
            if (num > 0) {
                id = data.getId();
            }
        }
        return id;
    }

    @Override
    public User updateById(User data) {
        User returnData = null;
        if (data != null && data.getId() != null) {
            User user = userMapper.get(data.getId());
            if (user != null) {
                BeanUtils.copyProperties(data, user);
                int num = userMapper.updateById(user);
                if (num > 0) {
                    returnData = new User();
                    BeanUtils.copyProperties(user, returnData);
                }
            }
        }
        return returnData;
    }

    @Override
    public int removeById(long id) {
        int num = 0;
        User user = userMapper.get(id);
        if (user != null) {
            num = userMapper.delete(id);
        }
        return num;
    }

    @Override
    public User login(User data) {
        User entity = userMapper.login(data);
        return entity;
    }
}
