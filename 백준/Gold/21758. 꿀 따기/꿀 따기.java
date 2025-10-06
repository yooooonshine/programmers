import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}

		int[] sum = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			sum[n] = sum[n - 1] + arr[n];
		}

		int[] revSum = new int[N + 2];
		for (int n = N; n >= 1; n--) {
			revSum[n] = revSum[n + 1] + arr[n];
		}

		int result = 0;

		// 벌통벌
		int tmpSum = sum[N] - arr[1] - arr[N];
		for (int n = 2; n <= N - 1; n++) {
			if (result < tmpSum + arr[n]) {
				result = tmpSum + arr[n];
			}
		}

		// 통벌벌
		tmpSum = sum[N] - arr[N];
		for (int n = 2; n <= N - 1; n++) {
			if (result < tmpSum + sum[n - 1] - arr[n]) {
				result = tmpSum + sum[n - 1] - arr[n];
			}
		}

		// 벌벌통
		tmpSum = sum[N] - arr[1];
		for (int n = 2; n <= N - 1; n++) {
			if (result < tmpSum + revSum[n + 1] - arr[n]) {
				result = tmpSum + revSum[n + 1] - arr[n];
			}
		}

		System.out.println(result);
	}
}

// 벌들이 딸 수 있는 최대 꿀의 양
// N개의 장소
// 벌통 1, 꿀장소 2(여기는 벌들이 꿀 채취 불가)

// N에서끝내야 함

// 이분탐색
// 투포인트
// 그리디

// 이분탐색, 그리디

// 꿀통은 반드시 양끝이 유리한가?
