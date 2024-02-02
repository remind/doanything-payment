package cn.doanything.framework.state;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wxj
 * 2024/2/2
 */
public class StateMachineFactory {

    @SuppressWarnings({"rawtypes"})
    private static final Map<String, StateMachine> stateMachineMap = new ConcurrentHashMap<>();

    public static <S, E, C> void register(StateMachine<S, E, C> stateMachine) {
        String machineId = stateMachine.getMachineId();
        if (stateMachineMap.get(machineId) != null) {
            throw new RuntimeException("不存在对应状态机:" + machineId);
        }
        stateMachineMap.put(stateMachine.getMachineId(), stateMachine);
    }

    @SuppressWarnings({"rawtypes"})
    public static <S, E, C> StateMachine<S, E, C> get(String machineId) {
        StateMachine stateMachine = stateMachineMap.get(machineId);
        if (stateMachine == null) {
            throw new RuntimeException("不存在对应状态机:" + machineId);
        }
        return stateMachine;
    }
}
