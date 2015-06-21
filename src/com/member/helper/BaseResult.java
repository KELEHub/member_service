package com.member.helper;

import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

public class BaseResult<T> {
	private String msgCode;
	private boolean success;
	private T result;
	private String msg;
	private String codeName;
	private String extension;
	private static final String CODE_SUCCESS = "global_success";

	private static final String CODE_FAIL_DEFALUT = "global_fail";

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 * @return
	 */
	public BaseResult<T> setSuccess(boolean success) {
		this.success = success;
		if (StringUtils.isEmpty(msgCode) && success) {
			codeName = CODE_SUCCESS;
		} else {
			codeName = CODE_FAIL_DEFALUT;
		}
		return this;
	}

	public T getResult() {
		return result;
	}

	public BaseResult<T> setResult(T result) {
		this.result = result;
//		this.success = true;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public BaseResult<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public BaseResult<T> setMsgCode(String msgCode) {
		this.msgCode = msgCode;
		return this;
	}

	public BaseResult<T> formatMessage(MessageSource msgSource) {
		if(StringUtils.isEmpty(codeName)){
			return this;
		}
		String msgLocale = msgSource.getMessage(codeName, null, null);
		String[] msgs = msgLocale.split("_");
		if (StringUtils.isEmpty(msgCode)) {
			this.msgCode = msgs[0];
		}
		if (StringUtils.isEmpty(msg)) {
			this.msg = msgs[1];
		}
		return this;
	}

	@Override
	public String toString() {
		return "BaseResult [msgCode=" + msgCode + ",msg=" + msg + ", result="
				+ result + ", success=" + success + "]";
	}

}
