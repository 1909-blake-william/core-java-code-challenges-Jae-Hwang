package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j = 0; i >= 0; i--, j++) { // Iterating from 0 to length and length to 0
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String[] stringArray;
		String result = "";

		stringArray = phrase.split("[^a-zA-Z]+");
		// System.out.println(Arrays.toString(stringArray));
		for (String e : stringArray) {
			result += e.charAt(0);
		}
		result = result.toUpperCase();
		// System.out.println(result);
		return result;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return this.getSideOne() == this.getSideTwo() && this.sideOne == this.sideThree;
		}

		public boolean isIsosceles() { //
			return this.getSideOne() == this.getSideTwo()
				|| this.getSideOne() == this.getSideThree()
				|| this.getSideTwo() == this.getSideThree();
		}

		public boolean isScalene() {
			return !this.isIsosceles(); // if not Isosceles, is scalene
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */

	private static HashMap<Character, Integer> letterValue = new HashMap<Character, Integer>();
	{

		letterValue.put('a', 1);
		letterValue.put('e', 1);
		letterValue.put('i', 1);
		letterValue.put('o', 1);
		letterValue.put('u', 1);
		letterValue.put('l', 1);
		letterValue.put('n', 1);
		letterValue.put('r', 1);
		letterValue.put('s', 1);
		letterValue.put('t', 1);

		letterValue.put('d', 2);
		letterValue.put('g', 2);

		letterValue.put('b', 3);
		letterValue.put('c', 3);
		letterValue.put('m', 3);
		letterValue.put('p', 3);

		letterValue.put('f', 4);
		letterValue.put('h', 4);
		letterValue.put('v', 4);
		letterValue.put('w', 4);
		letterValue.put('y', 4);

		letterValue.put('k', 5);

		letterValue.put('j', 8);
		letterValue.put('x', 8);

		letterValue.put('q', 10);
		letterValue.put('z', 10);
	}

	public int getScrabbleScore(String string) {
		char[] charArray;
		charArray = string.toLowerCase().toCharArray();
		int score = 0;
		for (char c : charArray) { 			// for each character in the string
			score += letterValue.get(c);	// add the score for the character
		}

		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) throws IllegalArgumentException {
		String returnString = string.replaceAll("[^0-9]", "").replaceAll("^1", ""); // strip all none number, and 1 at the start if it starts with 1
		// System.out.println(returnString);
		if (returnString.length() != 10) {				// if length is not matching the requirements
			throw new IllegalArgumentException();		// throw this exception
		}
		return returnString;							// return the numbers as string
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		String[] stringArray = string.split("[^a-zA-z]+");

		// System.out.println(Arrays.toString(stringArray));

		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		for (String word : stringArray) {
			if (returnMap.containsKey(word)) {
				returnMap.replace(word, returnMap.get(word) + 1);	// if it is already in the map, replace it with 1 higher number
			} else {
				returnMap.put(word, 1); 							// if it is not already in the map, add it and set to 1
			}
		}

		return returnMap;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {

			// System.out.println(sortedList.toString());
			int i = (int) Math.floor(sortedList.size() / 2);		// get index to compare
			List<T> subList;
			int subListResult;

			if (sortedList.get(i).compareTo(t) == 0) {				// if compare to returns 0
				return i;											// means it is match, return
				
			} else if (sortedList.size() == 1) {
				return -1;
			
			} else if (sortedList.get(i).compareTo(t) > 0) {			// if compareto is positive, obj at index is greater than T
				subList = sortedList.subList(0, i);				 		// get list for lesser side.
				subListResult = new BinarySearch<>(subList).indexOf(t);	// recursively call for less side
				if (subListResult == -1) {								// if at some point returns -1, means it didnt find
					return -1;
				} else {
					return subListResult;
				}
			
			} else {													// if compareto is negative, obj at index is less than T
				subList = sortedList.subList(i, sortedList.size());		// get list for greater side
				subListResult = new BinarySearch<>(subList).indexOf(t);	// recursively call for g
				if (subListResult == -1) {
					return -1;
				} else {
					return i + subListResult;
				}
			}
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		String[] stringArray = string.split(" ");
		StringBuilder tempFirst;
		StringBuilder tempLast;
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < stringArray.length; i++) {
			if (i != 0) {
				result.append(" ");
			}
			tempFirst = new StringBuilder();
			tempLast = new StringBuilder();

			tempFirst.append(stringArray[i].replaceFirst("^(qu|[bcdfghjklmnprstvwxyz])+", ""));
			// System.out.println(tempFirst);
			int lengthDif = stringArray[i].length() - tempFirst.length();

			tempLast.append(stringArray[i].substring(0, lengthDif));
			// System.out.println(tempLast);

			result.append(tempFirst);
			result.append(tempLast);
			result.append("ay");
		}

		return result.toString();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		int numOfDigits = 0;
		List<Integer> digits = new ArrayList<Integer>();
		for (int i = input; i > 0; i /= 10) {
			digits.add(i % 10);
			numOfDigits += 1;
		}

		int comp = 0;
		for (int e : digits) {
			comp += Math.pow(e, numOfDigits);
		}

		// System.out.println(comp);
		if (comp == input) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {

		List<Long> result = new ArrayList<Long>();

		while (l != 1) {
			for (int i = 2; i <= l; i++) {
				if (l % i == 0) {
					result.add((long) i);
					l = l / i;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;
		private Map<Character, Character> rotMap;

		public RotationalCipher(int key) {
			super();
			this.key = key;
			rotMap = new HashMap<Character, Character>();

			char[] alphChars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			// System.out.println(alphChars);

			for (int i = 0; i < alphChars.length; i++) {
				// System.out.print(alphChars[(i+key)%26]);
				rotMap.put(alphChars[i], alphChars[(i + this.key) % 26]);
			}
			// System.out.println();

			alphChars = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
			// System.out.println(alphChars);

			for (int i = 0; i < alphChars.length; i++) {
				// System.out.print(alphChars[(i+key)%26]);
				rotMap.put(alphChars[i], alphChars[(i + this.key) % 26]);
			}
			// System.out.println();

		}

		public String rotate(String string) {
			char[] charArray = string.toCharArray();
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < charArray.length; i++) {
				if (rotMap.containsKey(charArray[i])) {
					result.append(rotMap.get(charArray[i]));
				} else {
					result.append(charArray[i]);
				}
			}
			// System.out.println(result.toString());
			return result.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) throws IllegalArgumentException {
		if (i < 1) {
			throw new IllegalArgumentException();
		}

		int nPrime = 2;
		for (int primeCount = 1, curNum = 3; primeCount < i; curNum++) { // iterating until we find i number of primes,
																			// incrementing curNum per loop
			boolean isPrime = true;

			for (int div = 2; div <= curNum / 2; div++) { // checking if j is prime
				if (curNum % div == 0) { // break if number is divisible by number between 2 and itself
					isPrime = false;
					break;
				}
			}

			if (isPrime) { // if it did not break, means prime, increment prime count
				primeCount++;
				nPrime = curNum;
			}
		}
		return nPrime;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */

	private static HashMap<Character, Character> atbashMap = new HashMap<Character, Character>();
	{
		String atbashStr = "abcdefghijklmnopqrstuvwxyz";
		char[] charArray = atbashStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			atbashMap.put(charArray[i], charArray[charArray.length - 1 - i]);
		}
	}

	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			char[] charArray = string.toLowerCase().replaceAll("[^a-z0-9]", "").toCharArray();
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < charArray.length; i++) {
				if (i != 0 && i % 5 == 0) {
					result.append(' ');
				}
				if (atbashMap.containsKey(charArray[i])) {
					result.append(atbashMap.get(charArray[i]));
				} else {
					result.append(charArray[i]);
				}
			}
			// System.out.println(result.toString());
			return result.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			return encode(string).replaceAll(" ", "");
		}

	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {

		char[] charArray = string.replaceAll("-", "").toCharArray();
		if (charArray.length != 10) {
			return false;
		} else {
			int result = 0;
			for (int i = 2; i <= charArray.length; i++) {

				// System.out.print(charArray[charArray.length - i] + "\t");
				int cVal = Character.getNumericValue(charArray[charArray.length - i]);
				// System.out.print(cVal + "\t");

				if (cVal < 0 || cVal > 9) {
					return false;
				}

				result += cVal * i;
				// System.out.print("\t" + result + "\n");
			}

			int lastDigit = Character.getNumericValue(charArray[9]);
			if (lastDigit == 33) {
				lastDigit = 10;
			} else if (lastDigit < 0 || lastDigit > 9) {
				return false;
			}
			result += lastDigit;

			// System.out.println(result);

			if (result % 11 == 0) {
				// System.out.println("tru");
				return true;
			} else {
				// System.out.println("fal");
				return false;
			}
		}
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		char[] charArray = string.toLowerCase().replaceAll("[^a-z]", "").toCharArray();
		HashSet<Character> charSet = new HashSet<Character>();
		for (char c : charArray) {
			charSet.add(c);
		}
		return (charSet.size() == 26);
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		String parsed = given.toString();
		if (given instanceof LocalDate) {
			parsed += "T00:00:00";
		}
//		System.out.println(parsed);
//		System.out.println(LocalDateTime.parse(parsed).plusSeconds(1000000000L));
		return LocalDateTime.parse(parsed).plusSeconds(1000000000L);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {

		HashSet<Integer> multiples = new HashSet<Integer>();

		for (int mult = 1; mult < i; mult++) { // looping through 1 to given number
			for (int check : set) { // for each elem in given set, check if iterator is multiple of that
				if (mult % check == 0) { // mod = 0 means is multiple
					multiples.add(mult); // add it to the set if multiple
				}
			}
		}
		// System.out.println(multiples);
		int result = 0;
		for (int mult : multiples) {
			result += mult; // sum all multiples to result
		}
		return result;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		char[] charArray = string.replaceAll(" ", "").toCharArray();
		if (charArray.length < 2) {
			return false;
		} else {
			int result = 0;
			for (int i = 1; i <= charArray.length; i++) {

				// System.out.print(charArray[charArray.length - i] + "\t");
				int cVal = Character.getNumericValue(charArray[charArray.length - i]);

				if (cVal < 0 || cVal > 9) {
					return false;
				}

				if (i % 2 == 0) {
					cVal *= 2;
					// System.out.print(cVal + " ");
					if (cVal > 9) {
						cVal -= 9;
						// System.out.print(cVal);
					}
				}

				result += cVal;
				// System.out.print("\t" + result + "\n");
			}

			// System.out.println(result);

			if (result % 10 == 0) {
				// System.out.println("tru");
				return true;
			} else {
				// System.out.println("fal");
				return false;
			}
		}
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	private static HashMap<String, Integer> wordSet = new HashMap<String, Integer>();
	{
		wordSet.put("plus", 0);
		wordSet.put("minus", 1);
		wordSet.put("multiplied", 2);
		wordSet.put("divided", 3);
	}

	public int solveWordProblem(String string) {
		String[] stringArray = string.replaceAll("[^ \\-a-zA-Z0-9]+", "").split(" ");
		List<Integer> nums = new ArrayList<Integer>();
		int operator = 4; // 4 = invalid
							// System.out.println(Arrays.toString(stringArray));
		for (String e : stringArray) {
			if (e.matches("^-?[0-9]+$")) {
				// System.out.println(Integer.valueOf(e));
				nums.add(Integer.valueOf(e));
			} else if (wordSet.containsKey(e)) {
				// System.out.println(e);
				operator = wordSet.get(e);
			}
		}

		if (2 != nums.size() || operator == 4) {
			return -1;
		}

		switch (operator) {
		case 0:
			return nums.get(0) + nums.get(1);
		case 1:
			return nums.get(0) - nums.get(1);
		case 2:
			return nums.get(0) * nums.get(1);
		case 3:
			return nums.get(0) / nums.get(1);

		default:
			return -1;
		}
	}
}