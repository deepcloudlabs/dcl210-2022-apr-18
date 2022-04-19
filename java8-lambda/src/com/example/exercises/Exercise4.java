package com.example.exercises;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;

import java.util.Objects;

import com.example.dao.CityDao;
import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise4 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();
	private static final CityDao cityDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		countryDao.findAllCountries()
		          .stream()
		          .parallel()
		          .map(Country::getCapital)
		          .map(cityDao::findCityById)
		          .filter(Objects::nonNull)
		          .collect(maxBy(comparing(City::getPopulation)))
		          .ifPresent(System.out::println);
	}

}
