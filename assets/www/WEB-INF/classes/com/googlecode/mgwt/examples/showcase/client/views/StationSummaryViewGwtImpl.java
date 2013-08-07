package com.googlecode.mgwt.examples.showcase.client.views;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.examples.showcase.client.model.BasicCell;
import com.googlecode.mgwt.examples.showcase.client.model.Station;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;

import java.util.List;

public class StationSummaryViewGwtImpl implements StationSummaryView {

    private LayoutPanel main;
    private HeaderButton headerBackButton;
    private HeaderPanel headerPanel;
    private CellListWithHeader<Station> cellList;

    public StationSummaryViewGwtImpl() {
        main = new LayoutPanel();
        headerPanel = new HeaderPanel();
        headerBackButton = new HeaderButton();
        headerBackButton.setText("Home");


        headerPanel.setLeftWidget(headerBackButton);
        headerBackButton.setBackButton(true);
//        headerBackButton.setVisible(!MGWT.getOsDetection().isAndroid());
        headerBackButton.setVisible(true);

        main.add(headerPanel);

        cellList = new CellListWithHeader<Station>(new BasicCell<Station>() {

            @Override
            public String getDisplayString(Station model) {
                return model.getDescription();
            }

            @Override
            public boolean canBeSelected(Station model) {
                return true;
            }
        });

        cellList.getCellList().setRound(true);

        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(cellList);
        scrollPanel.setScrollingEnabledX(false);
        main.add(scrollPanel);
    }


    @Override
    public HasTapHandlers getBackButton() {
        return headerBackButton;
    }

    @Override
    public void setTitle(String title) {
        headerPanel.setTitle(title);
    }

    @Override
    public void renderItems(List<Station> stations) {
        cellList.getCellList().render(stations);
    }

    @Override
    public HasCellSelectedHandler getCellSelectedHandler() {
        return cellList.getCellList();
    }

    @Override
    public Widget asWidget() {
        return main;
    }
}
