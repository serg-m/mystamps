package ru.mystamps.web.tests;

import org.junit.*;
import org.apache.commons.lang3.Validate;
import ru.mystamps.web.entity.Image;
import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

/**
 * Тестируем код в SeriesServiceImpl.addImageToSeries()
 * @author Serg
 *
 */
public class AddImageToSeriesTest {
	@Test
	public void testValidateIsTrue() {
		/*
		* Если бросит исключение (любой наследник Exception), тест пройден
		*/
		boolean flag = false;
		try {
			Validate.isTrue(false, "");
		} catch(Exception e) {
			flag = true;
		} finally {
			assertTrue(flag);
		}

		/*
		* Не бросил исключение, тест пройден
		*/
		try {
			Validate.isTrue(true, null);
			Validate.isTrue(true, "");
		} catch(Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testImageServiceSave() {
		/*
		* Не бросит исключение, тест пройден
		*/
		try {
			imageService.save(null);
		} catch(Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSeriesDaoSave() {
		/*
		* Не бросит исключение, тест пройден
		*/
		try {
			seriesDao.save(null);
		} catch(Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testGetIds() {
		assertTrue(image.getId() instanceof Integer);
		assertTrue(series.getId() instanceof Integer);
		assertTrue(user.getId() instanceof Integer);
		
		/*
		* Не знаю, судя по всему можно проверить все три метода на условие getId() > 0...
		* По идее они не должны быть отрицательными или равны 0 (это предположение) 
		*/
		assertTrue(image.getId() > 0);
		assertTrue(series.getId() > 0);
		assertTrue(user.getId() > 0);
	}
	
	@Test
	public void testLogInfo() {
		/*
		* Не бросит исключение, тест пройден
		*/
		try {
			LOG.info(null, null, null, null);
		} catch(Exception e) {
			assertTrue(false);
		}
	}
}
