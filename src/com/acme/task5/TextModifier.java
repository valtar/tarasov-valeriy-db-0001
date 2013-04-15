package com.acme.task5;

public class TextModifier {
	public static void main(String[] args) {
		//TODO: check number of arguments:
		args = new String[] {"5352nj5324kjn5323n24+2r4-"};
		String str = args[0];
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < str.length(); i++) {
			char charAtIndex = str.charAt(i);
			//TODO: do the task at labguide. Note that String is not a simple char array, but an object
			if(Character.isDigit(charAtIndex)) continue;
			sb.append(charAtIndex);
			if(charAtIndex == '+' || charAtIndex == '-'){
				sb.append(charAtIndex);
			}
		}
		System.out.println(sb.toString());
	}
}
