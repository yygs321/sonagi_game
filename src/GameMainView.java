import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//이름 입력과 단계 선택 
public class GameMainView extends JFrame implements ActionListener{
	JButton start; //시작 버튼
	JLabel id, level, img; // 이름 입력
	JTextField name; 
	JComboBox levels; //레벨 선택
	GridBagLayout gridbag;
	GridBagConstraints con;
	ImageIcon back;
	public String ID;
	public String Level;
    Main main;
	
	public GameMainView() {
		super("단어 맞추기 게임");
		
		gridbag = new GridBagLayout();
		con = new GridBagConstraints();
		setLayout(gridbag);
		
		//그림
		back = new ImageIcon("img/back.png");
		img = new JLabel(back);
		con.fill = GridBagConstraints.BOTH;
		addComponent(img, 0, 0, 4, 3, 0, 0);
		
		//이름 입력
		id = new JLabel("ID ");
		addComponent(id, 0, 3, 1, 1, 0, 0);
		
		name = new JTextField(10);
		name.setToolTipText("5글자 이내로 설정");
		addComponent(name, 1, 3, 2, 1, 0, 0);
		
		
		//레벨 선택
		level = new JLabel("Level ");
		addComponent(level, 0, 4, 1, 1, 0, 0);
		
		String[] item = {"1단계", "2단계", "3단계"};
		levels = new JComboBox(item);
		addComponent(levels, 1, 4, 2, 1, 0, 0);
		
		//시작 버튼
		start = new JButton("Start!");
		start.setBackground(new Color(200,200,200));
		addComponent(start, 3, 3, 1, 2, 0, 0);
		start.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setVisible(true);
		
	}
	
    //메인 실행
	public void setMain(Main main) {
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	     {
		  
		if(!name.getText().equals("")){
			//입력받은 값 ID 변수에 저장 후 게임화면 띄우기 
			ID = name.getText().toString();
			Level= levels.getSelectedItem().toString();
			main.playGame(this, ID, Level);
		}
	}
	
	//컴포넌트 추가하는 메소드
	public void addComponent (Component c, int col, int row, int w, int h, int wx, int wy) {
		con.gridx = col; 
		con.gridy = row;
		con.gridwidth = w;
		con.gridheight = h;
		con.weightx = wx;
		con.weighty = wy;
		
		gridbag.setConstraints(c, con);
		add(c);
	}

}
