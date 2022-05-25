package poketmon;

import java.util.Arrays;

public class PlayGameMain {

	public static void main(String[] args) {
		GameManager gm = new GameManager(); // +
		LoginManager lm = LoginManager.getLoginManager();
		BattleMewtwo mewtwo = new BattleMewtwo(); // +

		int choice = 0;
		boolean loginResult = false;

		loginResult = lm.loginStep();
		lm.readPoketmonsFromFile();

		while (loginResult) {
			switch (choice) {
			case 0:
				MenuViewer.showMainMenu();
				try {
					String num = GameConst.sc.nextLine().trim();
					if (num.length() == 1) {
						choice = Integer.parseInt(num);
					} else {
						System.out.println("              잘못입력하셨습니다..");
					}
					continue;

				} catch (NumberFormatException e) {
					System.out.println("              숫자만 입력하세요..");
				}

				break;
			case 1:
				MenuViewer.showCatchMenu();
				try {
					if (gm.getPoketmon(Integer.parseInt(GameConst.sc.nextLine()))) {
						choice = 3;
					} else {
						System.out.println("            메인메뉴로 돌아갑니다..");
						choice = 0;
					}
				} catch (NumberFormatException e) {
					System.out.println("              숫자만 입력하세요...");
				} catch (NullPointerException e) {
					System.out.println("              숫자만 입력하세요.....");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("            없는 선택지입니다...");
				}
				break;
			case 2:
				MenuViewer.showPoketBook();
				GameConst.sc.nextLine();
				choice = 0;
				break;
			case 3:
				MenuViewer.showPoketBag();
				gm.showBagInPokekmon();
				MenuViewer.showPoketBag2();
				choice = 0; // 초기메뉴로 이동
				break;
			case 4:
				if (GameConst.poketmonBag.size() == 0) {
					System.out.println("              빈 가방입니다..");
					choice = 0;
					break;
				}
				MenuViewer.deleteMenu();
				try {
					gm.showBagInPokekmon();
					MenuViewer.deleteMenu2();
					gm.deletePoketmon(Integer.parseInt(GameConst.sc.nextLine()));

					System.out.println();
				} catch (NumberFormatException e) {
					System.out.println("             숫자만 입력하세요..");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("             없는 선택지입니다..");
				}
				choice = 0;
				break;
			case 5:
				if (GameConst.poketmonBag.size() < 3) {
					System.out.println("            입장하실 수 없습니다..    　");
					System.out.println();
					choice = 0;
					break;
				}
				System.out.println("	  입장하시겠습니까? [Y/N]");
				System.out.print("	입력 → ");
				String select = GameConst.sc.nextLine().trim();
				int[] selectPoketmons = new int[3];
				if ("Y".equalsIgnoreCase(select)) {

					try {
						for (int i = 0; i < 3; i++) {
							MenuViewer.vsMenu();
							gm.showBagInPokekmon(); // 내용 출력
							MenuViewer.vsMenu2();
							int poketmonChoice = Integer.parseInt(GameConst.sc.nextLine());
							if (poketmonChoice <= 0 || poketmonChoice > GameConst.poketmonBag.size()) {
								System.out.println("              잘못입력하셨습니다..");
								i--;
								continue;
							}
							if(gm.selectVsMewtwo(poketmonChoice)) {
								selectPoketmons[i]=poketmonChoice-1;
								System.out.println();
								System.out.println("	  선택한 포켓몬은..");
								for (int j = 0; j < GameConst.vsList.size(); j++) { // 고른 포켓몬 출력하기
									
									Poketmon p = GameConst.vsList.get(j);
									System.out.println(
											"          [" + (j + 1) + "]" + " [" + p.name + "] [ CP " + p.cp + "]");
								}
							}else {i--;}
						}

						System.out.println();
						System.out.println("           [TOTAL CP] " + mewtwo.getTotalCp()); // CP합계 출력
						System.out.println("           [뮤츠의　CP] " + mewtwo.MewtwoCP);
						mewtwo.battleIf();
						GameConst.vsList.clear();
						Arrays.sort(selectPoketmons);
						GameConst.poketmonBag.remove(selectPoketmons[0]);
						GameConst.poketmonBag.remove(selectPoketmons[1]-1);
						GameConst.poketmonBag.remove(selectPoketmons[2]-2);
					} catch (NumberFormatException e) {
						System.out.println("              숫자만 입력하세요..");
						GameConst.vsList.clear(); //추가함
						selectPoketmons[0]=0;
						selectPoketmons[1]=0;
						selectPoketmons[2]=0;
					}
				} else {
					choice = 0;
					break;
				}

				System.out.println("            메인메뉴로 돌아갑니다.."); // false일 경우
				choice = 0;
				break;
			case 6:
				System.out.println("            게임을 종료합니다..");
				lm.savePoketmonsToFile(); // +

				return; // 프로그램 종료
			}
		}
	}
}
