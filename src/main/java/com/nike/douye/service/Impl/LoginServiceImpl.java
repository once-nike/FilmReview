package com.nike.douye.service.Impl;

import com.nike.douye.Enum.Code;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.mapper.LoginMapper;
import com.nike.douye.service.LoginService;
import com.nike.douye.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public String login(UserDTO user) {
        if(StringUtils.isBlank(user.getUserName())){
            throw new BaseException("请输入用户名或手机号",Code.PARAM_MISSING.getValue());
        }else if(StringUtils.isBlank(user.getPassword())){
            throw new BaseException("请输入密码",Code.PARAM_MISSING.getValue());
        }
        UserDTO userDTO = loginMapper.queryUserByUserNameOrPhone(user.getUserName());
        if(userDTO==null){
            throw new BaseException("用户名不正确", Code.PARAM_ERROR.getValue());
        }else if(!userDTO.getPassword().equals(user.getPassword())){
            throw new BaseException("密码不正确", Code.PARAM_ERROR.getValue());
        }
        String token = JwtUtil.createJWT(1000 * 60, userDTO);
//        loginMapper.insertAccessToken(userDTO.getId(),token);
        return token;
    }



    @Test
    public void test(){
        Jedis jedis = new Jedis();
        jedis.flushAll();
        String key = null;
        int orderId = 1;
        for(;orderId<=3;orderId++){
            key = "order:"+orderId;
            Map order = new HashMap();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date =new Date();
            String time = simpleDateFormat.format(date);
            order.put("orderId",String.valueOf(orderId));
            order.put("money",String.valueOf(36.0));
            order.put("time",time);
            jedis.hmset(key,order);
            jedis.rpush("orderIdList",key);
        }

        //第四个订单
        Map order = new HashMap();
        key = "order:"+orderId;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date =new Date();
        String time = simpleDateFormat.format(date);
        order.put("orderId",String.valueOf(orderId));
        order.put("money",String.valueOf(100.0));
        order.put("time",time);
        jedis.hmset(key,order);
        jedis.lpush("orderIdList",key);

        List<String> orderIdList = jedis.lrange("orderIdList", 0, 10);
        for(String singleOrderId :orderIdList){
            List<String> orderDitals = jedis.hmget(singleOrderId,"orderId","money","time");
            System.out.println("订单"+orderDitals.get(0)+"的信息：");
            int num = 0;
            for(String orderDital:orderDitals){
                System.out.print(orderDital+"    ");
                num++;
                if(num == 3){
                    System.out.println();
                }
            }
        }
    }
}
