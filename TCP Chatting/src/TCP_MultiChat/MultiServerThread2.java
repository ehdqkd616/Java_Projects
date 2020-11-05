package TCP_MultiChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiServerThread2 extends Thread {
	private Socket socket;
	// private MultiServer2 multiServer2;
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	// private PrintWriter pw = null;

	String strId = "";
	boolean isLogin;
	MultiChatRoom multiChatRoom;

	public MultiServerThread2(Socket tmpSocket, MultiChatRoom tmpMultiChatRoom) {
		socket = tmpSocket;
		multiChatRoom = tmpMultiChatRoom;

		//////////////////////////////////////////////////////////////////////////////
		// 1. 입/출력 Stream 생성
		//////////////////////////////////////////////////////////////////////////////
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// pw = new PrintWriter(socket.getOutputStream());

		} catch (IOException e) {
			System.out.println("MultiServerThread2 I/O Stream 생성 실패");
		}
		//////////////////////////////////////////////////////////////////////////////
	}

	public void run() {
		String message = "";

		//////////////////////////////////////////
		// 1. 로그인 처리(Client 아이디 받아오기)
		//////////////////////////////////////////
		login();
		//////////////////////////////////////////

		try {
			while (!message.equals("bye")) {
				//////////////////////////////////////////////////////////////////
				// 4. Client 가 보낸 메시지 받은 후 Server 에서 출력
				//////////////////////////////////////////////////////////////////
				message = br.readLine();

				// 받은 메시지출력
				System.out.println("받은 메시지 ==> " + strId + " : " + message);
				//////////////////////////////////////////////////////////////////

				////////////////////////////////////////////////////////////////
				// bye 입력 시 서버 나감
				////////////////////////////////////////////////////////////////
				if (message.equals("bye")) {
					multiChatRoom.broadCasting(strId + "님이 퇴장하셨습니다.");
				}
				////////////////////////////////////////////////////////////////

				/////////////////////////////////////////////////////
				// 5. 모든 Client 에게 메시지 전송
				/////////////////////////////////////////////////////
				multiChatRoom.broadCasting(strId + " : " + message);
				/////////////////////////////////////////////////////

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("메세지를 수신하여 송신중 예외 발생....");
		} finally {
			multiChatRoom.exitRoom(this);

			try {
				br.close();
				bw.close();
				// pw.close();
				socket.close();
			} catch (Exception e) {
			}

			System.out.println("MultiServerThread2 종료");
		}
	}

	// 로그인 (Client 에서 Server 로 ID 전송해서 받음)
	public void login() {
		// String members[] = {"이예진", "조수희", "조아란"}; //접속 가능한 아이디가 3개만 존재한다고 가정한다.
		// String tempId = null;

		try {
			System.out.println("Client ID 받아오는 중....");
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			///////////////////////////////////////////////
			// Client 가 입력한 ID 받아오기
			///////////////////////////////////////////////
			// tempId = br.readLine();
			strId = br.readLine();
			// System.out.println("Client ID : " + strId);

			///////////////////////////
			// 3. 접속자 수 보여주기
			///////////////////////////
			multiChatRoom.display();
			///////////////////////////

			sendMessage("ok");
			// System.out.println("서버 - 로그인 이름 확인");
			///////////////////////////////////////////////

		} catch (IOException e) {
			System.out.println("MultiServerThread2 login() 예외 발생");
		}
	}

	// 메세지 전송
	void sendMessage(String message) {
		try {
			///////////////////////////////////////////
			// 메시지 전송
			///////////////////////////////////////////
			// message = message + System.getProperty("line.separator");
			bw.write(message + "\n"); // \n : 줄바꿈
			bw.flush(); // 출력 버퍼를 비우는 메서드

			// pw.println(message);
			// pw.flush();
			///////////////////////////////////////////
		} catch (Exception e) {
			System.out.println("MultiServerThread2 sendMessage() 예외 발생");
		}
	}

}