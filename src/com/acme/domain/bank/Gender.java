package com.acme.domain.bank;

public enum Gender {
	MALE("Mr."), FEMALE("Mrs.");
	private String salutatuon = "";

	private Gender(String aSalutation) {
		this.salutatuon = aSalutation;
	}

	@Override
	public String toString() {
		return salutatuon;
	}
}
