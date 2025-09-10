
import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}

		int s = 1;
		int e = N;

		int min = 200000005;
		while (s < e) {
			if ((int)Math.abs(A[s] + A[e]) < Math.abs(min)) {
				min = A[s] + A[e];
			}

			if (A[s] + A[e] > 0) {
				e--;
			} else if (A[s] + A[e] < 0) {
				s++;
			} else {
				min = 0;
				break;
			}
		}

		System.out.println(min);
	}
}


// ㅑint 사용
// 투포인트
// 시작 끝
// 둘의 합 -> 음수면 s++
// 둘의 합 -> 양수면 e--
// 둘의 합의 절댓값의 최소