package org.yan.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yanshuai on 17/5/3.
 */
@Data
@NoArgsConstructor
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = -6802159466200163388L;
	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Timestamp updateTime;

}
