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
		add(new Label("3�ܰ� ��ŷ", Label.CENTER), BorderLayout.NORTH);
		
		
		String[] columnName= {"����", "�̸�","�ð�"	};
		Object[][] data= {
				{"1��", "ȫ�浿", "2:00"},
				{"2��", "�ڴ���", "3:02"},
				{"3��", "�̼���", "5:02"},
				{"4��", "�贫��", "5:46"},
				{"5��", "�ּ���", "6:32"},
				{"6��", "�Ǵ���", "7:26"},
		};
		
		table= new JTable(data, columnName);
		scroll=new JScrollPane(table);
		add(scroll,BorderLayout.CENTER);

		
		Panel panel2= new Panel();
		panel2.setLayout(new FlowLayout());
		
		restart = new JButton("�ٽ� ����");
		end = new JButton("����");
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
