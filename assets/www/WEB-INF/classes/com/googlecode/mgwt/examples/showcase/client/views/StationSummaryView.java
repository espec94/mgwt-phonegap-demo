package com.googlecode.mgwt.examples.showcase.client.views;


import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.examples.showcase.client.model.Station;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;

import java.util.List;

public interface StationSummaryView extends IsWidget {

    public HasTapHandlers getBackButton();

    public void setTitle(String title);

    public void renderItems(List<Station> items);

    public HasCellSelectedHandler getCellSelectedHandler();
}
