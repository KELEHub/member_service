package com.member.form;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileForm {

	private String relativelyPath;
	
	private MultipartFile[] files;

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getRelativelyPath() {
		return relativelyPath;
	}

	public void setRelativelyPath(String relativelyPath) {
		this.relativelyPath = relativelyPath;
	}
}
