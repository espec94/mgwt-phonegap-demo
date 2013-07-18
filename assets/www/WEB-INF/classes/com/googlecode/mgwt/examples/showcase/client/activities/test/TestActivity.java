package com.googlecode.mgwt.examples.showcase.client.activities.test;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.DetailActivity;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class TestActivity extends MGWTAbstractActivity {

    private final ClientFactory clientFactory;

    public TestActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;

    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        super.start(panel, eventBus);
        TestView view = clientFactory.getTestView();

        view.setTitle("Test");
        view.renderItems(clientFactory.getStationUtil().getAllStation());
        panel.setWidget(view);
    }
}
