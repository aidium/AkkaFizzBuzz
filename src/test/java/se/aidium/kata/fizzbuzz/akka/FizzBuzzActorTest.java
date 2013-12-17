package se.aidium.kata.fizzbuzz.akka;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;

public class FizzBuzzActorTest {

	private ActorSystem system;

	@Before
	public void createSystem() {
		system = ActorSystem.create();
	}

	@Test
	public void shouldCreateInitialActor() throws Exception {
		final Props props = Props.create(FizzBuzzActor.class);
		final TestActorRef<FizzBuzzActor> ref = TestActorRef.create(system, props, "test");
		final Future<Object> future = akka.pattern.Patterns.ask(ref, "Test", 3000);
		assertTrue(future.isCompleted());
		assertEquals("Hello Test", Await.result(future, Duration.Zero()));
	}

	@Test
	public void shouldAnswerWithFizzBuzz() throws Exception {
		final Props props = Props.create(FizzBuzzActor.class);
		final TestActorRef<FizzBuzzActor> ref = TestActorRef.create(system, props, "test");
		final Future<Object> futureFizzBuzz = akka.pattern.Patterns.ask(ref, 15, 3000);
		final Future<Object> futureFizz = akka.pattern.Patterns.ask(ref, 3, 3000);
		final Future<Object> futureBuzz = akka.pattern.Patterns.ask(ref, 5, 3000);
		final Future<Object> future4 = akka.pattern.Patterns.ask(ref, 4, 3000);
		assertTrue(futureFizzBuzz.isCompleted());
		assertEquals(new CalculationResult(15, "FizzBuzz"), Await.result(futureFizzBuzz, Duration.Zero()));
		assertEquals(new CalculationResult(3, "Fizz"), Await.result(futureFizz, Duration.Zero()));
		assertEquals(new CalculationResult(5, "Buzz"), Await.result(futureBuzz, Duration.Zero()));
		assertEquals(new CalculationResult(4, "4"), Await.result(future4, Duration.Zero()));
	}
}
