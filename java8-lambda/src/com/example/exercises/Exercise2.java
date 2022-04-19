package com.example.exercises;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

import java.util.List;
import java.util.function.Function;

import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise2 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the most populated city of each continent
		var countries = countryDao.findAllCountries();
		Function<ContinentCityPair, City> byCity = ContinentCityPair::city;
		Function<City, Integer> byPopulation = City::getPopulation;
		Function<Country,ContinentCitiesPair> toContinentCitiesPair = country -> new ContinentCitiesPair(country.getContinent(), country.getCities());
		var continentMostPopCity =
		countries.stream()
		         .map(toContinentCitiesPair)
		         .map(continentCities -> continentCities.cities()
		        		                                .stream()
		        		                                .map(city -> new ContinentCityPair(continentCities.continent(), city) )
		                                                .toList()
		         )
		         .flatMap(List::stream)
		         .collect(groupingBy(ContinentCityPair::continent,maxBy(comparing(byCity.andThen(byPopulation)))));
		continentMostPopCity.forEach(
            (continent,continentCityPair) -> System.out.println("%14s: %-24s %9d".formatted(continent,continentCityPair.get().city().getName(),continentCityPair.get().city().getPopulation()))
		);
	}

}

record ContinentCitiesPair(String continent,List<City> cities) {}
record ContinentCityPair(String continent,City city) {}