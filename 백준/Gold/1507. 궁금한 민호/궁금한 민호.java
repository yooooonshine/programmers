import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int dist[][] = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				dist[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] newDist = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			Arrays.fill(newDist[r], 100000000);
			newDist[r][r] = 0;
		}

		int sum = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = r + 1; c <= N; c++) {
				boolean canMake = false;
				for (int m = 1; m <= N; m++) {
					if (dist[r][c] == dist[r][m] + dist[m][c]) {
						if (r == m || c == m) {
							continue;
						}

						canMake = true;
						break;
					}
				}

				if (!canMake) {
					sum += dist[r][c];
					newDist[r][c] = dist[r][c];
					newDist[c][r] = dist[r][c];
				}
			}
		}

		// 플로이드워셜
		for (int m = 1; m <= N; m++) {
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (newDist[r][c] > newDist[r][m] + newDist[m][c]) {
						newDist[r][c] = newDist[r][m] + newDist[m][c];
					}
				}
			}
		}
		boolean isOk = true;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (dist[r][c] != newDist[r][c]) {
					isOk = false;
					break;
				}
			}
			if (!isOk) {
				break;
			}
		}

		if (!isOk) {
			System.out.println(-1);
			return;
		}

		System.out.println(sum);
	}
}

// N개의 도시
// M개의 도로 -> 시간, 모든 도시끼리 이동기능
// 모든 도시에대해 최소 이동 시간 존재 -> 도로 ㅇ몇개읹
// 모든 도로의 개수가 최솟값일 때
// 도로 시간의 합
// 일단 모든 도시끼리이므로 플로이드 워셜

// 두 경로를 합쳐서, 새 경로를 저장한다.
// -> 길이 두 경로로 생성되면 제거?