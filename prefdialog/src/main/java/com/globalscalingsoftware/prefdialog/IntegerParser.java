package com.globalscalingsoftware.prefdialog;

public class IntegerParser implements IParser<Integer> {

	@Override
	public Integer parse(String input) {
		return Integer.parseInt(input);
	}

}
