package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

//微信回调
@Controller
@RequestMapping("wx")
public class WXController {

	String sToken = "GQYTr5fpwFbqYFALPUKiTz";
	String sCorpID = "wx46be78f8feff44a2";
	String sEncodingAESKey = "MqzuMQOCVEJiBVlFgi3RBreb46FcHKiRRqh9NNt8qtt";

	WXBizMsgCrypt wxcpt = null;
	private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
	public WXController(){
		try {
			wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("url")
	public String verifyURL(String msg_signature, String timestamp, String nonce, String echostr){
		String res = null;
		try {
			res = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug(res);
		return res;
	}
}
