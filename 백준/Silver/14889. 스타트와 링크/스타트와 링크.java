import java.util.*;
import java.io.*;

public class Main {

	public static int[][] arr;
	public static boolean[] team;
	public static int N;

	public static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 인원 수

		arr = new int [N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		team = new boolean[N + 1];

		choiceTeam(1, 0);

		System.out.println(result);
	}

	public static void choiceTeam(int index, int count) {
		if (count >= N / 2) {
			// 최소 계산
			Sum sum = calcSum();
			if (result > (int)Math.abs(sum.team1 - sum.team2)) {
				result = (int)Math.abs(sum.team1 - sum.team2);
			}

			return;
		}

		if (index > N) {
			return;
		}

		// 선택 x
		choiceTeam(index + 1, count);

		// 선택 o
		team[index] = true;
		choiceTeam(index + 1, count + 1);
		team[index] = false;
	}

	public static Sum calcSum() {
		int team1 = 0;
		int team2 = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (team[i] == team[j]) {
					if (team[i]) {
						team1 += arr[i][j] + arr[j][i];
					} else {
						team2 += arr[i][j] + arr[j][i];
					}
				}
			}
		}

		return new Sum(team1, team2);
	}

	public static class Sum {
		int team1;
		int team2;

		public Sum(int team1, int team2) {
			this.team1 = team1;
			this.team2 = team2;
		}
	}
}
