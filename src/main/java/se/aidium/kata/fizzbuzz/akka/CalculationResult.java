package se.aidium.kata.fizzbuzz.akka;

public class CalculationResult {
	private final int number;
	private final String result;

	public CalculationResult(int number, String result) {
		this.number = number;
		this.result = result;
	}

	public int number() {
		return number;
	}

	public String result() {
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		CalculationResult other = (CalculationResult) obj;
		if (number != other.number) return false;
		if (result == null) {
			if (other.result != null) return false;
		} else if (!result.equals(other.result)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalculationResult [number=" + number + ", result=" + result + "]";
	}

}
