import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			adj.get(start).add(end);
		}

		int[] insertDegrees = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j : adj.get(i)) {
				insertDegrees[j]++;
			}
		}


		int visitNode = 0;
		for (int i = 1; i < N + 1; i++) {
			if (insertDegrees[i] == 0) {
				visitNode = i;
				break;
			}
		}

		List<Integer> results = new ArrayList<>();
		while(true) {

			results.add(visitNode);
			insertDegrees[visitNode] = -1;

			for (int i : adj.get(visitNode)) {
				insertDegrees[i]--;
			}

			boolean existence = false;
			for (int i = 1; i <= N; i++) {
				if (insertDegrees[i] == 0) {
					visitNode = i;
					existence = true;
				}
			}
			if (!existence) {
				break;
			}
		}

		for (int i : results) {
			bw.write(i + " ");
		}
		bw.flush();
		bw.close();
	}
}