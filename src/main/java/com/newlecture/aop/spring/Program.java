package com.newlecture.aop.spring;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		Calculator origin= new NewLecCalculator();
		
		// Proxy�� �����ؼ� ���� �־��� ������ ����.
		
		
		//int data = origin.add(3, 4);
		int data =cal.add(3,4);  // ����ó���������� print


		System.out.println(data);
	}

}
