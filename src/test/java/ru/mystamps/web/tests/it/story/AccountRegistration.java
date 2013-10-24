package ru.mystamps.web.tests.it.story;

import org.junit.runner.RunWith;
import org.junit.Test;

import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import ru.mystamps.web.tests.it.Site;

@RunWith(ThucydidesRunner.class)
@Story(Site.Account.Registration.class)
public class AccountRegistration {
	
	@Test
	public void anonymousUserRegistersAnAccount() {
		// TODO
	}
	
}
