package com.example.bike.service;

import com.alibaba.fastjson.JSONObject;
import com.example.bike.dto.User;
import com.github.qcloudsms.SmsSingleSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;


@Transactional
@Service
public class UserServiceImpl implements UserService {

    //操作redis中的字符串类型数据
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User getUserByOpenid(String openid) {
        return mongoTemplate.findById(openid, User.class);
    }

    @Override
    public void register(User user) {
        mongoTemplate.save(user);
        //mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())), new Update().set("name", user.getName()).set("idNum", user.getIdNum()),"user");
    }

    /**
     * 校验手机验证码并保存用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean verify(User user) {
        boolean flag = false;
        String phoneNum = user.getPhoneNum();
        String verifyCode = user.getVerifyCode();
        String code = stringRedisTemplate.opsForValue().get(phoneNum);
        if (verifyCode != null && verifyCode.equals(code)) {
            mongoTemplate.save(user);
            flag = true;
        }
        return flag;
    }

    /**
     * 调用腾讯云api获取短信验证码发送并保存到redis中
     *
     * @param nationCode
     * @param phoneNum
     * @throws Exception
     */
    @Override
    public void genVerifyCode(String nationCode, String phoneNum) throws Exception {
        String appkey = stringRedisTemplate.opsForValue().get("appkey");
        //redisTemplate.
        //调用腾讯云的短信接口（短信的appid， 短信的appkey）
        SmsSingleSender singleSender = new SmsSingleSender(1400228097, appkey);
        //普通单发
        String code = (int) ((Math.random() * 9 + 1) * 1000) + "";
        //调用发送短信功能
        //SmsSingleSenderResult result = singleSender.send(0, nationCode, phoneNum, "您的登录验证码为" + code, "", "");
        //System.out.println(result);
        //将数据保存到redis中，redis的key手机号，value是验证码，有效时长120秒
        stringRedisTemplate.opsForValue().set(phoneNum, code, 5 * 60, TimeUnit.SECONDS);
    }


    /**
     * 保存充值初始额信息
     *
     * @param user
     * @return
     */
    @Override
    public void deposit(User user) {
        mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())), new Update().set("status", user.getStatus()).set("deposit", 299), User.class);
    }

    /**
     * 录入身份信息
     *
     * @param user
     * @return
     */
    @Override
    public void identify(User user) {
        mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())), new Update().set("status", user.getStatus()).set("name", user.getName()).set("idNum", user.getIdNum()), User.class);
    }

    /**
     * 支付，增加余额
     * @param params
     * @return
     */
    @Override
    public User recharge(String params) {
        User user = null;
        Map<String, Object> map = JSONObject.parseObject(params, Map.class);
        String phoneNum = (String) map.get("phoneNum");
        Object money = map.get("payMoney");
        Double payMoney = Double.parseDouble(money + "");
        //跟新用户的余额
        try {
            mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(phoneNum)), new Update().inc("balance", payMoney), User.class);
            user = mongoTemplate.findOne(new Query(Criteria.where("phoneNum").is(phoneNum)), User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
