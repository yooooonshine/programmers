
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[] visit = new boolean[N + 1];

		List<Integer>[] adj = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			List<Integer> tmp = new ArrayList<>();
			adj[i] = tmp;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			adj[start].add(end);
			adj[end].add(start);
		}

		int count = 0;
		for (int i = 1; i <= N; i++) {
			if(visit[i] == false) {
				count ++;
				dfs(adj, i, visit);
			}
		}

		System.out.println(count);
	}

	public static void dfs(List<Integer>[] adj, int start, boolean[] visit) {

		Stack<Integer> myStack = new Stack<>();

		myStack.add(start);
		while (!myStack.isEmpty()) {
			int node = myStack.pop();
			if (visit[node] == true) {
				continue;
			}
			visit[node] = true;
			for (int i = 0; i < adj[node].size(); i++) {
				myStack.add(adj[node].get(i));
			}
		}
	}
}
