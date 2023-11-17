package com.jobfinder.util;

import com.ibm.icu.text.Transliterator;

public class SearchUtils {
	
	public static String removeAccents(String input) {
		 Transliterator transliterator = Transliterator.getInstance("NFD; [:Nonspacing Mark:] Remove; NFC");
	       return transliterator.transliterate(input).toLowerCase();
	}
	
}
