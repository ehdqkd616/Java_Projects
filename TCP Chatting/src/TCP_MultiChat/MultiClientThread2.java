package TCP_MultiChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiClientThread2 extends Thread {

	Socket socket = null;
	BufferedReader stdin; // 표준입력객체(키보드)
	BufferedWriter bw = null; // 데이터 쓰기
	BufferedReader br = null; // 데이터 읽기
	// PrintWriter pw = null;

	// String strId = "";

	public MultiClientThread2(Socket tmpSocket) {
		socket = tmpSocket;

		try {
			//////////////////////////////////////////////////////////////////////////
			// 1. 입/출력 객채 생성
			//////////////////////////////////////////////////////////////////////////
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// pw = new PrintWriter(socket.getOutputStream());
			//////////////////////////////////////////////////////////////////////////
		} catch (IOException e) {
			System.out.println("MultiClientThread2 스트램 생성 실패");
		}
	}

	public void run() {
		try {
			String msg = "";

			while (!msg.equals("bye")) {
				////////////////////////////////////
				// 1. Client 의 입력을 받은 후 출력
				////////////////////////////////////
				msg = br.readLine();
				System.out.println(msg);
				////////////////////////////////////
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("MultiClientThread2 run() 예외 발생");
		} finally {
			try {
				br.close();
				bw.close();
				// pw.close();
				socket.close();
			} catch (Exception e) {
			}
		}
	}

	// 채팅
	public void chatProcess() {
		try {
			String msg = "";
			while (!msg.equals("bye")) {
				System.out.println("메세지를 입력하시오 : ");
				msg = stdin.readLine();

				// msg = msg + System.getProperty("line.separator");
				bw.write(msg + "\n");
				bw.flush();

				// pw.println(msg);
				// pw.flush();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("*** 메세지를 입력받아 전송중 예외 발생 ***");
		} finally {
			try {
				br.close();
				bw.close();
				// pw.close();
				socket.close();
				System.out.println("chatProcess() 종료");
			} catch (IOException e) {
			}
		}
	}

	// Client 로그인(ID 입력)
	public void login() {
		try {
			stdin = new BufferedReader(new InputStreamReader(System.in));
			String result;
			String strId;
			do {
				////////////////////////////////////////////
				// 1. Client ID 입력받기
				////////////////////////////////////////////
				System.out.print("id를 입력하시오 ==> ");
				strId = stdin.readLine();
				////////////////////////////////////////////

				///////////////////////////////
				// 2. Server 로 Client ID 저장
				///////////////////////////////
				// strId = strId + System.getProperty("line.separator");
				bw.write(strId + "\n");
				bw.flush();

				// pw.println(strId);
				// pw.flush();
				///////////////////////////////

				// Server 로 'ok' 읽어오기(미리 입력된 아이디가 있을 경우 사용함.. 지금 사용해도 무관)
				result = br.readLine();

			} while (!result.equals("ok"));

			System.out.println("########### " + strId + "님 로그인 성공" + " ###########");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("login() 중 예외 발생");
		}
	}

}
