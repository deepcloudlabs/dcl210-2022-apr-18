package com.example.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomNumber {

	int min() default 1;

	int max();

	int size();

	boolean distinct() default true;

	boolean sorted() default true;

}
