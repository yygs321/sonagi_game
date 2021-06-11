import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Ranking extends JFrame{
	private JButton restart, end;
	private JTable table;
	private JScrollPane scroll;
	
	public Ranking() throws IOException {
		this.setSize(350,230);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Ranking");
		
		setLayout(new BorderLayout(5,5));
		add(new Label("3단계 랭킹", Label.CENTER), BorderLayout.NORTH);
		
		
		String[] columnName= {"순위", "이름","시간"	};

		/*텍스트파일에서 불러와서 넣기
		String line[];

		Scanner input= new Scanner(new File("result.txt"));
		String str;
		int col=3;
		int row=0;
		while(input.hasNextLine()) {
			row +=1;
		}
		Object[][] data= new Object[row][col];
		input.close();
		
		input=new Scanner(new File("result.txt"));
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(input.hasNextLine());
			}
		}
		*/
		
		
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
