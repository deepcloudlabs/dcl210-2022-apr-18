package com.example.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

public class AuditingHandler implements InvocationHandler {
	private final Object target;

	public AuditingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("%s runs at %s.".formatted(method.getName(),new Date()));
		System.out.println("%s takes the parameters: %s.".formatted(method.getName(),Arrays.toString(args)));
		var result = method.invoke(target, args);
		System.out.println("%s return %s.".formatted(method.getName(),result));
		return result;
	}

}
