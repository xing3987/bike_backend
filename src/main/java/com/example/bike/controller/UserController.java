package com.example.bike.controller;

import com.example.bike.dto.User;
import com.example.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/host")
    public String checkBike() throws UnknownHostException {
        String hostName = InetAddress.getLocalHost().getHostName();
        return hostName;
    }

    @GetMapping("/phoneNum/{openid}")
    @ResponseBody
    public User getPhoneNum(@PathVariable("openid") String openid) {
        User user = userService.getUserByOpenid(openid);
        return user;
    }

    @PostMapping("/genCode")
    @ResponseBody
    public String genCode(String nationCode, String phoneNum) {
        String msg = "true";
        try {
            //生成4位随机数 -> 调用短信接口发送验证码 -> 将手机号对应的验证码保存到redis中，并且设置这个key的有效时长
            userService.genVerifyCode(nationCode, phoneNum);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "false";
        }
        return msg;
    }

    /**
     * 校验手机验证码并保存用户
     * @param user
     * @return
     */
    @PostMapping("/verify")
    @ResponseBody
    public boolean verify(User user) {
        boolean flag = userService.verify(user);
        return flag;
    }

    /**
     * 保存充值初始额信息
     * @param user
     * @return
     */
    @PostMapping("/deposit")
    @ResponseBody
    public String deposit(User user) {
        userService.deposit(user);
        return "success";
    }

    /**
     * 录入身份信息
     * @param user
     * @return
     */
    @PostMapping("/identify")
    @ResponseBody
    public String identify(User user) {
        userService.identify(user);
        return "success";
    }

    /**
     * 支付，增加余额
     * @return
     */
    @PostMapping("/recharge")
    @ResponseBody
    public Double recharge(@RequestBody String params) {
        System.out.println(params);
        User user = userService.recharge(params);
        return user.getBalance();
    }

    /*@PostMapping("/reg")
    @ResponseBody
    public String register(@RequestBody User user) {
        System.out.println(user);
        userService.register(user);
        return "success";
    }*/
}
