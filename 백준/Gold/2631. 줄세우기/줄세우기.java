import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] nums = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			nums[i][0] = Integer.parseInt(br.readLine());
			nums[i][1] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int k = 1; k <= i - 1; k++) {
				if (nums[k][0] < nums[i][0] && nums[k][1] >= nums[i][1]) {
					nums[i][1] = nums[k][1] + 1;
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (max < nums[i][1]) {
				max = nums[i][1];
			}
		}

		System.out.println(N - max);
	}
}

// N명의 아이들
// n줄 동안 1부터 N까지의 숫자 중하나.

// 전체 증가하는 거 최대 구해서
// N에 빼면되지않나.
// 최대 증가 수열이네.
// dp잖아