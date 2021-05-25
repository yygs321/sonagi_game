//1713056 문헌정보학과 박소민

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Ranking extends JFrame{
	private JButton restart, end;
	private JTable table;
	private JScrollPane scroll;
	
	public Ranking() {
		this.setSize(350,230);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Ranking");
		
		setLayout(new BorderLayout(5,5));
		add(new Label("3단계 랭킹", Label.CENTER), BorderLayout.NORTH);
		
		
		String[] columnName= {"순위", "이름","시간"	};
		Object[][] data= {
				{"1등", "홍길동", "2:00"},
				{"2등", "박눈송", "3:02"},
				{"3등", "이숙명", "5:02"},
				{"4등", "김눈송", "5:46"},
				{"5등", "최숙명", "6:32"},
				{"6등", "권눈송", "7:26"}
		};
		
		table= new JTable(data, columnName);
		scroll=new JScrollPane(table);
		add(scroll,BorderLayout.CENTER);

		
		Panel panel2= new Panel();
		panel2.setLayout(new FlowLayout());
		
		restart = new JButton("다시 시작");
		end = new JButton("종료");
		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		panel2.add(restart);
		panel2.add(end);
		add(panel2, BorderLayout.SOUTH);

		setVisible(true);
	}
	

}
