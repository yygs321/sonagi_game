import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
	

    public static void main(String[] args) {
    	Main main= new Main();
    	GameMainView login= new GameMainView();
    	
    	login.setMain(main);
    }
    
	
	public void playGame(GameMainView login, String ID){
		login.dispose();
        //setLayout(null);

        JFrame myFrame = new JFrame("산성비 게임"); // JFrame 객체를 생성한다
        //Word wordThread = new Word(1);
        //wordThread.setBackground(Color.white);
        //wordThread.setBounds(0,80,800,400);
        //myFrame.add(wordThread);
        myFrame.add(new Gui(ID));




        myFrame.setSize(800, 596); // JFrame 사이즈 정의
        myFrame.setLocationRelativeTo(null); // JFrame을 모니터의 중앙에 띄우게 한다

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
	}

}
