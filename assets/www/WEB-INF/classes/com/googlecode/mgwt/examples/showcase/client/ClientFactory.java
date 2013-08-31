package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.showcase.client.utils.StationUtil;
import com.googlecode.mgwt.examples.showcase.client.views.*;


public interface ClientFactory {
    public ShowCaseListView getHomeView();

    public EventBus getEventBus();

    public PlaceController getPlaceController();

    /**
     * @return
     */

    public AboutView getAboutView();

    public StationSummaryView getStationSummaryView();

    public StationUtil getStationUtil();

    public StationDetailsView getStationDetailsView();
}
