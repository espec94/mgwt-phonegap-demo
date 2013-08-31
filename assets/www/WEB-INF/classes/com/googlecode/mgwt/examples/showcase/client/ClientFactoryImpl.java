package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.googlecode.mgwt.examples.showcase.client.utils.StationUtil;
import com.googlecode.mgwt.examples.showcase.client.views.*;

public class ClientFactoryImpl implements ClientFactory {

    private EventBus eventBus;
    private PlaceController placeController;
    private ShowCaseListView homeViewImpl;
    private AboutView aboutView;
    private StationUtil stationUtil;
    private StationSummaryView stationSummaryView;
    private StationDetailsView stationDetailsView;

    public ClientFactoryImpl() {
        eventBus = new SimpleEventBus();

        placeController = new PlaceController(eventBus);

        homeViewImpl = new ShowCaseListViewGwtImpl();
    }

    @Override
    public ShowCaseListView getHomeView() {
        if (homeViewImpl == null) {
            homeViewImpl = new ShowCaseListViewGwtImpl();
        }
        return homeViewImpl;
    }

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public AboutView getAboutView() {
        if (aboutView == null) {
            aboutView = new AboutViewGwtImpl();
        }

        return aboutView;
    }


    @Override
    public StationUtil getStationUtil() {
        if (stationUtil == null) {
            stationUtil = new StationUtil();
        }
        return stationUtil;
    }

    @Override
    public StationSummaryView getStationSummaryView() {
        if (stationSummaryView == null) {
            stationSummaryView = new StationSummaryViewGwtImpl();
        }
        return stationSummaryView;
    }

    @Override
    public StationDetailsView getStationDetailsView() {
        if (stationDetailsView == null) {
            stationDetailsView = new StationDetailsViewGwtImpl();
        }
        return stationDetailsView;
    }
}
