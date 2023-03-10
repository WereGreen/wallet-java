package com.wsz.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
public class UserBalanceTake implements Serializable {

    private static final long serialVersionUID = 1L;    //记录ID

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private LocalDateTime takeDate;                     //记录时间

    private String operate;                             //操作具体金额

    private Double surplus;                             //操作后用户余额

    private String username;                            //用户名


}
