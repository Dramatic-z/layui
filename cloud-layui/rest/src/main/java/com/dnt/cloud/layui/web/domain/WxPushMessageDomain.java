package com.dnt.cloud.layui.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dramatic
 */
public class WxPushMessageDomain {

	/**
	 * 小程序的原始ID
	 */
	@JsonProperty("ToUserName")
	private String ToUserName;

	/**
	 * 发送者的openid
	 */
	@JsonProperty("FromUserName")
	private String FromUserName;

	/**
	 * 消息创建时间(整型）
	 */
	@JsonProperty("CreateTime")
	private long CreateTime;

	/**
	 * text
	 */
	@JsonProperty("MsgType")
	private String MsgType;

	/**
	 * 文本消息内容
	 */
	@JsonProperty("Content")
	private String Content;

	/**
	 * 消息id，64位整型
	 */
	@JsonProperty("MsgId")
	private long MsgId;
	
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	
	
}
