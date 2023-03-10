package com.wsz;

import com.wsz.controller.UserBalanceTakeController;
import com.wsz.controller.UserWalletController;
import com.wsz.entity.UserBalanceTake;
import com.wsz.entity.UserWallet;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class WalletJavaApplicationTests {

    @Autowired
    private UserWalletController userWalletController;

    @Autowired
    private UserBalanceTakeController userBalanceTakeController;


    /**
     * 测试功能：
     * 1.  查询用户钱包余额
     * 2. 用户消费100元的接口
     * 3. 用户退款20元接口
     * 4. 查询用户钱包金额变动明细的接口
     */
    @Test
    void test() {

        UserWallet user;

        String username = "wsz";

        user = userWalletController.info(username);
        double balance = userWalletController.infoBalance("wsz");

        System.out.println("用户钱包余额为:" + balance);

        userWalletController.userPay(user, 100);
        System.out.println("用户消费了100元！");
        user = userWalletController.info(username);


        userWalletController.userDrawback(user, 20);
        System.out.println("用户退款20元！");

        System.out.println("查询用户钱包金额变动明细");

        System.out.println("姓名\t\t时间\t\t\t\t操作\t\t余额");

        List<UserBalanceTake> take = userBalanceTakeController.getTake(user);

        for (UserBalanceTake balanceTake : take) {
            System.out.print(balanceTake.getUsername() + "\t");
            System.out.print(balanceTake.getTakeDate() + "\t\t");
            System.out.print(balanceTake.getOperate() + "\t\t");
            System.out.print(balanceTake.getSurplus() + "\t\t");
            System.out.println();
        }

    }

}
