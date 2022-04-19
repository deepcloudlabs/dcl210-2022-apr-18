package com.example.lottery.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-100)
public class ProfilingAspect {

	@Around("(@annotation(profiled) || @target(profiled)) && execution(* com.example.lottery..*(..))")
	public Object profile(ProceedingJoinPoint pjp,Profiled profiled) throws Throwable {
		var start = System.nanoTime();
		var result = pjp.proceed();
		var stop = System.nanoTime();
		System.out.println("%s runs %d ns.".formatted(pjp.getSignature().getName(), stop - start));
		return result;
	}	
}
