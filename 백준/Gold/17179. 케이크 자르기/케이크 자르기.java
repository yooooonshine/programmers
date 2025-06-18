import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;
	public static int L;
	public static int[] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 자르는 횟수
		M = Integer.parseInt(st.nextToken()); // 자를 수 있는 지점
		L = Integer.parseInt(st.nextToken()); // 롤 케이크 길이

		S = new int[M + 2];
		for (int m = 1; m <= M; m++) {
			S[m] = Integer.parseInt(br.readLine());
		}
		S[M + 1] = L;

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int l = 1; l <= N; l++) {
			int cut = Integer.parseInt(br.readLine());
			bw.write(calc(cut) + "\n");
		}
		bw.flush();
	}

	public static int calc(int cut) {
		int s = 0;
		int e = L;

		int result = 0;

		int m;
		while (s <= e) { // 맞나
			m = (s + e) / 2;

			int before = 0;
			int count = 0;
			for (int i = 1; i <= M; i++) {
				if (S[i] - before >= m) {
					before = S[i];
					count++;
				}

				if (cut == count) {
					break;
				}
			}


			if (count == cut && L - before >= m) {
				result = Math.max(result, m);
				s = m + 1; // 더 큰 길이로
			} else {
				e = m - 1; // 더 작은 길이로
			}
		}
		return result;
	}
}

// 가장 작은 길이의 최댓값

// 완탐 x
// dp, 그리디, 투포인트, 이분탐색

// 가장 작은  k -> 이분탐색 기준
// 롤케이크 앞에서부터 cut 번 자르기
//
