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
package ru.mystamps.web.it.story.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import net.serenitybdd.junit.spring.integration.SpringIntegrationSerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import ru.mystamps.web.config.TestContext;
import ru.mystamps.web.it.step.AnonymousSteps;
import ru.mystamps.web.it.step.UserSteps;

@RunWith(SpringIntegrationSerenityRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestContext.class)
public class WhenUserOpenIndexPage {
	
	@Managed(uniqueSession = true)
	private WebDriver driver;
	
	@Steps
	private UserSteps user;
	
	@Steps
	private AnonymousSteps anonymous;
	
	@Value("${valid_user_login}")
	private String userLogin;
	
	@Value("${valid_user_password}")
	private String userPassword;
	
	@Before
	public void loginAsUser() {
		anonymous.loginAsUser(userLogin, userPassword);
	}
	
	@After
	public void logout() {
		user.logout();
	}
	
	@Test
	public void shouldSeeTitleForActions() {
		user.openIndexPage();
		user.shouldSeeTitleForActions();
	}
	
	@Test
	public void shouldSeeLinkForAddingSeries() {
		user.openIndexPage();
		user.shouldSeeLinkForAddingSeries();
	}
	
	@Test
	public void shouldNotSeeLinkForAddingCategories() {
		user.openIndexPage();
		user.shouldNotSeeLinkForAddingCategories();
	}
	
	@Test
	public void shouldNotSeeLinkForAddingCountries() {
		user.openIndexPage();
		user.shouldNotSeeLinkForAddingCountries();
	}
	
}
