import java.util.*;
import java.io.*;

public class Main {

	public static int R;
	public static int C;
	public static int[][] arr;

	public static int[] rs = {0, 0, -1, 1};
	public static int[] cs = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[R + 2][C + 2];
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0;
		int cheezeCount = 0;
		while (true) {
			List<Vertex> result = dfs(0,0);
			if (result.size() == 0) {
				break;
			}

			cheezeCount = 0;
			for (Vertex v : result) {
				if (arr[v.r][v.c] == 1) {
					arr[v.r][v.c] = 0;
					cheezeCount++;
				}
			}

			count++;
		}

		System.out.println(count);
		System.out.println(cheezeCount);
	}

	public static List<Vertex> dfs(int r, int c) {
		List<Vertex> result = new ArrayList<>();

		boolean[][] visit = new boolean[R + 2][C + 2];
		Stack<Vertex> myS = new Stack<>();
		myS.add(new Vertex(r, c));

		while (!myS.isEmpty()) {
			Vertex now = myS.pop();
			int nowR = now.r;
			int nowC = now.c;

			if (visit[nowR][nowC]) {
				continue;
			}
			visit[nowR][nowC] = true;

			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];

				if (nextR < 0 || nextR > R + 1 || nextC < 0 || nextC > C + 1) {
					continue;
				}

				if (arr[nextR][nextC] == 1) {
					result.add(new Vertex(nextR, nextC));
					continue;
				}

				myS.add(new Vertex(nextR, nextC));
			}
		}

		return result;
	}

	public static class Vertex {
		int r;
		int c;

		public Vertex(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}

// 테두리는 x , 치즈 x
// 치즈가 공기에 놓이면 없어진다.
// 치즈는 구멍이 있을 수 있다.

// 모두 녹아 없어지느데 걸리는 시간
// 녹기 한시간전에 남아있는 치즈조각 칸 개수

// 10000 * 50 -> 구현문제구나b

// 테두리만 어떻게 없앨거야

// 모든 테두리에서 탐색을 시작해서 담는다.
// 탐색을 하다가. 0과 인접한 1은 리스트에 담는다.
// 리스트에 담고 마지막에 배열에 반영한다.

// 이 때 배열의 1이 0이되면, 그 시간과 리스트 개수 출력
