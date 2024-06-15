import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 노드 개수

		int[] tree = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}

		int rootNode = 0;
		for (int i = 0; i < N; i++) {
			if (tree[i] == -1) {
				rootNode = i;
				break;
			}
		}

		int dNode = Integer.parseInt(br.readLine());

		if (dNode == rootNode) {
			System.out.println(0);
			return;
		}

		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < N; i++) {
			if (tree[i] == -1) {
				continue;
			}
			adj.get(tree[i]).add(i);
		}

		List<Integer> adjNodes = adj.get(tree[dNode]);
		for (int i = 0; i < adjNodes.size(); i++) {
			if (adjNodes.get(i) == dNode) {
				adjNodes.remove(i);
				break;
			}
		}

		System.out.println(calcLeafs(adj, rootNode));

	}

	public static int calcLeafs(List<List<Integer>> adj, int node) {
		List<Integer> adjNodes = adj.get(node);

		if (adjNodes.isEmpty()) {
			return 1;
		}

		int count = 0;
		for (int visitNode : adjNodes) {
			int leafs = calcLeafs(adj, visitNode);

			count += leafs;
		}
		return count;
	}

}