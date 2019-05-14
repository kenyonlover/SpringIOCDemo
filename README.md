# SpringIOCDemo
手写SpringIOC，帮助理解反射机制，读取xml中的bean使用反射机制创建对应的Javabean，并赋值
```
Class<?> forName = Class.forName("com.springioc.entity.User");
Object newInstance = forName.newInstance();

Field declaredField = forName.getDeclaredField("name");
Field declaredField2 = forName.getDeclaredField("sex");

declaredField.setAccessible(true);
declaredField2.setAccessible(true);

declaredField.set(newInstance, "小红");
declaredField2.set(newInstance, "女");

User user = (User) newInstance;
System.out.println(user.toString());
```
