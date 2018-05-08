package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value="添加用户信息", notes="根据页面信息进行注册用户")
	@ResponseBody
    @RequestMapping(value = "/add", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
    }

	@ApiOperation(value="获取全部用户", notes="")
    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
    	return userService.findAllUser(pageNum,pageSize);
    }
	
}
