package org.yan.base.repository;

import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * 所有DAO的基类, 继承Mapper中单独接口, 如有需要扩展, 请参考
 *
 * @see tk.mybatis.mapper.common.Mapper
 * Created by yanshuai on 17/5/3.
 */
public interface BaseMapper<T> extends
	tk.mybatis.mapper.common.BaseMapper<T>,
	SelectByIdsMapper<T> {
}
