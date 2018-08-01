package com.spm.api;

import com.alibaba.fastjson.JSON;
import com.spm.common.*;
import com.spm.model.User;
import com.spm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    LoginContext loginContext;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultWarp<User> get(@PathVariable(value = "id") Long id) {
        LoginInfo info = loginContext.getInfo();
        User data = userService.get(id);
        if (data != null && id > 0) {
            return new ResultWarp<>(1, "获取成功", data);
        }
        return new ResultWarp<>(0, "暂无记录", null);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ListResult<List<User>> getList(User user) {
        List<User> list = userService.getList(user);
        if (list != null && list.size() > 0) {
            return new ListResult<>(1, "获取成功", list);
        }
        list = new ArrayList<>();
        return new ListResult<>(0, "暂无记录", list);
    }

    @AllowAnonymous
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultWarp<Long> add(@RequestBody User user) {
        if (user.getPassword() != null && !"".equals(user.getPassword())) {
            user.setPassword(MD5Util.MD5(user.getPassword()));
            long id = userService.add(user);
            if (id > 0) {
                return new ResultWarp<>(1, "添加成功", id);
            } else {
                return new ResultWarp<>(0, "添加失败", id);
            }
        }
        return new ResultWarp<>(0, "提交信息有误", 0L);
    }

    //用户登录
    @AllowAnonymous
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody User user) {
        user.setPassword(MD5Util.MD5(user.getPassword()));
        User data = userService.login(user);
        LoginInfo loginInfo = new LoginInfo();
        if (data != null) {
            BeanUtils.copyProperties(data, loginInfo);
            //设置缓存信息
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            loginContext.setAuthCookie(token, JSON.toJSONString(loginInfo), 0);
            return new Result(1, "登录成功");
        }
        return new Result(0, "登录失败");
    }
}
