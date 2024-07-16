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
		System.out.println("========================================");

		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("192.168.0.39", 10001)); // 학원 컴퓨터 IP임.

		System.out.println("[서버에 연결 되었습니다.]");

		// 쓰기 스트림 - 메세지 보내기
		OutputStream out = socket.getOutputStream(); // socket 주스트림을 만들어서 달라고하면 됨
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		// 읽기 스트림 - 메세지 받기
		InputStream in = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 스캐너 준비
		Scanner sc = new Scanner(System.in);

		while (true) {
			// 키보드 사용
			System.out.print("입력: ");
			String str = sc.nextLine();

			if ("/q".equals(str)) {
				break;
			}

			// 메세지 보내기
			bw.write(str);
			bw.newLine();
			bw.flush();// bufferedWriter에 자료가 꽉 차지 않아도 전송해달라.

			// 메세지 받기
			String reMsg = br.readLine();
			System.out.println(reMsg);
		}
		System.out.println("========================================");
		System.out.println("클라이언트 종료");

		// 닫기
		br.close();
		bw.close();
		socket.close();
		sc.close();
	}

}
