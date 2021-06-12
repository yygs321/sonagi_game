//1713056 문헌정보학과 박소민

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.Arrays;
import java.util.Comparator;

public class Rank extends JFrame{
	private JButton restart, end;
	private JTable table;
	private JScrollPane scroll;
	
	public Rank(String level) throws IOException, FileNotFoundException {
		this.setSize(350,230);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Ranking");
		
		//난이도별 랭킹 이름 변경
		String levelTitle=level+" 랭킹";
		setLayout(new BorderLayout(5,5));
		add(new JLabel(levelTitle, JLabel.CENTER), BorderLayout.NORTH);
		
		//난이도별 파일 불러오기
		String filename="";
		switch(level) {
			case "1단계":
				filename="result1.txt";
				break;
			case "2단계": 
				filename="result2.txt";
				break;
			case "3단계": 
				filename="result3.txt";
				break;
			default: break;
		}
		
		String[] columnName= {"순위", "이름","시간"	};

		//텍스트파일에서 불러와서 랭킹화면에 넣기
		Scanner input= new Scanner(new File(filename));
		
		int col=3;
		int row=0;
		
		//텍스트파일에 들어있는 결과 갯수 세기
		while(input.hasNextLine()) {
			input.nextLine();
			row +=1;
		}
		
		String[][] data= new String[row][col];
		input.close();
		
		Scanner input2= new Scanner(new File(filename));
		for(int i=0;i<row;++i) {
			for(int j=0;j<col;++j) {
				if(input2.hasNextLine()){
					//난이도,이름,시간 하나씩 출력되서 배열로 들어감.
					data[i][j]= input2.next();
				}
			}
		}
		
		//3번째 열 기준으로 순서 정렬
		Arrays.sort(data, new Comparator<String[]>() {
			public int compare(final String[] s1, final String[] s2) {
				final String time1=s1[2];
				final String time2=s2[2];
				return time1.compareTo(time2);
			}
		});
		
		//정렬된 파일 순위 매겨주기(난이도 적은 부분 순위로 )
		for(int i=0;i<row;++i) {
			data[i][0]=Integer.toString(i+1)+"위";
		}
		
		//랭킹 창 띄우기
		table= new JTable(data, columnName);
		scroll=new JScrollPane(table);
		add(scroll,BorderLayout.CENTER);

		Panel panel2= new Panel();
		panel2.setLayout(new FlowLayout());
		
		//버튼 실행
		restart = new JButton("다시 시작");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//이전 랭킹창 지움
				setVisible(false);
				
				//메인함수 재실행
				String[] args={"1"};
				Main m= new Main();
				m.main(args);
			}
		});
		end = new JButton("종료");
		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		panel2.add(restart);
		panel2.add(end);
		add(panel2, BorderLayout.SOUTH);

		super.setLocationRelativeTo(null);		//랭킹창이 가운데에 뜨도록
		setVisible(true);
	}
	

}
