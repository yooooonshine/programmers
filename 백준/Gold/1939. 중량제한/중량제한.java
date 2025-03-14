import java.util.*;
import java.io.*;

public class Main {

	public static List<List<Edge>> adj;
	public static int END;
	public static long MAX = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 섬의 수
		int M = Integer.parseInt(st.nextToken()); // 다리 수

		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		long max = 0;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));

			if (max < c) {
				max = c;
			}
		}

		st = new StringTokenizer(br.readLine());
		int START = Integer.parseInt(st.nextToken());
		END = Integer.parseInt(st.nextToken());


		long s = 1;
		long e = max;

		long result = 0;
		while (s <= e) {
			long mid = (s + e) / 2;

			if (dfs(START, N, mid)) {
				s = mid + 1;

				result = mid;
			} else {
				e = mid - 1;
			}
		}

		System.out.println(result);
	}

	public static boolean dfs(int s, int N, long mid) {
		boolean[] visit = new boolean[N + 1];

		Stack<Integer> stack = new Stack<>();
		stack.add(s);

		while (!stack.isEmpty()) {
			int cur = stack.pop();

			if (cur == END) {
				return true;
			}

			for (Edge e : adj.get(cur)) {
				if (!visit[e.e] && e.w >= mid) {
					visit[e.e] = true;
					stack.add(e.e);
				}
			}
		}

		return false;
	}

	public static class Edge {
		int e;
		long w;

		public Edge(int e, long w) {
			this.e = e;
			this.w = w;
		}
	}
}

// N 개의 섬
// 다리 중량  제한

// N, M(섬 개수, 개의 줄)
// M 개의 줄
// A, B, C (A섬, B섬 사이 중량C인 다리)
// 공장 위치 섬의 번호 두 개

// 두 섬 사이 여러 개의  다리 가능, 양방향 -> 그래프네

