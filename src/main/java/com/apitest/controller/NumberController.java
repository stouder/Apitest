package com.apitest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.apitest.exception.ParameterException;
import com.apitest.service.ReturnNumberService;

@RestController
public class NumberController {
	    
	@Autowired
	private ReturnNumberService reponseService;
		
	@GetMapping(value = "/number/{id}", produces = "application/json")
	public ResponseEntity<String> getnumber(@PathVariable(name = "id", required = false) String id) {

		if (Integer.parseInt(id) < 3) {	
			//throw new ParameterException("Le paramètre 'id' ne doit pas être inferieur à 3");
		}
		
		if (id == null) {
			return new ResponseEntity<>("Aucun 'id' fourni.", HttpStatus.BAD_REQUEST);
		}

		if (!isInteger(id)) {
			throw new IllegalArgumentException("Le paramètre 'id' doit être un entier.");
		}

		return new ResponseEntity<>(reponseService.getNumbers(Integer.parseInt(id)), HttpStatus.OK);
	}

	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
