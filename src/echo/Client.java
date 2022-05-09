package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		
		Socket socket = new Socket();
		System.out.println("<클라이언트 시작>");
		System.out.println("============================");
		
		
		
		System.out.println("[서버에 연결을 요청합니다.]");
		//접속할 ip와 port번호를 써준다.
		//conncet()메소드는 서버에 접속할 ip와 port번호를 써주어야한다.
		socket.connect(new InetSocketAddress("192.168.0.7", 10001));
		
		System.out.println("[서버에 연결되었습니다.]");
		
		//메세지 보내기용 Stream
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//메세지 받기용 Stream
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//스캐너
		Scanner sc = new Scanner(System.in);
		
		//반복
		while(true) {
			//키보드입력
			String str = sc.nextLine();
			
			if(str.equals("/q")) {
				break;
			}
			
			//메세지 보내기		
			bw.write(str);
			bw.newLine();
			//BufferedReader에 데이터가 다 안담겨있어도 그냥 보내라는의미
			// flush()안쓰면 데이터가 덜 차있어서 메세지 안보내짐
			bw.flush();
			
			//메세지 받기
			String reMsg = br.readLine();
			System.out.println("server :[" + reMsg + "]");
		}//반복 끝
		
		
		
		
		System.out.println("============================");
		System.out.println("<클라이언트 종료>");
		sc.close();
		br.close();
		bw.close();
		socket.close();
	}

}
