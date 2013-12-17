package se.aidium.kata.fizzbuzz.akka;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FizzBuzzUtilTest {
	private final FizzBuzzUtil util = new FizzBuzzUtil();

	@Test
	public void shouldReturnANumber() {
		assertEquals("1", util.fizzBuzzOrNumber(1));
	}

	@Test
	public void shuldReturnFizz() {
		assertEquals("Fizz", util.fizzBuzzOrNumber(3));
	}

	@Test
	public void shuldReturnBuzz() {
		assertEquals("Buzz", util.fizzBuzzOrNumber(5));
	}

	@Test
	public void shuldReturnFizzBuzz() {
		assertEquals("FizzBuzz", util.fizzBuzzOrNumber(15));

	}
}
