//1713056 문헌정보학과 박소민
import javax.swing.JFrame;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Result {
	//게임 종료 후 난이도, 닉네임, 시간 결과 받아서 파일에 저장
	public Result(String Level, String ID, int Minute, int Second, int Ms) throws IOException, ParseException {
		String level=Level;
		String id= ID;
		String minute=Integer.toString(Minute);
		String second=Integer.toString(Second);
		String ms= Integer.toString(Ms);
		//String time=minute+":"+second;
		
		//시간을 00:00:00형식으로 출력하도록 설정
		String pattern="mm:ss:SS";
		SimpleDateFormat sdf= new SimpleDateFormat(pattern);
		Date date= sdf.parse(minute+":"+second+":"+ms);
		
		String time= sdf.format(date);

		String filename = "";
		
		
		//난이도 별로 파일 따로 생성
		switch(level) {
			case "1단계":
				filename="result1.txt";
				openFile(filename,level,id,time);
				break;
			case "2단계": 
				filename="result2.txt";
				openFile(filename,level,id,time);
				break;
			case "3단계": 
				filename="result3.txt";
				openFile(filename,level,id,time);
				break;
			default: break;
		}
	}
	
	public void openFile(String f, String level, String id, String time) throws IOException {
		File file=new File(f);
		boolean isExists=file.exists();
		if(isExists) {
			
			//텍스트 파일이 이미 존재하는 경우
			BufferedReader br= new BufferedReader(new FileReader(f));
			BufferedWriter bw=new BufferedWriter(new FileWriter(f,true));
			bw.append(level+" "+ id+" "+ time);
			bw.newLine();
			br.close();
			bw.close();
		}	
		else {
			//작성된 텍스트파일에 내용 추가
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			bw.write(level+" "+ id+" "+ time);
			bw.newLine();
			bw.close();

		}
			
	}
	
}
