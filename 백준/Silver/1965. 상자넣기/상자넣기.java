import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 상자 수

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] nums = new int[n + 1][2];
		for (int i = 1; i <= n; i++) {
			nums[i][0] = Integer.parseInt(st.nextToken());
			nums[i][1] = 1;
		}

		// 갱신
		for (int i = 1; i <= n; i++) {

			for (int r = i - 1; r >= 1; r--) {
				if (nums[r][0] < nums[i][0] && nums[r][1] >= nums[i][1]) {
					{
						nums[i][1] = nums[r][1] + 1;
					}
				}
			}


		}

		int max = 0;
		for (int i = 1; i <= n; i++) {
			if (nums[i][1] > max) {
				max = nums[i][1];
			}
		}

		System.out.println(max);
	}
}
// 앞에 있는 상자가 뒤보다 작으면 넣는다.
// 한 번에 넣을 수 있는 최대 상자 개수

// 현재의 최선이 미래의 최선은 아니야. 그리디 아니다.
// n^2도 가능하다.
// dp 가능할지도

// 앞에서 작은 것의 MAX면 되지 않을까?

// 16257356
// 12234345

// 이 안에서 MAx를 찾으면 되겠다.