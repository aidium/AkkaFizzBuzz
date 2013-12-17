package se.aidium.kata.fizzbuzz.akka;

import akka.actor.UntypedActor;

public class FizzBuzzActor extends UntypedActor {

	private final FizzBuzzUtil util = new FizzBuzzUtil();

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			onStringMessage();
		} else if (message instanceof Integer) {
			onIntegerMessage((Integer) message);
		} else {
			unhandled(message);
		}
	}

	private void onStringMessage() {
		getSender().tell("Hello Test", getSelf());
	}

	private void onIntegerMessage(Integer number) {
		getSender().tell(new CalculationResult(number, util.fizzBuzzOrNumber(number)), getSelf());
	}
}
