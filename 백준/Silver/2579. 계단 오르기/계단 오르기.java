import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] score = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}

		int[][] max = new int[n + 1][2];
		if (n == 1) {
			max[1][0] = score[1];
		} else if (n == 2) {
			max[1][0] = score[1];
			max[2][0] = score[1] + score[2];
		} else {
			int[] before = new int[n + 1];

			max[1][0] = score[1];
			max[1][1] = score[1];

			max[2][0] = score[1] + score[2];
			max[2][1] = score[2];

			for (int i = 3; i <= n; i++) {
				max[i][0] = max[i - 1][1] + score[i];
				max[i][1] = Math.max(max[i-2][0], max[i - 2][1]) + score[i];
			}
		}

		System.out.println(Math.max(max[n][0], max[n][1]));
	}
}

// 이전 밟음 max[i][0] = max[i - 1][0]\
// 이이전 밟음 max[i][1] = max[i - 2][0] or max[i - 2][1]

// 계단 한계단 or 두계단
// 연속 세 개의 계단은 x
// 마지막 계단 밟기

// 점수 최댓값?  이분 탐색 x
// 그리디 or dp
// 규칙이 보인다?

// dp배열로 하면 max[n]을 구하면 된다.

// 연속 3계단

// 이전 계단을 저장해둔다.
// max[i] = max[i - 1] + score[i] or max[i - 2] + score[i]
// 여기서 max[i - 1]의 경우 before[i - 1] != i - 2

// dp니 시작 조심

// 최대는 이전(이이전을 밞음), 이전(밟지 않음), 이이전

// 각각을 이전 밟음, 이이전 밟음의 최대를 저장

// 계단 한계단 or 두계단
// 연속 세 개의 계단은 x
// 마지막 계단 밟기

// 점수 최댓값?  이분 탐색 x
// 그리디 or dp
// 규칙이 보인다?

// dp배열로 하면 max[n]을 구하면 된다.

// 연속 3계단

// 이전 계단을 저장해둔다.
// max[i] = max[i - 1] + score[i] or max[i - 2] + score[i]
// 여기서 max[i - 1]의 경우 before[i - 1] != i - 2

// dp니 시작 조심