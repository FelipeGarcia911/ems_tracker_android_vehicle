package co.original.codigo.ems_tracker.helpers.eventBus;

public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

}
