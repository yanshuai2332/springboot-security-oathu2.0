package org.yan.base.responsedata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by yanshuai on 17/6/2.
 */
@JsonInclude(content = JsonInclude.Include.NON_NULL, value = JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2ResponseData implements Serializable {

	private static final long serialVersionUID = -7046192805129500778L;

	@JsonUnwrapped
	private ResponseData responseData;

	private String error;

}
