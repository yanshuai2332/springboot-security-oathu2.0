package org.yan.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.yan.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.Date;

/**
 * 用户
 * Created by yanshuai on 17/5/9.
 */
@Table(name = "app_user")
@Data
@NoArgsConstructor
public class User extends BaseEntity {

	private static final long serialVersionUID = -5283333962574874388L;
	/**
	 * 辖区Id
	 */
	private Long areaId;

	/**
	 * 唯一标识码
	 */
	@Column(name = "identification_code")
	private String identificationCode;

	/**
	 * 真实姓名
	 */
	@Column(name = "realname")
	private String realname;

	/**
	 * 昵称
	 */
	@Column(name = "nickname")
	private String nickname;

	/**
	 * 手机号
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 状态
	 *
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 身份证号
	 */
	@Column(name = "id_card")
	private String idCard;

	/**
	 * 生日
	 */
	@Column(name = "birthday")
	private Date birthday;

	/**
	 * 性别: 1男,2女,0默认
	 */
	@Column(name = "gender")
	private Integer gender;

	/**
	 * 年龄
	 */
	@Column(name = "age")
	private Integer age;

	/**
	 * 婚姻状况: 1未婚, 2已婚, 3离异
	 */
	@Column(name = "marriage")
	private Integer marriage;

	/**
	 * 用户类型
	 *
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * 民警编号
	 */
	@Column(name = "police_code")
	private String policeCode;

	/**
	 * 是否删除
	 *
	 */
	@Column(name = "is_deleted")
	private Boolean deleted;

	/**
	 * 审核驳回原因
	 */
	@Column(name = "reason")
	private String reason;

	public User(Long id, Integer status, String reason) {
		this.id = id;
		this.status = status;
		this.reason = reason;
	}

	public User(Long id, Boolean deleted) {
		this.id = id;
		this.deleted = deleted;
	}

	public User(String mobile, Boolean deleted) {
		this.mobile = mobile;
		this.deleted = deleted;
	}

	public User(String mobile, Integer status, Boolean deleted) {
		this.mobile = mobile;
		this.status = status;
		this.deleted = deleted;
	}

	public User(String mobile, String password) {
		this.mobile = mobile;
		this.password = password;

	}


}
