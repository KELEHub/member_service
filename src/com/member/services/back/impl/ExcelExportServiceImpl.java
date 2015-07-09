package com.member.services.back.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.member.entity.Charge;
import com.member.entity.Withdrawals;
import com.member.services.back.ExcelExportService;

@Service("ExcelExportServiceImpl")
public class ExcelExportServiceImpl implements ExcelExportService {
	
	public InputStream exportWithdrawals(List<Withdrawals> list) throws Exception{
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("提现统计 ");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue("会员编号 ");
        cell.setCellStyle(style);  
  
        HSSFCell cell1 = row.createCell(1);  
        cell1.setCellValue("收款账户 ");
        cell1.setCellStyle(style); 
        
        HSSFCell cell2 = row.createCell(2);  
        cell2.setCellValue("收款户姓名 ");
        cell2.setCellStyle(style); 
        
        HSSFCell cell3 = row.createCell(3);  
        cell3.setCellValue("转账金额 ");
        cell3.setCellStyle(style); 
        
        HSSFCell cell4 = row.createCell(4);  
        cell4.setCellValue("备注 ");
        cell4.setCellStyle(style); 
        
        HSSFCell cell5 = row.createCell(5); 
        cell5.setCellValue("收款银行 ");
        cell5.setCellStyle(style); 
        
        HSSFCell cell6 = row.createCell(6); 
        cell6.setCellValue("收款银行支行 ");
        cell6.setCellStyle(style); 
        
        HSSFCell cell7 = row.createCell(7); 
        cell7.setCellValue("收款省/直辖市 ");
        cell7.setCellStyle(style); 
        
        HSSFCell cell8 = row.createCell(8); 
        cell8.setCellValue("收款市县 ");
        cell8.setCellStyle(style); 
        
        HSSFCell cell9 = row.createCell(9); 
        cell9.setCellValue("余额"); 
        cell9.setCellStyle(style); 
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        for (int i = 0; i < list.size(); i++)  
        {  
        	 //"姓名: 4 > 银行名称:中国建设银行 > 银行账号:4112 > 银行地址:重庆 重庆市 万州区 重庆1 > 联系电话:15884560698"
        	
        	Withdrawals t = list.get(i);
            // 第四步，创建单元格，并设置值  
            String backInfo = t.getWithdrawalsBackInfo();
            
            //分开总的字符串
            String[] arr1 = backInfo.split(" > ");
            
            //姓名
            String[] arr2 = arr1[0].split(":");
            
            //银行名称
            String[] arr3 = arr1[1].split(":");
            
            //银行账号
            String[] arr4 = arr1[2].split(":");
            
            //银行地址
            String[] arr5 = arr1[3].split(":");
            String[] arr6 = arr5[1].split(" ");
            
            HSSFRow row1 = sheet.createRow(i+1);  
			// 会员编号
            row1.createCell(0).setCellValue(t.getNumber());
			// 收款账户
            row1.createCell(1).setCellValue(arr4[1]);
			// 收款户姓名
            row1.createCell(2).setCellValue(arr2[1]);
			// 转账金额
            row1.createCell(3).setCellValue(t.getTradeAmt().toString());
			// 备注
            row1.createCell(4).setCellValue("提现");
			// 收款银行
            row1.createCell(5).setCellValue(arr3[1]);
			// 收款银行支行
            row1.createCell(6).setCellValue(arr6[3]);
			// 收款省/直辖市
            row1.createCell(7).setCellValue(arr6[0]);
			// 收款市县
            row1.createCell(8).setCellValue(arr6[1]);
			// 余额
            row1.createCell(9).setCellValue(t.getBalanceAmt().toString());
        }  
        // 第六步，将文件存到指定位置  
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		
		return is;
	}

	@Override
	public InputStream exportCharge(List<Charge> list) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("提现统计 ");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue("会员编号 ");
        cell.setCellStyle(style);  
  
        HSSFCell cell1 = row.createCell(1);  
        cell1.setCellValue("账户 ");
        cell1.setCellStyle(style); 
        
        HSSFCell cell2 = row.createCell(2);  
        cell2.setCellValue("姓名 ");
        cell2.setCellStyle(style); 
        
        HSSFCell cell3 = row.createCell(3);  
        cell3.setCellValue("充值金额 ");
        cell3.setCellStyle(style); 
        
        HSSFCell cell4 = row.createCell(4);  
        cell4.setCellValue("备注 ");
        cell4.setCellStyle(style); 
        
        HSSFCell cell5 = row.createCell(5); 
        cell5.setCellValue("充值银行 ");
        cell5.setCellStyle(style); 
        
        HSSFCell cell6 = row.createCell(6); 
        cell6.setCellValue("充值银行支行 ");
        cell6.setCellStyle(style); 
        
        HSSFCell cell7 = row.createCell(7); 
        cell7.setCellValue("充值银行省/直辖市 ");
        cell7.setCellStyle(style); 
        
        HSSFCell cell8 = row.createCell(8); 
        cell8.setCellValue("充值银行市县 ");
        cell8.setCellStyle(style); 
        
//        HSSFCell cell9 = row.createCell(9); 
//        cell9.setCellValue("余额"); 
//        cell9.setCellStyle(style); 
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        for (int i = 0; i < list.size(); i++)  
        {  
        	 //"姓名: 4 > 银行名称:中国建设银行 > 银行账号:4112 > 银行地址:重庆 重庆市 万州区 重庆1 > 联系电话:15884560698"
        	
        	Charge t = list.get(i);
            // 第四步，创建单元格，并设置值  
            String backInfo = t.getChageBackInfo();
            
            //分开总的字符串
            String[] arr1 = backInfo.split(" > ");
            
            //姓名
            String[] arr2 = arr1[0].split(":");
            
            //银行名称
            String[] arr3 = arr1[1].split(":");
            
            //银行账号
            String[] arr4 = arr1[2].split(":");
            
            //银行地址
            String[] arr5 = arr1[3].split(":");
            String[] arr6 = arr5[1].split(" ");
            
            HSSFRow row1 = sheet.createRow(i+1);  
			// 会员编号
            row1.createCell(0).setCellValue(t.getNumber());
			// 收款账户
            row1.createCell(1).setCellValue(arr4[1]);
			// 收款户姓名
            row1.createCell(2).setCellValue(arr2[1]);
			// 转账金额
            row1.createCell(3).setCellValue(t.getChageAmt().toString());
			// 备注
            row1.createCell(4).setCellValue("充值");
			// 收款银行
            row1.createCell(5).setCellValue(arr3[1]);
			// 收款银行支行
            row1.createCell(6).setCellValue(arr6[3]);
			// 收款省/直辖市
            row1.createCell(7).setCellValue(arr6[0]);
			// 收款市县
            row1.createCell(8).setCellValue(arr6[1]);
        }  
        // 第六步，将文件存到指定位置  
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		
		return is;
	}
	
}
