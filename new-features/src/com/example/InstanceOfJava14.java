package com.example;

import java.util.Objects;

public class InstanceOfJava14 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		A a3 = new C();
		A a4 = new D();
		B b1 = new B();
		B b2 = new D();
		C c = new C();
		D d = new D();
		if ( a1 instanceof B) { // guard
			B b3 = (B) a1; // safe
		}
		// short-hand notation of the above code block
		if ( a1 instanceof B b3) { // guard
			
		}

		if (a1 instanceof D) {
			D d1 = (D) a1;
			if (Objects.nonNull(d1) && d1 instanceof X) {
				
			}
		}
		
		// short-hand notation of the code block above
		if (a1 instanceof D d1 && Objects.nonNull(d1) && d1 instanceof X) {
			
		}
		
	}

}

class A {}
class B extends A {}
class C extends A implements X{}
class D extends B implements Z{}

interface X {}
interface Y {}
interface Z extends X, Y {} // multiple inheritance