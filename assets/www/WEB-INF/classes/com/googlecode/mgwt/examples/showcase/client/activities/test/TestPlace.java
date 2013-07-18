package com.googlecode.mgwt.examples.showcase.client.activities.test;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TestPlace extends Place {

    public static class TestPlaceTokenizer implements PlaceTokenizer<TestPlace> {

        @Override
        public TestPlace getPlace(String token) {
            return new TestPlace();
        }

        @Override
        public String getToken(TestPlace place) {
            return "";
        }
    }

}
