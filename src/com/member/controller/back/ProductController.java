package com.member.controller.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Product;
import com.member.form.ProductOperForm;
import com.member.helper.BaseResult;
import com.member.services.back.ProductService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@Resource(name = "ProductServiceImpl")
	private ProductService productService;
	
	
	
	@RequestMapping(value = "/getProductList",method = RequestMethod.POST)
	public String getProductList(Model model){
		return "back/product/showproduct";
	}
	
	
	
	
	@RequestMapping(value = "/getProductPage")
	@ResponseBody
	public Map getProductPage(HttpServletRequest request,Model model) {
		String productName = request.getParameter("productName");
		String productNumber = request.getParameter("productNumber");
		String iDisplayLength = request.getParameter("iDisplayLength");	
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		int iTotalRecords = productService.countProductList(productName,productNumber);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<Product> result = productService.getProductList(productName,productNumber,Integer.parseInt(iDisplayLength),
				pageNumber);
		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
	

	@RequestMapping(value = "/getProduDetail",method = RequestMethod.POST)
	public String getProduDetail(@RequestBody ProductOperForm form,Model model){
		Product result = productService.getProductDetailById(form.getId());
		model.addAttribute("result", result);
		return "back/product/showproductdetail";
	}
	
	@RequestMapping(value = "/saveProduct",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> saveProduct(@RequestBody ProductOperForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			if("".equals(form.getAllfirm()) || "".equals(form.getProductModel()) || "".equals(form.getProductName()) || "".equals(form.getProductNumber()) || "".equals(form.getProductPrice()) || "".equals(form.getProductIntroduction())){
				result.setMsg("请完整的填写商品信息");
				result.setSuccess(false);
				return result;
			}
			Product p = productService.getProductInfoByProductNumber(form.getProductNumber());
			if(p != null){
				result.setMsg("商品编号已经存在，请重新尝试");
				result.setSuccess(false);
				return result;
			}
			productService.saveProduct(form);
			result.setMsg("保存产品信息成功.");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			result.setMsg("商品信息填写有误.请输入正确的数字");
			result.setSuccess(false);
			return result;
		}
		
	}
	
	@RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> updateProduct(@RequestBody ProductOperForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		if("".equals(form.getAllfirm()) || "".equals(form.getProductModel()) || "".equals(form.getProductName()) || "".equals(form.getProductNumber()) || "".equals(form.getProductPrice()) || "".equals(form.getProductIntroduction())){
			result.setMsg("请完整的填写商品信息");
			result.setSuccess(true);
			return result;
		}
		productService.updateProduct(form);
		result.setMsg("更新产品信息成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/deleteProduct",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteProduct(@RequestBody ProductOperForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			productService.deleteProduct(form.getId());
			result.setMsg("删除产品信息成功.");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			result.setMsg("删除产品信息失败.");
			result.setSuccess(false);
			return result;
		}
		
	}
	
}
