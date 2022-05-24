package poketmon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginManager {
	Map<String, String> userInfostorage = new HashMap<String, String>(); //id,password 담을 맵.
	File loginSaveFile = new File("C:\\Temp\\LoginSaveFile.txt"); //save할 파일 선언
	private static LoginManager lm = new LoginManager(); // 로그인매니저를 싱글톤으로 만들려고함.
	private LoginManager() {}//싱글톤위해서 선언.
	
	public static String id;   //GameManager에서 포켓몬 가방을 불러올때 id를 써야하므로 광역으로 선언.
	public String password; // password 닮을 변수.
	
	public static LoginManager getLoginManager() {
		return lm;//싱글톤
	}
	
	boolean loginStep() {
		boolean loginResult = false;
		int choice=0;
		lm.readUserFromFile();
		while (loginResult == false) {
			MenuViewer.loginMenu();
			choice = Integer.parseInt(GameConst.sc.nextLine());
			switch (choice) {
			case 1:
				lm.newLogIn();
				break;
			case 2:
				loginResult = lm.login();
				choice = 0;
				break;
			case 3:
				System.out.println("종료합니다");
				lm.saveUserToFile();
				System.exit(0);
			}
		}
		return loginResult;
	}
	
	public boolean login() {
		int count = 0;
		while (true) {
			System.out.println("id를 입력하세요.");
			id = GameConst.sc.nextLine();//id 변수에 입력 아이디를 담음.
			System.out.println("password를 입력하세요.");
			password = GameConst.sc.nextLine();//password 변수에 입력 패스워드 담음.
			if (!userInfostorage.containsKey(id) || !userInfostorage.containsValue(password)) {
				System.out.println("아이디가 없거나 비밀번호를 잘못입력하셨습니다.");
				return false;//userInfostorage에 아이디나 비번이 없으면 false를 반환함.
			}
			System.out.println("로그인되었습니다.");
			return true;//userInfostorage에 아이디와 비번이 일치하면 true 반환.
		}
	}
	
	public void newLogIn() {    //회원가입절차.
		while (true) {
			System.out.println("가입할 id를 입력하세요.");
			id = GameConst.sc.nextLine();
			if (userInfostorage.containsKey(id)) {
				System.out.println("이미 가입된 id입니다.");
				continue;
			}
			System.out.println("password를 입력하세요.");
			password = GameConst.sc.nextLine();
			userInfostorage.put(id, password);
			System.out.println("가입이 완료되었습니다.");
			break;
		}
	}
	
	void saveUserToFile() {       //아이디와 비번을 파일로 저장
		FileOutputStream fos = null;    
		ObjectOutputStream oos = null;  
		if (!loginSaveFile.exists()) { //로그인세이브파일이 없을경우에 실행.
			try {
				loginSaveFile.createNewFile();   //로그인세이브파일을 만듦.
			} catch (Exception e) {}
		}
		try {
			fos = new FileOutputStream(loginSaveFile);   //로그인세이브파일에 저장하려고 스트림만듦.
			oos = new ObjectOutputStream(fos);          
			oos.writeObject(userInfostorage);  //파일에 userInfostorage(회원아이디,비밀번호) 를 저장.
		} catch (IOException e) {
		} finally {
			try {
				if (oos != null) {
					oos.flush();          //아웃풋스트림에 있는것을 다 내보냄.
					oos.close();          //오브젝트아웃풋스트림 닫기
				}
				if (fos != null) {
					fos.close();           //파일아웃풋스트림 닫기
				}
			} catch (Exception e) {
			}
		}
	}
	
	void readUserFromFile() {      //유저들의 아이디와 비번 정보를 파일로부터 가져오기.
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		if (!loginSaveFile.exists()) { //로그인세이브파일이 없으면 종료.
			return;
		}
		try {
			fis = new FileInputStream(loginSaveFile); //로그인세이브파일로부터 불러오려고 인풋스트림만듦.
			ois = new ObjectInputStream(fis);
			HashMap<String, String> tmp = (HashMap<String, String>) ois.readObject(); //불러온 오브젝트 리턴값을 형변환해서 맵으로 받음
			userInfostorage = tmp; // userInfostorage 에 읽어온 값을 넣음.
		} catch (Exception e) {
		} finally {
			try {
				if (ois != null)
					ois.close();    //인풋스트림 닫기.
				if (fis != null)
					fis.close();     //인풋스트림 닫기.
			} catch (Exception e) {
			}
		}
	}
}
