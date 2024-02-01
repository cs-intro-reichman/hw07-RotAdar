

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(lowerCase(hashTag), dictionary);
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

	public static boolean existInDictionary(String word, String []dictionary) {
		// Check if the input word exist in the dictionary
		boolean exist = false;
		for (int i = 0; i < dictionary.length; i ++)
		{
			if (word.equals(dictionary[i]))
			{
				exist = true;
			}
		}
		return exist;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Return the words from the dictionary that combines the hahshtag
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
        int N = hashtag.length();
        for (int i = 1; i <= N; i++)
		{
			if (existInDictionary(hashtag.substring(0 , i), dictionary))
			{
				System.out.println(hashtag.substring(0 , i));
				breakHashTag(hashtag.substring(i , N), dictionary);
				return;
			}
        }
    }

	public static String lowerCase (String hashtag) {
		// gets an hashtag and return it in lower case
		String lowerHash = "";
		for ( int i = 0; i < hashtag.length(); i++)
		{
			if (hashtag.charAt(i) > 'A' && hashtag.charAt(i) < 'Z')
			  {
				  lowerHash += (char)(hashtag.charAt(i) - 32);
			  }
			else
			{
				lowerHash += hashtag.charAt(i);
			}
		}
		return lowerHash;
	}

}
