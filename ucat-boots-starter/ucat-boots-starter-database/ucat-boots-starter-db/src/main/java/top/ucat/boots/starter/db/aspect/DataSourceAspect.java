package top.ucat.boots.starter.db.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import top.ucat.boots.starter.db.annotation.Db;
import top.ucat.boots.starter.db.beans.DynamicDataSourceHolder;

@Aspect
@Slf4j
public class DataSourceAspect {


    @Pointcut("@within(top.ucat.boots.starter.db.annotation.Db)")
    public void classPointCut() {
    }

    @Pointcut("@annotation(top.ucat.boots.starter.db.annotation.Db)")
    public void methodPointCut() {
    }


    /**
     * 注解在方法上触发aop
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("methodPointCut()")
    public Object methodDbChange(ProceedingJoinPoint joinPoint) throws Throwable {
        DynamicDataSourceHolder.defaultDataSource();//默认数据源
        //获取切入点上的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Db db = methodSignature.getMethod().getDeclaredAnnotation(Db.class);
        //设置数据源
        this.setDbResource(db);
        Object object = joinPoint.proceed();// 执行原方法
        return object;
    }

    /**
     * 注解在类上触发aop
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("classPointCut()")
    public Object classPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        DynamicDataSourceHolder.defaultDataSource();//默认数据源
        //获取切入点的class
        Class controllerClass = joinPoint.getTarget().getClass();
        Db db = (Db) controllerClass.getAnnotation(Db.class);
        //设置数据源
        this.setDbResource(db);
        Object object = joinPoint.proceed();// 执行原方法
        return object;
    }

    private void setDbResource(Db db) {
        if (db != null) {
            String dataSourceName = db.value();
            if (StringUtils.isEmpty(dataSourceName))
                DynamicDataSourceHolder.defaultDataSource();
            else
                DynamicDataSourceHolder.putDataSourceKey(dataSourceName);
        }
    }
}
