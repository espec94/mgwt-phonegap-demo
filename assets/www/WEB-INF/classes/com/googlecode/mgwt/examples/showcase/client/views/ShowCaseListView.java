
package com.googlecode.mgwt.examples.showcase.client.views;

import java.util.List;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.examples.showcase.client.model.Topic;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;


public interface ShowCaseListView extends IsWidget {

	public void setTitle(String text);

	public void setRightButtonText(String text);

	public HasTapHandlers getAboutButton();

	public HasCellSelectedHandler getCellSelectedHandler();

	public void setTopics(List<Topic> createTopicsList);

	public HasText getFirstHeader();

}
