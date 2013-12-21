/*
 * Copyright (C) 2009-2013 Slava Semushin <slava.semushin@gmail.com>
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
package ru.mystamps.web.tests.it.story;

import org.apache.commons.lang3.StringUtils;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import ru.mystamps.web.Url;
import ru.mystamps.web.tests.it.Site;
import ru.mystamps.web.tests.it.page.AccountRegistrationPage;
import ru.mystamps.web.tests.it.page.ActivateAccountPage;
import ru.mystamps.web.tests.it.step.AnonymousSteps;

import static ru.mystamps.web.tests.TranslationUtils.tr;
import static ru.mystamps.web.validation.ValidationRules.EMAIL_MAX_LENGTH;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(ThucydidesRunner.class)
@Story(Site.Account.Registration.class)
public class AccountRegistration {
	
	// TODO: why it's public?
	// TODO: it is really needed?
	@Managed
	public WebDriver driver;
	
	// TODO: why it's public?
	// TODO: it is really needed?
	@ManagedPages(defaultUrl = Url.SITE)
	public Pages pages;
	
	// TODO: why it's public?
	@Steps
	public AnonymousSteps anonymousSteps;
	
	@Test
	public void anonymousUserRegistersAnAccount() {
		anonymousSteps.openAccountRegistrationPage();
		
		// TODO: fillForm + submitForm?
		ActivateAccountPage page = anonymousSteps.registerUser("coder@rock.home");
		
		// TODO: move assertion to Step?
		assertThat(page.getSuccessfulMessageText(), is(equalTo(tr("t_activation_sent_message"))));
	}
	
	@Test
	public void anonymousUserCannotRegisterAccountWhenEmailIsTooLong() {
		anonymousSteps.openAccountRegistrationPage();

		String longEmail = StringUtils.repeat("0", EMAIL_MAX_LENGTH) + "@mail.ru";
		AccountRegistrationPage page = anonymousSteps.registerUserWithErrors(longEmail);
		
		assertThat(page.getErrorForEmailField(), is(equalTo(tr("value.too-long", EMAIL_MAX_LENGTH))));
	}

	// NOTE: Unfortunately JUnit doesn't have something similar to TestNG's @DataProvider.
	// All implementations which I know require own Runner. Because of that we have
	// duplicated checks.
	@Test
	public void anonymousUserCannotRegisterAccountWhenEmailIsInvalid() {
		String expectedErrorMessage = tr("ru.mystamps.web.validation.jsr303.Email.message");
		
		anonymousSteps.openAccountRegistrationPage();
		
		AccountRegistrationPage page = anonymousSteps.registerUserWithErrors("login");
		assertThat(page.getErrorForEmailField(), is(equalTo(expectedErrorMessage)));
		
		page = anonymousSteps.registerUserWithErrors("login@domain");
		assertThat(page.getErrorForEmailField(), is(equalTo(expectedErrorMessage)));
	}
	
	// NOTE: this test will fail in FF because incorrect value won't be send to the server
	@Test
	public void emailShouldBeStripedFromLeadingAndTrailingSpacesDuringRegistration() {
		anonymousSteps.openAccountRegistrationPage();
		
		AccountRegistrationPage page = anonymousSteps.registerUserWithErrors(" test ");
		
		assertThat(page.getValueOfEmailField(), is(equalTo("test")));
	}
	
}
