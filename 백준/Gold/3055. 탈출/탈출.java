import java.util.*;
import java.io.*;

public class Main {
	static int R, C;
	static char[][] map;
	static int[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new int[R][C];

		Queue<Point> q = new LinkedList<>();

		Point start = null;

		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = row.charAt(j);
				if (map[i][j] == '*') {
					q.add(new Point(i, j, 0, '*'));  // 먼저 물부터 넣기
				}
				if (map[i][j] == 'S') {
					start = new Point(i, j, 0, 'S');  // 고슴도치 위치 기억
				}
			}
		}

		q.add(start); // 물 다음에 고슴도치 넣기

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

				if (now.type == '*') {
					// 물 확장
					if (map[nr][nc] == '.') {
						map[nr][nc] = '*';
						q.add(new Point(nr, nc, 0, '*'));
					}
				} else if (now.type == 'S') {
					// 고슴도치 이동
					if ((map[nr][nc] == '.' || map[nr][nc] == 'D') && visited[nr][nc] == 0) {
						visited[nr][nc] = visited[now.r][now.c] + 1;
						q.add(new Point(nr, nc, visited[nr][nc], 'S'));
					}
				}
			}
		}

		// 결과 확인
		boolean found = false;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'D' && visited[i][j] != 0) {
					System.out.println(visited[i][j]);
					found = true;
				}
			}
		}

		if (!found) System.out.println("KAKTUS");
	}

	static class Point {
		int r, c, time;
		char type;

		public Point(int r, int c, int time, char type) {
			this.r = r;
			this.c = c;
			this.time = time;
			this.type = type;
		}
	}
}