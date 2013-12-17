package se.aidium.kata.fizzbuzz.akka;

public class CalculationPackage {
	private final int toNumber;

	public CalculationPackage(int toNumber) {
		this.toNumber = toNumber;
	}

	public int toNumber() {
		return toNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + toNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		CalculationPackage other = (CalculationPackage) obj;
		if (toNumber != other.toNumber) return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalculationPackage [toNumber=" + toNumber + "]";
	}

}
