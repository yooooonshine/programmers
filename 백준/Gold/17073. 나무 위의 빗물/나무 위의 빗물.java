
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 노드의 수
		int W = Integer.parseInt(st.nextToken()); // 1번 노드 고인 물의 양

		List<List<Integer>> adj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>());
		}

		for (int n = 1; n <= N - 1; n++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		double[] waters = new double[N + 1];
		waters[1] = W;
		boolean[] visit = new boolean[N + 1];

		Stack<Integer> myS = new Stack<>();
		myS.add(1);

		while(!myS.isEmpty()) {
			Integer now = myS.pop();

			if (visit[now]) {
				continue;
			}
			visit[now] = true;

			double count = 0D;
			for (Integer next : adj.get(now)) {
				if (!visit[next]) {
					count++;
				}
			}

			if (count != 0D) {
				for (Integer next : adj.get(now)) {
					if (!visit[next]) {
						myS.add(next);
						waters[next] = waters[now] / count;
					}
				}

				waters[now] = 0D;
			}

		}

		int c = 0;
		double sum = 0D;
		for (int n = 1; n <= N; n++) {
			if (waters[n] > 0D) {
				c++;
				sum += waters[n];
			}
		}

		System.out.println(String.format("%.7f", sum / c));
	}
}

// 자식 중 하나에게 물 1
// 받은 물은 즉시 자식에게 주지는 않음. (중요)
// i번 정점의 쌓인 물의 기댓값 Pi
// 0보다 큰 곳들의 평균

// bfs를 통해 n개로 나눈다.