package se.aidium.kata.fizzbuzz.akka;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.FromConfig;

public class FizzBuzzCalculatorActor extends UntypedActor {

	private final static Logger log = LoggerFactory.getLogger(FizzBuzzCalculatorActor.class);

	private ActorRef initiator;
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
		ActorRef actorRef = getContext().actorOf(Props.create(FizzBuzzActor.class).withRouter(FromConfig.getInstance()), "router");
		for (int i = 0; i <= calculationPackage.toNumber(); ++i) {
			actorRef.tell(i, getSelf());
		}
	}

	private void initiateState(CalculationPackage message) {
		calculationPackage = message;
		resultArray = new String[calculationPackage.toNumber() + 1];
		initiator = getSender();
		recivedResults = 0;
		startTime = System.currentTimeMillis();
	}

	private void onCalculationResult(CalculationResult result) {
		resultArray[result.number()] = result.result();
		log.debug("Total calcualtion time {} sec", (System.currentTimeMillis() - startTime) / 1000.f);
		if (recivedResults++ == calculationPackage.toNumber()) {
			log.info("Total calcualtion time {} sec", (System.currentTimeMillis() - startTime) / 1000.f);
			initiator.tell(Arrays.asList(resultArray), getSelf());
			log.info("Total calcualtion time {} sec", (System.currentTimeMillis() - startTime) / 1000.f);
		}
	}
}
