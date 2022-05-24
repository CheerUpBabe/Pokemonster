package poketmon;

import java.util.stream.Collectors;

public class BattleMewtwo {
	int MewtwoCP=1000;


	public int getTotalCp() { // vsList에 담긴 포켓몬의 cp를 더한 총 합
		return (int) GameConst.vsList.stream().collect(Collectors.summarizingInt(v -> v.cp)).getSum();
	}
	
	public void battleIf() {
		if(getTotalCp() > MewtwoCP) { //뮤츠 CP설정, 이겼을 경우
			System.out.println("                    <승리>");
		}else {
			System.out.println("                    <패배>");
		}
	}

}
