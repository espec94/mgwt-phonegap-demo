package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.common.ApplicationConstants;
import com.googlecode.mgwt.examples.showcase.client.model.Topic;
import com.googlecode.mgwt.examples.showcase.client.places.AboutPlace;
import com.googlecode.mgwt.examples.showcase.client.places.StationSummaryPlace;
import com.googlecode.mgwt.examples.showcase.client.views.ShowCaseListView;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ShowCaseListActivity extends MGWTAbstractActivity {

    private Logger logger = Logger.getLogger(this.getClass().getName());
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

        addEventHandlers(view);
        panel.setWidget(view);

        String requestURI = ApplicationConstants.BASE_URL + "/" + ApplicationConstants.ALL_STATIONS;
        logger.info("Sending HTTP Get request: " + requestURI);
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, requestURI);
        httpGetRequest(builder);
    }

    private void addEventHandlers(ShowCaseListView view) {
        addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(
                new CellSelectedHandler() {

                    @Override
                    public void onCellSelected(CellSelectedEvent event) {
                        int index = event.getIndex();
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
    }

    private void httpGetRequest(RequestBuilder builder) {
        try {
            builder.setTimeoutMillis(5000);

            Request response = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                    logger.severe("Can't connect to server" + exception.getMessage());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        String responseText = response.getText();
                        clientFactory.getStationUtil().setAllStationXml(responseText);
                    } else {
                        // Handle the error. Can get the status text from response.getStatusText()
                        logger.warning("Response from server: " + response.getStatusCode() + "," + response.getText());
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
            logger.severe("Exception thrown while requesting to server" + e.getMessage());
        }
    }

    private List<Topic> createTopicsList() {
        ArrayList<Topic> list = new ArrayList<Topic>();
        list.add(new Topic("Stations", 0));
        return list;
    }

}
