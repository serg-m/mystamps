/**
 * Copyright (C) 2009-2014 Slava Semushin <slava.semushin@gmail.com>
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
package ru.mystamps.web.test.it.story

import net.thucydides.core.annotations.Story
import net.thucydides.junit.runners.ThucydidesRunner

import org.junit.Test
import org.junit.runner.RunWith

import ru.mystamps.web.test.it.Site

@RunWith(ThucydidesRunner.class)
@Story(Site.Account.Registration.class)
class AccountRegistration {
	
	@Test
	def "anonymous user registers an account"() {
		println "TODO: anonymous user registers an account"
		// TODO: implement
	}
	
}
