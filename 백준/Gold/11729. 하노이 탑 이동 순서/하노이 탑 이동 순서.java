import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<List<Edge>> result = new ArrayList<>();
		for (int i = 0; i <= 20; i++) {
			result.add(new ArrayList<>());
		}
		result.get(1).add(new Edge(1, 3));

		result.get(2).add(new Edge(1, 2));
		result.get(2).add(new Edge(1, 3));
		result.get(2).add(new Edge(2, 3));

		for (int i = 3; i <= N; i++) {
			for (Edge edge : result.get(i - 1)) {
				int s = edge.s;
				int e = edge.e;

				if (s == 2) {
					s = 3;
				} else if (s == 3) {
					s = 2;
				}

				if (e == 2) {
					e = 3;
				} else if (e == 3) {
					e = 2;
				}
				result.get(i).add(new Edge(s, e));
			}
			result.get(i).add(new Edge(1, 3));

			for (Edge edge : result.get(i - 1)) {
				int s = edge.s;
				int e = edge.e;

				if (s == 1) {
					s = 2;
				} else if (s == 2) {
					s = 1;
				}

				if (e == 1) {
					e = 2;
				} else if (e == 2) {
					e = 1;
				}

				result.get(i).add(new Edge(s, e));
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(result.get(N).size() + "\n");
		for (Edge edge : result.get(N)) {
			bw.write(edge.s + " " + edge.e + "\n");
		}
		bw.flush();
	}

	public static class Edge {
		int s;
		int e;

		public Edge(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}

// 첫 장대에 n개의 원판이 반경이 큰순서대로
// 원판은 항상 위 < 아래
// 이동 횟수 최소

// 이동 횟수와 이동 출력