import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int T;

	public static boolean[][] hoops;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 홉개수
		T = Integer.parseInt(st.nextToken()); // 이동 y

		List<Node> nodes = new ArrayList<>();


		int xMax = 0;
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // x
			int y = Integer.parseInt(st.nextToken()); // y

			if (x > xMax) {
				xMax = x;
			}

			nodes.add(new Node(x, y, 0));
		}
		hoops = new boolean[xMax + 1][T + 1];

		for (Node node : nodes) {
			int x = node.x;
			int y = node.y;

			// 홈이 있는 곳은 true로 표시
			hoops[x][y] = true;
		}

		boolean[][] visit = new boolean[xMax + 1][T + 1];
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0,0));

		int result = -1;

		while (!q.isEmpty()) {
			Node nowN = q.poll();
			int nowX = nowN.x;
			int nowY = nowN.y;
			int nowC = nowN.count;

			if (nowY >= T) {
				result = nowC;
				break;
			}

			if (visit[nowX][nowY]) {
				continue;
			}
			visit[nowX][nowY] = true;

			for (int x = -2; x <= 2; x++) {
				for (int y = -2; y <= 2; y++) {
					if (x == 0 && y == 0) {
						continue;
					}

					int nextX = x + nowX;
					int nextY = y + nowY;
					if (nextX < 0 || nextX > xMax || nextY < 0 || nextY > T) {
						continue;
					}

					if (hoops[nextX][nextY]) {
						q.add(new Node(nextX, nextY, nowC + 1));
					}
				}
			}
		}
		System.out.println(result);
		// 오를 수 없으면 -1 출력
	}

	public static class Node {
		int x;
		int y;
		int count;

		public Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}

// 암벽에 n개의 홈 (n <= 50000)
// 각 홈 (x,y) 위아래2, 좌우2 이동
// y가 T가 될떄까지 이동
// 현재(0,0)
// 이동회수 최소
//

// 방법
// bfs
// dfs

// bfs 합리적
// 그럼 근처를 어떻게 알지?

// 배열을 둘까?

