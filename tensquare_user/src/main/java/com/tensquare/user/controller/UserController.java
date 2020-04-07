package com.tensquare.user.controller;

import com.tensquare.user.dto.LoginDTO;
import com.tensquare.user.dto.RegisterDTO;
import com.tensquare.user.dto.SearchListDTO;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@Api(tags = "UserController", description = "用户操作")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;



    /**
     * 加入黑名单
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "加入黑名单", notes = "加入黑名单")
    @GetMapping(value = "/lock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "query")
    })
    public Result lockState(@RequestParam String userId) {
        return new Result(true, StatusCode.OK, "加入黑名单成功", userService.lockState(userId));
    }


    /**
     * 解封黑名单
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "解封", notes = "解封")
    @GetMapping(value = "/unlock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "query")
    })
    public Result unlockState(@RequestParam String userId) {
        return new Result(true, StatusCode.OK, "解封成功", userService.unlockState(userId));
    }



    /**
     * 用户登录
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginDTO param) {
        User user = userService.findByUsernameOrMobileAndPassword(param.getText(), param.getPassword());
        if (user != null) {
            String token = jwtUtil.createJWT(user.getUserId(), user.getUsername(), "user");
            Map map = new HashMap();
            map.put("token", token);
            map.put("userId", user.getUserId());
            return new Result(true, StatusCode.OK, "登录成功", map);
        } else {
            return new Result(false, StatusCode.LOGINERROR, "用户名/手机或密码错误");
        }
    }


    /**
     * 用户注册
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO param) {
        userService.add(param);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码")
    @GetMapping("/sendsms")
    public Result sendsms(@RequestParam(required = true) String mobile) {
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送成功");
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperation(value = "查询全部数据", notes = "查询全部数据")
    @GetMapping("/findAll")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    @GetMapping("/findById")
    public Result findById(@RequestParam(required = true) String id) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param param
     */
    @ApiOperation(value = "分页+多条件查询", notes = "分页+多条件查询")
    @PostMapping(value = "/searchList")
    public Result findSearch(@RequestBody SearchListDTO param) {
        if(param.getPage() == null){
            param.setPage(new Integer(1));
        }
        if(param.getSize() == null){
            param.setSize(new Integer(10));
        }
        Page<User> pageList = userService.findSearch(param);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
//    @ApiOperation(value = "根据条件查询", notes = "根据条件查询")
//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    public Result findSearch(@RequestBody Map searchMap) {
//        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
//    }

    /**
     * 增加
     *
     * @param user
     */
//    @ApiOperation(value = "增加", notes = "增加")
//    @RequestMapping(method = RequestMethod.POST)
//    public Result add(@RequestBody User user) {
//        userService.add(user);
//        return new Result(true, StatusCode.OK, "增加成功");
//    }

//    /**
//     * 修改
//     *
//     * @param user
//     */
//    @ApiOperation(value = "修改", notes = "修改")
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public Result update(@RequestBody User user, @PathVariable String id) {
//        user.setUserId(id);
//        userService.update(user);
//        return new Result(true, StatusCode.OK, "修改成功");
//    }

//    /**
//     * 删除 必须有admin角色才能删除
//     *
//     * @param id
//     */
//    @ApiOperation(value = "删除 必须有admin角色才能删除",notes = "删除 必须有admin角色才能删除")
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public Result delete(@PathVariable String id) {
//        Claims claims = (Claims) request.getAttribute("admin_claims");
//        if (claims == null) {
//            return new Result(true, StatusCode.ACCESSERROR, "无权访问");
//        }
//        userService.deleteById(id);
//        return new Result(true, StatusCode.OK, "删除成功");
//    }

}
