/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portalweb.portlet.wikidisplay.wikipage.addwdfrontpagechildpageformathtml;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class AddWDFrontPageChildPageFormatHTMLTest extends BaseTestCase {
	public void testAddWDFrontPageChildPageFormatHTML()
		throws Exception {
		selenium.selectWindow("null");
		selenium.selectFrame("relative=top");
		selenium.open("/web/guest/home/");
		selenium.clickAt("link=Wiki Display Test Page",
			RuntimeVariables.replace("Wiki Display Test Page"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Add Child Page"),
			selenium.getText(
				"//div[@class='article-actions']/span/a[contains(.,'Add Child Page')]"));
		selenium.clickAt("//div[@class='article-actions']/span/a[contains(.,'Add Child Page')]",
			RuntimeVariables.replace("Add Child Page"));
		selenium.waitForPageToLoad("30000");
		selenium.select("//select", RuntimeVariables.replace("HTML"));
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.getConfirmation()
						   .matches("^You may lose some formatting when switching from Creole to HTML. Do you want to continue[\\s\\S]$"));
		assertEquals("HTML", selenium.getSelectedLabel("//select"));
		selenium.type("//input[contains(@id,'_title')]",
			RuntimeVariables.replace("Wiki FrontPage ChildPage Title"));
		selenium.waitForVisible("//td[@class='cke_top']");
		selenium.waitForElementPresent(
			"//textarea[contains(@id,'_editor') and contains(@style,'display: none;')]");
		assertEquals(RuntimeVariables.replace("Source"),
			selenium.getText("//span[.='Source']"));
		selenium.clickAt("//span[.='Source']",
			RuntimeVariables.replace("Source"));
		selenium.waitForVisible("//a[@class='cke_button_source cke_on']");
		selenium.waitForVisible(
			"//td[contains(@id,'cke_contents__54')]/textarea");
		selenium.type("//td[contains(@id,'cke_contents__54')]/textarea",
			RuntimeVariables.replace(
				"<a herf=http://www.liferay.com>Welcome to LIFERAY</a>"));
		assertEquals(RuntimeVariables.replace("Source"),
			selenium.getText("//span[.='Source']"));
		selenium.clickAt("//span[.='Source']",
			RuntimeVariables.replace("Source"));
		selenium.waitForElementPresent(
			"//textarea[contains(@id,'_editor') and contains(@style,'display: none;')]");
		assertTrue(selenium.isVisible(
				"//td[contains(@id,'cke_contents__54')]/iframe"));
		selenium.selectFrame("//td[contains(@id,'cke_contents__54')]/iframe");
		selenium.waitForText("//body", "Welcome to LIFERAY");
		selenium.selectFrame("relative=top");
		selenium.clickAt("//input[@value='Publish']",
			RuntimeVariables.replace("Publish"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace(
				"Your request completed successfully."),
			selenium.getText("//div[@class='portlet-msg-success']"));
		selenium.open("/web/guest/home/");
		selenium.clickAt("link=Wiki Display Test Page",
			RuntimeVariables.replace("Wiki Display Test Page"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Wiki FrontPage ChildPage Title"),
			selenium.getText("//div[@class='child-pages']/ul/li/a"));
		selenium.clickAt("//div[@class='child-pages']/ul/li/a",
			RuntimeVariables.replace("Wiki FrontPage ChildPage Title"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Wiki FrontPage ChildPage Title"),
			selenium.getText("//h1[@class='header-title']/span"));
		assertEquals(RuntimeVariables.replace("Welcome to LIFERAY"),
			selenium.getText("//div[@class='wiki-body']/p/a"));
		assertEquals(RuntimeVariables.replace("Details"),
			selenium.getText(
				"//div[@class='page-actions top-actions']/span/a[contains(.,'Details')]"));
		selenium.clickAt("//div[@class='page-actions top-actions']/span/a[contains(.,'Details')]",
			RuntimeVariables.replace("Details"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("HTML"),
			selenium.getText("//tr[2]/td"));
	}
}