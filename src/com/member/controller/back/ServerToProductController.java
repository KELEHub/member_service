package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Product;
import com.member.entity.StockDistribute;
import com.member.form.back.ServerToProductForm;
import com.member.helper.BaseResult;
import com.member.services.back.ProductService;

@Controller
@RequestMapping(value = "/ServerToProduct")
public class ServerToProductController {
	
	@Resource(name = "ProductServiceImpl")
	private ProductService productService;
	
	
	
	@RequestMapping(value = "/getServerToProduct",method = RequestMethod.POST)
	public String getServerToProductList(Model model){
		return "back/product/firmDisbute";
	}
	
	@RequestMapping(value = "/getStockList",method = RequestMethod.POST)
	public String getStockList(@RequestBody ServerToProductForm form,Model model){
		try {
			if(form.getProductNumber()!=null && !"".equals(form.getProductNumber().trim())){
				 List<StockDistribute> result =  productService.getStockDistributeLsit(form.getProductNumber());
				 Product pr =  productService.getProductInfoByProductNumber(form.getProductNumber());
				 model.addAttribute("result",result);
				 model.addAttribute("productNumber", form.getProductNumber());
				 model.addAttribute("productName", pr.getProductName());
				 model.addAttribute("compentFirm", pr.getFirmStock());
				 model.addAttribute("productAllKC", pr.getProductAllKC());
				 
			 }
		} catch (Exception e) {
			
		}
		 
		return "back/product/firmDisbute";
	}
	
	@RequestMapping(value = "/setProductFirm")
	@ResponseBody
	public BaseResult<Void> setProductFirm(@RequestBody ServerToProductForm form,HttpServletRequest request, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			if(!"".equals(form.getIdArr())){
				String[] idStr = form.getIdArr().split(",");
				String[] valueStr = form.getValueArr().split(",");
				for(int i=0;i<valueStr.length;i++){
					if("".equals(valueStr[i].trim())){
						 result.setMsg("数量不能为空");
						 result.setSuccess(false);
						 return result;
					}
					int a = Integer.valueOf(valueStr[i].trim());
				}
				 Product pr =  productService.getProductInfoByProductNumber(form.getProductNumber());
				 boolean flg =  productService.countStockDistribute(idStr, valueStr, pr);
				 if(!flg){
					 result.setMsg("公司库存不足，请重新调整分配");
						result.setSuccess(false);
						return result;
				 }
				productService.updateStockDistribute(idStr, valueStr,pr);
				result.setMsg("分配完成成功");
				result.setSuccess(true);
				return result;
			}
			result.setMsg("没有分配对象");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			result.setMsg("数量只能填写为数字");
			result.setSuccess(false);
			return result;
		}
		
	}
	

}
