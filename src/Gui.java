import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

//1714098 미디어학부 박예원
//1713056 문헌정보학과 박소민

public class Gui extends JPanel{

    int level,time,num,m,s,ms;
    int score=0;
    String ID, Level;
    private JLabel Ltime,Lscore,Llife,Lprofile,Ltop,Lbottom,LLevel,levelLabel, scoreLabel,idLabel;
    public static JLabel[] heartLabel;
    public static ImageIcon hicon;
    private JTextField answer;
    private JButton sendB,stopB;


    public Gui(String id, String Level) throws IOException, ParseException{
        ID=id;

        switch(Level) {
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

        Word wordThread = new Word(Level);
        new_playTime pt = new new_playTime();

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
        scoreLabel.setHorizontalAlignment(JLabel.CENTER); //텍스트 가운데 정렬
        scoreLabel.setOpaque(false);
        scoreLabel.setBounds(490,10,85,30);
        add(scoreLabel);

        //텍스트라벨 표시

        pt.setBounds(340, 10, 150, 150);
        add(pt);

        ImageIcon pause = new ImageIcon("img/pause_icon.png");
        stopB = new JButton(pause);
        stopB.setBounds(700,10,65,65);
        stopB.setOpaque(false);
        stopB.setBorderPainted(false);
        stopB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==stopB){
                    stopB.setEnabled(false);
                    wordThread.rain.suspend();
                    pt.p_display.suspend();
                    int result = JOptionPane.showConfirmDialog(null,"다시 시작 하시겠습니까? ","일시정지됨",JOptionPane.OK_CANCEL_OPTION);
                    if (result==0){
                        //창닫기
                        setVisible(false);
                        Main main= new Main();
                        GameMainView login= new GameMainView();
                        login.setMain(main);
                        login.setLocationRelativeTo(null);
                    }else
                    {
                        stopB.setEnabled(true);
                        wordThread.rain.resume();
                        pt.p_display.resume();
                    }
                }

            }
        });
        add(stopB);

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



        hicon = new ImageIcon("img/heart_3.png");
        heartLabel = new JLabel[3];
        for(int i = 0 ; i < heartLabel.length; i++){
            heartLabel[i] = new JLabel(hicon);
            heartLabel[i].setOpaque(false);
            heartLabel[i].setBounds(157+(i*25),535,20,20);
            add(heartLabel[i]);
            heartLabel[i].setVisible(true);
        }


        ImageIcon lifeB = new ImageIcon("img/box.png");
        Llife = new JLabel(lifeB);
        Llife.setOpaque(false);
        Llife.setBounds(145,530,95,30);
        add(Llife);

        answer = new JTextField();
        answer.setText("단어를 입력하세요");
        answer.setOpaque(false);
        answer.setBounds(300,527,250,35);
        answer.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
            	if(e.getKeyCode()==e.VK_ENTER) {
                    for (int i = 0; i < num; i++) {
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
                if(score == num){ // 다 맞출 경우
                    wordThread.rain.suspend();
                    pt.p_display.suspend();
                    m=pt.mm;
                    s=pt.ss;
                    ms=pt.ms;
                    
                    //결과 파일로 저장
                    try {
						Result result= new Result(Level,ID,m,s,ms);
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    //결과 불러오기
                    try {
						Rank rank=new Rank(Level);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });
        add(answer);


        sendB = new JButton("전송");
        sendB.setBounds(570,527,65,35);
        sendB.setOpaque(false);
        sendB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                for (int i = 0; i < num; i++) {
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
        });
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