package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.event.ActionEvent;
import com.googlecode.mgwt.examples.showcase.client.event.ActionNames;
import com.googlecode.mgwt.examples.showcase.client.event.StationSelectedEvent;
import com.googlecode.mgwt.examples.showcase.client.model.Station;
import com.googlecode.mgwt.examples.showcase.client.views.StationSummaryView;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;

import java.util.logging.Logger;

public class StationSummaryActivity extends MGWTAbstractActivity {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private final ClientFactory clientFactory;

    public StationSummaryActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;

    }

    @Override
    public void start(AcceptsOneWidget panel, final EventBus eventBus) {
        super.start(panel, eventBus);
        StationSummaryView view = clientFactory.getStationSummaryView();

        view.setTitle("Station List");
        view.renderItems(clientFactory.getStationUtil().getAllStation());

        addHandlerRegistration(view.getBackButton().addTapHandler(new TapHandler() {

            @Override
            public void onTap(TapEvent event) {
                ActionEvent.fire(eventBus, ActionNames.BACK);

            }
        }));

        addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(
                new CellSelectedHandler() {

                    @Override
                    public void onCellSelected(CellSelectedEvent event) {
                        int index = event.getIndex();
                        Station station = clientFactory.getStationUtil().getAllStation().get(index);
                        String stationName = station.getDescription();
                        logger.info("Clicked a " + stationName + " station");
                        StationSelectedEvent.fire(eventBus, stationName);
                    }
                }));

        panel.setWidget(view);
    }
}
