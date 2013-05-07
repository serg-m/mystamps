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
package ru.mystamps.web.service

import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Set

import javax.inject.Inject

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import static java.util.Calendar.JANUARY

import org.apache.commons.lang3.Validate

import ru.mystamps.web.dao.SeriesDao
import ru.mystamps.web.entity.GibbonsCatalog
import ru.mystamps.web.entity.MichelCatalog
import ru.mystamps.web.entity.Price
import ru.mystamps.web.entity.ScottCatalog
import ru.mystamps.web.entity.Series
import ru.mystamps.web.entity.User
import ru.mystamps.web.entity.YvertCatalog
import ru.mystamps.web.service.dto.AddSeriesDto
import ru.mystamps.web.util.CatalogUtils

@Service
class SeriesServiceImpl implements SeriesService {
	
	@Inject
	private SeriesDao seriesDao
	
	@Inject
	private ImageService imageService
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_USER')")
	override def add(dto: AddSeriesDto, user: User): Series = {
		Validate.isTrue(dto != null, "DTO must be non null")
		Validate.isTrue(dto.getQuantity() != null, "Stamps quantity must be non null")
		Validate.isTrue(
			dto.getPerforated() != null,
			"Stamps perforated property must be non null"
		)
		Validate.isTrue(user != null, "Current user must be non null")
		
		Series series = new Series()
		
		if (dto.getCountry() != null) {
			series.setCountry(dto.getCountry())
		}
		
		if (dto.getYear() != null) {
			Calendar releaseDate = GregorianCalendar.getInstance()
			releaseDate.clear()
			releaseDate.set(dto.getYear(), JANUARY, 1)
			
			series.setReleasedAt(releaseDate.getTime())
		}
		
		series.setQuantity(dto.getQuantity())
		series.setPerforated(dto.getPerforated())
		
		setMichelNumbersIfProvided(dto, series)
		setMichelPriceIfProvided(dto, series)
		
		setScottNumbersIfProvided(dto, series)
		setScottPriceIfProvided(dto, series)
		
		setYvertNumbersIfProvided(dto, series)
		setYvertPriceIfProvided(dto, series)
		
		setGibbonsNumbersIfProvided(dto, series)
		setGibbonsPriceIfProvided(dto, series)
		
		String imageUrl = imageService.save(dto.getImage())
		Validate.validState(imageUrl != null, "Image url must be non null")
		Validate.validState(imageUrl.length() <= Series.IMAGE_URL_LENGTH, "Too long image path")
		
		series.setImageUrl(imageUrl)
		
		if (dto.getComment() != null) {
			Validate.isTrue(
				!dto.getComment().trim().isEmpty(),
				"Comment must be non empty"
			)
			
			series.setComment(dto.getComment())
		}
		
		Date now = new Date()
		series.getMetaInfo().setCreatedAt(now)
		series.getMetaInfo().setUpdatedAt(now)
		
		series.getMetaInfo().setCreatedBy(user)
		series.getMetaInfo().setUpdatedBy(user)
		
		return seriesDao.save(series)
	}
	
	private def setMichelNumbersIfProvided(dto: AddSeriesDto, series: Series): Unit = {
		Set<MichelCatalog> michelNumbers =
			CatalogUtils.fromString(dto.getMichelNumbers(), MichelCatalog.class)
		if (!michelNumbers.isEmpty()) {
			series.setMichel(michelNumbers)
		}
	}
	
	private def setMichelPriceIfProvided(dto: AddSeriesDto, series: Series): Unit {
		if (dto.getMichelPrice() == null) {
			return
		}
		
		Validate.isTrue(
			dto.getMichelCurrency() != null,
			"Michel currency must be non null when price is specified"
		)
		series.setMichelPrice(new Price(dto.getMichelPrice(), dto.getMichelCurrency()))
	}
	
	private def setScottNumbersIfProvided(dto: AddSeriesDto, series: Series): Unit = {
		Set<ScottCatalog> scottNumbers =
			CatalogUtils.fromString(dto.getScottNumbers(), ScottCatalog.class)
		if (!scottNumbers.isEmpty()) {
			series.setScott(scottNumbers)
		}
	}
	
	private def setScottPriceIfProvided(dto: AddSeriesDto, series: Series): Unit = {
		if (dto.getScottPrice() == null) {
			return
		}
		
		Validate.isTrue(
			dto.getScottCurrency() != null,
			"Scott currency must be non null when price is specified"
		)
		series.setScottPrice(new Price(dto.getScottPrice(), dto.getScottCurrency()))
	}
	
	private def setYvertNumbersIfProvided(dto: AddSeriesDto, series: Series): Unit = {
		Set<YvertCatalog> yvertNumbers =
			CatalogUtils.fromString(dto.getYvertNumbers(), YvertCatalog.class)
		if (!yvertNumbers.isEmpty()) {
			series.setYvert(yvertNumbers)
		}
	}
	
	private def setYvertPriceIfProvided(dto: AddSeriesDto, series: Series): Unit = {
		if (dto.getYvertPrice() == null) {
			return
		}
		
		Validate.isTrue(
			dto.getYvertCurrency() != null,
			"Yvert currency must be non null when price is specified"
		)
		series.setYvertPrice(new Price(dto.getYvertPrice(), dto.getYvertCurrency()))
	}
	
	private def setGibbonsNumbersIfProvided(dto: AddSeriesDto, series: Series): Unit = {
		Set<GibbonsCatalog> gibbonsNumbers =
			CatalogUtils.fromString(dto.getGibbonsNumbers(), GibbonsCatalog.class)
		if (!gibbonsNumbers.isEmpty()) {
			series.setGibbons(gibbonsNumbers)
		}
	}
	
	private def setGibbonsPriceIfProvided(dto: AddSeriesDto, series: Series): Unit = {
		if (dto.getGibbonsPrice() == null) {
			return
		}
		
		Validate.isTrue(
			dto.getGibbonsCurrency() != null,
			"Gibbons currency must be non null when price is specified"
		)
		series.setGibbonsPrice(new Price(dto.getGibbonsPrice(), dto.getGibbonsCurrency()))
	}
	
}