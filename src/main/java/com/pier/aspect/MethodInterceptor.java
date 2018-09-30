package com.pier.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @auther zhongweiwu
 * @date 2018/9/5 16:38
 */
@Aspect
@Order(10)
@Component
public class MethodInterceptor {

    /** 切面一定要加在方法上不然不起作用 */
    @Before(value = "@annotation(monitor)")
    public void methodInfo(JoinPoint joinPoint, MethodLog monitor){
        System.out.println("before method: " + joinPoint.getSignature().getName()+"   start ...");
        System.out.println("before method   end ...");
    }

    //声明个切面，切哪呢？切到 com.lxk.service.StudentService 这个目录下，以save开头的方法，方法参数(..)和返回类型(*)不限
    @Pointcut("execution(* com.pier..*.*(..))")
    private void aa() {
    }//切入点签名

    /**
     * 后置通知
     */
    @AfterReturning(value = "execution(* com.pier..*.*(..)) && @annotation(methodLog)", argNames = "joinPoint, methodLog, result", returning = "result")
    public void methodAfterReturning(JoinPoint joinPoint, MethodLog methodLog, Object result) throws Throwable {
        System.out.println("AfterReturning method    start.......................");
        System.out.println("AfterReturning method   返回的结果：" + result);
        System.out.println("AfterReturning method       end.......................");
    }

    /**
     * 最终通知 after advice
     * 使用的是在上面声明的切面，并且带上个注解，意思是除了满足上面aa()方法的条件还得带上注解才OK
     */
    @After(value = "aa() && @annotation(methodLog)", argNames = "joinPoint, methodLog")
    public void methodAfter(JoinPoint joinPoint, MethodLog methodLog) throws Throwable {
        System.out.println("After method     start.......................");
        //获得自定义注解的参数
        System.out.println("After method   methodLog 的参数，remark：" + methodLog.description() + " clazz：" + methodLog.clazz());
        MethodLog remark = getMethodRemark(joinPoint);
        System.out.println("After method        end.......................");
    }

    @Around(value = "@annotation(methodLog)", argNames = "joinPoint, methodLog")
    public Object methodAround(ProceedingJoinPoint joinPoint, MethodLog methodLog) throws Throwable {
        System.out.println("Around method    start.......................");
        //获得自定义注解的参数
        System.out.println("Around method  methodLog 的参数，remark：" + methodLog.description() + " clazz：" + methodLog.clazz());
        //执行目标方法，并获得对应方法的返回值
        Object result = joinPoint.proceed();
        System.out.println("Around method     返回结果：" + result);
        System.out.println("Around method          end.......................");
        return result;
    }

    // 匹配com.owenapp.service.impl包下所有类的、
    // 所有方法的执行作为切入点
    @AfterThrowing(throwing="ex", value="@annotation(error)")
    // 声明ex时指定的类型会限制目标方法必须抛出指定类型的异常
    // 此处将ex的类型声明为Throwable，意味着对目标方法抛出的异常不加限制
    public void doRecoveryActions(JoinPoint joinPoint, Throwable ex, Error error)
    {
        System.out.println(joinPoint.getTarget().getClass().getName()+"#"+joinPoint.getSignature().getName() + "()方法中抛出的异常:" + ex);
        System.out.println("模拟Advice对异常的修复...");
    }

    /**
     * 获取方法的中文备注____用于记录用户的操作日志描述
     */
    private MethodLog getMethodRemark(JoinPoint joinPoint) throws Exception {
        //返回目标对象
        Object target = joinPoint.getTarget();
        String targetName = target.getClass().getName();
        //返回当前连接点签名
        String methodName = joinPoint.getSignature().getName();
        //获得参数列表
        Object[] arguments = joinPoint.getArgs();

        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        //这个怎么这么low呢。
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    MethodLog methodCache = m.getAnnotation(MethodLog.class);
                    if (methodCache != null && !("").equals(methodCache.description())) {
                        return methodCache;
                    }
                    break;
                }
            }
        }
        return null;
    }
}
