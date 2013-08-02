package com.googlecode.mgwt.examples.showcase.client.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class StationSelectedEvent extends Event<StationSelectedEvent.Handler> {

    public static HandlerRegistration register(EventBus eventBus, Handler handler) {
        return eventBus.addHandler(TYPE, handler);
    }

    public interface Handler {
        void onStationSelected(StationSelectedEvent event);
    }

    private static final Type<StationSelectedEvent.Handler> TYPE = new Type<StationSelectedEvent.Handler>();
    private final String station;

    public static void fire(EventBus eventBus, String station) {
        eventBus.fireEvent(new StationSelectedEvent(station));
    }

    @Override
    public com.google.web.bindery.event.shared.Event.Type<Handler> getAssociatedType() {
        return TYPE;
    }

    public StationSelectedEvent(String station) {
        this.station = station;

    }

    @Override
    protected void dispatch(Handler handler) {
        handler.onStationSelected(this);

    }

    public static Type<StationSelectedEvent.Handler> getType() {
        return TYPE;
    }

    public String getStation() {
        return station;
    }

}
