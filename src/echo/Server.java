package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args)throws IOException {

		
		ServerSocket serverSocket = new ServerSocket();
		//ip는 문자열 port는 내가 임의의 숫자입력해주면 됨.
		//serverSocket.bind(new InetSocketAddress(ip, port)); 문법 외워야함
		//입력하면 bind가 내 ip와 설정한 port번호에 누가 접근하는지 감시하고있음.
		serverSocket.bind(new InetSocketAddress("192.168.0.7", 10001));
		
		//accept()메소드는 접근할때 허락해주는 메소드. socket과 socket을 연결해줌.
		System.out.println("<서버시작>");
		System.out.println("==========================================");
		System.out.println("[연결을 기다리고 있습니다.]");

		/////////////////반복시작/////////////////
		
		//Client의 socket이 serverSocket과 연결됐다는 의미.
		
		while(true) {//서버는 종료시키면안됨. 계속 돌아가야함.
			
			Socket socket = serverSocket.accept();
			
			
			//Thread 사용 (출장보내기 Stream 보강하고 대화해라)
			Thread thread = new ServerThread(socket);
			thread.start();
		}
		
	
		
		//////////////////반복 종료/////////////////
		
		
		/*
		
		System.out.println("==========================================");
		System.out.println("<서버종료>");
		bw.close();
		br.close();
		socket.close();
		serverSocket.close();
		
		*/
	}

}
