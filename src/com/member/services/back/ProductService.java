package com.member.services.back;

import java.util.List;

import com.member.entity.Information;
import com.member.entity.OrderDetails;
import com.member.entity.OrderNumber;
import com.member.entity.Product;
import com.member.entity.StockDistribute;
import com.member.form.ProductOperForm;

public interface ProductService {

	public List<Product> getProductList(String productName,String productNumber,int pageSize,int pageNumber);
	
	public List<Product> getAllProductList();
	
	public Product getProductInfoByProductNumber(String productNumber);
	
	public int countProductList(String productName,String productNumber);
	
	public Product getProductDetailById(Integer id);
	
	public void saveProduct(ProductOperForm form);
	
	public void updateProduct(ProductOperForm form);
	
	public void deleteProduct(Integer id);
	
	public List<StockDistribute> getStockDistributeLsit(String productNumber);
	
	public void updateStockDistribute(String[] idStr,String[] valueStr,Product pr);
	
	public boolean countStockDistribute(String[] idStr, String[] valueStr,Product pr);
	
	public String orderCreate(String[] idStr, String[] countStr,Information info);
	
	public List<StockDistribute> getStockDistributeByServerNumber(String number);
	
	public List<OrderNumber> getOrderNumberListByNumber(String number,String tnumber,int status,int pageSize,int pageNumber);
	
	public int countOrderNumberListByNumber(String number,String tnumber,int stauts);
	
	public OrderNumber getOrderNumberById(int id);
	
	public List<OrderDetails> getOrderDetailsListByNumber(String number,String tnumber,int status);

	
	
}
