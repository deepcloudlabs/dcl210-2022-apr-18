package com.example.world.repository.template;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.world.entity.City;
import com.example.world.repository.CityRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Repository
public class JdbcTemplateCityRepository implements CityRepository {
	private static final String SELECT_CITY_BY_ID = "SELECT * FROM CITY WHERE ID=?";
	private static final String SELECT_CITIES_BY_COUNTRYCODE = "SELECT * FROM CITY WHERE COUNTRYCODE=?";
	private static final String SELECT_ALL_CITIES = "SELECT * FROM CITY";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Optional<City> findOne(Long id) {
		City city = jdbcTemplate.queryForObject(SELECT_CITY_BY_ID,
				new BeanPropertyRowMapper<City>(City.class), new Object[] { id });
		return Optional.ofNullable(city);
	}

	@Override
	public Collection<City> findAll() {
		return jdbcTemplate.queryForList(SELECT_ALL_CITIES, City.class);
	}

	@Override
	public City add(City entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City update(City entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<City> remove(Long key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<City> findByCountryCode(String countryCode) {
		return jdbcTemplate.queryForList(SELECT_CITIES_BY_COUNTRYCODE, City.class,countryCode);
	}

}
