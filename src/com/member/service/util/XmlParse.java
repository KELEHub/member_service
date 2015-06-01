package com.member.service.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

/**
 * 初始化终端参�?
 * @author zj
 *
 */
@Component
@SuppressWarnings("unchecked")
 public class XmlParse {
	
	@SuppressWarnings("unused")
	private void initStart() throws Exception {
	

		SAXReader reader = new SAXReader();
		// 通过read方法读取�?��文件 转换成Document对象
		Map<String,Object> oidConfig = new  HashMap<String,Object>();
		Object path = XmlParse.class
		.getResource("/config/frame/OidConfig.xml").getPath();
		Document document = reader
				.read(new File(path.toString()));
		Element root = document.getRootElement();
		parseChildrenNode(root, 1);

	}

	private void parseChildrenNode(Element parentNode, int parentDeep) {
		parentDeep++;
		Iterator<Element> childNodes = parentNode.elementIterator();
		if(childNodes==null)
		{
			return;
		}
		while (childNodes.hasNext()) {
		
			Element childNode = childNodes.next();
			if(parentDeep==3)
			{
				String parentNodeName = parentNode.attribute("name").getValue()
						.replaceAll(" ", "");
				
			    saveTerminalParameterOid(parentNodeName,childNode);
			
		
			}
			parseChildrenNode(childNode,parentDeep);
			
		}

	}
	public void saveTerminalParameterOid(String parentNodeName,
		
			Element childNode) {
		//String parameterNameEn=childNode.attributeValue("parameterNameEn","").replaceAll(" ","");
		//String parameterOid=childNode.attributeValue("parameterOid","").replaceAll(" ","");

		
		
	}

}
