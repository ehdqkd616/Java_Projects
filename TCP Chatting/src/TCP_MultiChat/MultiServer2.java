package TCP_MultiChat;

import java.io.IOException;

import java.net.ServerSocket;

import java.net.Socket;

import java.util.ArrayList;

/*

 * 1:N 채팅

 * N 명의 Client 끼리 계속적인 대화가 가능하도록 구현

 */

public class MultiServer2 {

	public static void main(String[] args) {

		Socket socket = null;

		ServerSocket serverSocket = null;

		MultiChatRoom multiChatRoom = null; // MultiCatRoom 객체

		MultiServerThread2 mst = null; // Client 연결 임시 객체

		int portNumber = 3000; // port 번호

		try {

			/////////////////////////////////////

			// 1. MultiChatRoom 객채 생성

			/////////////////////////////////////

			multiChatRoom = new MultiChatRoom();

			/////////////////////////////////////

			//////////////////////////////////////////////////////////

			// 2. portNumber 를 기반으로 하는 소켓 생성

			//////////////////////////////////////////////////////////

			serverSocket = new ServerSocket(portNumber);

			System.out.println("##################################");

			System.out.println("#        Server 준비 완료        #");

			System.out.println("##################################");

			//////////////////////////////////////////////////////////

			// Server 유지

			while (true) {

				////////////////////////////////////////////////////////////////////////////

				// 3. Client의 연결요청 대기, 연결되면 Client Socket 이 만들어짐

				////////////////////////////////////////////////////////////////////////////

				socket = serverSocket.accept();

				System.out.println(socket.getInetAddress() + " 연결");

				////////////////////////////////////////////////////////////////////////////

				/////////////////////////////////////////////////////////

				// 4. 접속된 Client 를 ArrayList에 저장

				/////////////////////////////////////////////////////////

				// 채팅 객체 생성

				mst = new MultiServerThread2(socket, multiChatRoom);

				// Thread 작동시켜 1)로그인 처리 2)채팅 시작

				mst.start();

				// 채팅 객체를 ArrayList에 저장

				multiChatRoom.enterRoom(mst);

				/////////////////////////////////////////////////////////

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
