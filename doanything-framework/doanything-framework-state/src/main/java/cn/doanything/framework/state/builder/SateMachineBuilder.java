package cn.doanything.framework.state.builder;

import cn.doanything.framework.state.Action;
import cn.doanything.framework.state.StateMachine;
import cn.doanything.framework.state.StateMachineFactory;
import cn.doanything.framework.state.Transition;
import cn.doanything.framework.state.impl.StateMachineImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态构造器
 *
 * @author wxj
 * 2024/2/2
 */
public class SateMachineBuilder<S, E, C> {

    private String machineId;

    private TransitionBuilder<S, E, C> current = new TransitionBuilder<>();

    private final List<Transition<S, E, C>> transitions = new ArrayList<>();

    public static <S, E, C> SateMachineBuilder<S, E, C> create(String machineId) {
        return new SateMachineBuilder<>(machineId);
    }

    private SateMachineBuilder() {
    }

    private SateMachineBuilder(String machineId) {
        this.machineId = machineId;
    }

    public SateMachineBuilder<S, E, C> source(S state) {
        current.source(state);
        return this;
    }

    public SateMachineBuilder<S, E, C> event(E event) {
        current.event(event);
        return this;
    }

    public SateMachineBuilder<S, E, C> action(Action<S, E, C> action) {
        current.action(action);
        return this;
    }

    public SateMachineBuilder<S, E, C> target(S state) {
        current.target(state);
        return this;
    }

    public SateMachineBuilder<S, E, C> and() {
        this.transitions.add(current.build());
        current = new TransitionBuilder<>();
        return this;
    }

    public StateMachine<S, E, C> build() {
        StateMachineFactory.register(new StateMachineImpl<>(machineId, transitions));
        return StateMachineFactory.get(machineId);
    }

}
