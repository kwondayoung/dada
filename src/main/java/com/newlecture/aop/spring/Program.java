package com.newlecture.aop.spring;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		Calculator origin= new NewLecCalculator();
		
		// Proxy를 생성해서 실제 주업무 로직을 위임.
		
		
		//int data = origin.add(3, 4);
		int data =cal.add(3,4);  // 사전처리보조업무 print


		System.out.println(data);
	}

}
