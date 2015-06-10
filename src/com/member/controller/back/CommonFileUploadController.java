package com.member.controller.back;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.member.beans.back.UploadFileResultDto;
import com.member.form.ProductOperForm;
import com.member.form.UploadFileForm;
import com.member.helper.BaseResult;

@Controller
@RequestMapping(value = "/upload")
public class CommonFileUploadController {
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<List<UploadFileResultDto>> headByFile(UploadFileForm form,HttpServletRequest request,Model model) throws IOException {
		BaseResult<List<UploadFileResultDto>> result = new BaseResult<List<UploadFileResultDto>>();
		List<UploadFileResultDto> uploadFileList = new ArrayList<UploadFileResultDto>();
		

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path;
		
		MultipartFile[] files = form.getFiles();
		int length = files.length;
		for(int i=0;i<length;i++){
			UploadFileResultDto uploadResult = new UploadFileResultDto();
			MultipartFile file = files[0];
			// 保存到文件
			String tempServerPath = getLocalTempFilePath(file,form,request);
			uploadResult.setRelativePath(tempServerPath);
			uploadResult.setAbsolutePath(basePath);
			uploadFileList.add(uploadResult);
		}
		
		model.addAttribute("basePath", basePath);
		result.setSuccess(true);
		result.setResult(uploadFileList);
		return result;
	}
	
	@RequestMapping(value = "/testForm", method = RequestMethod.POST)
	public void test(@RequestParam("file")   MultipartFile file,ProductOperForm form,Model model){
		System.out.println(form.getId());
	}
	
	private String getLocalTempFilePath(MultipartFile file,UploadFileForm form,HttpServletRequest request) throws IOException{
		InputStream fileInputStream = file.getInputStream();
		
		ByteArrayOutputStream buf = new ByteArrayOutputStream(8192);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = fileInputStream.read(b)) != -1) {
			buf.write(b, 0, len);
		}
		fileInputStream.close();

		byte[] arr = buf.toByteArray();
		
		String tempServerPath = request.getRealPath("");
		
		//
		String returnPath = "/uploadFile/"+form.getRelativelyPath()+"/";
		String osname = System.getProperty("os.name");
		if(osname.indexOf("Windows")>=0){
			
			tempServerPath = tempServerPath+"\\uploadFile\\"+form.getRelativelyPath()+"\\";
		}else {
			tempServerPath = tempServerPath+"/uploadFile/"+form.getRelativelyPath()+"/";
		}
		String filename = System.currentTimeMillis()+file.getOriginalFilename();
		File headPath = new File(tempServerPath);//获取文件夹路径
        if(!headPath.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
        	headPath.mkdirs();
        }
		FileOutputStream out = new FileOutputStream(tempServerPath+ filename);
		out.write(arr);
		out.close();
		return returnPath+ filename;
	}
}
