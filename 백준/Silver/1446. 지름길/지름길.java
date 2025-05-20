import java.util.*;
import java.io.*;

public class Main {

	public static int D;
	public static int N;

	public static List<List<Node>> adj = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 지름길 수
		D = Integer.parseInt(st.nextToken()); // 고속도로 길이

		for (int i = 0; i <= D; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < D; i++) {
			adj.get(i).add(new Node(i + 1, 1));
		}

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			if (s < 0 || s > D || e < 0 || e > D) {
				continue;
			}
			adj.get(0).add(new Node(s, s));
			adj.get(s).add(new Node(e, d));
			adj.get(e).add(new Node(D, D - e));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));
		int[] dist = new int[D + 1];
		for (int i = 0; i <= D; i++) {
			dist[i] = 10000000;
		}
		dist[0] = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int nowE = now.e;


			for (Node next : adj.get(nowE)) {
				if (dist[next.e] > dist[nowE] + next.d) {
					dist[next.e] = dist[nowE] + next.d;
					pq.add(new Node(next.e, dist[next.e]));
				}
			}
		}

		System.out.println(dist[D]);
	}


	public static class Node implements Comparable<Node> {
		int e;
		int d;

		public Node(int e, int d) {
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Node n) {
			return this.d - n.d;
		}
	}
}

// D킬로 미터 고속도로
// 커브가 많다.
// 지름길(일반통행,단방향)
// 운전거리 최솟값

// 지금길 N(12이하 1이상), 고속도로 길이D(10000이하)
// 지름길 (시작, 도착, 길이)


// 넘는길은 갈 수 없다.
// 정확히 D 위치로 가야 한다.
// 길이 하나 들어오면, 기존 길 + 새 길 + 다음길 생성

// 다익스트라
// pq
// 노드 넣기
// dist 배열
// pq 빌 때까지
// 인접노드에 대해
// 거리 갱신