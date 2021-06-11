import java.io.*;
import java.util.*;

import javax.swing.*;

//IT공학전공 1916513 박희수

public class Word{
	
	List<String> words = new ArrayList<String>();
	List<String> RandomWords = new ArrayList<String>();
	private JLabel label[] = new JLabel[100];
	private int speed, i;
	private JFrame frame;
	
	public Word(JFrame myFrame, int level) {
		RandomWords = WordCreate(level);
		System.out.println(RandomWords);
		speed = Speed(level);
		frame = myFrame;
		Rain rain = new Rain();
		rain.start();
	}
	
	//파일 읽어서 단어 배열에 추가
	public  List<String> WordCreate(int level) {
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
	
	//무작위로 단어 선택해서 배열 리턴 
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
	
	//레벨에 따른 스피드
	public int Speed(int level) {

		switch(level) {
		case 1: return 1000;
		case 2: return 800;
		case 3: return 400;
		default:
			return 3000;
		}
	}
	
	//단어 생성하는 쓰레드
	class Rain extends Thread{
		@Override
		public void run() {
			for(i =0; i<= 10000; i++) {
				Random random = new Random();
				label[i] = new JLabel(RandomWords.get(i));
				label[i].setBounds(0, 0, 80, 20);
				frame.add(label[i]);
				label[i].setLocation(random.nextInt(700), 2);// x값 랜덤으로 보여주기
				new RainFall().start();
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}
		
	}
	//단어 떨어지는 쓰레드
	class RainFall extends Thread{
		@Override
		public void run() {
			for(int j =0; j<= i; j++) {
				if (label[j].isVisible()) {
					int sp = label[j].getY();
					int xp = label[j].getX();

					label[j].setLocation(xp, sp + 10);

				}
			}
		}
		
		
	}
}