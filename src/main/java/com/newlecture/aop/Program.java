package com.newlecture.aop;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		Calculator origin= new NewlecCalculator();
		
		// Proxy를 생성해서 실제 주업무 로직을 위임.
		Calculator cal = (Calculator) Proxy.newProxyInstance(
								NewlecCalculator.class.getClassLoader(), 
								new Class[] {Calculator.class},  //프록시는 원본에 해당하는 동일한 인터페이스를 넘겨받아야한다.
								new InvocationHandler() {
									
									@Override
									public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
										System.out.println("사전처리 보조업무");
										Object result = method.invoke(origin,  args);
										return result;
									}

								});
		
		//int data = origin.add(3, 4);
		int data =cal.add(3,4);  // 사전처리보조업무 print


		System.out.println(data);
	}

}
