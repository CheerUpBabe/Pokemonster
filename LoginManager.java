
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginManager {
	Map<String, String> userInfostorage = new HashMap<>(); // id,password 담을 맵.
	File loginSaveFile = new File("C:\\Temp\\LoginSaveFile.txt"); // save할 파일 선언
	private static LoginManager lm = new LoginManager(); // 로그인매니저를 싱글톤으로 만들려고함.
	Map<String, List<Poketmon>> userPoketmons = new HashMap<>();
	File poketmonSaveFile = new File("C:\\Temp\\PoketmonSaveFile.txt");
	
	private LoginManager() {
	}// 싱글톤위해서 선언.
	
	public static String id; // GameManager에서 포켓몬 가방을 불러올때 id를 써야하므로 광역으로 선언.
	public String password; // password 닮을 변수.
	
	public static LoginManager getLoginManager() {
		return lm;// 싱글톤
	}
	
	boolean loginStep() {
		boolean loginResult = false;
		int choice;
		lm.readUserFromFile();
		while (!loginResult) {
			MenuViewer.loginMenu();
			choice = Integer.parseInt(GameConst.sc.nextLine());
			switch (choice) {
			case 1:
				lm.newLogIn();
				break;
			case 2:
				loginResult = lm.login();
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
		String choice;
		while (true) {
			System.out.println("로그인을 진행하시겠습니까? y,n");
			choice = GameConst.sc.nextLine().trim();
			
			if(choice.equalsIgnoreCase("y")) {
				System.out.println("id를 입력하세요.");
				id = GameConst.sc.nextLine();// id 변수에 입력 아이디를 담음.
				System.out.println("password를 입력하세요.");
				password = GameConst.sc.nextLine();// password 변수에 입력 패스워드 담음.
				if (!userInfostorage.containsKey(id) || !userInfostorage.containsValue(password)) {
					System.out.println("아이디가 없거나 비밀번호를 잘못입력하셨습니다.");
					return false;// userInfostorage에 아이디나 비번이 없으면 false를 반환함.
				}
				System.out.println("로그인되었습니다.");
				return true;// userInfostorage에 아이디와 비번이 일치하면 true 반환.
				}else if(choice.equalsIgnoreCase("n")) {
					return false;
				}else {
					System.out.println("잘못입력하셨습니다.");
					return false;
				}
			}
	}

	public void newLogIn() { // 회원가입절차.
		String choice;
		
		while (true) {
			System.out.println("회원가입을 진행하시겠습니까? y,n");
			choice = GameConst.sc.nextLine().trim();
			
			if(choice.equalsIgnoreCase("y")) {
				System.out.println("가입할 id를 입력하세요.");
				id = GameConst.sc.nextLine();
				if (userInfostorage.containsKey(id)) {
					System.out.println("이미 가입된 id입니다.");
					continue;
				}else if(id.length()==0) {
					while(id.length()==0) {
						System.out.println("id를 입력하세요.");
						id = GameConst.sc.nextLine();
						continue;
					}
				}
				System.out.println("password를 입력하세요.");
				password = GameConst.sc.nextLine();
				if(password.length()==0) {
					while(password.length()==0) {
						System.out.println("password를 입력하세요.");
						password = GameConst.sc.nextLine();
						continue;
					}
				}
				userInfostorage.put(id, password);
				System.out.println("가입이 완료되었습니다.");
				break;
			}else if(choice.equalsIgnoreCase("n")) {
				return;
			}else {
				System.out.println("잘못입력하셨습니다.");
				return;
			}
		}
	}
	
	void saveUserToFile() { // 아이디와 비번을 파일로 저장
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		if (!loginSaveFile.exists()) { // 로그인세이브파일이 없을경우에 실행.
			try {
				loginSaveFile.createNewFile(); // 로그인세이브파일을 만듦.
			} catch (Exception e) {
			}
		}
		try {
			fos = new FileOutputStream(loginSaveFile); // 로그인세이브파일에 저장하려고 스트림만듦.
			oos = new ObjectOutputStream(fos);
			oos.writeObject(userInfostorage); // 파일에 userInfostorage(회원아이디,비밀번호) 를 저장.
		} catch (IOException e) {
		} finally {
			try {
				if (oos != null) {
					oos.flush(); // 아웃풋스트림에 있는것을 다 내보냄.
					oos.close(); // 오브젝트아웃풋스트림 닫기
				}
				if (fos != null) {
					fos.close(); // 파일아웃풋스트림 닫기
				}
			} catch (Exception e) {
			}
		}
	}

	void readUserFromFile() { // 유저들의 아이디와 비번 정보를 파일로부터 가져오기.
		if (!loginSaveFile.exists()) { // 로그인세이브파일이 없으면 종료.
			return;
		}
		try (FileInputStream fis = new FileInputStream(loginSaveFile);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			// 로그인세이브파일로부터 불러오려고 인풋스트림만듦.
			userInfostorage = (HashMap<String, String>) ois.readObject(); // userInfostorage 에 읽어온 값을 넣음.
		} catch (Exception e) {
		}
		// 인풋스트림 닫기.
		// 인풋스트림 닫기.
	}

	void savePoketmonsToFile() {
		userPoketmons.put(LoginManager.id, GameConst.poketmonBag);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		if (!poketmonSaveFile.exists()) {
			try {
				poketmonSaveFile.createNewFile();
			} catch (Exception e) {
			}
		}
		try {
			fos = new FileOutputStream(poketmonSaveFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(userPoketmons);
		} catch (IOException e) {
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
			}
		}
	}

	void readPoketmonsFromFile() {
		if (!poketmonSaveFile.exists()) {
			return;
		}
		try (FileInputStream fis = new FileInputStream(poketmonSaveFile);
				ObjectInputStream ois = new ObjectInputStream(fis)) {

			Map<String, List<Poketmon>> tmp = (Map<String, List<Poketmon>>) ois.readObject();
			userPoketmons = tmp;
			if (userPoketmons.get(LoginManager.id).size() != 0) {
				GameConst.poketmonBag = userPoketmons.get(LoginManager.id);
			}
		} catch (Exception e) {
		}
	}
}
