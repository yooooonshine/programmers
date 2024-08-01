
import java.util.*;
import java.io.*;

public class Main {
	private static final int MAX_N = 100000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		bfs(N, K);
	}

	public static void bfs(int start, int end) {
		boolean[] visit = new boolean[MAX_N + 1];

		Position position = new Position(start, 0);

		Queue<Position> q = new LinkedList<>();
		q.add(position);

		while (!q.isEmpty()) {
			Position poll = q.poll();
			int now = poll.now;
			int t = poll.t;

			if (visit[now]) {
				continue;
			}

			if (now == end) {
				System.out.println(t);
				return;
			}

			visit[now] = true;


			if (now - 1 >= 0 && !visit[now - 1]) {
				q.add(new Position(now - 1, t + 1));
			}

			if (now + 1 <= MAX_N && !visit[now + 1]) {
				q.add(new Position(now + 1, t + 1));
			}

			if (now * 2 <= MAX_N && !visit[now * 2]) {
				q.add(new Position(now * 2, t + 1));
			}
		}
	}
}

class Position {
	int now;
	int t;

	public Position(int now, int t) {
		this.now = now;
		this.t = t;
	}
}

// 수빈이 위치 N 0~100000
// 동생 위치 K 0~100000