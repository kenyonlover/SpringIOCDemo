package com.springioc;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.springioc.entity.User;

/**
 * 作者：administrator
 * 时间：2019年5月14日 下午9:28:26
 * 说明：手写SpringIOC，帮助理解反射机制，读取xml中的bean使用反射机制创建对应的Javabean，并赋值
 */
public class SpringIOCTest {

	public static void main(String[] args) throws Exception {
		ApplicationResouceReader arr = new ApplicationResouceReader("springIocTest.xml");
		User bean = (User) arr.getBean("user1");
		System.out.println(bean.toString());
	}

}

class ApplicationResouceReader {
	private String url;

	public ApplicationResouceReader(String url) {
		this.url = url;
	}

	public Object getBean(String id) throws Exception {

		// 获取到xml文件
		InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(url);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(resourceAsStream);
		// 解析XML，获取到beanId相同的节点
		Element beans = doc.getRootElement();
		List<Element> beanlist = beans.elements();
		Element ele;
		for (int i = 0; i < beanlist.size(); i++) {
			ele = beanlist.get(i);
			if (!ele.attributeValue("id").equals(id)) {
				continue;
			}
			// 拿到我们的class全路径，方便之后反射
			String beanClass = ele.attributeValue("class");
			// 反射出实体类
			Class<?> forName = Class.forName(beanClass);
			Object userInstance = forName.newInstance();
			
			// 获取当前符合id的节点的属性列表
			List<Element> attrslist = ele.elements();
			
			// 对属性进行赋值
			for (int j = 0; j < attrslist.size(); j++) {
				String key_zhi = attrslist.get(j).attributeValue("key");
				// 这个是获取到key里面的name和sex
				Field declaredField = forName.getDeclaredField(key_zhi);
				String value_zhi = attrslist.get(j).attributeValue("value");
				declaredField.setAccessible(true);
				declaredField.set(userInstance, value_zhi);
			}
			return userInstance;
		}

		return null;
	}

}
