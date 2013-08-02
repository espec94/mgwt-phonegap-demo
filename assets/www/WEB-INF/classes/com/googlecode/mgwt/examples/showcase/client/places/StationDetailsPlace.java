package com.googlecode.mgwt.examples.showcase.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;


public class StationDetailsPlace extends Place {
    public static class StationDetailsPlaceTokenizer implements PlaceTokenizer<StationDetailsPlace> {

        @Override
        public StationDetailsPlace getPlace(String token) {
            return new StationDetailsPlace();
        }

        @Override
        public String getToken(StationDetailsPlace place) {
            return "";
        }

    }
}
