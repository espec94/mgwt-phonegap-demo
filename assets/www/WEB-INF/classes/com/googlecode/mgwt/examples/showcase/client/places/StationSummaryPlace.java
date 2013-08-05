package com.googlecode.mgwt.examples.showcase.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StationSummaryPlace extends Place {

    public static class TestPlaceTokenizer implements PlaceTokenizer<StationSummaryPlace> {

        @Override
        public StationSummaryPlace getPlace(String token) {
            return new StationSummaryPlace();
        }

        @Override
        public String getToken(StationSummaryPlace place) {
            return "";
        }
    }

}
