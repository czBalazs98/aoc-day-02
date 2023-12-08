package hu.czmarkob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CubeGameSet {

	private int numberOfBlue = 0;

	private int numberOfGreen = 0;

	private int numberOfRed = 0;

	public boolean isPossible() {
		return numberOfBlue <= 14 && numberOfGreen <= 13 && numberOfRed <= 12 &&
				numberOfBlue >= 0 && numberOfGreen >= 0 && numberOfRed >= 0;
	}

	public int getPower() {
		return numberOfBlue * numberOfGreen * numberOfRed;
	}
}
