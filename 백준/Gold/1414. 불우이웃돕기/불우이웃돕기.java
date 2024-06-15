import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] adjA = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			String[] strs = br.readLine().split("");


			for (int j = 1; j <= N; j++) {
				if(Objects.equals(strs[j - 1], "0")) {
					continue;
				}
				adjA[i][j] = convertToInt(strs[j - 1]);
			}
		}

		int totalSum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				totalSum += adjA[i][j];
			}
		}

		List<Edge> adj = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j) {
					if (adjA[i][j] == 0) {
						continue;
					}
					adj.add(new Edge(i, j, adjA[i][j]));
				}
			}
		}

		Collections.sort(adj);

		int[] group = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			group[i] = i;
		}

		int count = 0;
		int sum = 0;
		for (Edge edge : adj) {
			if (count >= N - 1) {
				break;
			}

			int s = edge.s;
			int e = edge.e;
			int w = edge.w;

			if (find(group, s) == find(group, e)) {
				continue;
			} else {
				union(group, s, e);
				count++;
				sum+=w;
			}
		}
		if (count < N - 1) {
			System.out.println(-1);
			return;
		}

		System.out.println(totalSum - sum);
	}

	public static int convertToInt(String str) {
		char myChar = str.charAt(0);

		if ('a' <= myChar && myChar <= 'z') {
			return myChar - 'a' + 1;
		} else {
			return myChar - 'A' + 1 + 26;
		}
	}

	public static int find(int[] group, int node) {
		if (node == group[node]) {
			return node;
		} else {
			return group[node] = find(group, group[node]);
		}
	}

	public static void union(int[] group, int node1, int node2) {
		int p1 = find(group, node1);
		int p2 = find(group, node2);

		if (p1 == p2) {
			return;
		} else if (p1 < p2) {
			group[p2] = p1;
		} else {
			group[p1] = p2;
		}
	}
}

class Edge implements Comparable<Edge> {
	int s;
	int e;
	int w;

	public Edge(int s, int e, int w) {
		this.s = s;
		this.e = e;
		this.w = w;
	}

	@Override
	public int compareTo(Edge edge) {
		int w1 = this.w;
		int w2 = edge.w;

		return w1 - w2;
	}
}
