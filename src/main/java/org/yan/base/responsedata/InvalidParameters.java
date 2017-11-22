package org.yan.base.responsedata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.io.Serializable;

/**
 * 返回给客户端的不合法请求参数信息
 * Created by yanshuai on 17/5/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(content = JsonInclude.Include.NON_NULL, value = JsonInclude.Include.NON_EMPTY)
public class InvalidParameters implements Serializable {

	private static final long serialVersionUID = -5245150396597822537L;
	
	/**
	 * 不合法字段
	 */
	private String field;

	/**
	 * 不合法原因
	 */
	private String message;

	public InvalidParameters(FieldError fieldError) {
		this.field = fieldError.getField();
		this.message = fieldError.getDefaultMessage();
	}

	public InvalidParameters(String message) {
		this.message = message;
	}
}
