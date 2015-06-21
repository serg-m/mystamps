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
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import ru.mystamps.web.Url;
import ru.mystamps.web.config.TestContext;
import ru.mystamps.web.it.step.AnonymousSteps;
import ru.mystamps.web.it.step.UserSteps;

import static ru.mystamps.web.tests.TranslationUtils.tr;

@RunWith(SpringIntegrationSerenityRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestContext.class)
@WithTags({
	@WithTag("page:" + Url.TOGGLZ_CONSOLE_PAGE),
	@WithTag("user:authenticated")
})
public class WhenUserOpenTogglzConsole {
	
	@Managed(uniqueSession = true)
	private WebDriver driver;
	
	@Steps
	private AnonymousSteps anonymous;
	
	@Steps
	private UserSteps user;
	
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
	public void shouldSeeErrorMessage() {
		user.openTogglzConsole();
		user.shouldSeeErrorMessage(tr("t_403_description"));
	}
	
	@Test
	public void shouldSeeErrorCode() {
		user.openTogglzConsole();
		user.shouldSeeErrorCode("403");
	}
	
}
