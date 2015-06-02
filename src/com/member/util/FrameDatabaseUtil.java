package com.member.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.helper.dao.BaseDao;
import com.member.helper.entity.QueryCondition;

@Component
 public class FrameDatabaseUtil {

	private static BaseDao baseDaoImpl;
	private static ObjectMapper om = new ObjectMapper();

	public static BaseDao getBaseDaoImpl() {
		return baseDaoImpl;
	}

	@SuppressWarnings("static-access")
	@Resource(name = "BaseDaoImpl")
	public void setBaseDaoImpl(BaseDao baseDaoImpl) {
		this.baseDaoImpl = baseDaoImpl;
	}
    public static SessionFactory getSessionFactory()
    {
    	return baseDaoImpl.getSessionFactory();
    }
   /**
    *  通过tableName表名（忽略大小写），获取po类名�?po类className,po对应的表主键的字段名�?po对应的表�?
	 paramName可取：poName,className,tableIdentityName,tableName 这四个�?
    * @param tableName
    * @param paraName
    * @return
    */
	public static String getPoInfoByTableName(String tableName, String paraName) {
		Map<String, Map<String, Object>> tableNameMap = baseDaoImpl.getPoInfoByType("tableName");
		Map<String,Object> poInfoMap=(Map<String, Object>)tableNameMap.get(tableName.toLowerCase());
		if (poInfoMap == null || poInfoMap.size() == 0) {
			return null;
		}
		return poInfoMap.get(paraName).toString();
	}

	/**
	 *  通过poName实体类名（忽略大小写），获取po类名�?po类className,po对应的表主键的字段名�?po对应的表�?
	 paramName可取：poName,className,tableIdentityName,tableName 这四个�?
	 * @param poName
	 * @param paraName
	 * @return
	 */
	public static String getPoInfoByPoName(String poName, String paraName) {
		Map<String, Map<String, Object>> poNameMap = baseDaoImpl.getPoInfoByType("poName");
		Map<String,Object> poInfoMap=(Map<String,Object> )poNameMap.get(poName.toLowerCase());
		if (poInfoMap == null || poInfoMap.size() == 0) {
			return null;
		}
		return poInfoMap.get(paraName).toString();
	}

	/**
	 *  通过className类名（忽略大小写），获取po类名�?po类className,po对应的表主键的字段名�?po对应的表�?
	 paramName可取：poName,className,tableIdentityName,tableName 这四个�?
	 * @param className
	 * @param paraName
	 * @return
	 */
	public static String getPoInfoByClassName(String className, String paraName) {
		Map<String, Map<String, Object>> classNameMap = baseDaoImpl.getPoInfoByType("className");
		Map<String,Object> poInfoMap=(Map<String,Object>) classNameMap.get(className.toLowerCase());
		if (poInfoMap == null || poInfoMap.size() == 0) {
			return null;
		}
		return poInfoMap.get(paraName).toString();
	}

	public static DetachedCriteria getDetachedCriteriaByCondition_TableName(
			String condition, String tableName) throws Exception {
		String className = getPoInfoByTableName(tableName, "className");
		return getDetachedCriteriaByCondition(condition, className);

	}

	public static DetachedCriteria getDetachedCriteriaByCondition_PoName(
			String condition, String poName) throws Exception {
		String className = getPoInfoByPoName(poName, "className");
		return getDetachedCriteriaByCondition(condition, className);


	}
	public static DetachedCriteria getDetachedCriteriaByCondition_ClassName(
			String condition, String className) throws Exception {
		return getDetachedCriteriaByCondition(condition, className);
	}
	private static DetachedCriteria getDetachedCriteriaByCondition(
			String condition, String className) throws ClassNotFoundException,
			IOException, JsonParseException, JsonMappingException, Exception {

		if(condition==null||condition.isEmpty())
		{
			Class<?> clazz = Class.forName(className);
			DetachedCriteria de = DetachedCriteria.forClass(clazz);
		
			return de;
		}
		QueryCondition[] queryConditions = om.readValue(condition,
				QueryCondition[].class);

		DetachedCriteria de = FrameDatabaseUtil.addQuery2Criteria(className,
				Arrays.asList(queryConditions));
		return de;
	}
	public static DetachedCriteria addQuery2Criteria(String className,
			List<QueryCondition> conditionList) throws Exception {
		Class<?> clazz = Class.forName(className);
		DetachedCriteria dtr = DetachedCriteria.forClass(clazz);
		if ((conditionList == null) || (conditionList.isEmpty())) {
			return dtr;
		}
		Criterion criterion = null;
		QueryCondition matchCondition = null;
		for (QueryCondition condition : conditionList) {
			if (condition == null) {
				continue;
			}
			if ("query".equals(condition.getOperation())) {
				matchCondition = condition;
			} else
				criterion = addQuery2Criterion(criterion, className, condition,
						dtr);
		}

		if (criterion != null) {
			dtr.add(criterion);
		}
		if ((matchCondition != null) && (matchCondition.getValue() != null)) {
			try {
				// dtr = dao.createFieldMatchCriteria(dtr,
				// matchCondition.getValue().toString(),
				// DynamicQuery.getSingleton().getExcludeQueryField(dao.getTableName()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtr;
	}

	@SuppressWarnings("unchecked")
	public static Criterion addQuery2Criterion(Criterion criterion,
			String className, QueryCondition condition, DetachedCriteria dtr)
			throws Exception {
		if (condition == null) {
			return criterion;
		}
		String property = condition.getFieldName();
		Object value = condition.getValue();
		Class<?> clazz = Class.forName(className);
		if ("eqproperty".equalsIgnoreCase(condition.getOperation()))
			return criterion == null ? Restrictions.eqProperty(property, value
					.toString()) : Restrictions.and(criterion, Restrictions
					.eqProperty(property, value.toString()));
		if ("neproperty".equalsIgnoreCase(condition.getOperation()))
			return criterion == null ? Restrictions.neProperty(property, value
					.toString()) : Restrictions.and(criterion, Restrictions
					.neProperty(property, value.toString()));
		if ("leproperty".equalsIgnoreCase(condition.getOperation()))
			return criterion == null ? Restrictions.leProperty(property, value
					.toString()) : Restrictions.and(criterion, Restrictions
					.leProperty(property, value.toString()));
		if ("ltproperty".equalsIgnoreCase(condition.getOperation()))
			return criterion == null ? Restrictions.ltProperty(property, value
					.toString()) : Restrictions.and(criterion, Restrictions
					.ltProperty(property, value.toString()));
		if ("geproperty".equalsIgnoreCase(condition.getOperation()))
			return criterion == null ? Restrictions.geProperty(property, value
					.toString()) : Restrictions.and(criterion, Restrictions
					.geProperty(property, value.toString()));
		if ("gtproperty".equalsIgnoreCase(condition.getOperation()))
			return criterion == null ? Restrictions.gtProperty(property, value
					.toString()) : Restrictions.and(criterion, Restrictions
					.gtProperty(property, value.toString()));
		if ("or".equalsIgnoreCase(condition.getOperation())) {
			List<QueryCondition> list = convert2QueryCondition(value.toString());
			Criterion orCriterion = null;
			for (QueryCondition query : list) {
				orCriterion = addQuery2Criterion(orCriterion, className, query,
						dtr);
			}
			if (orCriterion == null) {
				return criterion;
			}
			return criterion == null ? orCriterion : Restrictions.or(criterion,
					orCriterion);
		}

		String node = condition.getFieldNode();

		if (node != null && !FrameStringUtil.isEmptyString(node)) {
			if (!"id".equalsIgnoreCase(node.toLowerCase())) {
				dtr.createAlias(node, node);
				try {
					Field field = clazz.getDeclaredField(node);
					value = convert2ClassFieldValue(field.getType(), property,
							condition.getValue());
				} catch (Exception e) {
					return criterion;
				}
				property = node + "." + condition.getFieldName();
			} else {
				value = convert2ClassFieldValue(clazz, property, condition
						.getValue());
				property = node + "." + condition.getFieldName();
			}
		} else
			value = convert2ClassFieldValue(clazz, property, condition
					.getValue());

		if (("eq".equalsIgnoreCase(condition.getOperation()))
				|| ("=".equalsIgnoreCase(condition.getOperation())))
			return criterion == null ? Restrictions.eq(property, value)
					: Restrictions.and(criterion, Restrictions.eq(property,
							value));
		if (("ne".equalsIgnoreCase(condition.getOperation()))
				|| ("!=".equalsIgnoreCase(condition.getOperation())))
			return criterion == null ? Restrictions.ne(property, value)
					: Restrictions.and(criterion, Restrictions.ne(property,
							value));
		if (("ge".equalsIgnoreCase(condition.getOperation()))
				|| (">=".equalsIgnoreCase(condition.getOperation())))
			return criterion == null ? Restrictions.ge(property, value)
					: Restrictions.and(criterion, Restrictions.ge(property,
							value));
		if (("gt".equalsIgnoreCase(condition.getOperation()))
				|| (">".equalsIgnoreCase(condition.getOperation())))
			return criterion == null ? Restrictions.gt(property, value)
					: Restrictions.and(criterion, Restrictions.gt(property,
							value));
		if (("le".equalsIgnoreCase(condition.getOperation()))
				|| ("<=".equalsIgnoreCase(condition.getOperation())))
			return criterion == null ? Restrictions.le(property, value)
					: Restrictions.and(criterion, Restrictions.le(property,
							value));
		if (("lt".equalsIgnoreCase(condition.getOperation()))
				|| ("<".equalsIgnoreCase(condition.getOperation())))
			return criterion == null ? Restrictions.lt(property, value)
					: Restrictions.and(criterion, Restrictions.lt(property,
							value));
		if ("like".equalsIgnoreCase(condition.getOperation()))
			return criterion == null ? Restrictions.like(property, value
					.toString()) : Restrictions.and(criterion, Restrictions
					.like(property, value.toString()));
		if ("ilike".equalsIgnoreCase(condition.getOperation()))
			return criterion == null ? Restrictions.ilike(property, value
					.toString()) : Restrictions.and(criterion, Restrictions
					.ilike(property, value.toString()));
		if ("between".equalsIgnoreCase(condition.getOperation())) {
			if ((value instanceof List)) {
				List valueList = (List) value;
				if (valueList.size() > 1)
					return criterion == null ? Restrictions.between(property,
							valueList.get(0), valueList.get(1)) : Restrictions
							.and(criterion, Restrictions.between(property,
									valueList.get(0), valueList.get(1)));
			}
		} else if (("in".equalsIgnoreCase(condition.getOperation()))
				|| ("exist".equalsIgnoreCase(condition.getOperation()))) {
			if ((value instanceof List))
				return criterion == null ? Restrictions.in(property,
						(List) value) : Restrictions.and(criterion,
						Restrictions.in(property, (List) value));
		} else if (("notin".equalsIgnoreCase(condition.getOperation()))
				|| ("notin".equalsIgnoreCase(condition.getOperation()))) {
			if ((value instanceof List)) {
				return criterion == null ? Restrictions.not(Restrictions.in(
						property, (List) value)) : Restrictions.and(criterion,
						Restrictions.not(Restrictions
								.in(property, (List) value)));
			}
		}
		return criterion;
	}

	public static Object convert2ClassFieldValue(Class<?> clazz,
			String property, Object value) {
		try {
			if (value == null) {
				return null;
			}
			if (property.replaceAll(" ", "").equals("")) {
				return value;
			}
			Field field = clazz.getDeclaredField(property);
			if (field == null) {
				return value;
			}
			return  FrameObjectUtil.convertValueByFieldType(field.getType(), value);
		} catch (Exception e) {
			System.out.println(clazz.getName() + "没有字段:" + property);
		}
		return value;
	}

	
	
//	public static Criterion getCondition(QueryCondition condition,String className)
//			throws Exception {
//		if (condition == null) {
//			return null;
//		}
//		String property = condition.getFieldName();
//		Object value = condition.getValue();
//		Class clazz = Class.forName(className);
//	
//		if ("or".equalsIgnoreCase(condition.getOperation())) {
//			List<QueryCondition> list = convert2QueryCondition(value.toString());
//			Criterion orCriterion = null;
//			for (QueryCondition query : list) {
//				
//			}
//		}
//
//		String node = condition.getFieldNode();
//
//		if (node != null && !FrameStringUtil.isEmptyString(node)) {
//			if (!"id".equalsIgnoreCase(node.toLowerCase())) {
//				dtr.createAlias(node, node);
//				try {
//					Field field = clazz.getDeclaredField(node);
//					value = convert2ClassFieldValue(field.getType(), property,
//							condition.getValue());
//				} catch (Exception e) {
//					return criterion;
//				}
//				property = node + "." + condition.getFieldName();
//			} else {
//				value = convert2ClassFieldValue(clazz, property, condition
//						.getValue());
//				property = node + "." + condition.getFieldName();
//			}
//		} else
//			value = convert2ClassFieldValue(clazz, property, condition
//					.getValue());
//           String conditionString="and";
//		if (("eq".equalsIgnoreCase(condition.getOperation()))
//				|| ("=".equalsIgnoreCase(condition.getOperation())))
//			return Restrictions.eq(property, value).toSqlString(criteria, criteriaQuery);
//		if (("ne".equalsIgnoreCase(condition.getOperation()))
//				|| ("!=".equalsIgnoreCase(condition.getOperation())))
//			return criterion == null ? Restrictions.ne(property, value)
//					: Restrictions.and(criterion, Restrictions.ne(property,
//							value));
//		if (("ge".equalsIgnoreCase(condition.getOperation()))
//				|| (">=".equalsIgnoreCase(condition.getOperation())))
//			return criterion == null ? Restrictions.ge(property, value)
//					: Restrictions.and(criterion, Restrictions.ge(property,
//							value));
//		if (("gt".equalsIgnoreCase(condition.getOperation()))
//				|| (">".equalsIgnoreCase(condition.getOperation())))
//			return criterion == null ? Restrictions.gt(property, value)
//					: Restrictions.and(criterion, Restrictions.gt(property,
//							value));
//		if (("le".equalsIgnoreCase(condition.getOperation()))
//				|| ("<=".equalsIgnoreCase(condition.getOperation())))
//			return criterion == null ? Restrictions.le(property, value)
//					: Restrictions.and(criterion, Restrictions.le(property,
//							value));
//		if (("lt".equalsIgnoreCase(condition.getOperation()))
//				|| ("<".equalsIgnoreCase(condition.getOperation())))
//			return criterion == null ? Restrictions.lt(property, value)
//					: Restrictions.and(criterion, Restrictions.lt(property,
//							value));
//		if ("like".equalsIgnoreCase(condition.getOperation()))
//			return criterion == null ? Restrictions.like(property, value
//					.toString()) : Restrictions.and(criterion, Restrictions
//					.like(property, value.toString()));
//		if ("ilike".equalsIgnoreCase(condition.getOperation()))
//			return criterion == null ? Restrictions.ilike(property, value
//					.toString()) : Restrictions.and(criterion, Restrictions
//					.ilike(property, value.toString()));
//		if ("between".equalsIgnoreCase(condition.getOperation())) {
//			if ((value instanceof List)) {
//				List valueList = (List) value;
//				if (valueList.size() > 1)
//					return criterion == null ? Restrictions.between(property,
//							valueList.get(0), valueList.get(1)) : Restrictions
//							.and(criterion, Restrictions.between(property,
//									valueList.get(0), valueList.get(1)));
//			}
//		} else if (("in".equalsIgnoreCase(condition.getOperation()))
//				|| ("exist".equalsIgnoreCase(condition.getOperation()))) {
//			if ((value instanceof List))
//				return criterion == null ? Restrictions.in(property,
//						(List) value) : Restrictions.and(criterion,
//						Restrictions.in(property, (List) value));
//		} else if (("notin".equalsIgnoreCase(condition.getOperation()))
//				|| ("notin".equalsIgnoreCase(condition.getOperation()))) {
//			if ((value instanceof List)) {
//				return criterion == null ? Restrictions.not(Restrictions.in(
//						property, (List) value)) : Restrictions.and(criterion,
//						Restrictions.not(Restrictions
//								.in(property, (List) value)));
//			}
//		}
//		return criterion;
//	}

	
	

	public static List<QueryCondition> convert2QueryCondition(String condition)
			throws JsonParseException, JsonMappingException, IOException {

		return Arrays.asList(om.readValue(condition, QueryCondition[].class));
	}
	public static List<?> queryByHqlCondition(String hql,List<QueryCondition> conditions) 
	{
			return queryByHqlCondition( hql,  conditions, null, null) ;

	}
	public static List<?> queryBySqlCondition(String hql, List<QueryCondition> conditions,Integer start,Integer limit) {
		return queryByCondition( hql,  conditions, start, limit,false) ;

	}
	public static List<?> queryByHqlCondition(String hql,List<QueryCondition> conditions,Integer start,Integer limit) {
		return queryByCondition( hql,  conditions, start, limit,true) ;

	}
	public static List<?> queryByCondition(String hql, List<QueryCondition> conditions,Integer start,Integer limit,Boolean isHql) {

		Map<String, Object> values = new HashMap<String, Object>();

		
				hql = getHql(hql,conditions, values,"and");
        List <?> result=null;
		if(limit!=null&&start!=null)
		{
			int   pageNumber=start/limit+1;
			if(isHql)
			{
			result= baseDaoImpl.queryByHql(hql,pageNumber,limit,values);
			}
			else{
				result= baseDaoImpl.queryBySql(hql,pageNumber,limit,values);
			}

		}
		else{
			if(isHql)
			{
			result= baseDaoImpl.queryByHql(hql,values);
			}
			else{
				result= baseDaoImpl.queryBySql(hql,values);
			}

		}
		return result;
	}

	public static String getHql(String hql, List<QueryCondition> conditions,
			Map<String, Object> values,String connect)
	{
		if(!hql.toLowerCase().contains("where"))
		{
			hql=hql+" where ";
		}
		else{
		if(!hql.toLowerCase().trim().endsWith("and"))
		{
			hql=hql+" and ";
		}
		}
		
		hql = getHql2(hql,conditions, values,connect).trim();
		if(hql.endsWith("and"))
		{
			hql=hql.substring(0,hql.lastIndexOf("and"));
		}
		if(hql.endsWith("or"))
		{
			hql=hql.substring(0,hql.lastIndexOf("or"));

		}
		return hql;
	}
	@SuppressWarnings("unchecked")
	private static String getHql2(String hql, List<QueryCondition> conditions,
			Map<String, Object> values,String connect) {
		if(conditions!=null&&conditions.size()!=0)
		{

			connect=" "+connect+" ";
	    	for (QueryCondition condition : conditions) {
			String operation = condition.getOperation();
			String fieldName = condition.getFieldName()==null?"":condition.getFieldName().replaceAll(" ", "");
			String paramName=fieldName.contains(".")?fieldName.replaceAll("\\.", "_"):fieldName;
			Object value = getValueByValueType(condition);
			if (("eq".equalsIgnoreCase(operation))
					|| ("=".equalsIgnoreCase(operation))
					|| ("eqproperty".equalsIgnoreCase(operation))) {
				hql = hql  + fieldName + " = " + ":"
						+ paramName + connect;
				values.put(paramName, value);
			}
			if (("ne".equalsIgnoreCase(operation))
					|| ("!=".equalsIgnoreCase(operation))) {
				hql = hql  + fieldName + " != " + ":"
						+ paramName + connect;
				values.put(paramName, value);
			}
			if (("ge".equalsIgnoreCase(operation))
					|| (">=".equalsIgnoreCase(operation))
					|| ("geproperty".equalsIgnoreCase(operation))) {
				hql = hql  + fieldName + " >= " + ":"
						+ paramName + connect;
				values.put(paramName, value);
			}
			if (("gt".equalsIgnoreCase(operation))
					|| (">".equalsIgnoreCase(operation))
					|| ("gtproperty".equalsIgnoreCase(operation))) {
				hql = hql  + fieldName + " > " + ":"
						+ paramName + connect;
				values.put(paramName, value);
			}
			if (("le".equalsIgnoreCase(operation))
					|| ("<=".equalsIgnoreCase(operation))
					|| ("leproperty".equalsIgnoreCase(operation))) {
				hql = hql  + fieldName + " <= " + ":"
						+ paramName + connect;
				values.put(paramName, value);
			}
			if (("lt".equalsIgnoreCase(operation))
					|| ("<".equalsIgnoreCase(operation))
					|| ("ltproperty".equalsIgnoreCase(operation))) {
				hql = hql  + fieldName + " < " + ":"
						+ paramName + connect;
				values.put(paramName, value);
			}
			if ("like".equalsIgnoreCase(operation)) {
				hql = hql  + fieldName + " like " + ":"
						+ paramName + connect;
				values.put(paramName, value);
			}
			if ("ilike".equalsIgnoreCase(operation)) {
				hql = hql  + fieldName + " ilike "
						+ ":" + paramName + connect;
				values.put(paramName, value);
			}
			if ("between".equalsIgnoreCase(operation)) {
				{
					List<?> vals = (List<?>) value;
					hql = hql  + fieldName
							+ " between " + ":" + paramName + "1" 
							+":" +paramName + "2";
					values.put(paramName + "1", vals.get(0));
					values.put(paramName + "2", vals.get(1));
				}
			}
			if (("in".equalsIgnoreCase(operation))
					|| ("exist".equalsIgnoreCase(operation))) {
				hql = hql  + fieldName + " in " + ":"
						+ paramName + connect;
				values.put(paramName, value);

			}
		
			if (("notin".equalsIgnoreCase(operation))
					|| ("notin".equalsIgnoreCase(operation))) {
				hql = hql  + fieldName + " in " + ":"
						+ paramName + connect;
				values.put(paramName, value);

			}
			if ("is".equalsIgnoreCase(operation)
					) {
				if("null".equalsIgnoreCase(value.toString()))
				{
				 hql = hql  + fieldName + " is null "+connect;
				}
				else{
					hql = hql  + fieldName + " is "
					+ ":" + paramName + connect;
			       values.put(paramName, value);			
			       }

			}
			
			if ("or".equalsIgnoreCase(operation)) {
				 
			  List<Object> valueList = (List<Object>) value;
			  List<QueryCondition> cons=new ArrayList<QueryCondition>();
			  for(Object va:valueList)
			  {
				  QueryCondition con = null;
				  if(va instanceof QueryCondition)
				  {
                         con=(QueryCondition)va;
				  }
				  else{
				   con = (QueryCondition) FrameObjectUtil.convertMapToBean(QueryCondition.class, (Map<String,Object>)va);
				  }
			
				  cons.add(con);
			  }
			     hql = getHql2(hql+" ( ",cons,values,"or");
			     if(hql.endsWith("or "))
				{
						hql=hql.substring(0,hql.lastIndexOf("or "))+" ) and " ;

				}
			 
			}


		}
		}
		
		return hql;
	}

	public static Object getValueByValueType(QueryCondition condition) {
		String valueType = condition.getValueType();
		Object value = condition.getValue();
		if("or".equalsIgnoreCase(condition.getOperation()))
		{
			return value;
		}
		if (valueType.contains("[") || valueType.contains("]")) {
			if (valueType.toLowerCase().contains("Integer".toLowerCase())) {
				if (value instanceof Collection<?>) {
					List<Integer> newValue = new ArrayList<Integer>();
					Collection<?> val = (Collection<?>) value;
					for (Object v : val) {
						newValue.add(Integer.parseInt(v.toString()));

					}
					return newValue;
				}
			}
			if (valueType.toLowerCase().contains("String".toLowerCase())) {
				if (value instanceof Collection<?>) {
					List<String> newValue = new ArrayList<String>();
					Collection<?> val = (Collection<?>) value;
					for (Object v : val) {
						newValue.add(v.toString());

					}
					return newValue;
				}
			}
			if (valueType.toLowerCase().contains("Long".toLowerCase())) {
				if (value instanceof Collection<?>) {
					List<Long> newValue = new ArrayList<Long>();
					Collection<?> val = (Collection<?>) value;
					for (Object v : val) {
						newValue.add(Long.parseLong(v.toString()));

					}
					return newValue;
				}
			}
			if (valueType.toLowerCase().contains("Date".toLowerCase())) {
				if (value instanceof Collection<?>) {
					List<Date> newValue = new ArrayList<Date>();
					Collection<?> val = (Collection<?>) value;
					for (Object v : val) {
						newValue.add(FrameDateUtil.changeStringToDate(v.toString()));

					}
					return newValue;
				}
			}

		} else {
			if ("Integer".equalsIgnoreCase(valueType)) {
				return Integer.parseInt(condition.getValue().toString());
			}
			if ("String".equalsIgnoreCase(valueType)) {
				return condition.getValue().toString();
			}
			if ("Long".equalsIgnoreCase(valueType)) {
				return Long.parseLong(condition.getValue().toString());
			}
			if ("Date".equalsIgnoreCase(valueType)) {
				return FrameDateUtil.changeStringToDate(condition.getValue()
						.toString());
			}
			if ("Boolean".equalsIgnoreCase(valueType)) {
				return Boolean.parseBoolean(condition.getValue().toString());
			}
			if ("null".equalsIgnoreCase(valueType)) {
				return "null";
			}


		}
		return value;
	}

}
