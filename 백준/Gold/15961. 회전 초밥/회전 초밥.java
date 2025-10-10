import java.util.*;
import java.io.*;

public class Main {

	public static int N; // 접시의 수
	public static int D; // 초밥의 가짓수(1~D)
	public static int K; // 연속 접시
	public static int C; // 쿠폰번호

	public static Map<Integer, Integer> counts;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 벨트 채우기
		int[] belt = new int[N];
		for (int n = 0; n < N; n++) {
			belt[n] = Integer.parseInt(br.readLine());
		}

		counts = new HashMap<>();
		for (int i = 0; i < K; i++) {
			add(belt[i]);
		}
		add(C);

		int max = 0;
		for (int i = 0; i < N; i++) {
			if (max < counts.size()) {
				max = counts.size();
			}

			pop(belt[i]);
			add(belt[(i + K) % N]);
		}

		System.out.println(max);
	}

	public static void add(int v) {
		if (counts.containsKey(v)) {
			counts.replace(v, counts.get(v) + 1);

		} else {
			counts.put(v, 1);
		}
	}

	public static void pop(int v) {

		int count = counts.get(v);

		if (count > 1) {
			counts.replace(v, count - 1);
		} else {
			counts.remove(v);
		}
	}
}

// k개 연속 접시 -> 정액할인
// 쿠폰 -> 무료, 없으면 요리사가 만들어 제공
// 최대한 다양한 밥

// for문으로 돌리면서
// 체크
// 앞에 빠고, 뒤에 넣고 -> 넘어가면 끝

// 5개라면 0 1 2 3 4
// N으로 나눈다.