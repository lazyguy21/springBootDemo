package org.yyf.springBootDemo.springevent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * Created by tobi on 2017/4/20.
 * 创建的event继承自ApplicationContextEvent的情况下，
 * 注入了ApplicationContext作为source对象
 *
 */
public class MyEvent<T> extends ApplicationContextEvent{
    private T data;
    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public MyEvent(ApplicationContext source) {
        super(source);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
