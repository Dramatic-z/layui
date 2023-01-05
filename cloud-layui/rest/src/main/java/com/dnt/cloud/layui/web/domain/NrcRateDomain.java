package com.dnt.cloud.layui.web.domain;

import com.dnt.cloud.common.domain.BaseDomain;

import java.util.Date;

/**
 * @author dramatic
 */
public class NrcRateDomain extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

    private String bankConversionPri;

    private String fBuyPri;

	private String fSellPri;

    private String mSellPri;

    private String mBuyPri;

    private String time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankConversionPri() {
		return bankConversionPri;
	}

	public void setBankConversionPri(String bankConversionPri) {
		this.bankConversionPri = bankConversionPri;
	}

	public String getfBuyPri() {
		return fBuyPri;
	}

	public void setfBuyPri(String fBuyPri) {
		this.fBuyPri = fBuyPri;
	}

	public String getfSellPri() {
		return fSellPri;
	}

	public void setfSellPri(String fSellPri) {
		this.fSellPri = fSellPri;
	}

	public String getmSellPri() {
		return mSellPri;
	}

	public void setmSellPri(String mSellPri) {
		this.mSellPri = mSellPri;
	}

	public String getmBuyPri() {
		return mBuyPri;
	}

	public void setmBuyPri(String mBuyPri) {
		this.mBuyPri = mBuyPri;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}