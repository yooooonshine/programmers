
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		List<List<Integer>> adj = new ArrayList<>();
		List<List<Integer>> reverseAdj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<Integer>());
			reverseAdj.add(new ArrayList<Integer>());
		}

		int [] time = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			time[i] = Integer.parseInt(st.nextToken());

			while (true) {
				int tmp = Integer.parseInt(st.nextToken());

				if (tmp == -1) {
					break;
				}

				adj.get(tmp).add(i);
				reverseAdj.get(i).add(tmp);
			}
		}

		topologySort(adj, N, time, reverseAdj);

		for (int i = 1; i <= N; i++) {
			System.out.println(time[i]);
		}
	}

	public static void topologySort(List<List<Integer>> adj, int N, int[] time, List<List<Integer>> reverseAdj) {
		int[] inDegree = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j : adj.get(i)) {
				inDegree[j]++;
			}
		}

		Queue<Integer> myQ = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				myQ.add(i);
			}
		}

		while (!myQ.isEmpty()) {
			int now = myQ.poll();

			for (int i : adj.get(now)) {
				inDegree[i]--;

				if (inDegree[i] == 0) {
					myQ.add(i);

					int maxTime = 0;
					for (int k : reverseAdj.get(i)) {
						if (time[k] > maxTime) {
							maxTime = time[k];
						}
					}
					time[i] += maxTime;

				}
			}
		}
	}
}
