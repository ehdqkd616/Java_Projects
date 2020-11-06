package TCP_MultiChat;

import java.util.ArrayList;

public class MultiChatRoom {

	ArrayList<MultiServerThread2> list = new ArrayList<MultiServerThread2>();

	// Server 에 보여주는 화면 : 접속자 수, 접속자 명
	public void display() {
		// 접속자 수
		System.out.println("현재 접속자 정보 : 접속자 수 -> " + list.size());

		// 현재 접속된 Client ID 보여줌, 만약 접속한 Client 가 없을 경우 보여주지 않음 - 실패...

		if (list.size() != 0) {
			System.out.println("******** 접속한 Client ID ********");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).strId);
			}
			System.out.println("**********************************");
		}

	}

	// Client 를 ArrayList 에 추가
	public void enterRoom(MultiServerThread2 tmpMultiServerThread2) {
		list.add(tmpMultiServerThread2);
	}

	// Client 에게 메시지 전송
	public void broadCasting(String message) {
		MultiServerThread2 multiServerThread2 = null;

		for (int i = 0; i < list.size(); i++) {
			multiServerThread2 = list.get(i);
			multiServerThread2.sendMessage(message);
		}
	}

	// Client 가 채팅에서 나갈 때
	public void exitRoom(MultiServerThread2 tmpMultiServerThread2) {
		boolean isDelete = list.remove(tmpMultiServerThread2);
		if (isDelete) {
			System.out.println(tmpMultiServerThread2.strId + " Client를 MultiServerThread2에서 제거");
		} else {
			System.out.println(tmpMultiServerThread2.strId + " Client를 MultiServerThread2에서 제거 실패");
		}
	}

}
