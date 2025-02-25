import java.util.*;
import java.io.*;

public class Main {
	public static int[] A;
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 숫자 크기
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		if (N == 1 || N == 2) {
			System.out.println(N);
			return;
		}

		Arrays.sort(A);

		int len = 2;
		for (int i = 0; i < N; i++) {
			for (int j = i + 2; j < N; j++) {
				if (A[i] + A[i + 1] > A[j]) {
					if (len < j - i + 1) {
						len = j - i + 1;
					}
				}
			}
		}

		System.out.println(len);
	}

}
// 삼각 수열의 최대 길이
// 크기 N
// 최대 50?
// 이거 모든 케이스 다 세도 되겠다.

// 50C3 = 25000 정도

// 50C1 + 50C2 + 50C3 /.... =

// 배열이 부분 삼각인지 체크하는 함수

// 모든 배열의 경우의 수 체크?

// n에 해당하는 거 만들기
// 그럼 재귀로 n개 선택
