package com.member.services.back.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.ProductDao;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.entity.OrderDetails;
import com.member.entity.OrderNumber;
import com.member.entity.Product;
import com.member.entity.ShopingCart;
import com.member.entity.StockDistribute;
import com.member.form.ProductOperForm;
import com.member.services.back.ProductService;
import com.member.util.CommonUtil;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	
	@Resource(name="ProductDaoImpl")
	private ProductDao productDao;
	
	@Override
	public List<Product> getProductList(String productName,String productNumber,int pageSize,int pageNumber) {
		String hql = "from Product p where p.productStatus=0 ";
		Map<String, Object> arguments = new HashMap<String, Object>();
		if(!"".equals(productName)){
			hql=hql + " and p.productName=:productName";
			arguments.put("productName", productName);
		}
		if(!"".equals(productNumber)){
			hql=hql + " and productNumber=:productNumber";
			arguments.put("productNumber", productNumber);
		}
		hql = hql+" order by operDate desc";
		List result = productDao.queryByHql(hql,pageNumber,pageSize,arguments);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
		newPdu.setProductNumber(form.getProductNumber());
		/**产品类型 */
		newPdu.setProductModel(form.getProductModel());
		/**产品类型 */
		newPdu.setProductPrice(new BigDecimal(form.getProductPrice()));
		/**产品类型 */
		newPdu.setProductAllKC(Integer.valueOf(form.getAllfirm()));
		/**产品类型 */
		newPdu.setFirmStock(Integer.valueOf(form.getAllfirm()));
		productDao.saveOrUpdate(newPdu);
		String hql = "from Information where isFrim = 1 ";
		List<Information> result = (List<Information>)productDao.queryByHql(hql);
		if(result!= null && result.size()>0){
			for(Information info : result){
				StockDistribute sb = new StockDistribute();
				sb.setProductId(newPdu.getId());
				sb.setProductNumber(newPdu.getProductNumber());
				sb.setProductName(newPdu.getProductName());
				sb.setServerId(info.getId());
				sb.setServerNumber(info.getNumber());
				sb.setOperDate(new Date());
				sb.setServerFirm(0);
				productDao.saveOrUpdate(sb);
			}
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
		/**产品状态 0:有效，1：无效*/
		updPdu.setProductStatus(0);
		/**产品类型 */
		updPdu.setProductNumber(form.getProductNumber());
		/**产品类型 */
		updPdu.setProductModel(form.getProductModel());
		/**产品类型 */
		updPdu.setProductPrice(new BigDecimal(form.getProductPrice()));
		/**产品类型 */
		updPdu.setProductAllKC(Integer.valueOf(form.getProductAllKC()));
		
		productDao.update(updPdu);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteProduct(Integer id) {
		Product delPdu = getProductDetailById(id);
		String hql = "from StockDistribute where productId = ? ";
		List<StockDistribute> result = (List<StockDistribute>)productDao.queryByHql(hql,delPdu.getId());
		if(result!= null && result.size()>0){
			for(StockDistribute sb : result){
				productDao.delete(sb);
			}
		}
		productDao.delete(delPdu);
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

	@Override
	public int countProductList(String productName, String productNumber) {
		String hql = "from Product p where p.productStatus=0 ";
		Map<String, Object> arguments = new HashMap<String, Object>();
		if(!"".equals(productName)){
			hql=hql + " and p.productName=:productName";
			arguments.put("productName", productName);
		}
		if(!"".equals(productNumber)){
			hql=hql + " and productNumber=:productNumber";
			arguments.put("productNumber", productNumber);
		}
		
		List result = productDao.queryByHql(hql,arguments);
		if(result!= null && result.size()>0){
			return result.size();
		}
		return 0;
	}

	@Override
	public List<StockDistribute> getStockDistributeLsit(String productNumber) {
		String hql="from StockDistribute where productNumber = ?  order by serverNumber";
		List<StockDistribute> result = (List<StockDistribute>)productDao.queryByHql(hql,productNumber);
		return result;
	}

	@Override
	public Product getProductInfoByProductNumber(String productNumber) {
		String hql="from Product where productNumber = ? ";
		List<Product> result = (List<Product>)productDao.queryByHql(hql,productNumber);
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public boolean countStockDistribute(String[] idStr, String[] valueStr,Product pr) {
		if(idStr.length>0){
			int count = 0;
			for(int i=0;i<idStr.length;i++){
				String hql = "from StockDistribute where id=? ";
				List<StockDistribute> result = (List<StockDistribute>)productDao.queryByHql(hql,Integer.valueOf(idStr[i]));
				if(result!=null && result.size()>0){
					for(StockDistribute st : result){
						count = count+Integer.valueOf(valueStr[i])-st.getServerFirm();
					}
				}
			}
			if(pr.getFirmStock()<count){
				return false;
			}else{
				return true;
			}
			
		}else{
			return true;
		}
		
	}
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateStockDistribute(String[] idStr, String[] valueStr,Product pr) {
		if(idStr.length>0){
			int count = 0;
			for(int i=0;i<idStr.length;i++){
				String hql = "from StockDistribute where id=? ";
				List<StockDistribute> result = (List<StockDistribute>)productDao.queryByHql(hql,Integer.valueOf(idStr[i]));
				if(result!=null && result.size()>0){
					for(StockDistribute st : result){
						count = count+Integer.valueOf(valueStr[i])-st.getServerFirm();
						st.setServerFirm(Integer.valueOf(valueStr[i]));
						productDao.update(st);
					}
				}
			}
			if(pr!=null){
				pr.setFirmStock(pr.getFirmStock()-count);
				productDao.update(pr);
			}
			
		}
		
	}

	@Override
	public List<StockDistribute> getStockDistributeByServerNumber(String number) {
		String hql = "from StockDistribute where serverNumber=? ";
		List<StockDistribute> result = (List<StockDistribute>)productDao.queryByHql(hql,number);
		return result;
	}

	@Override
	public List<Product> getAllProductList() {
		String hql = "from Product ";
		List<Product> result = (List<Product>)productDao.queryByHql(hql);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String orderCreate(String[] idStr, String[] countStr,Information info) {
		String msg ="下单失败";
		try {
			OrderNumber orderNumber = new OrderNumber();
			BigDecimal count= new BigDecimal(0);
			String number = getTnumber();
			while (number == null) {
				number= getTnumber();
			}
			String password = getPssword();
			while (password == null) {
				password= getPssword();
			}
			
			for(int i = 0;i<idStr.length;i++){
				String hql = "from ShopingCart where id=?";
				List<ShopingCart> result = (List<ShopingCart>)productDao.queryByHql(hql,Integer.valueOf(idStr[i]));
				ShopingCart sc = result.get(0);
				String pql = "from StockDistribute where serverId=:serverId and productId=:productId";
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("serverId", sc.getServerId());
				arguments.put("productId", sc.getProductId());
				List<StockDistribute> sdtList = (List<StockDistribute>)productDao.queryByHql(pql,arguments);
				StockDistribute sdt = sdtList.get(0);
				if(sdt.getServerFirm().compareTo(Integer.valueOf(countStr[i]))==-1){
					return msg="商品编号："+sc.getProductNumber()+"库存不足。";
				}
			}
			
			
			for(int i = 0;i<idStr.length;i++){
				String hql = "from ShopingCart where id=?";
				List<ShopingCart> result = (List<ShopingCart>)productDao.queryByHql(hql,Integer.valueOf(idStr[i]));
				ShopingCart sc = result.get(0);
				String pql = "from Product where id=?";
				List<Product> productList = (List<Product>)productDao.queryByHql(pql,sc.getProductId());
				Product pt = productList.get(0);
				BigDecimal t = pt.getProductPrice().multiply(new BigDecimal(countStr[i]));
				count = count.add(t);
				OrderDetails order = new OrderDetails();
				order.setNumber(sc.getNumber());
				order.setUserId(sc.getUserId());
				order.setProductId(sc.getProductId());
				order.setProductName(sc.getProductName());
				order.setProductNumber(sc.getProductNumber());
				order.setProductTarget(sc.getProductTarget());
				order.setServerId(sc.getServerId());
				order.setServerNumber(sc.getServerNumber());
				order.setShopCount(Integer.valueOf(countStr[i]));
				order.setShopPrice(pt.getProductPrice());
				order.setTnumber(number);
				order.setStauts(3);
				order.setProductPassword(password);
				order.setAllPrice(t);
				productDao.saveOrUpdate(order);
				productDao.delete(sc);
				
				pt.setProductAllKC(pt.getProductAllKC()-Integer.valueOf(countStr[i]));
				String sdtql = "from StockDistribute where serverId=:serverId and productId=:productId";
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("serverId", sc.getServerId());
				arguments.put("productId", sc.getProductId());
				List<StockDistribute> sdtList = (List<StockDistribute>)productDao.queryByHql(sdtql,arguments);
				StockDistribute sdt = sdtList.get(0);
				sdt.setServerFirm(sdt.getServerFirm()-Integer.valueOf(countStr[i]));
				productDao.saveOrUpdate(pt);
				productDao.saveOrUpdate(sdt);
			}
			
			info.setCoupon(info.getCoupon().subtract(count));
			AccountDetails shopingDetails = new AccountDetails();
			shopingDetails.setUserNumber(info.getNumber());
			shopingDetails.setCreateTime(new Date());
			shopingDetails.setKindData(KindDataEnum.xfpp);

			/** 日期类别统计 */
			shopingDetails.setDateNumber(CommonUtil.getDateNumber());
			shopingDetails.setProject(ProjectEnum.shopping);
			/** 积分余额 */
			shopingDetails.setPointbalance(info.getShoppingMoney());
			/** 葛粮币余额 */
			shopingDetails.setGoldmoneybalance(info.getCrmMoney());
			/** 消费卷 */
			shopingDetails.setXfmoneybalance(info.getCoupon());
			/** 收入 */
			shopingDetails.setIncome(new BigDecimal(0));
			/** 支出 */
			shopingDetails.setPay(count);

			/** 备注 */
			shopingDetails.setRedmin("购物，消费："+count);
			/** 用户ID */
			shopingDetails.setUserId(info.getId());
			shopingDetails.setCountNumber(CommonUtil.getCountNumber());
			productDao.saveOrUpdate(info);
			productDao.saveOrUpdate(shopingDetails);
			orderNumber.setCreateTime(new Date());
			orderNumber.setNumber(info.getNumber());
			orderNumber.setStauts(3);
			orderNumber.setTnumber(number);
			orderNumber.setUserId(info.getId());
			orderNumber.setProductPassword(password);
			orderNumber.setAllPrice(count);
			productDao.saveOrUpdate(orderNumber);
			String username = info.getNumber();
			String phone = info.getPhoneNumber();
		//	msg = sendMsgForPhone(username,phone,number,password);
			msg="true";
			if(!"true".equals(msg)){
				try {
					productDao.delete(null);
				} catch (Exception e) {
					return msg;
				}
			}
			return "true";
		} catch (Exception e) {
			return msg;
		}
	
	}
	
	
	
	private String getTnumber(){
		String orderhql = "from OrderNumber where tnumber=?";
		Random r = new Random();
		int x = r.nextInt(999999);
		String number = null;
		if (x > 100000) {
			 number = String.valueOf(x);
			 List info =productDao.queryByHql(orderhql,"GL"+number);
			 if(info!=null && info.size()>0){
				 return null;
			 }else{
				 return "GL"+number;
			 }
		}
	 return null;
	}
	
	private String getPssword(){
		Random r = new Random();
		int x = r.nextInt(999999);
		String number = null;
		if (x > 100000) {
			 number = String.valueOf(x);
			 return number;
		}
	 return null;
	}
	
	
	private String sendMsgForPhone(String username,String phone,String orderNumber,String password){
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		
//		int mobile_code = (int)((Math.random()*9+1)*100000);

		//System.out.println(mobile);
		
	 //   String content = new String("尊进的会员："+username+"。您此次的订单编码是：" + orderNumber + "。提货密码是："+password+"。相关信息请不要透露给他人。"); 
		 String content = new String("您的验证码是：" + password + "。请不要把验证码泄露给其他人。"); 
		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_2015GL"), 
			    new NameValuePair("password", "123456gl"), //密码可以使用明文密码或使用32位MD5加密
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", phone), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();


			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
						
			 if("2".equals(code)){
				return "true";
			}
			 if("0".equals(code)){
					return "提交失败";
				}
			 if("400".equals(code)){
					return "非法ip访问";
				}
			 if("401".equals(code)){
					return "帐号不能为空";
				}
			 if("402".equals(code)){
					return "密码不能为空";
				}
			 if("4030".equals(code)){
					return "手机号码已被列入黑名单";
				}
			 if("4050".equals(code)){
					return "账号被冻结";
				}
			 if("4051".equals(code)){
					return "剩余条数不足";
				}
			 if("408".equals(code)){
					return "您的帐户疑被恶意利用，已被自动冻结，如有疑问请与客服联系。";
				}
			 if("406".equals(code)){
					return "手机格式不正确。";
				}
			 
			 return "手机发送短信失败";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "手机发送短信异常";
		}  
		
	}

	@Override
	public List<OrderNumber> getOrderNumberListByNumber(String number,String tnumber,int stauts,int pageSize,int pageNumber) {
		String hql = "select dt from OrderNumber dt where dt.id in(select distinct p.id from OrderNumber p,OrderDetails pt where p.tnumber = pt.tnumber and pt.stauts=:stauts and pt.number=:number ) ";
		Map<String, Object> arguments = new HashMap<String, Object>();
		if(!"".equals(tnumber)&& tnumber!=null){
			hql = hql+" and dt.tnumber=:tnumber";
			arguments.put("tnumber", tnumber);
		}
		hql= hql+" order by dt.createTime";
		arguments.put("number", number);
		arguments.put("stauts", stauts);
		List<OrderNumber> result = (List<OrderNumber>)productDao.queryByHql(hql,pageNumber,pageSize,arguments);
		return result;
	}
	
	@Override
	public int countOrderNumberListByNumber(String number,String tnumber,int stauts) {
		String hql = "select dt.id  from OrderNumber dt where dt.id in(select distinct p.id from OrderNumber p,OrderDetails pt where p.tnumber = pt.tnumber and pt.stauts=:stauts and pt.number=:number ) ";
		Map<String, Object> arguments = new HashMap<String, Object>();
		if(!"".equals(tnumber)&& tnumber!=null){
			hql = hql+" and dt.tnumber=:tnumber";
			arguments.put("tnumber", tnumber);
		}
		hql= hql+" order by dt.createTime";
		arguments.put("number", number);
		arguments.put("stauts", stauts);
		List result = productDao.queryBySql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.size();
		}
		return 0;
	}

	@Override
	public OrderNumber getOrderNumberById(int id) {
		String hql = "from OrderNumber where id=?";
		List<OrderNumber> result = (List<OrderNumber>)productDao.queryByHql(hql,id);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<OrderDetails> getOrderDetailsListByNumber(String number,
			String tnumber, int status) {
		String hql = "from OrderDetails where number=:number and tnumber=:tnumber and stauts=:stauts";
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("tnumber", tnumber);
		arguments.put("number", number);
		arguments.put("stauts", status);
		List<OrderDetails> result = (List<OrderDetails>)productDao.queryByHql(hql,arguments);
		return result;
	}
}
