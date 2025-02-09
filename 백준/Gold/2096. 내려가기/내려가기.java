import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][][] arr = new int[N + 1][3][3]; //마지막 0은 받은 값 1은 최대, 2은 최소

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}

		arr[1][0][1] = arr[1][0][0];
		arr[1][0][2] = arr[1][0][0];
		arr[1][1][1] = arr[1][1][0];
		arr[1][1][2] = arr[1][1][0];
		arr[1][2][1] = arr[1][2][0];
		arr[1][2][2] = arr[1][2][0];

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					arr[i][j][1] = Math.max(arr[i - 1][j][1], arr[i - 1][j + 1][1]) + arr[i][j][0]; // 최대
					arr[i][j][2] = Math.min(arr[i - 1][j][2], arr[i - 1][j + 1][2]) + arr[i][j][0]; // 최소
				} else if (j == 1) {
					int max = Math.max(arr[i - 1][j - 1][1], arr[i - 1][j][1]);
					max = Math.max(max, arr[i - 1][j + 1][1]);
					arr[i][j][1] = max  + arr[i][j][0];

					int min = Math.min(arr[i - 1][j - 1][2], arr[i - 1][j][2]);
					min = Math.min(min, arr[i - 1][j + 1][2]);
					arr[i][j][2] = min  + arr[i][j][0];
				} else {
					arr[i][j][1] = Math.max(arr[i - 1][j][1], arr[i - 1][j - 1][1]) + arr[i][j][0];
					arr[i][j][2] = Math.min(arr[i - 1][j][2], arr[i - 1][j - 1][2]) + arr[i][j][0];
				}
			}
		}

		int max = Math.max(arr[N][0][1], arr[N][1][1]);
		max = Math.max(max, arr[N][2][1]);

		int min = Math.min(arr[N][0][2], arr[N][1][2]);
		min = Math.min(min, arr[N][2][2]);

		System.out.println(max + " " + min);
	}
}


// N줄에 0이상 9이하 숫자 3개씩
// 내려갈 때는 바로 아래의 수 or 바로 아래의 수와 붙어있는 수
// 최대 점수 or 최소 점수

// 최대 최소? 그리디? dp? -> 그리디 x 현재의 최선이 최선이아니다.

// 모든 케이스를 다봐야 하나 -> 시간초과난다.


// dp다
// 1을 선택했을 때 2를 선택했을 때 3을 선택했을 때
// 배열 2차원

// 1 2 3
// 6 8 9