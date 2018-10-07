import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary(); 

	
	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	}

	
	public static String stackToReverseString(MyStack stack) {
		// 'pops' every value of the given stack and 'pushes' it into a new stack in reverse order.
		// after it has every value of the orginal stack, the new stack then pops every one of its value,
		// not only pushing it back into the original stack, but saving the stacks content in reverse order
		// within a new string. The string is returned. 

		String stringInOrder = "";
		MyStack originalStack = stack;
		MyStack tempStack = new MyStack();
		Object tempObject = null;

		// while loop that popes every value of the 'originalStack' and pushes it in reverse order
		// for the 'tempStack'.
		while(!originalStack.isEmpty()){
			tempStack.push(originalStack.pop());
		}

		// while loop that pushes every value of the 'tempStack' and pushes it back into its original order
		// within the 'originalStack', while saving the reverse order in the string 'stringInOrder'.
		while(!tempStack.isEmpty()){
			tempObject = tempStack.pop();
			originalStack.push(tempObject);
			stringInOrder = stringInOrder + (String)tempObject + " ";

		}

		return stringInOrder;
	}

	public static String reverseStringAndRemoveNonAlpha(String text) {
		// cycles through all the characters within the String 'text', pulling each individual char.
		// The pointer 'foundChar' points to the given char value. The if statement uses the function 
		// 'Character.isAlphabetic' to determine if the given char (pointed to by'foundChar') is within 
		// the alphabet. If so, it's pushed into the stack 'newStack'. The final while loop cycles
		// through all the values of 'newStack', popping off all the values and saving them as 
		// Characters in the string 'stringInOrder'. The String is returned. 

		MyStack newStack = new MyStack();
		String stringInOrder = "";
		Character poppedCharacter = null;

		// determines if the chars of the string 'text' are in the alphabet, if so, they are added 
		// to 'newStack'.
		for(int i = 0; i < text.length(); i++){
			char foundChar = text.charAt(i);
			if(Character.isAlphabetic(foundChar)){
				Character tempCharacter = new Character(foundChar);
				newStack.push(tempCharacter);
			}
		}

		// pops and saves values of 'newStack' in the String 'stringInOrder'.
		while(!newStack.isEmpty()){
			poppedCharacter = (Character)newStack.pop();
			stringInOrder = stringInOrder + poppedCharacter;

		}

		return stringInOrder.toLowerCase();
	}



	// Returns true if the text is a palindrome, false if not.
	public static boolean isPalindrome(String text) {
		// creates a new stack and a new queue called 'newStack' and 'newQueue' respectively.
		// cycles through all the characters within the String 'text', pulling each individual char.
		// The pointer 'foundChar' points to the given char value. The if statement uses the function 
		// 'Character.isAlphabetic' to determine if the given char (pointed to by'foundChar') is within 
		// the alphabet. If so, it's pushed into the stack 'newStack' and enqueu into the queue 'newQueue'.
		// the final while loop pops and dequeues characters from the stack and queue, casting and saving 
		// the found values as 'stackCharacter' and 'queueCharacter' through each iteration. It then checks
		// to see if the two characters are not equal, if so, it returns false. The default is for it to 
		// return true.

		// makes the given String 'text' all lowercase and sets it equal to the String 'lowerCaseText'.
		String lowerCaseText = text.toLowerCase();
		MyStack newStack = new MyStack();
		MyQueue newQueue = new MyQueue();

		// for loop that finds each pulls each character in the 'lowerCaseText', wraps them as a 
		// Character, and adds them to the data structures 'newStack' and 'newQueue'.
		for(int i = 0; i < lowerCaseText.length(); i++){
			char foundChar = lowerCaseText.charAt(i);
			if(Character.isAlphabetic(foundChar)){
				Character tempCharacter = new Character(foundChar);
				newStack.push(tempCharacter);
				newQueue.enqueue(tempCharacter);
			}
		}

		// while loop that checks if each character is equal forward to backward.
		while(!newQueue.isEmpty() && !newStack.isEmpty()){
			Character stackCharacter = (Character)newStack.pop();
			Character queueCharacter = (Character)newQueue.dequeue();
			if(!stackCharacter.equals(queueCharacter)){
				return false;
			}
		}

		return true;
	}




	public static void explorePalindrome(String text) {
		// Takes the given String 'text' and uses 'reverseStringAndRemoveNonAlpha' to remove 
		// everything that is not within the alphanet. It's reversed and saved a String 'reversedText'
		// initializes a new Stack, 'newStack'
		// implements decomposeText with the given values and a start index of '0'
		String reversedText = reverseStringAndRemoveNonAlpha(text);
		MyStack newStack = new MyStack();
		decomposeText(text,reversedText,0,newStack);
	}

	// Recursive helper function designed for the process of 'explorePalindrome'. Uses the 'getWord' function to pull
	// all the possible words formed of the string 'textToDecompose' starting at index 0 of the string. Whatever words
	// are found are saved in the string array 'word'. The for loop pushes each word in the array to the stack 
	// decomposition. A recursive call to decomposeText with the updated stack decomposition and the index heightened
	// by the size of the given word's size allows the function to search for any more words following the orginal.
	// if so, the index will eventually reach the size of the original text's length and it will print out the possible
	// iterations. If not, it will try the other words within the 'getWord' array and complete the same process. 
	public static void decomposeText(String orginalText, String textToDecompose, int index, MyStack decomposition){
		if(index == textToDecompose.length()){
			System.out.print(orginalText + ": ");
			String finalProduct = stackToReverseString(decomposition);
			System.out.println(finalProduct);
		} else {
			String[] getWord = getWords(textToDecompose,index);

			for(int i = 0; i < getWord.length; i++){
				String word = getWord[i];
				int wordLength = word.length();
				decomposition.push(word);
				decomposeText(orginalText,textToDecompose,index + wordLength,decomposition);
				decomposition.pop();
			}	
		}
	}

	// This function looks at the arguments that are passed 
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {	
		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = {"A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami"};
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);

			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					explorePalindrome(testPalindromes[i]);
				}	
			}
			else {
				System.out.println("unknown mode: " + mode);
			}
		}
		
	}
}