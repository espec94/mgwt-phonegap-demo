package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.examples.showcase.client.event.ActionEvent;
import com.googlecode.mgwt.examples.showcase.client.event.ActionNames;
import com.googlecode.mgwt.examples.showcase.client.event.StationSelectedEvent;
import com.googlecode.mgwt.examples.showcase.client.places.*;
import com.googlecode.mgwt.mvp.client.history.HistoryHandler;
import com.googlecode.mgwt.mvp.client.history.HistoryObserver;

public class AppHistoryObserver implements HistoryObserver {

    private final ClientFactory clientFactory;

    public AppHistoryObserver(final ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void onPlaceChange(Place place, HistoryHandler handler) {

    }

    @Override
    public void onHistoryChanged(Place place, HistoryHandler handler) {

    }

    @Override
    public void onAppStarted(Place place, HistoryHandler historyHandler) {
        onPhoneNav(place, historyHandler);
    }

    @Override
    public HandlerRegistration bind(EventBus eventBus, final HistoryHandler historyHandler) {

//        HandlerRegistration register3 = UIEntrySelectedEvent.register(eventBus, new UIEntrySelectedEvent.Handler() {
//            @Override
//            public void onAnimationSelected(UIEntrySelectedEvent event) {
//                UIEntry entry = event.getEntry();
//                Place place = null;
//
//                switch (entry) {
//                    default:
//                        break;
//                }
//                historyHandler.goTo(place);
//            }
//        });

        HandlerRegistration register2 = ActionEvent.register(eventBus, ActionNames.BACK, new ActionEvent.Handler() {

            @Override
            public void onAction(ActionEvent event) {
                History.back();
            }
        });


        HandlerRegistration stationDetailsHandlerRegister = StationSelectedEvent.register(eventBus, new StationSelectedEvent.Handler() {

            @Override
            public void onStationSelected(StationSelectedEvent event) {
                System.out.println("AppHistoryObserver received StationSelectedEvent");
                clientFactory.getStationUtil().setCurrentStation(event.getStation());
                Place place = new StationDetailsPlace();
                historyHandler.goTo(place);
            }
        });

        HandlerRegistrationCollection col = new HandlerRegistrationCollection();
        col.addHandlerRegistration(register2);
        col.addHandlerRegistration(stationDetailsHandlerRegister);
        return col;
    }

    private void onPhoneNav(Place place, HistoryHandler historyHandler) {
        if (place instanceof AboutPlace || place instanceof StationSummaryPlace) {
            historyHandler.replaceCurrentPlace(new HomePlace());
        } else {
            if(place instanceof StationDetailsPlace){
                historyHandler.replaceCurrentPlace(new HomePlace());
                historyHandler.pushPlace(new StationSummaryPlace());
            }
//            if (place instanceof ButtonBarPlace ) {
//                historyHandler.replaceCurrentPlace(new HomePlace());
//
//                historyHandler.pushPlace(new UIPlace());
//            }

        }
    }

}
