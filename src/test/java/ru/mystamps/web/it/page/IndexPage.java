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

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import ru.mystamps.web.Url;

import static ru.mystamps.web.tests.TranslationUtils.tr;

@DefaultUrl(Url.SITE)
public class IndexPage extends PageObject {
	
	private static final String LOGOUT_BUTTON_XPATH = "//form[@id=\"LogoutForm\"]/input[@type=\"submit\"]";
	
	public boolean hasTitleForActions() {
		return containsText(tr("t_you_may"));
	}
	
	public void logout() {
		try {
			WebElement logoutButton = find(By.xpath(LOGOUT_BUTTON_XPATH));
			logoutButton.submit();
			
		} catch (NoSuchElementException ignored) {
			// if there is no logout button most likely it means that we are already logged out
		}
	}
	
	public boolean linkForAddingSeriesIsPresent() {
		return isLinkWithTextPresent(tr("t_add_series"));
	}
	
	public boolean linkForAddingCategoriesIsPresent() {
		return isLinkWithTextPresent(tr("t_create_category"));
	}
	
	public boolean linkForAddingCountriesIsPresent() {
		return isLinkWithTextPresent(tr("t_add_country"));
	}
	
	private boolean isLinkWithTextPresent(String linkText) {
		return !findAll(By.linkText(linkText)).isEmpty();
	}
	
}
