import java.awt.*;
import javax.swing.*;

public class Gui extends JPanel {
    int level,time,score;
    String ID;

    private JLabel Llevel,Ltime,Lscore,Llife,Lprofile,Ltop,Lbottom,Level,scoreLabel,idLabel,heartLabel;
    private JTextField answer;
    private JButton stopB,sendB;
    
    public Gui(String id){
    	ID=id;
    	
        setSize(1280,720);
        setLayout(null);
        setBackground(Color.white);

        Level = new JLabel("난이도");
        Level.setOpaque(false);
        Level.setBounds(275,10,85,30);
        add(Level);

        scoreLabel = new JLabel("점수");
        scoreLabel.setOpaque(false);
        scoreLabel.setBounds(520,10,85,30);
        add(scoreLabel);

        //텍스트라벨 표시


        ImageIcon levelB = new ImageIcon("img/box.png");
        Llevel = new JLabel(levelB);
        Llevel.setOpaque(false);
        Llevel.setBounds(250,40,85,30);
        add(Llevel);

        
        ImageIcon timeB = new ImageIcon("img/box.png");
        Ltime = new JLabel(timeB);
        Ltime.setOpaque(false);
        Ltime.setBounds(370,40,85,30);
        add(Ltime);

        ImageIcon scoreB = new ImageIcon("img/box.png");
        Lscore = new JLabel(scoreB);
        Lscore.setOpaque(false);
        Lscore.setBounds(490,40,85,30);
        add(Lscore);

        //난이도, 시간, 점수를 나타낼 공간 표시


        ImageIcon pause = new ImageIcon("img/pause_icon.png");
        stopB = new JButton(pause);
        stopB.setBounds(700,10,65,65);
        stopB.setOpaque(false);
        add(stopB);

        //일시정지 버튼, 이후 ActionListener 추가 예정

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
        add(answer);


        sendB = new JButton("전송");
        sendB.setBounds(570,527,65,35);
        sendB.setOpaque(false);
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
    


}