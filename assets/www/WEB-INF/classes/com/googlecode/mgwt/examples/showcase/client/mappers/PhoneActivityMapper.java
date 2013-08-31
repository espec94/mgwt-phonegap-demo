package com.googlecode.mgwt.examples.showcase.client.mappers;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.activities.*;
import com.googlecode.mgwt.examples.showcase.client.places.*;

public class PhoneActivityMapper implements ActivityMapper {

    private final ClientFactory clientFactory;

    public PhoneActivityMapper(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof HomePlace) {
            return new ShowCaseListActivity(clientFactory);
        }

        if (place instanceof AboutPlace) {
            return new AboutActivity(clientFactory);
        }

        if (place instanceof StationSummaryPlace) {
            return new StationSummaryActivity(clientFactory);
        }

        if (place instanceof StationDetailsPlace) {
            return new StationDetailsActivity(clientFactory);
        }
        return new ShowCaseListActivity(clientFactory);
    }
}
