
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

//1714098 미디어학부 박예원

public class Gui extends JPanel implements ActionListener,KeyListener {

    int level,time,num;
    int score=0;
    String ID, Level;
    private JLabel Ltime,Lscore,Llife,Lprofile,Ltop,Lbottom,LLevel,levelLabel, scoreLabel,idLabel,heartLabel;
    private JTextField answer;
    private JButton sendB;

    Word wordThread = new Word(level);
    new_playTime pt = new new_playTime();
    
    public Gui(String id, String level){
    	ID=id;
    	Level=level;

    	//난이도에 따라 단어 갯수 늘리기
    	switch(level) {
		case "1단계":
			num=30;
			break;
		case "2단계": 
			num=40;
			break;
		case "3단계": 
			num=50;
			break;
		default: break;
    	}
    	
        wordThread.setBackground(Color.white);
        wordThread.setBounds(0, 80, 800, 400);
        add(wordThread);
    	
        setSize(1280,720);
        setLayout(null);
        setBackground(Color.white);

        LLevel = new JLabel("난이도");
        LLevel.setHorizontalAlignment(JLabel.CENTER); //텍스트 가운데 정렬
        LLevel.setOpaque(false);
        LLevel.setBounds(255,10,85,30);
        add(LLevel);

        scoreLabel = new JLabel("맞은 갯수");
        scoreLabel.setOpaque(false);
        scoreLabel.setBounds(520,10,85,30);
        add(scoreLabel);

        //텍스트라벨 표시

        pt.setBounds(340, 10, 150, 150);
        add(pt);

        //타이머 배치
      
        
        //ImageIcon timeB = new ImageIcon("img/box.png");
        //Ltime = new JLabel(timeB);
        //Ltime.setOpaque(false);
        //Ltime.setBounds(230,40,85,30);
        //add(Ltime);
        // 타이머 따로 구현함

        /*
        ImageIcon scoreB = new ImageIcon("img/box.png");
        Lscore = new JLabel(scoreB);
        Lscore.setOpaque(false);
        Lscore.setBounds(490,40,85,30);
        add(Lscore);
		*/ 
        
        //난이도, 시간, 점수를 나타낼 공간 표시


        //ImageIcon pause = new ImageIcon("img/pause_icon.png");
        //stopB = new JButton(pause);
       // stopB.setBounds(700,10,65,65);
        //stopB.setOpaque(false);
        //stopB.setBorderPainted(false);
        //add(stopB);

        //일시정지 버튼이 타이머에 귀속됨

        // 상단바 완료

        ImageIcon profile = new ImageIcon("img/user_icon.png");
        Lprofile = new JLabel(profile);
        Lprofile.setOpaque(false);
        Lprofile.setBounds(10,520,48,48);
        add(Lprofile);

        idLabel = new JLabel();
        idLabel.setOpaque(false);
        idLabel.setBounds(60,523,70,30);
        idLabel.setText(ID);
        add(idLabel);
        
        levelLabel= new JLabel();
        levelLabel.setOpaque(true);
        levelLabel.setBounds(260,40,85,30);
        levelLabel.setText(Level);
        levelLabel.setHorizontalAlignment(JLabel.CENTER); //텍스트 가운데 정렬
        Color color1= new Color(234,234,234);
        levelLabel.setBackground(color1);  
        levelLabel.setForeground(Color.black);    
        add(levelLabel);

        scoreLabel= new JLabel();
        scoreLabel.setOpaque(true);
        scoreLabel.setBounds(490,40,85,30);
        scoreLabel.setText(Integer.toString(score)+" / "+Integer.toString(num));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER); //텍스트 가운데 정렬
        Color color2= new Color(234,234,234);
        scoreLabel.setBackground(color2);  
        scoreLabel.setForeground(Color.black);    
        add(scoreLabel);
        
        ImageIcon hearts = new ImageIcon("img/heart_3.png");
        heartLabel = new JLabel(hearts);
        heartLabel.setOpaque(false);
        heartLabel.setBounds(150,515,90,60);
        add(heartLabel);

        ImageIcon lifeB = new ImageIcon("img/box.png");
        Llife = new JLabel(lifeB);
        Llife.setOpaque(false);
        Llife.setBounds(145,530,95,30);
        add(Llife);

        answer = new JTextField();
        answer.setText("단어를 입력하세요");
        answer.setOpaque(false);
        answer.setBounds(300,527,250,35);
        answer.addKeyListener(this);
        add(answer);


        sendB = new JButton("전송");
        sendB.setBounds(570,527,65,35);
        sendB.setOpaque(false);
        sendB.addActionListener(this);
        add(sendB);


        ImageIcon bottom = new ImageIcon("img/bottom.png");
        Lbottom = new JLabel(bottom);
        Lbottom.setOpaque(false);
        Lbottom.setBounds(0,520,1280,100);
        add(Lbottom);



        ImageIcon top = new ImageIcon("img/top.png");
        Ltop = new JLabel(top);
        Ltop.setOpaque(false);
        Ltop.setBounds(0,0,1280,80);
        add(Ltop);

    }
    public void removeAnswer() { // 정답처리 메소드
        for (int i = 0; i < 20; i++) {
            if (answer.getText().equals(wordThread.RandomWords.get(i))) {
                wordThread.label[i].setVisible(false);
                score++;
                //score변경해서 맞힌개수 다시 출력
                scoreLabel.setText(Integer.toString(score)+" / "+Integer.toString(num));
                // 해당단어 삭제, 점수 추가
                //점수 업데이트 함수 들어가야 함
            }
        }
        answer.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == sendB) {
            removeAnswer();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()== KeyEvent.VK_ENTER) { // 엔터 누르면 답변 지움
            removeAnswer();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

}