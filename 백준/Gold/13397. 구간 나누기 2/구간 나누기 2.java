import java.util.*;
import java.io.*;

public class Main {

	public static int[] arr;
	public static int N;
	public static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수
		M = Integer.parseInt(st.nextToken()); // 구간 최대 개수

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}

		int s = 0;
		int e = 10000;

		int low = 10001;
		int m;
		while (s <= e) {
			m = (s + e) / 2;

			if (can(m)) {
				if (low > m) {
					low = m;
				}
				e = m - 1;
			} else {
				s = m + 1;
			}
		}

		System.out.println(low);
	}

	public static boolean can(int v) {
		int cutCount = 0;

		int low = arr[1];
		int high = arr[1];

		for (int n = 1; n <= N; n++) {
			if (arr[n] < low) {
				low = arr[n];
			}
			if (arr[n] > high) {
				high = arr[n];
			}

			if (high - low > v) {
				cutCount++;
				low = arr[n];
				high = arr[n];
			}
		}

		if (cutCount + 1 <= M) {
			return true;
		}
		return false;
	}
	// 시작부터 최대 최소의 차가 v보다 크면 쪼개기
	// 그 값을 low, high로 두기
}

// N개의 수 1차원 배열
// M개 이하의 구간으로 나누기, 구간 점수 최댓값을 최소로

// 한구간은 두개의 수 이상,
// 모든 수는 한 구간에 포함되어야 함.
// 구간의 최댓값과 최소값의 차이 -> 대표값
// 대표값이 최소로 되게

// 그리디 -> 그리디 하지 않아
// dp 하지 않아.


// 이분탐색
// 최댓 값으로 이분탐색? O(log(10000) 이닌듯
// 나누는 경우의 수 nCm - 너무 많아...
// 특정 값을 넘으려하면 쪼갠다.
// 이전과의 차가 넘는다면 안된다.
// 이전만 아니면 되나
