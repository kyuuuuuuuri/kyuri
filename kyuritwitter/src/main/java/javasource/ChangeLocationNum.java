package javasource;

import java.math.BigDecimal;

public class ChangeLocationNum {

	private double locationDouble;


	public static void main(String[] args) {
		ChangeLocationNum changeL = new ChangeLocationNum();
		System.out.println(changeL.changetoDouble("134.334356234"));

	}

	public double changetoDouble(String LocationNum){

		locationDouble = Double.parseDouble(LocationNum);

		BigDecimal LocationBD = new BigDecimal(locationDouble);
		LocationBD = LocationBD.setScale(4, BigDecimal.ROUND_HALF_UP);
		String BDStr = String.valueOf(LocationBD);
		locationDouble = Double.parseDouble(BDStr);

		return locationDouble;

	}

}
