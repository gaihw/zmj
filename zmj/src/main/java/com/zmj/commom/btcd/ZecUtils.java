package com.zmj.commom.btcd;

import com.alibaba.fastjson.JSONObject;
import com.zmj.config.Config;
import com.zmj.commom.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Base64;

@Component
@Slf4j
public class ZecUtils {

    @Autowired
    private HttpUtils httpUtils;

    /**
     * 获取地址对应的未花费总金额
     * @param fromAddress
     * @param txid
     * @param vout
     * @return
     */
    public  BigDecimal getAmount(String fromAddress, String txid, String vout){
//        String listunspent = listunspent(fromAddress);
//        JSONArray jsonArray = JSONObject.parseObject(listunspent).getJSONArray("result");
//        for (int i = 0 ; i < jsonArray.size() ; i++){
//            if (jsonArray.getJSONObject(i).getString("txid").equals(txid)
//                    &&jsonArray.getJSONObject(i).getString("vout").equals(vout)){
//                return jsonArray.getJSONObject(i).getBigDecimal("amount");
//            }
//        }
//        return null;
        String gettxout = gettxout(txid,vout);
        return JSONObject.parseObject(gettxout).getJSONObject("result").getBigDecimal("value");
    }

    /**
     * 获取地址私钥
     * @param address
     * @return
     */
    public  String dumpprivkey(String address){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"1.0\", \"id\":\"curltest\", \"method\": \"dumpprivkey\", \"params\": [\""+address+"\"] }";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }
    /**
     * 根据txid和vout，获取utxo信息
     * @param txid
     * @param vout
     * @return
     */
    public  String gettxout(String txid,String vout){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"1.0\", \"id\":\"curltest\", \"method\": \"gettxout\", \"params\": [\""+txid+"\","+vout+"] }";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }
    /**
     * getpeerinfo
     * @return
     */
    public  String getpeerinfo(){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\":\"2.0\", \"id\": \"curltest\", \"method\": \"getpeerinfo\", \"params\":[]}";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }
    /**
     * listunspent
     * @return
     */
    public  String listunspent(){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\":\"2.0\", \"id\": \"curltest\", \"method\": \"listunspent\", \"params\":[]}";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }

    /**
     * getbalance
     * @return
     */
    public  String getbalance(){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\":\"2.0\", \"id\": \"curltest\", \"method\": \"getbalance\", \"params\":[]}";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }

    /**
     * 查看未花费交易
     * @param address
     * @return
     */
    public  String listunspent(String address){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"2.0\", \"id\": \"curltest\", \"method\": \"listunspent\", \"params\": [0, 99999999 ,[\""+address+"\"]]}";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }

    /**
     * 挖矿，并指定奖励的地址
     * @param generateNum
     * @param toAddress
     * @return
     */
    public  String generatetoaddress(String generateNum,String toAddress){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"2.0\", \"id\": \"curltest\", \"method\": \"generatetoaddress\", \"params\": ["+generateNum+",\""+toAddress+"\"]}";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }
    /**
     * getblockcount
     * @return
     */
    public  String getblockcount(){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\":\"2.0\", \"id\": \"curltest\", \"method\": \"getblockcount\", \"params\":[]}";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }

    /**
     * 转账 方式一 sendtoaddress
     * @param toAddress
     * @param amount
     * @return
     */
    public  String sendtoaddress(String toAddress,String amount){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"1.0\", \"id\": \"curltest\", \"method\": \"sendtoaddress\", \"params\": [\""+toAddress+"\", "+amount+", \"donation\", \"seans outpost\"]}";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }

    /**
     * 转账 方式二 创建交易&签名&广播
     */
    /**
     * 创建交易
     * @param txid
     * @param vout
     * @param toAddress
     * @param value
     * @return
     */
    public  String createrawtransaction(String txid,String vout,String toAddress,String value){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"1.0\", \"id\": \"curltest\", \"method\": \"createrawtransaction\", \"params\": [[{\"txid\":\""+txid+"\",\"vout\":"+vout+"}], {\""+toAddress+"\":\""+value+"\"}]}";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }

    /**
     * 签名交易
     * @param hex
     * @param txid
     * @param vout
     * @param scriptPubKey
     * @param amount
     * @param privatekey
     * @return
     */
    public  String signrawtransaction(String hex,String txid,String vout,String scriptPubKey,String amount,String privatekey,String redeemScript){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
//        String params = "{\"jsonrpc\": \"1.0\", \"id\": \"curltest\", \"method\": \"signrawtransactionwithkey\", \"params\": [\""+hex+"\", [\""+privatekey+"\"],[{\"txid\":\""+txid+"\",\"vout\":"+vout+",\"scriptPubKey\":\""+scriptPubKey+"\",\"amount\":"+value+"}]]}";
        String params = null;
        if ("null".equals(String.valueOf(redeemScript))){
            params = "{\"jsonrpc\": \"1.0\", \"id\": \"curltest\", \"method\": \"signrawtransaction\", \"params\": [\""+hex+"\",[{\"txid\":\""+txid+"\",\"vout\":"+vout+",\"scriptPubKey\":\""+scriptPubKey+"\",\"amount\":"+amount+"}], [\""+privatekey+"\"]]}";
        }else{
            params = "{\"jsonrpc\": \"1.0\", \"id\": \"curltest\", \"method\": \"signrawtransaction\", \"params\": [\""+hex+"\",[{\"txid\":\""+txid+"\",\"vout\":"+vout+",\"scriptPubKey\":\""+scriptPubKey+"\",\"redeemScript\":\""+redeemScript+"\",\"amount\":"+amount+"}], [\""+privatekey+"\"]]}";
        }
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }

    /**
     * 广播签名的交易
     * @param signHex
     * @return
     */
    public  String sendrawtransaction(String signHex){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"1.0\", \"id\": \"curltest\", \"method\": \"sendrawtransaction\", \"params\": [\""+signHex+"\"]}";
//        System.out.println(params);
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }

    /**
     * 解析交易信息
     * @param txid
     * @return
     */
    public  String getrawtransaction(String txid){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"1.0\", \"id\":\"curltest\", \"method\": \"getrawtransaction\", \"params\": [\""+txid+"\", true] }";
//        System.out.println(params);
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);
    }
    /**
     * 获取地址的信息
     * @param address
     * @return
     */
    public  String validateaddress(String address){
        Base64.Encoder encoder = Base64.getEncoder();
        String cred = encoder.encodeToString((Config.BTC_USERNMAME + ":" + Config.BTC_PASSWORD).getBytes());
        String params = "{\"jsonrpc\": \"1.0\", \"id\":\"curltest\", \"method\": \"validateaddress\", \"params\": [\""+address+"\"] }";
        return httpUtils.postByJson("http://"+Config.ZEC_URL+":"+Config.ZEC_PORT,cred,params);

    }
}
