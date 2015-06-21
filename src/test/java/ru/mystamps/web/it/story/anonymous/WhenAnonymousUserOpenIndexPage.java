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
package ru.mystamps.web.it.story.anonymous;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import ru.mystamps.web.Url;
import ru.mystamps.web.it.step.AnonymousSteps;

@RunWith(SerenityRunner.class)
@WithTags({
	@WithTag("page:/" + Url.INDEX_PAGE),
	@WithTag("user:anonymous")
})
public class WhenAnonymousUserOpenIndexPage {
	
	@Managed(uniqueSession = true)
	private WebDriver driver;
	
	@Steps
	private AnonymousSteps anonymous;
	
	@Test
	public void shouldNotSeeTitleForActions() {
		anonymous.openIndexPage();
		anonymous.shouldNotSeeTitleForActions();
	}
	
	@Test
	public void shouldNotSeeLinkForAddingSeries() {
		anonymous.openIndexPage();
		anonymous.shouldNotSeeLinkForAddingSeries();
	}
	
	@Test
	public void shouldNotSeeLinkForAddingCategories() {
		anonymous.openIndexPage();
		anonymous.shouldNotSeeLinkForAddingCategories();
	}
	
	@Test
	public void shouldNotSeeLinkForAddingCountries() {
		anonymous.openIndexPage();
		anonymous.shouldNotSeeLinkForAddingCountries();
	}
	
}
