
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][][] arr = new int[N + 1][3][2]; // 1부터 시작
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int k = 0; k <= 2; k++) {
				arr[i][k][0] = Integer.parseInt(st.nextToken());
			}
		}

		arr[1][0][1] = arr[1][0][0];
		arr[1][1][1] = arr[1][1][0];
		arr[1][2][1] = arr[1][2][0];

		for (int i = 2; i <= N; i++) {
			arr[i][0][1] = arr[i][0][0] + (int)Math.min(arr[i - 1][1][1], arr[i - 1][2][1]);
			arr[i][1][1] = arr[i][1][0] + (int)Math.min(arr[i - 1][0][1], arr[i - 1][2][1]);
			arr[i][2][1] = arr[i][2][0] + (int)Math.min(arr[i - 1][0][1], arr[i - 1][1][1]);
		}

		int result = (int)Math.min(arr[N][0][1], arr[N][1][1]);
		result = (int)Math.min(result, arr[N][2][1]);

		System.out.println(result);
	}
}

// n개의 집
// 거리는 선분
// 집(빨, 초, 파)
// 비용의 최솟값

// 양 끝이 아닌 집은 좌우와 집의 색이 같으며 안된다.
//

// 그리디인가?
// 모든 케이스 3^1000 -> x
// dp
// 이분 탐색
// 하나를 골랐을 때 다음 가능성은 2개

// 1번을 골랐을 때 최선 vs 2번을 골랐을 때 최선 vs 3번을 골랐을 때 최선
// 2중 배열을 사용
// 각각을 골랐을 때  최선을 저장한다.
// 1번을 골랐을 때 최선은 이전의 2번 최선 vs 3번 최선

