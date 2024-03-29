/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package ir.sain.commerce.payment.method.sadad.util.ui;

import com.liferay.commerce.payment.constants.CommercePaymentScreenNavigationConstants;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.util.Portal;
import ir.sain.commerce.payment.method.sadad.SadadCommercePaymentMethod;
import ir.sain.commerce.payment.method.sadad.configuration.SadadGroupServiceConfiguration;
import ir.sain.commerce.payment.method.sadad.constants.SadadCommercePaymentMethodConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author Nader Jafari
 */
@Component(
	property = "screen.navigation.entry.order:Integer=20",
	service = ScreenNavigationEntry.class
)
public class SadadCommercePaymentMethodConfigurationScreenNavigationEntry
	implements ScreenNavigationEntry<CommercePaymentMethodGroupRel> {

	public static final String
            ENTRY_KEY_SADAD_COMMERCE_PAYMENT_METHOD_CONFIGURATION =
			"sadad-configuration";

	@Override
	public String getCategoryKey() {
		return CommercePaymentScreenNavigationConstants.
			CATEGORY_KEY_COMMERCE_PAYMENT_METHOD_CONFIGURATION;
	}

	@Override
	public String getEntryKey() {
		return ENTRY_KEY_SADAD_COMMERCE_PAYMENT_METHOD_CONFIGURATION;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			locale,
			CommercePaymentScreenNavigationConstants.
				CATEGORY_KEY_COMMERCE_PAYMENT_METHOD_CONFIGURATION);
	}

	@Override
	public String getScreenNavigationKey() {
		return CommercePaymentScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_PAYMENT_METHOD;
	}

	@Override
	public boolean isVisible(
		User user, CommercePaymentMethodGroupRel commercePaymentMethod) {
        System.out.println("commercePaymentMethod.getEngineKey() = " + commercePaymentMethod.getEngineKey());
		if (SadadCommercePaymentMethod.KEY.equals(
				commercePaymentMethod.getEngineKey())) {
			return true;
		}

		return false;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		try {
			SadadGroupServiceConfiguration sadadGroupServiceConfiguration =
					_configurationProvider.getConfiguration(
                            SadadGroupServiceConfiguration.class,
						new ParameterMapSettingsLocator(
							httpServletRequest.getParameterMap(),
							new GroupServiceSettingsLocator(
								_portal.getScopeGroupId(httpServletRequest),
									SadadCommercePaymentMethodConstants.
									SERVICE_NAME)));

			httpServletRequest.setAttribute(
                    SadadGroupServiceConfiguration.class.getName(),
				sadadGroupServiceConfiguration);
		}
		catch (Exception e) {
			e.printStackTrace();

			throw new IOException(e);
		}

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/configuration.jsp");
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=ir.sain.commerce.payment.method.sadad)"
	)
	private ServletContext _servletContext;

}