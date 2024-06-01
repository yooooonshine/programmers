import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] numbers = new int[N + 1][N + 1];
		for (int i = 0; i < N; i ++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				numbers[i + 1][j + 1] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] partialSums = new int[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			partialSums[0][i] = 0;
		}
		for (int i = 0; i <= N; i++) {
			partialSums[i][0] = 0;
		}

		for (int i = 1; i <= N; i ++) {
			for (int j = 1; j <= N; j++) {
				partialSums[i][j] = partialSums[i - 1][j] + partialSums[i][j - 1] - partialSums[i - 1][j - 1] + numbers[i][j];
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			System.out.println(partialSums[x2][y2] - partialSums[x2][y1 - 1] - partialSums[x1 - 1][y2] + partialSums[x1 - 1][y1 - 1]);
		}
	}
}
