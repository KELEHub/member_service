package com.member.services.back.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.member.dao.ProductDao;
import com.member.entity.Product;
import com.member.form.ProductOperForm;
import com.member.services.back.ProductService;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Resource(name="ProductDaoImpl")
	private ProductDao productDao;
	
	@Override
	public List<Product> getProductList(ProductOperForm form) {
		String hql = "from Product p where p.productStatus=0";
		List result = productDao.queryByHql(hql);
		return result;
	}

	@Override
	public void saveProduct(ProductOperForm form) {
		Product newPdu = new Product();
		/**产品名称 */
		newPdu.setProductName(form.getProductName());
		/**产品介绍 */
		newPdu.setProductIntroduction(form.getProductIntroduction());
		/**产品图片路径 */
		newPdu.setProductTarget(form.getProductTarget());
		/**操作时间 */
		newPdu.setOperDate(new Date());
		/**产品状态 0:有效，1：无效*/
		newPdu.setProductStatus(0);
		/**产品类型 */
		newPdu.setProductCategory(form.getProductCategory());
		productDao.save(newPdu);
	}

	@Override
	public void updateProduct(ProductOperForm form) {
		
		Product updPdu = getProductDetailById(form.getId());
		/**产品名称 */
		updPdu.setProductName(form.getProductName());
		/**产品介绍 */
		updPdu.setProductIntroduction(form.getProductIntroduction());
		/**产品图片路径 */
		updPdu.setProductTarget(form.getProductTarget());
		/**操作时间 */
		updPdu.setOperDate(new Date());
		/**产品类型 */
		updPdu.setProductCategory(form.getProductCategory());
		productDao.update(updPdu);
		
	}

	@Override
	public void deleteProduct(Integer id) {
		Product delPdu = getProductDetailById(id);
		/**操作时间 */
		delPdu.setOperDate(new Date());
		/**产品状态 0:有效，1：无效*/
		delPdu.setProductStatus(1);
		productDao.update(delPdu);
	}

	@Override
	public Product getProductDetailById(Integer id) {
		String hql = "from Product p where p.productStatus=0 and p.id=?";
		List result = productDao.queryByHql(hql,id);
		if(result!=null && result.size()>0){
			return (Product) result.get(0);
		}else{
			return null;
		}
	}
}
