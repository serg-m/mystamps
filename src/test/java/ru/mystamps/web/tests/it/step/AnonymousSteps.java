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
package ru.mystamps.web.tests.it.step;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import ru.mystamps.web.tests.it.page.AccountRegistrationPage;
import ru.mystamps.web.tests.it.page.ActivateAccountPage;

public class AnonymousSteps extends ScenarioSteps {
	
	@Step
	public void openAccountRegistrationPage() {
		AccountRegistrationPage page = getPages().get(AccountRegistrationPage.class);
		page.open();
	}
	
	@Step
	public ActivateAccountPage registerUser(String email) {
		AccountRegistrationPage page = getPages().get(AccountRegistrationPage.class);
		page.registerUser(email);
		
		return new ActivateAccountPage(getDriver());
	}
	
	public AccountRegistrationPage registerUserWithErrors(String email) {
		AccountRegistrationPage page = getPages().get(AccountRegistrationPage.class);
		page.registerUser(email);

		return new AccountRegistrationPage(getDriver());
	}
	
}
