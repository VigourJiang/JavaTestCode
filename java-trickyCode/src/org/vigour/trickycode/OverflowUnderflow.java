package org.vigour.trickycode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * http://javapapers.com/core-java/java-overflow-and-underflow/
 * 
 * Overflow and underflow is a condition where you cross the limit of prescribed
 * size for a data type. When overflow or underflow condition is reached, either
 * the program will crash or the underlying implementation of the programming
 * language will have its own way of handing things.
 * 
 * In Java arithmetic operators don¡¯t report overflow and underflow conditions.
 * They simply swallow it! It is a very dangerous thing to do. If one doesn¡¯t
 * know how Java handles overflow and underflow then he will not be aware of
 * things happening behind while doing arithmetic operations.
 * Overflow and Underflow in Java int operators
 * 
 * Arithmetic integer operations are performed in 32-bit precision. When the
 * resultant value of an operation is larger than 32 bits (the maximum size an
 * int variable can hold) then the low 32 bits only taken into consideration and
 * the high order bits are discarded. When the MSB (most significant bit) is 1
 * then the value is treated as negative.
 * Overflow and Underflow in Java floating point operators
 * 
 * While using java floating point operators, overflow will result in Infinity
 * and underflow will result 0.0 As a general rule here also Java doesn¡¯t throw
 * an error or exception for overflow and underflow.
 * 
 * ----Important points to remember for java overflow and underflow----
 * 1. Overflow or underflow conditions never throw a run time exception.
 * 2. Flowed output is predictable and reproducible. That is, its behaviour is
 * the same every time you run the program.
 * 
 * @author vigour
 * 
 */
public class OverflowUnderflow {

	@Test
	public void test() {

		assertTrue(Math.abs(Integer.MIN_VALUE) < 0);
		
		int overflowExample = Integer.MAX_VALUE + 1;
		assertTrue(overflowExample == Integer.MIN_VALUE);

		int underflowExample = Integer.MIN_VALUE - 1;
		assertTrue(underflowExample == Integer.MAX_VALUE);

		// double overflow
		double d = 1e308;
		System.out.println(d + "*10= " + d * 10);

		// double underflow
		d = 1e-305 * Math.PI;
		for (int i = 0; i < 4; i++)
			System.out.print(" " + (d /= 100000));

	}
}
