package com.dnt.cloud.core.common.exceptions;

/**
 * 统一的异常处理
 * 
 * @author hazyhao
 *
 */
public class NRCException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8783398481448284414L;

	private ReturnCode returnCode;

	private String memo;
	
	public NRCException(String message) {
        super(message);
    }

	public NRCException(Throwable e) {
		this(ReturnCode.SYSTEM_ERROR, e);
	}

	public NRCException(ReturnCode returnCode) {
		super(returnCode.getMessage());
		this.returnCode = returnCode;
	}

	public NRCException(ReturnCode returnCode, String message) {
		super(message, null);
		this.returnCode = returnCode;
	}

	public NRCException(ReturnCode returnCode, Throwable e) {
		super(returnCode.getMessage(), e);
		this.returnCode = returnCode;
	}

	public NRCException(ReturnCode returnCode, String message, Throwable e) {
		super(message, e);
		this.returnCode = returnCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public ReturnCode getReturnCode() {
		return returnCode;
	}

}
