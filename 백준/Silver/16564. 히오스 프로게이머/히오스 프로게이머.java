import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 캐릭터 수
		int K = Integer.parseInt(st.nextToken()); // 올릴 수 있는 레벨 K

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int n = 1; n <= N; n++) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		while (true) {
			int min = pq.poll();

			int minCount = 1;
			while (!pq.isEmpty() && pq.peek() == min) {
				pq.poll();
				minCount++;
			}

			// 비어있을 경우, 다 같으니 끝냄.
			if (pq.isEmpty()) {
				int tmp = K / minCount;

				System.out.println(min + tmp);
				return;
			}

			// 다음이랑 간격
			int interval = pq.peek() - min;

			int canUp = K / minCount;
			// 다 써도 다음 간격 못채우는 경우
			if (interval >= canUp) {
				System.out.println(canUp + min);
				return;
			} else {
				// 채우는 경우
				K -= interval * minCount;
				for (int i = 1; i <= minCount; i++) {
					pq.add(min + interval);
				}
			}
		}
	}
}

// N개의 캐릭터
// 각 캐릭터 레벨 Xi
// 레벨을 총합 K만큼

// 팀 목표레벨 T = min(Xi)의 최대 값
//