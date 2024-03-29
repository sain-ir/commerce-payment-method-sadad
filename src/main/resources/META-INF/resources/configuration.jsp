<%@ page import="ir.sain.commerce.payment.method.sadad.configuration.SadadGroupServiceConfiguration " %>

<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
	SadadGroupServiceConfiguration sadadGroupServiceConfiguration = (SadadGroupServiceConfiguration)request.getAttribute(SadadGroupServiceConfiguration.class.getName());
%>

<portlet:actionURL name="editSadadCommercePaymentMethodConfigurationMVCActionCommand" var="editCommercePaymentMethodActionURL" />

<aui:form action="<%= editCommercePaymentMethodActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<div class="alert alert-info">
		<%= LanguageUtil.format(resourceBundle, "mercanet-configuration-help", null) %>
	</div>

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset label="merchant-id">
			<aui:input label="merchant-id" name="settings--merchantId--" value="<%= sadadGroupServiceConfiguration.merchantId() %>" />
		</aui:fieldset>

        <aui:fieldset label="merchant-key">
			<aui:input label="merchant-key" name="settings--merchantKey--" value="<%= sadadGroupServiceConfiguration.merchantKey() %>" />
		</aui:fieldset>

        <aui:fieldset label="terminal-id">
			<aui:input label="terminal-id" name="settings--terminalId--" value="<%= sadadGroupServiceConfiguration.terminalId() %>" />
		</aui:fieldset>

		<aui:fieldset label="public-key">
			<aui:input label="public-key" name="settings--publicKey--" value="<%= sadadGroupServiceConfiguration.publicKey() %>" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>