package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.common.ApplicationConstants;
import com.googlecode.mgwt.examples.showcase.client.model.StationData;
import com.googlecode.mgwt.examples.showcase.client.model.TrainPosition;
import com.googlecode.mgwt.examples.showcase.client.utils.XmlParser;
import com.googlecode.mgwt.examples.showcase.client.views.StationDetailsView;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationDetailsActivity extends MGWTAbstractActivity {

    private final ClientFactory clientFactory;
    private List<StationData> listStationData = new ArrayList<StationData>();
    private Map<String, TrainPosition> listTrainPosition = new HashMap<String, TrainPosition>();

    public StationDetailsActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, final EventBus eventBus) {
        super.start(panel, eventBus);
        StationDetailsView view = clientFactory.getStationDetailsView();

        //init train information in tabpanel
        String stationDesc = clientFactory.getStationUtil().getCurrentStation();
        view.setTitle(stationDesc);
        String requestURL = ApplicationConstants.BASE_URL + "/" + ApplicationConstants.GET_STATION_DATA_BY_NAME + "?" + ApplicationConstants.STATION_DESC + "=" + stationDesc;

        System.out.println("Sending HTTP request:" + requestURL + " to get train details from current station.");
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, requestURL);

        try {
            builder.setTimeoutMillis(5000);
            Request response = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        String responseText = response.getText();
                        // System.out.println("all train information: " + responseText);
                        XmlParser.parseStationDataXml(responseText, listStationData);
                        clientFactory.getStationDetailsView().setTrainList(listStationData);
                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                        System.out.println("HTTP error code:" + response.getStatusCode() + "," + response.getStatusText());
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }

        String requestURL2 = ApplicationConstants.BASE_URL + "/" + ApplicationConstants.GET_CURRENT_TRAIN_XML;

        System.out.println("Sending HTTP request:" + requestURL2 + " to get current trains.");
        RequestBuilder builder2 = new RequestBuilder(RequestBuilder.GET, requestURL2);

        try {
            builder.setTimeoutMillis(5000);
            Request response2 = builder2.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        String responseText = response.getText();
                        // System.out.println("all train information: " + responseText);

                        XmlParser.parseTrainPositionsXml(responseText, listTrainPosition);
                        List<TrainPosition> trainsRelatedToCurrentStation = getRunningTrainsFromCurrentStation(listStationData, listTrainPosition);
                        //create a list of LatLng to mark it onto the map
                        clientFactory.getStationDetailsView().setOverraysOnMap(trainsRelatedToCurrentStation);
                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                        System.out.println("HTTP error code:" + response.getStatusCode() + "," + response.getStatusText());
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }

        view.getTabpanel().addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> integerSelectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
                int tabId = integerSelectionEvent.getSelectedItem();
                System.out.println("tab selected" + tabId);
            }
        });

        panel.setWidget(view);

    }

    private List<TrainPosition> getRunningTrainsFromCurrentStation(List<StationData> listStationData, Map<String, TrainPosition> listTrainPosition) {
        List<TrainPosition> result = new ArrayList<TrainPosition>();

        for (StationData current : listStationData) {
            TrainPosition trainPosition = listTrainPosition.get(current.getTrainCode());
            if (trainPosition != null) {
                result.add(trainPosition);
            }
        }
        return result;
    }

}
