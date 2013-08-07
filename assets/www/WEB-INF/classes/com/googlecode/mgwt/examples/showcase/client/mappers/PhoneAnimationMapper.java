/*
 * Copyright 2010 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.examples.showcase.client.mappers;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.examples.showcase.client.places.AboutPlace;
import com.googlecode.mgwt.examples.showcase.client.places.HomePlace;
import com.googlecode.mgwt.examples.showcase.client.places.UIPlace;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;


public class PhoneAnimationMapper implements AnimationMapper {

    @Override
    public Animation getAnimation(Place oldPlace, Place newPlace) {

        if (oldPlace instanceof UIPlace && newPlace instanceof HomePlace) {
            return Animation.SLIDE_REVERSE;
        }

        if (oldPlace instanceof AboutPlace && newPlace instanceof HomePlace) {
            return Animation.SLIDE_UP_REVERSE;
        }

        if (oldPlace instanceof HomePlace && newPlace instanceof AboutPlace) {
            return Animation.SLIDE_UP;
        }

        if (oldPlace instanceof HomePlace && newPlace instanceof UIPlace) {
            return Animation.SLIDE;
        }


        return Animation.SLIDE;
    }
}
