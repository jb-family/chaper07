package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{

	//필드
	private Socket socket;
	
	//생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	
	//메소드 - gs
	
	//메소드 - 일반
	
	public void run() {
		
	
		try {
			System.out.println("[클라이언트가 연결되었습니다.]");
			//메세지 받기용 Stream
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			
			//메세지 보내기용 Stream
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
		while(true) {
				
				//메세지 받기
				String msg = br.readLine();

				//주소비교 msg == null
				if(msg == null) {
					break;
				}
				System.out.println("받은메세지 :" + msg);
				//메세지 보내기
				//받은메세지를 다시 그대로 Client에 보낸다.
				bw.write(msg);
				bw.newLine();
				//BufferedReader에 데이터가 다 안담겨있어도 그냥 보내라는의미
				// flush()안쓰면 데이터가 덜 차있어서 메세지 안보내짐
				bw.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}
