package com.china1168.utils;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class JavaXMLUtils {

	public String parserXml(String fileName) {
		// System.out.println( this.getClass().getClassLoader().getSystemClassLoader());
		fileName=JavaXMLUtils.class.getResource("/").getPath()+fileName;
	System.out.println(JavaXMLUtils.class.getResource("/").toString());
		 
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		StringBuffer select = new StringBuffer();
		try {
			Document document = saxReader.read(inputXml);
			Element ele = document.getRootElement(); // h获取根节点
			for (Iterator i = ele.elementIterator(); i.hasNext();) { // 再从子节点在遍历其子节点
				Element employee = (Element) i.next();
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					Attribute ageAttr = node.attribute("PARENTID");
					if (node.getName().equals("PARENTID")) {
						select.append("<li id='" + node.getText());
					}
					if (node.getName().equals("NAME")) {
						select.append("' onclick=\"choseCity(this)\">"
								+ node.getText() + "</li>");

					}
					// System.out.println(node.getName() + ":" +
					// node.getText());

				}

			}
			System.out.println(select.toString());
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		return select.toString();
	}

	public String searchCity(String fileName, String fatherCity) {
		fileName=JavaXMLUtils.class.getResource("/").getPath()+fileName;
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		StringBuffer select = new StringBuffer();
		try {
			Document document = saxReader.read(inputXml);
			Element ele = document.getRootElement(); // h获取根节点
			for (Iterator i = ele.elementIterator(); i.hasNext();) { // 再从子节点在遍历其子节点
				Element employee = (Element) i.next();
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					Attribute ageAttr = node.attribute("PARENTID");
					// <li id="<%=info.getId() %>"
					// onclick="forIndex(this)"><%=info.getName() %></li>
					if (node.getName().equals("PARENTID")) {
						if (fatherCity.equals(node.getText())) {
							Element id = (Element)node.getParent().elements().get(1);
							Element name = (Element)node.getParent().elements().get(3);
							
							select.append("<li id='" + id.getText());
							
							
							
							//System.out.println(name.getText());
							select.append("' onclick=\"forIndex(this)\">"
									+ name.getText() + "</li>");
						}
						
					}
					
					// System.out.println(node.getName() + ":" +
					// node.getText());

				}

			}
			System.out.println(select.toString());
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		return select.toString();
	}

	public static void main(String[] args) {
		JavaXMLUtils javaXMLUtils = new JavaXMLUtils();
		javaXMLUtils.parserXml("/provincelist.xml");
		// javaXMLUtils.searchCity(
		//	"D:/workSpace6.5/ztq_wx/WebRoot/WEB-INF/citylist.xml", "25169");
	}

}
