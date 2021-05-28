package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LogAdvice {
	
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}

	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void getPointcut() {}
	
	@Before("allPointcut()")
	public void printLog() {
		System.out.println("[수행 전] 비즈니스 로직 수행 전 동작");
	}
}
