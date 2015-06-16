/*
 * Copyright (C) 2009-2015 Slava Semushin <slava.semushin@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package ru.mystamps.web.it.page;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.mystamps.web.Url;

@DefaultUrl(Url.SITE + Url.AUTHENTICATION_PAGE)
public class AuthPage extends PageObject {
	
	@FindBy(id = "login")
	private WebElement loginField;
	
	@FindBy(id = "password")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@type=\"submit\"]")
	private WebElement authButton;
	
	public void loginAsAdmin() {
		// TODO: do authentication only if we haven't authenticated yet
		open();
		loginField.sendKeys("admin"); // TODO
		passwordField.sendKeys("test"); // TODO
		authButton.submit();
	}
	
}
