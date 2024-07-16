package PracticePhoneDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class PhonelistApp {

	public static void main(String[] args) throws IOException {
		// 리스트 생성
		List<Phonelist> pList = new ArrayList<Phonelist>();

		// 읽기 스트림
		InputStream in = new FileInputStream("C:\\javaStudy\\workspace\\PhoneDB.txt");
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 쓰기 스트림
		OutputStream out = new FileOutputStream("C:\\javaStudy\\workspace\\PhoneDB_copy.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		while (true) {
			//읽어 온 파일을 한 줄씩 str에 저장
			String str = br.readLine();
			// 저장할 데이터가 없으면 반복문 종료
			if (str == null) {
				break;
			}
			// 한줄씩 저장한 str의 데이터를 ","을 기준으로 나눠서 배열로 각각 name, hp, company 방에 넣는다.
			String[] pInfo = str.split(",");
			String name = pInfo[0];
			String hp = pInfo[1];
			String company = pInfo[2];

			System.out.println("이름: " + name);
			System.out.println("핸드폰: " + hp);
			System.out.println("회사: " + company);
			System.out.println("");
			
			//배열로 나눈 개인정보를 리스트에 저장한다.
			Phonelist person = new Phonelist(name, hp, company);

			pList.add(person);

		}
		//읽기 메소드 종료
		br.close();

		// 자신의 정보를 추가
		String newName = "함민규";
		String newHp = "010-2673-6784";
		String newCompany = "010-2977-6784";

		// 자신의 정보를 리스트에 추가 - *기존의 데이터가 지워지고 새 데이터가 쓰여짐(주의)
		Phonelist newPerson = new Phonelist(newName, newHp, newCompany);
		pList.add(newPerson);

		// 자신의 정보를 파일에 추가
		for (Phonelist person : pList) { // 향상된 for문 : 기존의 정보 리스트를 person 이라는 변수에 재할당 
			bw.write(person.getName() + "," + person.getHp() + "," + person.getCompany());
			bw.newLine();

			System.out.println(person.getName() + "," + person.getHp() + "," + person.getCompany());
		}

		bw.close();

	}

}
