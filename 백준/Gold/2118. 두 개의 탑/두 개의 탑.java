import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// arr[n]은 n과 n + 1사이 거리
		int[] arr = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}

		int[] partialSum = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			partialSum[n] = arr[n] + partialSum[n - 1];
		}
		// s ~ e는 partialSum[e - 1] - partialSum[s - 1], e포함x
		// 그리고 partialSum[N] - partialSum[e - 1] + partialSum[s - 1];

		int max = 0;

		int e = 2;
		for (int s = 1; s <= N; s++) {
			int sumSE = partialSum[e - 1] - partialSum[s - 1];
			int sumES = partialSum[N] - partialSum[e - 1] + partialSum[s - 1];

			if (sumSE < sumES) {
				while (sumSE < sumES) {
					if (e == N) {
						break;
					}

					e++;
					sumSE = partialSum[e - 1] - partialSum[s - 1];
					sumES = partialSum[N] - partialSum[e - 1] + partialSum[s - 1];
				}

				if (max < Math.min(sumSE, sumES)) {
					max = Math.min(sumSE, sumES);
				}
				sumSE = partialSum[e - 2] - partialSum[s - 1];
				sumES = partialSum[N] - partialSum[e - 2] + partialSum[s - 1];
				if (max < Math.min(sumSE, sumES)) {
					max = Math.min(sumSE, sumES);
				}

			} else {
				while (sumSE > sumES) {
					if (e == N) {
						break;
					}

					e--;
					sumSE = partialSum[e - 1] - partialSum[s - 1];
					sumES = partialSum[N] - partialSum[e - 1] + partialSum[s - 1];
				}

				if (max < Math.min(sumSE, sumES)) {
					max = Math.min(sumSE, sumES);
				}
				sumSE = partialSum[e] - partialSum[s - 1];
				sumES = partialSum[N] - partialSum[e] + partialSum[s - 1];
				if (max < Math.min(sumSE, sumES)) {
					max = Math.min(sumSE, sumES);
				}
			}


		}

		System.out.println(max);
	}
}

// 1번부터 N번 지점
// 원형 연결

// 두 곳에 두 개의 탑
// 두 탑 거리가 최대
// 두 거리 중 작은 값을 거리

// 두 탑 거리 최댓값

// 이분탐색
// 투표인터

// 부분합 배열
// 투포인트
// 그리디.
// 완전탐색 -> 시간 초과

// 안되네..
// n^2은 불가하다
// n, nlogn

// s를 1부터 n까지 돌려,
// 거기서 e의 중간 위치 찾기

// 크기가 변경되는 e를 찾는게 핵심