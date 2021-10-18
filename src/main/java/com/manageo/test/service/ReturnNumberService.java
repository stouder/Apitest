package com.manageo.test.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageo.test.model.Word;

/**
 * Service pour trouver la chaine à afficher
 */
@Service
public class ReturnNumberService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReturnNumberService.class);

	@Autowired
	private WordService wordService;

	public List<List<Word>> getWords(int nb) {

		List<List<Word>> wordList = new ArrayList<>();

		if (nb < 1) {
			LOGGER.error("le nombre rentré doit etre superieur à 1");
			return wordList;
		}

		for (int i = 1; i <= nb; i++) {
			wordList.add(getWord(i));
		}

		return wordList;
	}

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

		for (int i = 1; i <= nb; i++) {
			List<Word> wordreponse = getWord(i);
			if (!wordreponse.isEmpty()) {
				wordreponse.stream().forEach(word -> screenDisplay.append(word.getName()));
			}
		}

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

			wordsAvailable.stream().forEach(word -> {
				word.setNumber(index);
				if (index % word.getReponse().getNumber() == 0) {
					wordReponse.add(word);
				}
			});

			// if (index % word.getReponse().getNumber() == 0) {
			// wordReponse.add(word);
			// }
			// }
		}
		return wordReponse;
	}

	
	public static String returnResultFizzBuzz(int pos, int end) {
		String result;
		if (pos <= end) {

			if (pos % 15 == 0) {
				result = "FizzBuzz";
			} else if (pos % 3 == 0) {
				result = "Fizz";
			} else if (pos % 5 == 0) {
				result = "Buzz";
			} else {
				result = String.valueOf(pos);
			}
			return result + "\n" + returnResultFizzBuzz(++pos, end);
		} else {
			return "";
		}
	}
}
