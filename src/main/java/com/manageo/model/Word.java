package com.manageo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Word implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8960417616550081711L;
	
	/**
	 * 	Valeur technique de la reponse
	 */
	private WordEnum reponse;

	/**
	 * 	Valeur de la reponse à afficher
	 */
	private String name;
	
}
