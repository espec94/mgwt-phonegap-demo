package com.googlecode.mgwt.examples.showcase.client.mappers;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.examples.showcase.client.places.AboutPlace;
import com.googlecode.mgwt.examples.showcase.client.places.HomePlace;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;


public class PhoneAnimationMapper implements AnimationMapper {

    @Override
    public Animation getAnimation(Place oldPlace, Place newPlace) {

        if (oldPlace instanceof AboutPlace && newPlace instanceof HomePlace) {
            return Animation.SLIDE_UP_REVERSE;
        }

        if (oldPlace instanceof HomePlace && newPlace instanceof AboutPlace) {
            return Animation.SLIDE_UP;
        }


        return Animation.SLIDE;
    }
}
