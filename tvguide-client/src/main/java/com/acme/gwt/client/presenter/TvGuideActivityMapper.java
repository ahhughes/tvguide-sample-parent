/**
 *  Copyright 2011 Colin Alworth
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.acme.gwt.client.presenter;

import com.acme.gwt.client.ioc.TvGuideGinjector;
import com.acme.gwt.client.place.AboutPlace;
import com.acme.gwt.client.place.ShowDetailPlace;
import com.acme.gwt.client.place.WelcomePlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author colin
 */
public class TvGuideActivityMapper implements ActivityMapper {
	@Inject
	TvGuideGinjector injector;
	
	@Inject
	Provider<AboutPresenter> aboutPresenterProvider;
	
	@Inject
	ActivityFactory factory;

	public Activity getActivity(Place place) {
		GWT.log("TvGuideActivityMapper.getActivity(place) invoked with argument type "+place.getClass().getName());
		if (place instanceof WelcomePlace) {
			return factory.createWelcomePresenter((WelcomePlace) place);
		}
		if (place instanceof ShowDetailPlace) {
			return factory.createShowDetailPresenter((ShowDetailPlace) place);
		}
		if (place instanceof AboutPlace) {
			final AboutPresenter p = aboutPresenterProvider.get();
//			injector.injectPresenter(p);
			return p;
		}

		return null;
	}
	/**
	 * Methods capable of creating presenters given the place that is passed in
	 *
	 */
	public interface ActivityFactory {
		WelcomePresenter createWelcomePresenter(WelcomePlace place);
		ShowDetailPresenter createShowDetailPresenter(ShowDetailPlace place);
	}
}
