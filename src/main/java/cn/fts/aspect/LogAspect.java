package cn.fts.aspect;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by 万洪基 on 2017/5/2.
 */
@Aspect
public class LogAspect {

    private static final Logger logger = Logger.getLogger(LogAspect.class);

    @Pointcut("execution(* cn.fts.service.impl.*.*(..))")
    public void serviceAspect(){}

    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint , Throwable e) throws Exception {
//        获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() !=null && joinPoint.getArgs().length > 0){
            for (int i = 0;i < joinPoint.getArgs().length;i++){
                params += joinPoint.getArgs()[i] + ";";
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n异常方法相关信息:\n"+getServiceMthodDescription(joinPoint))
                .append("\n异常代码:"+e.getClass().getName())
                .append("\n异常信息:"+e.getMessage())
                .append("\n异常原因:"+e.getCause())
                .append("\n输入参数:"+params);
        logger.error(stringBuilder.toString());
    }

    public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String description = "异常位置:"+targetName+"."+methodName;
        return description;
    }

}
