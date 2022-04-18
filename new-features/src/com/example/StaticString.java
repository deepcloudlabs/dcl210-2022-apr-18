package com.example;

public class StaticString {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String name = "jack";
		// Text Block
		String json = """
				{
				   x: 0,
				   y: 0,
				   radius: 100
				}
				""";
		String xml = """
				<?xml version="1.0"?>\
				<circle x="0" y="0">\
				  <radius>100</radius>\
				</circle>
				""";
		String select = """
				select co.name,co.code,co.population,ci.name\
				from countries co, city ci\
				where co.continent='Asia'
				and co.capital=ci.id
				order by population desc
				\"\n\t\t
				""";
		System.out.println(json);
		System.out.println(xml);
		System.out.println(select);
	}

}
