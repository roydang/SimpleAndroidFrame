package kr.androy.base.network;

import java.util.Map;

public abstract class RequestObj {
	
	private String mUrl;
	private Map<String, String> mParams;
	
	protected abstract void parseResponse();
	public String getUrl() {
		return mUrl;
	}
	public void setUrl(String url) {
		this.mUrl = url;
	}
	public Map<String, String> getParams() {
		return mParams;
	}
	public void setParams(Map<String, String> params) {
		this.mParams = params;
	}
}
