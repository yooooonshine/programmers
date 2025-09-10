import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		for (int n = 1; n <= N; n++) {
			String tmp = br.readLine();

			if (bfs(tmp)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	public static boolean bfs(String result) {
		Queue<Now> myQ = new LinkedList<>();

		myQ.add(new Now("", 0));

		while (!myQ.isEmpty()) {
			Now now  = myQ.poll();
			String nowS = now.s;

			// 글자수 초과
			if (nowS.length() > result.length()) {
				continue;
			}

			// 앞이 일치하지 않으면 끝
			if (!Objects.equals(result.substring(0, nowS.length()), nowS)) {
				continue;
			}

			if (now.state == 0 && Objects.equals(nowS, result)) {
				return true;
			}

			// 이걸 bfs로 처리할까?
			// 0 새로 시작
			// 1 현재 100 에 0채우는중
			// 2. 현재 100에 1채우는 중

			if (now.state == 0) {
				myQ.add(new Now(nowS + "100", 1));
				myQ.add(new Now(nowS + "01", 0));
			} else if (now.state == 1) {
				myQ.add(new Now(nowS + "0", 1));
				myQ.add(new Now(nowS + "1", 2));
			} else {
				myQ.add(new Now(nowS + "1", 2));
				myQ.add(new Now(nowS, 0));
			}
		}

		return false;
	}

	public static class Now {
		String s;
		int state;

		public Now(String s, int state) {
			this.s = s;
			this.state = state;
		}
	}
}


// (100+1+ | 01)+


// x+ 최소 1번 이상 반복
// (xyz)+ 최소 한번 이상 xyz 반복

// 분할 탐색?
// 1. 시작이 0이야 1이야?
// 2. 0이면 01인지 체크 -> 안되면 NO
// 3. 되면 다음 다시 01 체크
// 4. 1이면 100되는지 체크 x -> No
// 5. 그다음 0반복,
// 6. 그다음1있는지 체크
// 7, 그 다음이 1이면 1의 연장 or 새로운 100 시작


// 재귀로
// 200글자 넘어가면 끝
// 1. 100에 0과 1 늘리는 경우
// 2. 01 추가

// 이걸 bfs로 처리할까?
// 0 새로 시작
// 1 현재 100 에 0채우는중
// 2. 현재 100에 1채우는 중