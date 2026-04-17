//Created by Noah Duran - nduran CMU

package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
	public final static String DICTIONARY = "NewDictionary.txt";
	List<Word> wordList = new ArrayList<>();
	Map<String, Word> singleMap = new HashMap<>();
	Map<String, List<Word>> multiMap = new HashMap<>();

	//DO NOT CHANGE MAIN METHOD
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.loadWordList();
		d.loadSingleMap();
		d.loadMultiMap();
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter search word");
		String searchWord = input.nextLine();
		
		d.searchWordList(searchWord);
		d.searchSingleMap(searchWord);
		d.searchMultiMap(searchWord);
		input.close();
	}
	
	//DO NOT CHANGE THIS METHOD
	/**loadWordList() reads the txt file. For each line, it invokes 
	 * getWord() method that returns a Word object. This object is then
	 * added to the arrayList wordList
	 */
	void loadWordList() {
		String wordString = null;
		try {
			Scanner input = new Scanner(new File(DICTIONARY));
			while (input.hasNextLine()) {
				wordString = input.nextLine();
				if (wordString.length() == 0) continue;
				wordList.add(getWord(wordString));
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	//DO NOT CHANGE THIS METHOD
	/** getWord() is a helper method to extract a word and its meaning from 
	 * a line of text. 
	 * It takes a wordString and splits it on "(". The first
	 * element after split is the word, and rest are elements of its meaning. 
	 * So it uses first element to initialize 'word' of Word, and rest to 
	 * initialize 'meaning' of Word. As '(' may occur anywhere in the 
	 * 'meaning', the split string is put back together by putting
	 * '(' in front of each piece.  
	 * @param wordString
	 * @return
	 */
	Word getWord(String wordString) {
		String[] splits = wordString.split("\\(");  //split on (
		String word = null;
		StringBuilder wordMeaningString = new StringBuilder();
		if (splits[0].length() >0) 
			word = splits[0].trim();  //get the first string as it is the word
		for (int i = 1; i < splits.length; i++) {
			wordMeaningString.append("(" + splits[i]); //put back rest of the string together
		}
		return new Word(word, wordMeaningString.toString());
	}
	
	/** searchWordList() takes a searchWord String and and searches for it in wordList.
	 * If found, it prints all its meanings. Else it prints 'Sorry! word not found!'
	 * It also returns a list of meanings, if found. Else it returns an empty list.
	 * @param searchWord
	 */
	List<String> searchWordList(String searchWord) {
		List<String> meaningList = new ArrayList<>();

		
		System.out.println('\n' +"--------WordList Search--------");
		//searching for the word in wordList
		for (Word word : wordList) {
			if (word.word.equalsIgnoreCase(searchWord)) {
				// if the word was found regardless of upper or lower, it will print the meaning
				meaningList.add(word.meaning);
				System.out.println(word.meaning);
				// returning the list of meanings from the searched word
			}
		}

		if (meaningList.isEmpty()) {
		//Pringing out response if the word inst found
		System.out.println("Sorry! " + searchWord + " not found!");
		}
		
		return meaningList;
	}


	/** loadSingleMap() takes each word from
	 * wordList and loads it into singleMap with key being
	 * the Word's word in lowercase, and its value being the whole 
	 * Word object.
	 */
	void loadSingleMap() {
		//for every word thats in the list it will use the lowercase version of the word as the key and the definition 'word' from the word class as the value
		for (Word word : wordList) {
			singleMap.put(word.word.toLowerCase(), word);
		}
	}

	/**loadMultiMap() takes each word from wordList and loads it 
	 * into multiMap with key being the Word's word in lowercase, and 
	 * its value being a list of Words with its meanings found in the 
	 * dictionary
	 */
	void loadMultiMap() {
		/* for every word thats in the list it will use the lowercase version of the word as the key
		 and a list of definitions as the value */
		for (Word word : wordList) {
			String key = word.word.toLowerCase();
			//if the key isnt already in the map then it will add it with an empty list as the value
			if (!multiMap.containsKey(key)) {
				multiMap.put(key, new ArrayList<>());
			}
			multiMap.get(key).add(word);
		}
	}

	/** searchSingleMap() takes a searchWord String and searches for it in singleMap.
	 * If found, it prints its meaning. Else it prints 'Sorry! word not found!'
	 * It also returns the meaning string, if found, or else it returns an empty String. 
	 * @param searchWord
	 */
	String searchSingleMap(String searchWord) {
		//searching for the word and printing the meaning if found, otherwise printing a blank string
		Word word = singleMap.get(searchWord.toLowerCase());
		if (word != null) {
			System.out.println('\n' +"--------SingleMap Search--------");
			System.out.println(word.meaning);
			return word.meaning;
		} else {
			//Since the searchWordList mehtod is already printing the message this will be blank
			System.out.println('\n' +"--------SingleMap Search--------");
			System.out.println("Sorry! " + searchWord + " not found!");
			return null;
		}
	}
	//OPTIONAL
	/** searchMultiMap() takes a searchWord String and searches for it in multiMap. 
	 * If found, it prints all its meanings. Else it prints 'Sorry! word not found!'
	 * It also returns a list of meanings, if found. Else it returns an empty list.
	 * @param searchWord
	 */
	List<String> searchMultiMap(String searchWord) {
		//searching for the word but this time creating a new arraylist to hold all the meanings of the word
		List<Word> words = multiMap.get(searchWord.toLowerCase());
		if (words != null) {
			List<String> meanings = new ArrayList<>();
			System.out.println('\n' + "--------MultiMap Search--------");
			for (Word word : words) {
				//since the word is found the meaning is added to the arraylist to be printed
				meanings.add(word.meaning);
				System.out.println(word.meaning);
			}
			return meanings;
		} else {
			//if no word was found it will return an empty list since the message is already printed 
			System.out.println('\n' + "--------MultiMap Search--------");
			System.out.println("Sorry! " + searchWord + " not found!");
			return new ArrayList<>();
		}
}
	
}

