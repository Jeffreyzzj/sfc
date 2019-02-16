package zijie.zeng.homework.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import zijie.zeng.homework.study.exceptions.SfcExtSQLException;

import java.sql.SQLException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zijie.zeng
 * \* Date: 2019/2/11
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Slf4j
@Aspect
@Component
public class DaoMonitor {

    @Around("execution(* zijie.zeng.homework.study.dao.*.*(..))")
    public Object monitorDaoTime(JoinPoint point) {
        long startTime = System.currentTimeMillis();
        String methodName = null;
        try {
            methodName = point.getSignature().getName();
            Object result = ((ProceedingJoinPoint) point).proceed();
            long endTime = System.currentTimeMillis();
            log.info("{}方法执行时间为{}ms", methodName, endTime - startTime);
            return result;
        } catch (SQLException e) {
            log.error("发生数据库异常", e);
            throw new SfcExtSQLException("发生数据异常");
        } catch (Throwable e) {
            log.error("dao层发生异常", e);
            throw new SfcExtSQLException("发生数据异常");
        }
    }
}

