/*
 * Copyright 2010 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
    private UIView uiView;
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
    public UIView getUIView() {
        if (uiView == null) {
            uiView = new UIViewImpl();
        }
        return uiView;
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
