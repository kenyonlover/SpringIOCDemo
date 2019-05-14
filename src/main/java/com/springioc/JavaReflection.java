package com.springioc;

import java.lang.reflect.Field;

import com.springioc.entity.User;

/**
 * 作者：administrator
 * 时间：2019年5月14日 下午9:35:58
 * 说明：通过Java反射机制创建对象
 */
public class JavaReflection {

	public static void main(String[] args) throws Exception {
		Class<?> forName = Class.forName("com.springioc.entity.User");
		// 通过无参构造函数创建示例
		// Constructor<?> constructor = forName.getConstructor(String.class, String.class);
		// User user = (User) constructor.newInstance("小红", "女");
		Object newInstance = forName.newInstance();

		Field declaredField = forName.getDeclaredField("name");
		Field declaredField2 = forName.getDeclaredField("sex");

		declaredField.setAccessible(true);
		declaredField2.setAccessible(true);

		declaredField.set(newInstance, "小红");
		declaredField2.set(newInstance, "女");

		User user = (User) newInstance;
		System.out.println(user.toString());
	}

}
