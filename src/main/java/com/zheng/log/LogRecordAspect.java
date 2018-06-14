package com.zheng.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogRecordAspect {

    /**
     * 定义切点，为某一个注解
     */
    @Pointcut(value = "@annotation(com.zheng.log.LogRecord)")
    private void pointCut() {

    }


   @Around(value = "pointCut() && @annotation(logRecord)")
    public Object around(ProceedingJoinPoint point, LogRecord logRecord) {

        System.out.println("++++执行了around方法++++");

        String requestUrl = logRecord.requestUrl();

        //拦截的类名
        Class clazz = point.getTarget().getClass();
        //拦截的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        System.out.println("执行了 类:" + clazz + " 方法:" + method + " 自定义请求地址:" + requestUrl);

        try {
            return point.proceed(); //执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    /**
     * 方法执行后
     *
     * @param joinPoint
     * @param logRecord
     * @param result
     * @return
     */
    @AfterReturning(value = "pointCut() && @annotation(logRecord)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, LogRecord logRecord, Object result) {

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();

        System.out.println("++++执行了afterReturning方法++++");

        System.out.println("执行结果：" + result);

        return result;
    }

    /**
     * 方法执行后 并抛出异常
     *
     * @param joinPoint
     * @param logRecord
     * @param ex
     */
    @AfterThrowing(value = "pointCut() && @annotation(logRecord)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,  LogRecord logRecord, Exception ex) {
        System.out.println("++++执行了afterThrowing方法++++");
        System.out.println("请求：" + logRecord.requestUrl() + " 出现异常");
    }
/*    @Before(value = "pointCut()  && @annotation(logRecord) ")
    public void before(JoinPoint joinPoint,LogRecord logRecord){

    }*/
}
