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
package ru.mystamps.web.it.story.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import net.serenitybdd.junit.spring.integration.SpringIntegrationSerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import ru.mystamps.web.Url;
import ru.mystamps.web.config.TestContext;
import ru.mystamps.web.it.step.AdminSteps;
import ru.mystamps.web.it.step.AnonymousSteps;

@RunWith(SpringIntegrationSerenityRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestContext.class)
@WithTags({
	@WithTag("page:/" + Url.INDEX_PAGE),
	@WithTag("user:admin")
})
public class WhenAdminOpenIndexPage {
	
	@Managed(uniqueSession = true)
	private WebDriver driver;
	
	@Steps
	private AdminSteps admin;
	
	@Steps
	private AnonymousSteps anonymous;
	
	@Value("${valid_admin_login}")
	private String adminLogin;
	
	@Value("${valid_admin_password}")
	private String adminPassword;
	
	@Before
	public void loginAsAdmin() {
		anonymous.loginAsUser(adminLogin, adminPassword);
	}
	
	@After
	public void logout() {
		admin.logout();
	}
	
	@Test
	public void shouldSeeTitleForActions() {
		admin.openIndexPage();
		admin.shouldSeeTitleForActions();
	}
	
	@Test
	public void shouldSeeLinkForAddingSeries() {
		admin.openIndexPage();
		admin.shouldSeeLinkForAddingSeries();
	}
	
	@Test
	public void shouldSeeLinkForAddingCategories() {
		admin.openIndexPage();
		admin.shouldSeeLinkForAddingCategories();
	}
	
	@Test
	public void shouldSeeLinkForAddingCountries() {
		admin.openIndexPage();
		admin.shouldSeeLinkForAddingCountries();
	}
	
}
