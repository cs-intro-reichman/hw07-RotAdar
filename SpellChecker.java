
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) { // Return the 'tail' of a word
        return str.substring(1);
    }

	public static int levenshtein(String word1, String word2) {
		//Recursivly computes the Levinstein Distance of 2 words and return it
		word1 = lowerCase(word1);
		word2 = lowerCase(word2);
		if (word1.isEmpty())
		{
			return word2.length();
		}
		if (word2.isEmpty())
		{
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0))
		{
			return  levenshtein(tail(word1), tail(word2));
		}
		else
		{
			return 1 + Math.min(Math.min(levenshtein(tail(word1), tail(word2)) , levenshtein(word1, tail(word2))) , levenshtein(word2, tail(word1)));
		}
	}

	public static String lowerCase (String word) {
		// gets a word and return it in lower case
		String lowerWord = "";
		for ( int i = 0; i < word.length(); i++)
		{
			if (word.charAt(i) > 'A' && word.charAt(i) < 'Z')
			{
				lowerWord += (char)(word.charAt(i) - 32);
			}
			else
			{
				lowerWord += word.charAt(i);
			}
		}
		return lowerWord;
	}

	public static String[] readDictionary(String fileName) {
		//Return an array of all the words in the dictionary
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i ++)
		{
			dictionary[i] = in.readString();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Return the most similar word from the dictionary, in maximun thredsold value.
		// If there isn't such a word, return the original word
		for ( int j = 0; j <= threshold; j++) {
			for (int i = 0; i < dictionary.length; i++) {
				if (levenshtein(dictionary[i], word) == j) {
					return dictionary[i];
				}
			}
		}
		return word;
	}
}
