package se.aidium.kata.fizzbuzz.akka;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;

public class FizzBuzzCalculatorActorTest {

	private ActorSystem system;

	@Before
	public void createSystem() {
		system = ActorSystem.create();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldCreateWorkers() throws Exception {
		final Props props = Props.create(FizzBuzzCalculatorActor.class);
		final TestActorRef<FizzBuzzCalculatorActor> ref = TestActorRef.create(system, props, "test");
		final Future<Object> future = akka.pattern.Patterns.ask(ref, new CalculationPackage(50), 3000);
		List<String> result = (List<String>) Await.result(future, Duration.create(3, TimeUnit.SECONDS));
		assertTrue(future.isCompleted());
		assertEquals(result.get(0), "FizzBuzz");
		assertEquals(result.get(1), "1");
		assertEquals(result.get(2), "2");
		assertEquals(result.get(3), "Fizz");
		assertEquals(result.get(4), "4");
		assertEquals(result.get(5), "Buzz");
	}
}
