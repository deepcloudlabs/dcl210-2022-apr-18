package com.example;

import java.util.List;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class StudyObjectLayout {
     // -XX:-UseCompressedOops
	// -XX:AutoBoxCacheMax=
	public static void main(String[] args) {
		int x = 42; // 4B
		Integer y = 42; // 12B + 4B = 16B + 8B = 24B
		List<Integer> numbers;
		Integer u = Integer.valueOf(42);
		Integer v = 42;
		Integer p = 542;
		Integer q = 542;
		System.out.println("u==v? "+(u==v));
		System.out.println("p==q? "+(p==q));
		System.out.println(VM.current().details());	
		System.out.println(ClassLayout.parseClass(Integer.class).toPrintable());	
	}

}
// Production Load -> Heap Dump -> Analysis -> OQL -> 
// select count(a) from com.example.A a;
// 100 Milyon A sınıfı nesnesi -> 6.4 GB -> Heap Size?
// -Xms8g -Xmx8g
// Heap= [ Eden + S0 + S1 ] (Young)  + Tenure (Old)
//         512M   128M 128M              6.4GB
//         ~0.6GB                                    = 7G + 1G = 8G

class B { // 12B
	A r;  // 4B Compressed Oops
}         // 16B
// 64 Byte = 57 Byte
class A {			    // 12 (Object Header)
	byte b1;  			// 1
	byte b2;  			// 1
	byte b3;  			// 1
	short s1; 			// 2
	short s2; 			// 2
	int i;     			// 4
	char c1='\u20ba';  	// 2
	long l1;			// 8
	char c2='\u20ba';	// 2
	long l2;			// 8	
	boolean x1;			// 1
	float f;			// 4
	boolean x2;			// 1
	double d;			// 8 
}
