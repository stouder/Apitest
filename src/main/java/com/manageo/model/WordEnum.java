package com.manageo.model;

public enum WordEnum {

	FIZZ(3, "Fizz"),

	BUZZ(5, "Buzz");

	private WordEnum(Integer number,String name) {
		this.number = number;
		this.name = name ;
	}
	
	/**
	 * 	Valeur du multiple à tester
	 */
	private Integer number;

	/**
	 * 	Nom à afficher à l'ecran
	 */
	private String name;


	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
