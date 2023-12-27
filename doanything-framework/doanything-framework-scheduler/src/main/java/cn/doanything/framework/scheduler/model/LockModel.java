package cn.doanything.framework.scheduler.model;

/**
 * Created by 2023/12/13
 *
 * @author wxj
 * @version V1.0
 * @description: 任务锁模式
 */
public enum LockModel {

    /**
     * 严格模式，使用悲观锁，和处理器类的业务处理在同个事务中。
     */
    STRICT,

    /**
     * 宽松模式，使用乐观锁，不使用事务
     */
    KIND;
}
