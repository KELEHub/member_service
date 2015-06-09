package com.member.services.back;

import java.util.List;

import com.member.entity.Product;
import com.member.form.ProductOperForm;

public interface ProductService {

	public List<Product> getProductList(ProductOperForm form);
	
	public Product getProductDetailById(Integer id);
	
	public void saveProduct(ProductOperForm form);
	
	public void updateProduct(ProductOperForm form);
	
	public void deleteProduct(Integer id);
}
