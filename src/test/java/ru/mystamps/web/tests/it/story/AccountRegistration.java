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

import org.junit.runner.RunWith;
import org.junit.Test;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.openqa.selenium.WebDriver;

import ru.mystamps.web.Url;
import ru.mystamps.web.tests.it.Site;
import ru.mystamps.web.tests.it.page.ActivateAccountPage;
import ru.mystamps.web.tests.it.step.AnonymousSteps;

import static ru.mystamps.web.tests.TranslationUtils.tr;

import static org.fest.assertions.api.Assertions.assertThat;

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
		assertThat(page.getSuccessfulMessageText()).isEqualTo(tr("t_activation_sent_message"));
	}
	
}
