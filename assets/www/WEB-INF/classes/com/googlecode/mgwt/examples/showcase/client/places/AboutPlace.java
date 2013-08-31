package com.googlecode.mgwt.examples.showcase.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AboutPlace extends Place {
	public static class AboutPlaceTokenizer implements PlaceTokenizer<AboutPlace> {

		@Override
		public AboutPlace getPlace(String token) {
			return new AboutPlace();
		}

		@Override
		public String getToken(AboutPlace place) {
			return "";
		}

	}
}
