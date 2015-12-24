package com.tech.learning.proxyandreflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class ReflectionTest {

	@Test
	public void testGetClass() throws ClassNotFoundException{
		Class<?> s = Class.forName("interview.proxyandreflection.Person");
		assertEquals(s.getName(),Person.class.getName());
		
		Person person = new Person();
		assertEquals(s.getName(),person.getClass().getName());
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetClassException(){
		try {
			Class.forName("Person");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testInstance() throws Exception{
		Class<?> s = Class.forName("interview.proxyandreflection.Person");
		assertTrue(s.newInstance() instanceof Person);
	}
	
	@Test
	public void testConstructor() throws Exception{
		Class<?> s = Class.forName("interview.proxyandreflection.Person");
		Constructor<?> c = s.getConstructor(String.class);
		Person person = (Person)c.newInstance("jackie");
		assertEquals(person.getName(),"jackie");
	}
	
	/**
	 * 测试反射方法 getFields与getDeclaredFields的区别，其它Methods、Constructor也一样
	 * @throws ClassNotFoundException
	 */
	@Test
	public void test4Declared() throws ClassNotFoundException{
		Class<?> s = Class.forName("interview.proxyandreflection.Person");
		Field[] fields1 = s.getDeclaredFields();
		Field[] fields2 = s.getFields();
		assertNotEquals(fields1.length,fields2.length);
		
		for(Field f1 : fields1){
			System.out.print(Modifier.toString(f1.getModifiers())+" "+f1.getName()+";");
		}
		System.out.println();
		
		for(Field f2 : fields2){
			System.out.print(Modifier.toString(f2.getModifiers())+" "+f2.getName()+";");
		}
	}
	
	/**
	 * 对于field操作要设置accessible，这样可以访问私有变量
	 * @throws Exception
	 */
	@Test
	public void testGetSomeField() throws Exception{
		Class<?> s = Class.forName("interview.proxyandreflection.Person");
		Object o = s.newInstance();
		Field field = s.getDeclaredField("age");
		//Set the accessible flag for this object to the indicated boolean value.
		field.setAccessible(true);
		
		field.set(o,12);
		assertEquals(field.get(o),12);
	}
	
	
	@Test
	public void testMethodInvoke() throws Exception{
		Class<?> s = Class.forName("interview.proxyandreflection.Person");
		Object obj = s.newInstance();
		Method setmethod = s.getDeclaredMethod("setMobileno", String.class);
		Method getmethod = s.getDeclaredMethod("getMobileno");
		setmethod.invoke(obj, "jackie");
		assertEquals(getmethod.invoke(obj),"jackie");
	}


}
