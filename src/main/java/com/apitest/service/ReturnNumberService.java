package com.apitest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.apitest.exception.ParameterException;
import com.apitest.model.Word;

import lombok.extern.slf4j.Slf4j;

/**
 * Service pour trouver la chaine à afficher
 */
@Service
@Slf4j
public class ReturnNumberService {

	final CacheManager cacheManager;

	@Autowired
	public ReturnNumberService(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Autowired
	private WordService wordService;

	/**
	 * 
	 * @param nb - nombre rentré en parametre de l'api
	 * @return String - renvoie une chaine de caractere apres avoir testé tous les
	 *         nombre de 1 à nb
	 *
	 */
	@Cacheable(value = "cache-data", key = "#nb")
	public String getNumbers(int nb)  {
		
		StringBuilder screenDisplay = new StringBuilder();

		// code Java 8
		IntStream.rangeClosed(1, nb).mapToObj(this::getWord).filter(Objects::nonNull).flatMap(List::stream)
				.forEach(w -> screenDisplay.append(w.getName()));

		return screenDisplay.toString();
	}

	/**
	 * 
	 * @param nb - nombre à tester
	 * @return String - renvoie une chaine de caractere si necessaire le nombre est
	 *         multiple de 3 ou 5
	 *
	 */

	@Cacheable(value = "cache-data", key = "#index")
	public List<Word> getWord(int index) {
		if (index < 1) {
			log.error("le nombre rentré doit etre superieur à 1");
			return Arrays.asList();
		}

		List<Word> wordsAvailable = wordService.getAvailableWord();

		if (wordsAvailable == null || wordsAvailable.isEmpty()) {
			log.error("Pas de mot reponse renseigné");
			return new ArrayList<>();
		} else {
			return wordsAvailable.stream().filter(word -> index % word.getReponse().getNumber() == 0)
					.collect(Collectors.toList());
		}

	}

}
