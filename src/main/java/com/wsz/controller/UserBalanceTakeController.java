package com.wsz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsz.entity.UserBalanceTake;
import com.wsz.entity.UserWallet;
import com.wsz.service.UserBalanceTakeService;
import com.wsz.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wsz
 * @since 2023-03-10
 */
@RestController
@ComponentScan(basePackages = {"com.wsz"})
public class UserBalanceTakeController {

    @Autowired
    UserWalletService userWalletService;

    @Autowired
    UserBalanceTakeService userBalanceTakeService;

    /**
     * 获取用户钱包金额变动明细的接口
     * @param userWallet
     * @return
     */
    public List<UserBalanceTake> getTake(UserWallet userWallet) {

        QueryWrapper<UserBalanceTake> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("username", userWallet.getUsername());

        List<UserBalanceTake> userBalanceTakeList = userBalanceTakeService.list(queryWrapper);

        return userBalanceTakeList;

    }

    /**
     * 向表中添加钱包金额变动明细的接口
     * @param username
     * @param operate
     * @param surplus
     */
    public void saveTake(String username, String operate, double surplus) {

        UserBalanceTake userBalanceTake = new UserBalanceTake();

        LocalDateTime dateTime = LocalDateTime.now();

        userBalanceTake.setUsername(username);
        userBalanceTake.setOperate(operate);
        userBalanceTake.setSurplus(surplus);
        userBalanceTake.setTakeDate(dateTime);

        userBalanceTakeService.save(userBalanceTake);
    }

}
