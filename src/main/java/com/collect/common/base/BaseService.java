package com.collect.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;

@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseDao<T>, T> {

	@Autowired
	protected D dao;

	protected static boolean retBool(Integer result) {
		return null != result && result >= 1;
	}

	@Transactional(readOnly = false)
	public boolean insert(T entity) {
		return retBool(dao.insert(entity));
	}

	@Transactional(readOnly = false)
	public boolean insertBatch(List<T> entityList, int batchSize) {
		List<T> list = Lists.newArrayList();
		int size = entityList.size();
		for (int i = 0; i < size; i++) {
			list.add(entityList.get(i));
			if (i > 0 && i % batchSize == 0) {
				dao.insertBatch(list);
				list.clear();
			}
		}
		if (list.size() > 0) {
			dao.insertBatch(list);
		}
		return true;
	}

	@Transactional(readOnly = false)
	public boolean deleteById(Serializable id) {
		return retBool(dao.deleteById(id));
	}

	@Transactional(readOnly = false)
	public boolean deleteBatchIds(List<? extends Serializable> list) {
		return retBool(dao.deleteBatchIds(list));
	}

	@Transactional(readOnly = false)
	public boolean deleteByMap(Map<String, Object> params) {
		return retBool(dao.deleteByMap(params));
	}

	@Transactional(readOnly = false)
	public boolean update(T entity) {

		return retBool(dao.update(entity));
	}

	@Transactional(readOnly = false)
	public boolean updateByMap(Map<String, Object> params) {
		return retBool(dao.updateByMap(params));
	}

	public T selectById(Serializable id) {
		return dao.selectById(id);
	}

	public T selectOne(T entity) {
		return dao.selectOne(entity);
	}

	public Long selectCount(Map<String, Object> params) {
		return dao.selectCount(params);
	}

	public List<T> selectList(Map<String, Object> params) {
		return dao.selectList(params);
	}

	public Page<T> selectPage(Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		return (Page<T>) selectList(query);
	}

}
