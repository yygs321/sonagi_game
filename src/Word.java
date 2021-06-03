import java.io.*;
import java.util.*;

import javax.swing.JFrame;

//IT공학전공 1916513 박희수

public class Word {
	
	
	List<String> words = new ArrayList<String>();
	List<String> RandomWords = new ArrayList<String>();
	
	public List<String> WordCreate(int level) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("img/word.txt"));
	        String str;
	        while ((str = in.readLine()) != null) {
	        	String arr[] = str.split(" ");
	        	for(String data: arr) {
	        		words.add(data);
	        	}
	        }
	        in.close();
			} 
		catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {System.out.println(e);
		}
		//1단계일 때는 30개의 단어, 2단계일떄에는 40, 3단계에는 50개의 단어 리턴
		return Word.getRandomElement(words, 10 * level + 20);
	}
	
	public static List<String> getRandomElement(List<String> words2, int totalItems) { 
		Random rand = new Random(); 
		List<String> newList = new ArrayList<String>(); 
		for (int i = 0; i < totalItems; i++) { 
			int randomIndex = rand.nextInt(words2.size()); 
			newList.add(words2.get(randomIndex)); 
			words2.remove(randomIndex); 
		}	 
		return newList; 
	} 	
}
