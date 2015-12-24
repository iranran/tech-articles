package com.tech.learning.proxyandreflection;

public class Person {
	private String name;
    
	private int age;
	
	public String mobileno;
	
	public Person(){
		
	}
	
	public Person(String name){
		this.name = name;
	}
    
	public String getName() {
        return name;
    }
	
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    
    public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", mobileno="
				+ mobileno + "]";
	}
}
