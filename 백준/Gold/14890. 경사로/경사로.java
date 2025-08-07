
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 크기 N
		int L = Integer.parseInt(st.nextToken()); // 경사로 길이 L

		int[][] road = new int[N + 1][N + 1]; // 1~N 사용
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				road[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		Set<Integer> rSet = new HashSet<>();
		Set<Integer> cSet = new HashSet<>();

		boolean[][] use = new boolean[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			boolean canRoad = true;
			for (int c = 1; c < N; c++) {
				if (road[r][c] > road[r][c + 1] && road[r][c] == 1 + road[r][c + 1]) {
					if (c + L > N) {
						canRoad = false;
						break;
					}
					boolean can = true;
					for (int i = c + 1; i <= c + L; i++) {
						if (use[r][i] || road[r][i] != road[r][c + 1]) {
							can = false;
							break;
						}
					}
					if (!can) {
						canRoad = false;
						break;
					}
					for (int i = c + 1; i <= c + L; i++) {
						use[r][i] = true;
					}
				} else if (road[r][c] < road[r][c + 1] && road[r][c] + 1 == road[r][c + 1]) {
					if (c - L + 1 <= 0) {
						canRoad = false;
						break;
					}
					boolean can = true;
					for (int i = c; i > c - L; i--) {
						if (use[r][i] || road[r][i] != road[r][c]) {
							can = false;
							break;
						}
					}
					if (!can) {
						canRoad = false;
						break;
					}
					for (int i = c; i > c - L; i--) {
						use[r][i] = true;
					}
				} else if (road[r][c] == road[r][c + 1]) {
					continue;
				} else {
					canRoad = false;
					break;
				}
			}
			if (canRoad) {
				rSet.add(r);
			}
		}

		use = new boolean[N + 1][N + 1];

		for (int c = 1; c <= N; c++) {
			boolean canRoad = true;
			for (int r = 1; r < N; r++) {
				if (road[r][c] > road[r + 1][c] && road[r][c] == 1 + road[r + 1][c]) {
					if (r + L > N) {
						canRoad = false;
						break;
					}
					boolean can = true;
					for (int i = r + 1; i <= r + L; i++) {
						if (use[i][c] || road[i][c] != road[r + 1][c]) {
							can = false;
							break;
						}
					}
					if (!can) {
						canRoad = false;
						break;
					}
					for (int i = r + 1; i <= r + L; i++) {
						use[i][c] = true;
					}
				} else if (road[r][c] < road[r + 1][c] && road[r][c] + 1 == road[r + 1][c]) {
					if (r - L + 1 <= 0) {
						canRoad = false;
						break;
					}
					boolean can = true;
					for (int i = r; i > r - L; i--) {
						if (use[i][c] || road[i][c] != road[r][c]) {
							can = false;
							break;
						}
					}
					if (!can) {
						canRoad = false;
						break;
					}
					for (int i = r; i > r - L; i--) {
						use[i][c] = true;
					}
				} else if (road[r][c] == road[r + 1][c]) {
					continue;
				} else {
					canRoad = false;
					break;
				}
			}
			if (canRoad) {
				cSet.add(c);
			}
		}

		System.out.println(rSet.size() + cSet.size());
	}
}