package com.collect.common.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

	Integer insert(T entity);

	Integer insertBatch(List<T> entityList);

	Integer deleteById(Serializable id);

	Integer deleteBatchIds(Collection<? extends Serializable> idList);

	Integer deleteByMap(Map<String, Object> params);

	Integer update(T entity);

	Integer updateByMap(Map<String, Object> params);

	Long selectCount(Map<String, Object> params);

	T selectById(Serializable id);

	T selectOne(T entity);

	List<T> selectBatchIds(Collection<? extends Serializable> idList);

	List<T> selectList(Map<String, Object> params);

}
