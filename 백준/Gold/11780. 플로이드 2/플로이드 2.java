
import java.util.*;
import java.io.*;

public class Main {

	public static int MAX = 100000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 도시 수
		int M = Integer.parseInt(br.readLine()); // 버스 수



		// 플로이드워셜 테이블
		int[][] dist = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				dist[r][c] = MAX;
			}
			dist[r][r] = 0;
		}

		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (dist[a][b] > c) {
				dist[a][b] = c;
			}
		}

		// 경로 테이블
		List<List<List<Integer>>> path = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			path.add(new ArrayList<>());
			for (int m = 0; m <= N; m++) {
				path.get(n).add(new ArrayList<>());
			}
		}


		// 플로이드 워셜
		for (int m = 1; m <= N; m++) {
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (dist[r][c] > dist[r][m] + dist[m][c]) {
						dist[r][c] = dist[r][m] + dist[m][c];
						path.get(r).get(c).clear();
						List<Integer> rmPath = path.get(r).get(m);
						List<Integer> mcPath = path.get(m).get(c);

						path.get(r).get(c).clear();
						path.get(r).get(c).addAll(rmPath);
						path.get(r).get(c).add(m);
						path.get(r).get(c).addAll(mcPath);
					}
				}
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (dist[r][c] != MAX) {
					bw.write(dist[r][c] + " ");
				} else {
					bw.write(0 + " ");
				}
			}
			bw.write("\n");
		}

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (dist[r][c] == 0 || dist[r][c] == MAX) {
					bw.write(0 + "\n");
				} else {
					List<Integer> p = path.get(r).get(c);
					bw.write((p.size() + 2) + " ");
					bw.write(r + " ");
					for (int v : p) {
						bw.write(v + " ");
					}
					bw.write(c + "\n");
				}
			}
		}

		bw.flush();
	}
}

// n개의 도시
// m개의 버스(비용 존재)
// 모든 도시 A->B의 최소비용
// 플로이드 워셜