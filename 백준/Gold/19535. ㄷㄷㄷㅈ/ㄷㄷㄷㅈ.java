
import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static List<List<Integer>> adj;
	public static List<List<Integer>> revAdj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 트리 정점수
		adj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>());
		}
		revAdj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			revAdj.add(new ArrayList<>());
		}
		for (int n = 1; n <= N - 1; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			adj.get(s).add(e);
			adj.get(e).add(s);
			revAdj.get(e).add(s);
		}

		long d = 0L;
		long g = 0L;

		// g구하기
		for (int n = 1; n <= N; n++) {
			long c = (long)(adj.get(n).size());
			if (c <= 2) {
				continue;
			} else if (c == 3) {
				g++;
			} else {
				g += c * (c - 1L) * (c - 2L) / 6L;
			}
		}


		// d구하기
		d += dfs(1);

		if (d == g * 3L) {
			System.out.println("DUDUDUNGA");
		} else if (d > g * 3L) {
			System.out.println("D");
		} else {
			System.out.println("G");
		}
	}

	public static long dfs(int n) {
		Stack<Node> s = new Stack<>();
		s.add(new Node(n, n));

		boolean[] visited = new boolean[N + 1];

		long c = 0L;

		while (!s.isEmpty()) {
			Node now = s.pop();

			if (visited[now.e]) {
				continue;
			}
			visited[now.e] = true;

			for (int next : adj.get(now.e)) {
				if (now.e != next) {
					c += (adj.get(now.e).size() - 1L) * (adj.get(next).size() - 1L);
				}

				s.add(new Node(next, now.s + 1));
			}
		}

		return c / 2;
	}

	public static class Node {
		int e;
		int s;

		public Node(int e, int c) {
			this.e = e;
			this.s = c;
		}
	}
}

// 트리에서 정점 네 개인 집합 고르기
// ㄷ이 개수. ㅈ의 개수
// 각 점에서 깊이 3으로 이동하면될듯?

// 다세야 한다.

// ㅈ
// 부모노드 수 + 자식노드 수 C 3

// ㄷ
// 각 노드에서 깊이 3 모두 조회