package com.member.service.frame.database.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;



  public interface BaseDao   {
	public Session getOpenSession();
	public SessionFactory getSessionFactory();
    public Session getCurrentSession();
	public List<?> queryByHql(String hql);
 
	public void saveOrUpdateCollection(Collection<?>  entitys);
	

	public List<?> queryBySql(String query,
			List<Object> arguments) ;
	public  List<?> queryBySql(String query, int pageNumber, int pageSize,
			List<Object> arguments) ;
	public  List<?> queryBySql(String query, Map<String,Object> params) ;
	public  List<?> queryBySql(String query, int pageNumber, int pageSize,
			Map<String,Object> params) ;
	/**
	 * 建议使用saveOrUpdate代替
	 * @param entity
	 */
	public void save(Object entity) ;
	public void update(Object entity) ;
	void saveOrUpdate(Object entity);

	void delete(Object entity);
	/**
	 * 及时提交事务
	 * @param className
	 * @param id
	 */
	void deleteByIdentify(String className,Object id);
	
/**
 * 及时提交事务
 * @param className
 * @param id
 */
	void statusDeleteByIdentify(String className,Object id);

	void deleteAll(Collection<?> entity);
	/**
	 * 	及时提交事务
	 * @param sql
	 */
	void executeSqlUpdate1(List<String> sql);
	/**
	 * 批量执行SQL
	 */
	void executeSqlUpdate1(List<String> sqls,List<List<Object>> values);

	
	/**
	 * 执行sql�?对数据的更改操作 包含�? �?�? 及时提交事务
	 * @param sql
	 * @param arguments
	 * @return
	 */
	public void executeSqlUpdate(String sql, List<Object> arguments) ;
	 
	/**
	 * 执行sql�?对数据的更改操作 包含�? �?�? 及时提交事务
	 * @param sql
	 * @param arguments
	 * @return
	 */
	public void executeSqlUpdate(String hql, Map<String, Object> arguments);
	/**
	 * 批量执行HQL
	 */
	void executeHqlUpdate1(List<String> hqls,List<List<Object>> values);

	public void executeHqlUpdate1(List<String> hql);
 
/**
 * 执行HQL�?对数据的更改操作 .  及时提交事务
 * 执行该方法后，会锁住该记录，其他事务只能查询不能更新，事务提交后才能解锁。事务在注解方法�?��后提交，而不是在executeUpdate时提�?
 * 包含�? �?�?delete A  where id=? and runstatus=?.
 * @param delete
 * @param arguments
 * @return
 */
	
	public void executeHqlUpdate(String hql, List<Object> arguments);
	public void executeHqlUpdate(String hql, Object argument);
	/**
	 * 执行HQL�?对数据的更改操作 .包含�? �?�?delete A  where id=(:id) and runstatus=(:status)
	 *  及时提交事务
	 * @param query
	 * @param arguments
	 * @return
	 */

	public void executeHqlUpdate(String query, Map<String, Object> arguments);
	/**
	 * 按条件进行查�?
	 * 
	 * @param query
	 * @param pageNumber
	 * @param pageSize
	 * @param arguments
	 * @return
	 */
	//单条�?
	public List<?> queryByHql(String query, Object argument);
	//多条�?
	public List<?> queryByHql(String query, List<Object> arguments);
	//单条�?
	public List<?> queryByHql(String query, int pageNumber, int pageSize, Object argument);

    //多条�?
	public List<?> queryByHql(String query, int pageNumber, int pageSize, List<Object> arguments);
	public List<?> queryByHql(String query, int pageNumber, int pageSize);
	public List<?> queryByHql(String query, Map<String, Object> arguments);

	public List<?> queryByHql(String query, int pageNumber, int pageSize, Map<String, Object> arguments);

	public List<?> list(Class<?> clazz);

	public Object getEntityById(Class<?> entityClass, Object id);
	public List<?> findListByDetachedCriteria(DetachedCriteria detachedCriteria);
	public List<?> findListByDetachedCriteria(DetachedCriteria detachedCriteria,Integer start,Integer limit);

   //typeName : className ,poName,tableName
	public Map<String, Map<String, Object>> getPoInfoByType(String typeName);

}

