import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Map<String, Integer> myM = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			String tmp = br.readLine();

			myM.put(tmp, i);
		}

		int[] order = new int[N + 1]; //나가는 순서
		for (int i = 1; i <= N; i++) {
			String tmp = br.readLine();

			int out = myM.get(tmp);
			order[i] = out;
		}

		int loc = order[N];
		int result = 0;
		for (int i = N - 1; i >= 1; i--) {
			if (order[i] < loc) {
				loc = order[i];
			} else {
				result++;
			}
		}

		System.out.println(result);
	}
}



// 실선?
// 터널 내부는 추월 불가
// 들어가는 순서, 나오는 순서 적기

// N개 차량 지나간 후, 추월 차량

// N
// N 줄 대근이 차량 번호 목록
// N 줄 영식이 차량 번호 목록
