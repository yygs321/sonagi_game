import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class WordAndTimer {
	 private static final long serialVersionUID = 1L;
	    List<String> words = new ArrayList<String>();
	    List<String> RandomWords = new ArrayList<String>();
	    public JLabel[] label = new JLabel[100];
	    JPanel word, Timer;
	    
	    private int speed, i,num;

	    JButton pause;
	    Thread p_display;
	    JLabel w1, w2, w3,Time;
	    int mm, ss, ms, t = 0;
	    
	    Rain rain = new Rain();
	    playTime play = new playTime();
	    
	    public WordAndTimer(String Level) {
	        int level=0;

	        //난이도에 따라 단어 갯수 늘리기
	        switch(Level) {
	            case "1단계":
	                level=1;
	                num=30;
	                break;
	            case "2단계":
	                level=2;
	                num=40;
	                break;
	            case "3단계":
	                level=3;
	                num=50;
	                break;
	            default: break;
	        }

	        RandomWords = WordCreate(level);
	        //System.out.println(RandomWords);
	        speed = Speed(level);

	        rain.start();
	        buildGUI();
	        play.start();
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
	        return Word.getRandomElement(words, num);
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
	            case 1: return 1300;
	            case 2: return 1100;
	            case 3: return 900;
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
	                try {
	                    label[i] = new JLabel(RandomWords.get(i));
	                    label[i].setFont(new Font ("Serif", Font.BOLD, 14));
	                    label[i].setBounds(0, 0, 100, 20);
	                    word.add(label[i]);
	                    label[i].setLocation(random.nextInt(700), 2); // x값 랜덤으로 보여주기
	                } catch (IndexOutOfBoundsException e) {

	                }

	                RainFalling();
	                try {
	                    Thread.sleep(speed);
	                } catch (InterruptedException e) {

	                    e.printStackTrace();
	                }
	            }

	        }

			private void RainFalling() {
				// TODO Auto-generated method stub
				for(int j =0; j<= i; j++) {
		            try {
		                if (label[j].isVisible()) {
		                    label[j].setLocation(label[j].getX(), label[j].getY() + 14);
		                }
		            } catch(NullPointerException e) {

		            }
		    	}
			}

	    }
	    
	    class playTime extends Thread{
	            @Override
	            public void run() {
	                mm = Integer.parseInt(w1.getText());
	                ss = Integer.parseInt(w2.getText());
	                ms = Integer.parseInt(w3.getText());


	                while (p_display == Thread.currentThread()) {

	                    mm = t % (1000 * 60) / 100 / 60;
	                    ss = t % (1000 * 60) / 100 % 60;
	                    ms = t % 100;
	                    //분, 초를 단위에 맞게 변환

	                    try {
	                        Thread.sleep(10);

	                        w1.setText(String.format("%02d", mm));
	                        w2.setText(String.format("%02d", ss));
	                        w3.setText(String.format("%02d", ms));

	                        t++;

	                    } catch (InterruptedException e1) {
	                    }
	                }
	            }
	    	
	    }
	    
	    private void buildGUI() { //타이머 화면 설정
	        JPanel p = new JPanel(new BorderLayout());
	        JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        JPanel wp = new JPanel(new FlowLayout(FlowLayout.CENTER));

	        JLabel c1 = new JLabel(" : ");
	        JLabel c2 = new JLabel(" : ");
	        w1 = new JLabel("00");
	        w2 = new JLabel("00");
	        w3 = new JLabel("00");
	        Time = new JLabel("경과 시간");


	        pause = new JButton("PAUSE");
	        bp.add(Time);


	        wp.add(w1);
	        wp.add(c1);
	        wp.add(w2);
	        wp.add(c2);
	        wp.add(w3);

	        p.add(wp, BorderLayout.CENTER);
	        p.add(bp, BorderLayout.SOUTH);
	        Timer.add(p);


	        pause.setFont(new Font("serif", Font.BOLD, 10));


	        w1.setFont(new Font("courier", Font.BOLD, 15));
	        w2.setFont(new Font("courier", Font.BOLD, 15));
	        w3.setFont(new Font("courier", Font.BOLD, 15));

	        c1.setFont(new Font("courier", Font.BOLD, 15));
	        c2.setFont(new Font("courier", Font.BOLD, 15));

	        pause.setEnabled(true);
	        //처음에는 pause 버튼 활성

	    }
	
			
}
