package io.zhiller.gpt.application;

public interface IWeiXinValidateService {
  boolean checkSign(String signature, String timestamp, String nonce);

}
