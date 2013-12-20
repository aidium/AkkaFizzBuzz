package se.aidium.kata.fizzbuzz.akka;

import org.junit.Before;
import org.junit.Test;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;

public class FizzBuzzCalculatorActorTest {

	private ActorSystem system;

	@Before
	public void createSystem() {
		system = ActorSystem.create();
	}

	@Test
	public void shouldCreateWorkers() throws Exception {
		final Props props = Props.create(FizzBuzzCalculatorActor.class);
		final TestActorRef<FizzBuzzCalculatorActor> ref = TestActorRef.create(system, props, "test");
		akka.pattern.Patterns.ask(ref, new CalculationPackage(50), 500);
		// Wait for system to exit
		Thread.sleep(500);
	}
}
