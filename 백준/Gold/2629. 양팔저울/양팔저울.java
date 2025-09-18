import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 추의 개수

		boolean[] dpA = new boolean[40001];
		boolean[] dpB = new boolean[40001];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			int weight = Integer.parseInt(st.nextToken());

			dpB[weight] = true;

			for (int a = 1; a <= 15000; a++) {
				if (dpA[a]) {
					dpB[a + weight] = true;
					dpB[Math.abs(a - weight)] = true;
				}
			}

			dpA = Arrays.copyOf(dpB, dpB.length);
		}

		int beadCount = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= beadCount; i++) {
			if (dpA[Integer.parseInt(st.nextToken())]) {
				bw.write("Y ");
			} else {
				bw.write("N ");
			}

		}

		bw.flush();
	}
}

// 매번 15000개 업데이트

// 구슬이 x라고 하자.
// 추가 y야

// 1번 x = y 부분 합
// 2번 x = y 부분 합 - y 부분 합

// 모든 구슬을 (넣는다, 뺀다, 사용x)

// 추의 개수 30개
// 추는 500개 이하
// 확인 구슬은 7개 이하
// 구슬은 40000이하.

// 모든 가능성 3^30 -> 절대안된다.
// 완전탐색
// DP

// 새로운 것을 하나 추가했을 때? 기존에 x를 더한 것
// 기존에 x를 뺀것
