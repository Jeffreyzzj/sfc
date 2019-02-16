package zijie.zeng.homework.study.exceptions;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zijie.zeng
 * \* Date: 2019/2/11 11:07
 * \* To change this template use File | Settings | File Templates.
 * \* Description:系统错误转为运行时异常
 * \
 */
public class SfcExtException extends RuntimeException {

    public SfcExtException() {
    }

    public SfcExtException(String message) {
        super(message);
    }

    public SfcExtException(String message, Throwable cause) {
        super(message, cause);
    }

    public SfcExtException(Throwable cause) {
        super(cause);
    }

    public SfcExtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}