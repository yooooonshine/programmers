import java.util.*;
import java.io.*;

class Main {
	public static long inf = 999999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //도시 개수
		int M = Integer.parseInt(st.nextToken()); //버스 개수

		List<Edge> adj = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long dist = Integer.parseInt(st.nextToken());
			adj.add(new Edge(start, end, dist));
		}

		long[] distance = new long[N + 1];
		Arrays.fill(distance, inf);

		distance[1] = 0;
		for (int i = 1; i <= N - 1; i++) {
			bellmanFord(adj, distance);
		}

		long[] tmpDistance = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			tmpDistance[i] = distance[i];
		}

		bellmanFord(adj, distance);

		boolean timeMachine = false;
		for (int i = 1; i <= N; i++) {
			if (tmpDistance[i] != distance[i]) {
				timeMachine = true;
			}
		}

		if (timeMachine) {
			System.out.println(-1);
		} else {
			for (int i = 2; i <= N; i++) {
				if (distance[i] == inf) {
					System.out.println(-1);
				} else {
					System.out.println(distance[i]);
				}
			}
		}
	}

	public static void bellmanFord(List<Edge> adj, long[] distance) {
		for (Edge edge : adj) {
			int start = edge.getStart();
			int end = edge.getEnd();
			if (distance[start] == inf) {
				continue;
			}
			long dist = edge.getDist();
			if (distance[end] > distance[start] + dist) {
				distance[end] = distance[start] + dist;
			}
		}
	}
}

class Edge {
	private int start;
	private int end;
	private long dist;

	public Edge(int start, int end, long dist) {
		this.start = start;
		this.end = end;
		this.dist = dist;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	public long getDist() {
		return this.dist;
	}
}