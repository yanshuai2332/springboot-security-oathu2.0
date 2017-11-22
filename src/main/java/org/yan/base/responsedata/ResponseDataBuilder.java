package org.yan.base.responsedata;

import java.util.List;

/**
 * Created by yanshuai on 17/5/19.
 */
public class ResponseDataBuilder {

	private ResponseData responseData = new ResponseData();

	private ResponseDataBuilder() {
	}

	public static ResponseDataBuilder newBuilder() {
		return new ResponseDataBuilder();
	}


	public ResponseDataBuilder base(ResponseEnumInterface responseEnumInterface) {
		responseData.setCode(responseEnumInterface.value());
		responseData.setMessage(responseEnumInterface.text());
		return this;
	}

	public ResponseDataBuilder code(String code) {
		responseData.setCode(code);
		return this;
	}

	public ResponseDataBuilder userMessage(String userMessage) {
		responseData.setMessage(userMessage);
		return this;
	}

	public ResponseDataBuilder developerMessage(String developerMessage) {
		responseData.setDeveloperMessage(developerMessage);
		return this;
	}

	public ResponseDataBuilder moreInfo(String moreInfo) {
		responseData.setMoreInfo(moreInfo);
		return this;
	}

	public ResponseDataBuilder data(String key, Object value) {
		responseData.getData().put(key, value);
		return this;
	}

	public ResponseDataBuilder errors(List<InvalidParameters> errors) {
		responseData.setErrors(errors);
		return this;
	}

	public ResponseData build() {
		return responseData;
	}

}
