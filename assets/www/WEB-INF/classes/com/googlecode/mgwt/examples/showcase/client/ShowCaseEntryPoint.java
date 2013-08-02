/*
 * x * Copyright 2010 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.examples.showcase.client.mappers.AppPlaceHistoryMapper;
import com.googlecode.mgwt.examples.showcase.client.mappers.PhoneActivityMapper;
import com.googlecode.mgwt.examples.showcase.client.mappers.PhoneAnimationMapper;
import com.googlecode.mgwt.examples.showcase.client.places.HomePlace;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort.DENSITY;
import com.googlecode.mgwt.ui.client.util.SuperDevModeUtil;

public class ShowCaseEntryPoint implements EntryPoint {

    private void start() {

        // MGWTColorScheme.setBaseColor("#56a60D");
        // MGWTColorScheme.setFontColor("#eee");
        //
        // MGWTStyle.setTheme(new MGWTColorTheme());
        //
        // MGWTStyle.setDefaultBundle((MGWTClientBundle)
        // GWT.create(MGWTStandardBundle.class));
        // MGWTStyle.getDefaultClientBundle().getMainCss().ensureInjected();

        // MGWTStyle.setTheme(new CustomTheme());

        SuperDevModeUtil.showDevMode();

        ViewPort viewPort = new MGWTSettings.ViewPort();
        viewPort.setTargetDensity(DENSITY.MEDIUM);
        viewPort.setUserScaleAble(false).setMinimumScale(1.0).setMinimumScale(1.0).setMaximumScale(1.0);

        MGWTSettings settings = new MGWTSettings();
        settings.setViewPort(viewPort);
        settings.setIconUrl("logo.png");
        settings.setAddGlosToIcon(true);
        settings.setFullscreen(true);
        settings.setPreventScrolling(true);

        MGWT.applySettings(settings);

        final ClientFactory clientFactory = new ClientFactoryImpl();

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);

        createPhoneDisplay(clientFactory);

        AppHistoryObserver historyObserver = new AppHistoryObserver(clientFactory);

        MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(historyMapper, historyObserver);

        historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new HomePlace());
        historyHandler.handleCurrentHistory();
    }

    private void createPhoneDisplay(ClientFactory clientFactory) {
        AnimatableDisplay display = GWT.create(AnimatableDisplay.class);

        PhoneActivityMapper appActivityMapper = new PhoneActivityMapper(clientFactory);

        PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();

        AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, clientFactory.getEventBus());

        activityManager.setDisplay(display);

        RootPanel.get().add(display);

    }


    @Override
    public void onModuleLoad() {

        GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

            @Override
            public void onUncaughtException(Throwable e) {
                Window.alert("uncaught: " + e.getMessage());
                String s = buildStackTrace(e, "RuntimeExceotion:\n");
                Window.alert(s);
                e.printStackTrace();

            }
        });

        new Timer() {

            @Override
            public void run() {
                start();

            }
        }.schedule(1);

    }

    private String buildStackTrace(Throwable t, String log) {
        return "disabled";
        // if (t != null) {
        // log += t.getClass().toString();
        // log += t.getMessage();
        // //
        // StackTraceElement[] stackTrace = t.getStackTrace();
        // if (stackTrace != null) {
        // StringBuffer trace = new StringBuffer();
        //
        // for (int i = 0; i < stackTrace.length; i++) {
        // trace.append(stackTrace[i].getClassName() + "." + stackTrace[i].getMethodName() + "("
        // + stackTrace[i].getFileName() + ":" + stackTrace[i].getLineNumber());
        // }
        //
        // log += trace.toString();
        // }
        // //
        // Throwable cause = t.getCause();
        // if (cause != null && cause != t) {
        //
        // log += buildStackTrace(cause, "CausedBy:\n");
        //
        // }
        // }
        // return log;
    }

}
