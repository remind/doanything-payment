package cn.doanything.framework.state;

/**
 * @param source 来源状态
 * @param target 目标状态
 * @param event  事件
 * @param action 动作
 * @author wxj
 * 2024/2/2
 */
public record Transition<S, E, C>(S source, S target, E event, Action<S, E, C> action) {

}
