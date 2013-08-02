/*
 * Copyright 2010 Daniel Kurka
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
package com.googlecode.mgwt.examples.showcase.client.views;

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.RoundPanel;

public class AboutViewGwtImpl extends DetailViewGwtImpl implements AboutView {

  private RoundPanel round;
  private Button button;

  public AboutViewGwtImpl() {

    round = new RoundPanel();

    round.add(new HTML("Irish Rail Information"));
    round.add(new HTML("Version 1.0.0-SNAPSHOT"));
    round.add(new HTML("Built by Seung Kyun Hong"));

    if (MGWT.getOsDetection().isPhone()) {
      button = new Button("back");
      button.getElement().setAttribute("style", "margin:auto;width: 200px;display:block");
      round.add(button);
      headerBackButton.removeFromParent();
    }

    scrollPanel.setWidget(round);
    scrollPanel.setScrollingEnabledX(false);

  }

  @Override
  public HasTapHandlers getBackbutton() {
    if (MGWT.getOsDetection().isPhone()) {
      return button;
    }
    return super.getBackbutton();
  }

}
