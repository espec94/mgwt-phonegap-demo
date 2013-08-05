package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.activities.home.Topic;
import com.googlecode.mgwt.examples.showcase.client.common.ApplicationConstants;
import com.googlecode.mgwt.examples.showcase.client.places.AboutPlace;
import com.googlecode.mgwt.examples.showcase.client.places.StationSummaryPlace;
import com.googlecode.mgwt.examples.showcase.client.places.UIPlace;
import com.googlecode.mgwt.examples.showcase.client.views.ShowCaseListView;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;

import java.util.ArrayList;
import java.util.List;

public class ShowCaseListActivity extends MGWTAbstractActivity {

    private final ClientFactory clientFactory;

    public ShowCaseListActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;

    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        ShowCaseListView view = clientFactory.getHomeView();

        view.setTitle("Irish Rail Info");
        view.setRightButtonText("about");

        view.getFirstHeader().setText("Menu");

        view.setTopics(createTopicsList());

        addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(
                new CellSelectedHandler() {

                    @Override
                    public void onCellSelected(CellSelectedEvent event) {
                        int index = event.getIndex();

                        if (index == 1) {
                            clientFactory.getPlaceController().goTo(new UIPlace());

                            return;
                        }
                        if (index == 0) {
                            clientFactory.getPlaceController().goTo(new StationSummaryPlace());
                        }

                    }
                }));

        addHandlerRegistration(view.getAboutButton().addTapHandler(new TapHandler() {

            @Override
            public void onTap(TapEvent event) {
                clientFactory.getPlaceController().goTo(new AboutPlace());

            }
        }));


        panel.setWidget(view);

        String requestURI = ApplicationConstants.BASE_URL + "/" + ApplicationConstants.ALL_STATIONS;
        System.out.println("Sending HTTP request: " + requestURI);
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, requestURI);

        try {
            builder.setTimeoutMillis(5000);

            Request response = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
//                   System.out.println(exception.toString());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        String responseText = response.getText();
//                        System.out.println("all stations: "+responseText);
                        clientFactory.getStationUtil().setAllStationXml(responseText);
                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                        System.out.println("HTTP error code:" + response.getStatusCode() + "," + response.getStatusText());
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }


    }

    private List<Topic> createTopicsList() {
        ArrayList<Topic> list = new ArrayList<Topic>();
        list.add(new Topic("Stations", 0));
        list.add(new Topic("UI", 5));

        return list;
    }

}
