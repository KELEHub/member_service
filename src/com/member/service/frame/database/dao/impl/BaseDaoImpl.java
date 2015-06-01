package com.member.service.frame.database.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.service.frame.database.dao.BaseDao;
import com.member.service.util.LogUtil;

@Component("BaseDaoImpl")
public class BaseDaoImpl implements BaseDao {

	public HibernateTemplate hiberanteTemplate;

	private SessionFactory sessionFactory;
	private Map<String, Map<String, Map<String, Object>>> poInfoMaps = new HashMap<String, Map<String, Map<String, Object>>>();
	private final static Log log = LogFactory.getLog(BaseDaoImpl.class);

	Map<String, Object> tableName_classname_map = new HashMap<String, Object>();
	ObjectMapper om = new ObjectMapper();

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.hiberanteTemplate = createHibernateTemplate(sessionFactory);
		initMappings();
	}

	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	public HibernateTemplate getHiberanteTemplate() {
		return hiberanteTemplate;
	}

	public void setHiberanteTemplate(HibernateTemplate hiberanteTemplate) {
		this.hiberanteTemplate = hiberanteTemplate;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session getOpenSession() {
		return sessionFactory.openSession();
	}

	@Transactional(readOnly = true)
	public List<?> queryByHql(String hql) {
		List<Object> arguments = new ArrayList<Object>();
		return queryByHql(hql, arguments);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdateCollection(Collection<?> entitys) {
		for (Object entity : entitys) {
			saveOrUpdate(entity);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(Object entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Object entity) {
		getCurrentSession().save(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Object entity) {
		getCurrentSession().update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByIdentify(String className, Object id) {
		String tableNmae = className.substring(className.lastIndexOf(".") + 1);
		String hql = "delete " + tableNmae + " where id = ?";
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(id);
		executeHqlUpdate(hql, arguments);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByIdentifys(String className, Collection<Object> ids) {
		for (Object id : ids) {
			deleteByIdentify(className, id);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Object entity) {
		getCurrentSession().delete(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAll(Collection<?> entitys) {
		hiberanteTemplate.deleteAll(entitys);
	}

	@Transactional(readOnly = true)
	public Object getEntityById(Class<?> entityClass, Object id) {
		Object entity = null;
		try {
			Session session = getCurrentSession();
			entity = session.createCriteria(entityClass).add(
					Restrictions.eq("id", id)).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logError(log, e);
		}
		return entity;
	}

	@Transactional(readOnly = true)
	public List<?> list(Class<?> clazz) {

		List<?> list = null;
		try {
			Session session = getCurrentSession();
			list = session.createCriteria(clazz).list();
		} catch (Exception e) {

			e.printStackTrace();
			LogUtil.logError(log, e);
		}
		return list;
	}

	@Transactional(readOnly = true)
	public List<?> queryByHql(String hql, Object argument) {
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(argument);
		return queryByHql(hql, arguments);
	}

	@Transactional(readOnly = true)
	public List<?> queryByHql(String hql, List<Object> arguments) {
		return queryByHql(hql, 0, 0, arguments);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeSqlUpdate1(List<String> sql) {
		for (int i = 0; i < sql.size(); i++) {
			Session session = getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql.get(i));
			query.executeUpdate();
		}
	}

	/**
	 * 批量执行SQL
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeSqlUpdate1(List<String> sqls, List<List<Object>> values) {
		for (int i = 0; i < sqls.size(); i++) {

			String sql = sqls.get(i);
			List<Object> arguments = values.get(i);
			Session session = getCurrentSession();
			SQLQuery q = session.createSQLQuery(sql);
			if (arguments != null) {
				int len = arguments.size();
				for (int j = 0; j < len; j++) {
					q.setParameter(j, arguments.get(j));

				}
			}
			q.executeUpdate();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeSqlUpdate(String sql, List<Object> arguments) {
		Session session = getCurrentSession();
		Query q = session.createSQLQuery(sql);
		if (arguments != null) {
			int len = arguments.size();
			for (int i = 0; i < len; i++) {
				q.setParameter(i, arguments.get(i));

			}
		}

		q.executeUpdate();

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeSqlUpdate(String sql, Map<String, Object> arguments) {

		Session session = getCurrentSession();
		Query q = session.createSQLQuery(sql);
		for (Iterator<Entry<String, Object>> it = arguments.entrySet()
				.iterator(); it.hasNext();) {
			Entry<String, Object> entry = it.next();
			Object value = entry.getValue();
			if (value instanceof Collection<?>) {
				q.setParameterList(entry.getKey(), (Collection<?>) value);
			} else {
				q.setParameter(entry.getKey(), value);
			}
		}

		q.executeUpdate();

	}

	@Transactional(readOnly = true)
	public List<?> queryByHql(String query, int pageNumber, int pageSize) {

		List<Object> arguments = new ArrayList<Object>();
		return queryByHql(query, pageNumber, pageSize, arguments);
	}

	@Transactional(readOnly = true)
	public List<?> queryByHql(String query, int pageNumber, int pageSize,
			Object argument) {

		List<Object> arguments = new ArrayList<Object>();
		arguments.add(argument);
		return queryByHql(query, pageNumber, pageSize, arguments);
	}

	@Transactional(readOnly = true)
	public List<?> queryByHql(String query, int pageNumber, int pageSize,
			List<Object> arguments) {
		List<?> list = null;
		try {
			Session session = getCurrentSession();
			Query q = session.createQuery(query);

			if (arguments != null) {
				int len = arguments.size();
				for (int i = 0; i < len; i++) {
					Object arg = arguments.get(i);
					q.setParameter(i, arg);
				}
			}
			if (pageSize > 0) {
				q.setFirstResult((pageNumber - 1) * pageSize);
				q.setMaxResults(pageSize);
			}
			list = q.list();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logError(log, e);
		}
		return list;
	}

	@Transactional(readOnly = true)
	public List<?> queryBySql(String query, List<Object> arguments) {
		return queryBySql(query, 0, 0, arguments);
	}

	@Transactional(readOnly = true)
	public List<?> queryBySql(String query, int pageNumber, int pageSize,
			List<Object> arguments) {
		List<?> list = null;
		try {
			Session session = getCurrentSession();
			Query q = session.createSQLQuery(query);
			if (arguments != null) {
				int len = arguments.size();
				for (int i = 0; i < len; i++) {
					q.setParameter(i, arguments.get(i));

				}
			}
			if (pageSize > 0) {
				q.setFirstResult((pageNumber - 1) * pageSize);
				q.setMaxResults(pageSize);
			}
			list = q.list();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logError(log, e);
		}
		return list;

	}

	@Transactional(readOnly = true)
	public List<?> queryByHql(String query, Map<String, Object> arguments) {
		return queryByHql(query, 0, 0, arguments);
	}

	@Transactional(readOnly = true)
	public List<?> queryByHql(String query, int pageNumber, int pageSize,
			Map<String, Object> arguments) {
		List<?> list = null;
		try {
			Session session = getCurrentSession();
			Query q = session.createQuery(query);
			for (Iterator<Entry<String, Object>> it = arguments.entrySet()
					.iterator(); it.hasNext();) {
				Entry<String, Object> entry = it.next();
				Object value = entry.getValue();
				if (value instanceof Collection<?>) {
					q.setParameterList(entry.getKey(), (Collection<?>) value);
				} else {
					q.setParameter(entry.getKey(), value);
				}
			}
			if (!(pageNumber <= 0 || pageSize <= 0)) {
				q.setFirstResult((pageNumber - 1) * pageSize);
				q.setMaxResults(pageSize);
			}

			list = q.list();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logError(log, e);
		}
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeHqlUpdate1(List<String> hql) {
		for (int i = 0; i < hql.size(); i++) {
			Session session = getCurrentSession();
			Query query = session.createQuery(hql.get(i));
			query.executeUpdate();
		}
	}

	/**
	 * 批量执行HQL
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeHqlUpdate1(List<String> hqls, List<List<Object>> values) {
		for (int i = 0; i < hqls.size(); i++) {

			String hql = hqls.get(i);
			List<Object> arguments = values.get(i);
			Session session = getCurrentSession();
			Query q = session.createQuery(hql);
			if (arguments != null) {
				int len = arguments.size();
				for (int j = 0; j < len; j++) {
					q.setParameter(j, arguments.get(j));

				}
			}
			q.executeUpdate();
		}
	}

	/**
	 * 及时提交事务
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeHqlUpdate(String hql, Object argument) {
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(argument);
		executeHqlUpdate(hql, arguments);

	}

	/**
	 * 及时提交事务
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeHqlUpdate(String hql, List<Object> arguments) {
		Session session = getCurrentSession();
		Query q = session.createQuery(hql);
		if (arguments != null) {
			int len = arguments.size();
			for (int i = 0; i < len; i++) {
				q.setParameter(i, arguments.get(i));

			}
		}
		q.executeUpdate();

	}

	/**
	 * 及时提交事务
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeHqlUpdate(String hql, Map<String, Object> arguments) {
		Session session = getCurrentSession();
		Query q = session.createQuery(hql);
		for (Iterator<Entry<String, Object>> it = arguments.entrySet()
				.iterator(); it.hasNext();) {
			Entry<String, Object> entry = it.next();
			Object value = entry.getValue();
			if (value instanceof Collection<?>) {
				q.setParameterList(entry.getKey(), (Collection<?>) value);
			} else {
				q.setParameter(entry.getKey(), value);
			}
		}

		q.executeUpdate();
	}

	@Transactional(readOnly = true)
	public List<?> findListByDetachedCriteria(DetachedCriteria detachedCriteria) {
		List<?> list = null;
		try {
			list = hiberanteTemplate.findByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logError(log, e);
		}
		return list;
	}

	@Transactional(readOnly = true)
	public List<?> findListByDetachedCriteria(
			DetachedCriteria detachedCriteria, Integer start, Integer limit)

	{
		List<?> list = null;
		try {

			list = hiberanteTemplate.findByCriteria(detachedCriteria, start,
					limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void initMappings() {
		if (poInfoMaps == null || poInfoMaps.size() == 0) {
			Map<String, Map<String, Object>> classNameMap = new HashMap<String, Map<String, Object>>();
			Map<String, Map<String, Object>> poNameMap = new HashMap<String, Map<String, Object>>();
			Map<String, Map<String, Object>> tableNameMap = new HashMap<String, Map<String, Object>>();

			poInfoMaps.put("className", classNameMap);
			poInfoMaps.put("poName", poNameMap);
			poInfoMaps.put("tableName", tableNameMap);
			SessionFactory factory = this.getSessionFactory();
			Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
			Set<String> it = (Set<String>) metaMap.keySet();
			for (String key : it) {
				AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap
						.get(key);
				String tableName = classMetadata.getTableName();
				int index = tableName.indexOf(".");
				if (index >= 0) {
					tableName = tableName.substring(index + 1);
				}
				String className = classMetadata.getEntityMetamodel().getName();
				// 数据库表主键 字段�?，不是po属�?�?
				String tableIdentityName = classMetadata
						.getIdentifierColumnNames()[0];
				String poName = className
						.substring(className.lastIndexOf(".") + 1);
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("className", className);
				paramMap.put("poName", poName);
				paramMap.put("tableIdentityName", tableIdentityName);
				paramMap.put("tableName", tableName);
				classNameMap.put(className.toLowerCase(), paramMap);
				poNameMap.put(poName.toLowerCase(), paramMap);
				tableNameMap.put(tableName.toLowerCase(), paramMap);
			}
		}
	}

	public Map<String, Map<String, Object>> getPoInfoByType(String typeName) {
		initMappings();
		return poInfoMaps.get(typeName);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void statusDeleteByIdentify(String className, Object id) {
		String tableNmae = className.substring(className.lastIndexOf(".") + 1);
		String hql = "update " + tableNmae + " t set t.status=-1 where id = ?";
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(id);
		executeHqlUpdate(hql, arguments);

	}

	@Transactional(readOnly = true)
	public List<?> queryBySql(String query, Map<String, Object> arguments) {
		return queryByHql(query, 0, 0, arguments);
	}

	@Transactional(readOnly = true)
	public List<?> queryBySql(String query, int pageNumber, int pageSize,
			Map<String, Object> arguments) {
		List<?> list = null;
		try {
			Session session = getCurrentSession();
			SQLQuery q = session.createSQLQuery(query);
			for (Iterator<Entry<String, Object>> it = arguments.entrySet()
					.iterator(); it.hasNext();) {
				Entry<String, Object> entry = it.next();
				Object value = entry.getValue();
				if (value instanceof Collection<?>) {
					q.setParameterList(entry.getKey(), (Collection<?>) value);
				} else {
					q.setParameter(entry.getKey(), value);
				}
			}
			if (!(pageNumber <= 0 || pageSize <= 0)) {
				q.setFirstResult((pageNumber - 1) * pageSize);
				q.setMaxResults(pageSize);
			}

			list = q.list();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logError(log, e);
		}
		return list;
	}

}
