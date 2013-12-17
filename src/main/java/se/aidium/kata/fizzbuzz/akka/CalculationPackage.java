package se.aidium.kata.fizzbuzz.akka;

public class CalculationPackage {
	private final int instances;
	private final int toNumber;

	public CalculationPackage(int instances, int toNumber) {
		this.instances = instances;
		this.toNumber = toNumber;
	}

	public int instances() {
		return instances;
	}

	public int toNumber() {
		return toNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + instances;
		result = prime * result + toNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		CalculationPackage other = (CalculationPackage) obj;
		if (instances != other.instances) return false;
		if (toNumber != other.toNumber) return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalculationPackage [instances=" + instances + ", toNumber=" + toNumber + "]";
	}

}
