package com.googlecode.mgwt.examples.showcase.client.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.examples.showcase.client.model.StationData;
import com.googlecode.mgwt.examples.showcase.client.model.TrainPosition;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

import java.util.List;

public interface StationDetailsView extends IsWidget {

    public HasTapHandlers getBackButton();

    public void setTitle(String title);

    public TabPanel getTabpanel();

    public void setTrainList(List<StationData> trainList);

    public void setOverraysOnMap(List<TrainPosition> trainPositionsList);
}
