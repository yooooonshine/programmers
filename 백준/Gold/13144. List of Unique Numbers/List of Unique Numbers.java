import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long count = 0L;
		boolean[] exist = new boolean[100001];

		int s = 1, e = 1;

		while (s <= e) {
			if (e > N) {
				count += (long)(e - s) * (e - s + 1) / 2;
				break;
			}

			if (!exist[arr[e]]) {
				exist[arr[e]] = true;
				e++;
			} else {
				count += e - s;
				exist[arr[s]] = false;
				s++;
			}
		}

		System.out.println(count);
	}
}

// 길이 N인 수열
// 연속한 1개 이상의 수
// 같은수 없는 경우의 수
// 완점탐색?
// 백트래킹?
// 1로 시작하는 거

// s = 1 부터 시작
// e = s 부터 시작
// 곂치지 않으면 e++.
// 곂치거나, e가 범위 넘어가면 s++, e = s

// 투포인터로?
// s, e = 1시작
// e 늘리기
// 중복되면 e - 1까지에서 경우의 수 구하기
// s를 arr[e] 만날때까지 이동 + 1

// e부분 중복되지 않는다면 e++, exist에 추가
// e부분 중복되면 s를 arr[e] 만날때까지 ++, exist arr[s] 제거


// n개의 수가 중복되지 않는다면 n * (n + 1) / 2