package com.wsz.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsz
 * @since 2023-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserWallet implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;    //用户名

    private Double balance;     //用户余额


}
