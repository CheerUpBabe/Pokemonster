package poketmon;

public class MenuViewer {


	static void showMainMenu() { //메인메뉴 출력
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println("     ◆◆ＰＯＫＥＴＭＯＮ◆ＷＯＲＬＤ◆◆");
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println("        ┌────────────┐");
		System.out.println("         ① 포켓몬  사냥");
		System.out.println("        └────────────┘");
		System.out.println("        ┌────────────┐");
		System.out.println("         ② 포켓몬  가방");
		System.out.println("        └────────────┘");
		System.out.println("        ┌────────────┐");
		System.out.println("         ③ 포켓몬  방출");
		System.out.println("        └────────────┘");
		System.out.println("        ┌────────────┐");
		System.out.println("         ④ 뮤츠와  대결");
		System.out.println("        └────────────┘");
		System.out.println("        ┌────────────┐");
		System.out.println("         ⑤ 게임 ★ 종료");
		System.out.println("        └────────────┘");
	
	
		System.out.printf(" ★★★★★★ 메뉴 선택 ★★★★★★: ");
    }

    static void showCatchMenu() { //지역메뉴 출력 - 지역별로 출몰하는 포켓몬의 타입이 다름
    	System.out.println();
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println("□□                              □□");
		System.out.println("□ ◆◆포켓몬 Σ출몰Σ 서식지에 입장하였습니다◆◆□");
		System.out.println("□□                              □□");
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println("        ┌─────────────┐");
		System.out.println("         ① 저~~푸른 초원");
		System.out.println("        └─────────────┘");
		System.out.println("        ┌─────────────┐");
		System.out.println("         ② 뜨거운 사막지대");
		System.out.println("        └─────────────┘");
		System.out.println("        ┌─────────────┐");
		System.out.println("         ③ 제주도 푸른바다");
		System.out.println("        └─────────────┘");
		System.out.println("        ┌─────────────┐");
		System.out.println("         ④  한국전력공사");
		System.out.println("        └─────────────┘");
		
		System.out.printf(" ★★★ 원하는 서식지를 선택해주세요 ★★★: ");
    }

    static void deleteMenu() { //삭제하기
        System.out.println("내보낼 포켓몬을 고르세요");
    }
    
    static void loginMenu() {
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
		System.out.println("3.종료");
	}
    
    static void vsMenu() {
    	System.out.println("         ==========================　");
    	System.out.println("           대결할 포켓몬을 고르세요..     ");
    	System.out.println("         ==========================　");
    }
}