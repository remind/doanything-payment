package cn.doanything.framework.state;

/**
 * @author wxj
 * 2024/2/2
 */
public interface StateMachine<S, E, C> {

    /**
     * 获取状态机ID
     *
     * @return
     */
    String getMachineId();

    /**
     * 验证是否可执行
     *
     * @param source
     * @param event
     * @return
     */
    boolean verify(S source, E event);

    /**
     * 执行状态机
     *
     * @param source
     * @param event
     * @param ctx
     * @return
     */
    S fireEvent(S source, E event, C ctx);

}
