import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 물건의 수
		int C = Integer.parseInt(st.nextToken()); // 무게

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 1];

		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		for (int n = 1; n <= N; n++) {
			if (arr[n] == C) {
				System.out.println("1");
				return;
			}

			int s = n + 1;
			int e = N;

			while (arr[n] + arr[e] >= C && e > n) {
				if (arr[n] + arr[e] == C) {
					System.out.println("1");
					return;
				}
				e--;
			}

			while (s < e) {
				if (arr[n] + arr[s] + arr[e] == C) {
					System.out.println("1");
					return;
				} else if (arr[n] + arr[s] + arr[e] < C) {
					s++;
				} else {
					e--;
				}
			}
		}

		System.out.println("0");
	}
}

// 투포인트
// 5000 x 5000 -> 된다.
// 대신 항상 왼쪽 끝은 0으로

// 무게 C에 딱 맞게 물건을 가져오면 만원
// 최대 3개 선택, 중복 불가, 무게 모두 다름

// N 물건의 수
// 1개 -. 5000
// 2개 -> 2500000
// 3개 -> 10^9보다 큼
// 브루트 x
// 그리디
// 이분탐색


// C 무게 => 크다
//