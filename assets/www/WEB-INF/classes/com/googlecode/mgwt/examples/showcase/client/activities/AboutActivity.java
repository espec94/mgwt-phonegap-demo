package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.event.ActionEvent;
import com.googlecode.mgwt.examples.showcase.client.event.ActionNames;
import com.googlecode.mgwt.examples.showcase.client.views.AboutView;

public class AboutActivity extends DetailActivity {

	private final ClientFactory clientFactory;

	public AboutActivity(ClientFactory clientFactory) {
		super(clientFactory.getAboutView(), "nav");
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		AboutView aboutView = clientFactory.getAboutView();

		aboutView.getBackbuttonText().setText("Home");
		aboutView.getHeader().setText("About");
		aboutView.getMainButtonText().setText("Nav");

		addHandlerRegistration(aboutView.getBackbutton().addTapHandler(new TapHandler() {

			@Override
			public void onTap(TapEvent event) {
				ActionEvent.fire(eventBus, ActionNames.BACK);

			}
		}));

		panel.setWidget(aboutView);

	}

}
