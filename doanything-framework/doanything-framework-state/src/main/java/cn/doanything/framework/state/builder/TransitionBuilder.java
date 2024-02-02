package cn.doanything.framework.state.builder;

import cn.doanything.framework.state.Action;
import cn.doanything.framework.state.Transition;

/**
 * @author wxj
 * 2024/2/2
 */
public class TransitionBuilder<S, E, C> {

    private S source;
    private S target;
    private E event;
    private Action<S, E, C> action;

    public S getSource() {
        return source;
    }

    public TransitionBuilder<S, E, C> source(S source) {
        this.source = source;
        return this;
    }

    public TransitionBuilder<S, E, C> target(S target) {
        this.target = target;
        return this;
    }

    public TransitionBuilder<S, E, C> event(E event) {
        this.event = event;
        return this;
    }

    public TransitionBuilder<S, E, C> action(Action<S, E, C> action) {
        this.action = action;
        return this;
    }

    public Transition<S, E, C> build() {
        return new Transition<S, E, C>(source, target, event, action);
    }


}
