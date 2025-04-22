import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;

	public static int[][] buckets;

	// 1번부터 8번 이동방향
	public static int[] rs = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	public static int[] cs = {0, -1, -1, 0, 1, 1, 1, 0, -1};

	public static int[] wrs = {1, 1, -1, -1};
	public static int[] wcs = {1, -1, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 칸수
		M = Integer.parseInt(st.nextToken()); // M번 요청

		buckets = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				buckets[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[] D = new int[M + 1]; // 방향
		int[] S = new int[M + 1]; // 이동횟수

		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			D[m] = Integer.parseInt(st.nextToken());
			S[m] = Integer.parseInt(st.nextToken());
		}

		List<Cloud> clouds = new ArrayList<>();
		clouds.add(new Cloud(N, 1));
		clouds.add(new Cloud(N, 2));
		clouds.add(new Cloud(N - 1, 1));
		clouds.add(new Cloud(N - 1, 2));

		for (int m = 1; m <= M; m++) {
			int d = D[m];
			int s = S[m];

			boolean[][] visit = new boolean[N + 1][N + 1];
			for (Cloud cloud : clouds) {
				move(cloud, d, s);
				buckets[cloud.r][cloud.c]++;
				visit[cloud.r][cloud.c] = true;
			}

			for (Cloud cloud : clouds) {
				waterCopy(cloud);
			}

			clouds.clear();

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (visit[r][c]) {
						continue;
					}

					if (buckets[r][c] >= 2) {
						clouds.add(new Cloud(r, c));
						buckets[r][c] -= 2;
					}
				}
			}
		}

		int sum = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				sum += buckets[r][c];
			}
		}

		System.out.println(sum);
	}

	public static void move(Cloud cloud, int d, int s) {
		int rd = rs[d];
		int cd = cs[d];
		for (int i = 1; i <= s; i++) {
			if (cloud.r + rd > N) {
				cloud.r = (cloud.r + rd) % N;
			} else if (cloud.r + rd < 1) {
				cloud.r = (cloud.r + rd + N);
			} else {
				cloud.r = cloud.r + rd;
			}

			if (cloud.c + cd > N) {
				cloud.c = (cloud.c + cd) % N;
			} else if (cloud.c + cd < 1) {
				cloud.c = (cloud.c + cd + N);
			} else {
				cloud.c = cloud.c + cd;
			}
		}
	}

	public static void waterCopy(Cloud cloud) {
		int count = 0;
		for (int i = 0; i <= 3; i++) {
			int r = cloud.r + wrs[i];
			int c = cloud.c + wcs[i];

			if (r >= 1 && r <= N && c >= 1 && c <= N && buckets[r][c] > 0) {
				count++;
			}
		}

		buckets[cloud.r][cloud.c] += count;
	}

	public static class Cloud {
		int r;
		int c;

		public Cloud(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
