package com.manageo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageo.model.Word;

/**
 * Service pour trouver la chaine à afficher
 */
@Service
public class ReturnNumberService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReturnNumberService.class);

	@Autowired
	private WordService wordService;

	/**
	 * 
	 * @param nb - nombre rentré en parametre de l'api
	 * @return String - renvoie une chaine de caractere apres avoir testé tous les
	 *         nombre de 1 à nb
	 *
	 */
	public String getNumbers(int nb) {

		if (nb < 1) {
			LOGGER.error("le nombre rentré doit etre superieur à 1");
			return "";
		}

		StringBuilder screenDisplay = new StringBuilder();
		
		// code Java 8
		IntStream.rangeClosed(1, nb)
	    .mapToObj(this::getWord)
	    .filter(wordReponse -> !wordReponse.isEmpty())
	    .flatMap(List::stream)
	    .forEach(w -> screenDisplay.append(w.getName()));
		
// Code ancien		
//		
//
//		for (int i = 1; i <= nb; i++) {
//			List<Word> wordreponse = getWord(i);
//			if (!wordreponse.isEmpty()) {
//				for (Word w : wordreponse) {
//					screenDisplay.append(w.getName());
//				}
//			}
//		}

		return screenDisplay.toString();
	}

	/**
	 * 
	 * @param nb - nombre à tester
	 * @return String - renvoie une chaine de caractere si necessaire le nombre est
	 *         multiple de 3 ou 5
	 *
	 */
	public List<Word> getWord(int index) {
		if (index < 1) {
			LOGGER.error("le nombre rentré doit etre superieur à 1");
			return null;
		}

		// Instaciation de la liste de reponse
		List<Word> wordReponse = new ArrayList<Word>();

		List<Word> wordsAvailable = wordService.getAvailableWord();

		if (wordsAvailable == null || wordsAvailable.isEmpty()) {
			LOGGER.error("Pas de mot reponse renseigné");
		} else {
//			for (Word word : wordsAvailable) {
//				if (index % word.getReponse().getNumber() == 0) {
//					wordReponse.add(word);
//				}
//			}
			wordReponse = wordsAvailable.stream().filter(word -> index % word.getReponse().getNumber() == 0)
					.collect(Collectors.toList());
			;
		}
		
		return wordReponse;
	}
}
