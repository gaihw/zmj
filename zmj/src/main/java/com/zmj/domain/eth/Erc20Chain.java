package com.zmj.domain.eth;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.math.BigInteger;

@Data
public class Erc20Chain {

    /**
     * 钱包密码
     */
    private String password;

    /**
     * keystone中json文件中的内容
     */
    private JSONObject text;

    /**
     * url ----web3j
     */
    private String url;

    /**
     * 转入地址
     */
    private String toAddress;

    /**
     * gasPrice
     */
    private BigInteger gasPrice;

    /**
     * gasLimit
     */
    private BigInteger gasLimit;

    /**
     * 转账金额
     */
    private BigInteger amount;

    /**
     * 合约地址
     */
    private String contractAddress;
}
