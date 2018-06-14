package com.zheng.log;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAop {
    private final Logger logger = LoggerFactory.getLogger(LogAop.class);
    //Logger logger= LoggerFactory.getLogger(LogAop.class);

    public  void  methodAfter(JoinPoint call){
        //获取类名
       String className= call.getTarget().getClass().getName();
       //获取方法名
        String methodName=call.getSignature().getName();
        //System.out.println("类:"+className+";方法:"+methodName+";================>methodAfter");
        logger.info("loger:类:"+className+";方法:"+methodName+";================>methodAfter");
    }
    public  void  methodBefore(JoinPoint call){
        Class  cals=call.getTarget().getClass();
        //获取类名
        String className= cals.getName();
        //获取方法名
        String methodName=call.getSignature().getName();
        //System.out.println("类:"+className+";方法:"+methodName+";================>methodBefore");
        logger.info("loger:类:"+className+";方法:"+methodName+";================>methodBefore");
    }

    public  void  methodReturning(JoinPoint call){
        //获取类名
        String className= call.getTarget().getClass().getName();
        //获取方法名
        String methodName=call.getSignature().getName();
        //System.out.println("类:"+className+";方法:"+methodName+";================>methodReturning");

        logger.info("loger:类:"+className+";方法:"+methodName+";================>methodReturning");
    }
    public void methodThrowing(JoinPoint call){
        String className = call.getTarget().getClass().getName();
        String methodName = call.getSignature().getName();
       // System.out.println("类:"+className+";方法:"+methodName+";================>methodThrowing");

        logger.info("loger:类:"+className+";方法:"+methodName+";================>methodThrowing");
    }
}
