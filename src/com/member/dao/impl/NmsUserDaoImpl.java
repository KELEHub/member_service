package com.member.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Component;

import com.member.beans.back.MenuBean;
import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.helper.dao.impl.BaseDaoImpl;

@Component("NmsUserDaoImpl")
 public class NmsUserDaoImpl extends BaseDaoImpl implements NmsUserDao {

	@SuppressWarnings("unchecked")
	public List<String> getUserDataPermissonCode(Integer userId) {
		
		List<String> dataPermissionCodes=(List<String>) queryByHql(HqlUserRole.getUserDataPermission,userId);

		return dataPermissionCodes;
	}

	@SuppressWarnings("unchecked")
	public List<MenuBean> getMenuByUserId(Integer userId) {
		String sql="select "
				+ " mm.id as id,"
				+ " mm.menu_nm as menunm,"
				+ " mm.menu_url as menuurl,"
				+ " mm.p_menu_id as pid"
				+ " from t_menu mm where mm.id in( "
				+ " select mrmh.menu_id from t_user_role_hub murh "
				+ " inner join t_role_menu_hub mrmh on murh.role_id=mrmh.role_id "
				+ " where murh.user_id=?"
				+ ")";
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(userId);
		Session session = getCurrentSession();
		Query q = session.createSQLQuery(sql)
				.addScalar("id", IntegerType.INSTANCE)
				.addScalar("menunm", org.hibernate.type.StringType.INSTANCE)
				.addScalar("menuurl", org.hibernate.type.StringType.INSTANCE)
				.addScalar("pid", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(MenuBean.class));
		if (arguments != null) {
			int len = arguments.size();
			for (int i = 0; i < len; i++) {
				q.setParameter(i, arguments.get(i));

			}
		}
		List<MenuBean> list = q.list();
		return list;
	}

	
	@SuppressWarnings("unchecked")
	public List<MenuBean> getFrontMenuAll() {
		String sql="select "
				+ " mm.id as id,"
				+ " mm.menu_nm as menunm,"
				+ " mm.menu_url as menuurl,"
				+ " mm.p_menu_id as pid"
				+ " from member_menu mm";
		Session session = getCurrentSession();
		Query q = session.createSQLQuery(sql)
				.addScalar("id", IntegerType.INSTANCE)
				.addScalar("menunm", org.hibernate.type.StringType.INSTANCE)
				.addScalar("menuurl", org.hibernate.type.StringType.INSTANCE)
				.addScalar("pid", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(MenuBean.class));
		List<MenuBean> list = q.list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MenuBean> getFrontMenuBySome() {
		String sql="select "
			+ " mm.id as id,"
			+ " mm.menu_nm as menunm,"
			+ " mm.menu_url as menuurl,"
			+ " mm.p_menu_id as pid"
			+ " from member_menu mm where mm.menu_status='0'";
		Session session = getCurrentSession();
		Query q = session.createSQLQuery(sql)
				.addScalar("id", IntegerType.INSTANCE)
				.addScalar("menunm", org.hibernate.type.StringType.INSTANCE)
				.addScalar("menuurl", org.hibernate.type.StringType.INSTANCE)
				.addScalar("pid", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(MenuBean.class));
		List<MenuBean> list = q.list();
		return list;
	}
	
	
}
