package com.wsz.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wsz.entity.UserWallet;
import com.wsz.service.UserBalanceTakeService;
import com.wsz.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wsz
 * @since 2023-03-10
 */
@RestController

public class UserWalletController {

    @Autowired
    UserWalletService userWalletService;

    @Autowired
    UserBalanceTakeService userBalanceTakeService;

    @Autowired
    UserBalanceTakeController userBalanceTakeController;

    /**
     * 获取用户信息接口
     * @param username
     * @return
     */
    public UserWallet info(String username) {

        LambdaUpdateWrapper<UserWallet> wrapper = Wrappers.lambdaUpdate();

        wrapper.eq(UserWallet::getUsername,username);

        UserWallet user = userWalletService.getOne(wrapper);

        return user;
    }

    /**
     * 查询用户钱包余额信息接口
     * @param username
     * @return
     */
    public double infoBalance(String username) {

        LambdaUpdateWrapper<UserWallet> wrapper = Wrappers.lambdaUpdate();

        wrapper.eq(UserWallet::getUsername,username);

        UserWallet user = userWalletService.getOne(wrapper);

        return user.getBalance();
    }

    /**
     *用户消费接口
     * @param userWallet
     * @param money
     */
    @Transactional
    public void userPay (@RequestBody UserWallet userWallet, int money) {

        LambdaUpdateWrapper<UserWallet> updateWrapper = Wrappers.lambdaUpdate();

        double balance = userWallet.getBalance();

        if (balance - money > 0) {
            System.out.println(balance - money);

            updateWrapper.eq(UserWallet::getUsername, userWallet.getUsername());

            updateWrapper.set(UserWallet::getBalance,(balance - money) );

            userWalletService.update(null, updateWrapper);

            System.out.println("支付成功！~ 剩余余额为：" + (balance - money));

            userBalanceTakeController.saveTake(userWallet.getUsername(),"-" + money,balance - money);

        } else {
            System.out.println("余额不足");

        }

    }

    /**
     * 用户退款接口
     * @param userWallet
     * @param money
     */
    @Transactional
    public void userDrawback (@RequestBody UserWallet userWallet, int money) {
        LambdaUpdateWrapper<UserWallet> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper.eq(UserWallet::getUsername, userWallet.getUsername());
        updateWrapper.set(UserWallet::getBalance, (userWallet.getBalance() + money));

        userWalletService.update(null, updateWrapper);

        System.out.println("退款成功！~ 剩余余额为：" + (userWallet.getBalance() + money));

        userBalanceTakeController.saveTake(userWallet.getUsername(),"+" + money,userWallet.getBalance() + money);

    }

}
