package se.aidium.kata.fizzbuzz.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class FizzBuzzCalculator {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("FizzBuzzCalulator");
		ActorRef actorRef = system.actorOf(Props.create(FizzBuzzCalculatorActor.class), "FizzBuzzCalculatorActor");
		actorRef.tell(new CalculationPackage(5000000), ActorRef.noSender());
		system.shutdown();
	}
}
