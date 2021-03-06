package se.aidium.kata.fizzbuzz.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.FromConfig;

public class FizzBuzzCalculatorActor extends UntypedActor {

	private CalculationPackage calculationPackage;
	private String[] resultArray;
	private int recivedResults;
	private long startTime;

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof CalculationResult) {
			onCalculationResult((CalculationResult) message);
		} else if (message instanceof CalculationPackage) {
			onCalculationPackage((CalculationPackage) message);
		} else {
			unhandled(message);
		}
	}

	private void onCalculationPackage(CalculationPackage message) {
		initiateState(message);
		startFizzBuzzCalculation();
	}

	private void startFizzBuzzCalculation() {
		try {
			ActorRef actorRef = getContext().actorOf(Props.create(FizzBuzzActor.class).withRouter(FromConfig.getInstance()), "router");
			for (int i = 0; i <= calculationPackage.toNumber(); ++i) {
				actorRef.tell(i, getSelf());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void initiateState(CalculationPackage message) {
		calculationPackage = message;
		resultArray = new String[calculationPackage.toNumber() + 1];
		recivedResults = 0;
		startTime = System.currentTimeMillis();
	}

	private void onCalculationResult(CalculationResult result) {
		resultArray[result.number()] = result.result();
		if (recivedResults++ == calculationPackage.toNumber()) {
			System.out.println(String.format("Total calcualtion time %.03f sec", (System.currentTimeMillis() - startTime) / 1000.f));
			getContext().system().shutdown();
		}
	}
}
