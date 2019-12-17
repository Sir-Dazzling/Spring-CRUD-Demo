package com.crm_demo.aspects;

import java.util.Iterator;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect 
{
	//setting up logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//setting up pointcut declarations
	@Pointcut("execution(* com.crm_demo.controllers.*.*(..))")
	private void forControllersPackage() {}
	
	//setting up pointcut declarations
	@Pointcut("execution(* com.crm_demo.services.*.*(..))")
	private void forServicesPackage() {}
		
	//setting up pointcut declarations
	@Pointcut("execution(* com.crm_demo.models.*.*(..))")
	private void forModelsPackage() {}
	
	//For App Flow
	@Pointcut("forControllersPackage() || forServicesPackage() || forModelsPackage()")
	private void forAppFlow() {}
	
	//adding @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) 
	{
		//Displaying the Method being called
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> in @Before: calling method: "+theMethod);
		
		//Displaying arguments to the method
		
		//getting the arguments
		Object[] args = theJoinPoint.getArgs();
		
		//Looping through and displaying the arguments
		for (Object tempArg : args) 
		{
			myLogger.info("===> argument: "+tempArg);
		}
		
	}
	
		//adding @AfterReturning advice
		@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
		public void afterReturning(JoinPoint theJoinPoint,Object theResult) 
		{
			//Displaying the method we are returning from
			String theMethod = theJoinPoint.getSignature().toShortString();
			myLogger.info("====> in @AfterReturning: from method: "+theMethod);
			
			//Displaying Data being returned
			myLogger.info("====> result: "+theResult);
		}
}
















