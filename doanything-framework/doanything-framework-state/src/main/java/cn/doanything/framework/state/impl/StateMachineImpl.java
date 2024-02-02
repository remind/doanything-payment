package cn.doanything.framework.state.impl;

import cn.doanything.framework.state.StateMachine;
import cn.doanything.framework.state.Transition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxj
 * 2024/2/2
 */
public class StateMachineImpl<S, E, C> implements StateMachine<S, E, C> {

    private final String machineId;

    private final Map<S, Map<E, Transition<S, E, C>>> transitionMap = new HashMap<>();

    public StateMachineImpl(String machineId, List<Transition<S, E, C>> transitions) {
        this.machineId = machineId;
        transitions.forEach(transition -> {
            if (transitionMap.containsKey(transition.source())) {
                Map<E, Transition<S, E, C>> map = transitionMap.get(transition.source());
                if (map.containsKey(transition.event())) {
                    throw new RuntimeException("重复的事件");
                }
                map.put(transition.event(), transition);
            } else {
                Map<E, Transition<S, E, C>> map = new HashMap<>();
                map.put(transition.event(), transition);
                transitionMap.put(transition.source(), map);
            }
        });
    }

    @Override
    public String getMachineId() {
        return this.machineId;
    }

    @Override
    public boolean verify(S source, E event) {
        if (transitionMap.containsKey(source)) {
            Map<E, Transition<S, E, C>> map = transitionMap.get(source);
            return map.containsKey(event);
        }
        return false;
    }

    @Override
    public S fireEvent(S source, E event, C ctx) {
        if (verify(source, event)) {
            Transition<S, E, C> transition = transitionMap.get(source).get(event);
            transition.action().execute(source, transition.target(), event, ctx);
            return transition.target();
        }
        return null;
    }
}
