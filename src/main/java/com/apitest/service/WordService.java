package com.apitest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.apitest.model.Word;
import com.apitest.model.WordEnum;

@Component
public class WordService {

	/**
	 * 	liste de les nots à afficher possible 
	 */
	public List<Word> getAvailableWord() {
			return Stream.of(WordEnum.values()).map(w -> new Word(w, w.getName()))
					.collect(Collectors.toCollection(ArrayList::new));

			
		}
	}

