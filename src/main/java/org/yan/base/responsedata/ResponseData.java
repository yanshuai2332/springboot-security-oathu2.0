package org.yan.base.responsedata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * RESTFUL接口统一返回
 * Created by yanshuai on 17/5/9.
 */
@JsonInclude(content = JsonInclude.Include.NON_NULL, value = JsonInclude.Include.NON_EMPTY)
@Data
public class ResponseData implements Serializable {

	private static final long serialVersionUID = -3418641059206295418L;

	/**
	 * 业务编码
	 */
	private String code;

	/**
	 * 对用户友好的提示信息, 供前端显示使用
	 */
	private String message;

	/**
	 * 对客户端友好的提示信息, 供开发人员使用
	 */
	private String developerMessage;

	/**
	 * 更多信息, e.g. 提供链接供开发人员查看具体错误信息等
	 */
	private String moreInfo;

	/**
	 * 业务数据
	 */
	private Map<String, Object> data = new LinkedHashMap<>();

	/**
	 * 不合法参数
	 */
	private List<InvalidParameters> errors;


}
