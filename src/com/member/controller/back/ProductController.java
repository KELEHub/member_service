package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

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
		ProductOperForm form = new ProductOperForm();
		List<Product> result = productService.getProductList(form);
		model.addAttribute("result", result);
		return "back/product/showproduct";
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
		productService.saveProduct(form);
		result.setMsg("保存产品信息成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> updateProduct(@RequestBody ProductOperForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		productService.updateProduct(form);
		result.setMsg("更新产品信息成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/deleteProduct",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteProduct(@RequestBody ProductOperForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		productService.deleteProduct(form.getId());
		result.setMsg("删除产品信息成功.");
		result.setSuccess(true);
		return result;
	}
	
}
