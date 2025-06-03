import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 인형 수
		int K = Integer.parseInt(st.nextToken()); // 라이언 K개 이상

		st = new StringTokenizer(br.readLine());
		int[] dolls = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dolls[i] = Integer.parseInt(st.nextToken());
		}

		int s = 0;
		int e = 0;

		int tempCount = 0;
		for (int i = 1; i <= N; i++) {
			if (dolls[i] == 1) {
				tempCount++;
				if (tempCount == 1) {
					s = i;
				}
				if (tempCount == K) {
					e = i;
					break;
				}
			}
		}

		int result = e - s + 1;

		// k개 미만이면 리턴
		if (tempCount < K) {
			System.out.println(-1);
			return;
		}

		// 이동
		while (s <= e && e <= N) {
			e++;
			while (e <= N && dolls[e] != 1) {
				e++;
			}

			s++;
			while (s <= e && dolls[s] != 1) {
				s++;
			}

			// 조건을 넘어가면 끝
			if (s > e || e > N) {
				break;
			}

			result = Math.min(result, e - s + 1);
		}

		System.out.println(result);
	}
}

// 라이언 1
// 어피치 2
// 라이언이 K개 이상 있는 가장 작은 인형 집합

// N(인형 수), K(개 이상)
//

// 투포인트
// s, e
// e++하면서 1이 k개 될때까지, 이 때 갱신
// s++ 다음 1만날때까지

// e가 안넘어가고, s <= e까지
// e를 K개 될떄까지 ++
// 갱신 체크
// 기존 값보다 작으면 갱신
// s를 다음 K까지 증가, 이 경우 k - 1