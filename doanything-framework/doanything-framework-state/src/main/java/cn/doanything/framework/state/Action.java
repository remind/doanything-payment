package cn.doanything.framework.state;

/**
 * 状态机动作
 *
 * @author wxj
 * 2024/2/2
 */
public interface Action<S, E, C> {

    /**
     * 执行动作
     *
     * @param from    来源状态
     * @param to      目标状态
     * @param event   事件
     * @param context 上下文
     */
    void execute(S from, S to, E event, C context);
}
