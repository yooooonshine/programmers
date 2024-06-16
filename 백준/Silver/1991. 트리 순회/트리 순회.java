import java.util.*;
import java.io.*;

class Main {
	public static int endIndex = convertToInt('.');

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = convertToInt(st.nextToken().charAt(0));
			int child1 = convertToInt(st.nextToken().charAt(0));
			int child2 = convertToInt(st.nextToken().charAt(0));

			adj.get(parent).add(child1); // 왼쪽
			adj.get(parent).add(child2); // 오른쪽
		}

		preOrder(adj, 1);
		System.out.println("");
		inOrder(adj, 1);
		System.out.println("");
		postOrder(adj, 1);
	}

	public static int convertToInt(char a) {
		return a - 'A' + 1;
	}

	public static char convertToChar(int a) {
		return (char)(a + 'A' - 1);
	}

	public static void preOrder(List<List<Integer>> adj, int start) {
		if (start == endIndex) {
			return;
		}

		System.out.print(convertToChar(start));

		int child1 = adj.get(start).get(0);
		int child2 = adj.get(start).get(1);

		preOrder(adj, child1);
		preOrder(adj, child2);
	}

	public static void inOrder(List<List<Integer>> adj, int start) {
		if (start == endIndex) {
			return;
		}

		List<Integer> adjNodes = adj.get(start);

		int child1 = adjNodes.get(0);
		int child2 = adjNodes.get(1);

		inOrder(adj, child1);
		System.out.print(convertToChar(start));
		inOrder(adj, child2);
	}

	public static void postOrder(List<List<Integer>> adj, int start) {

		if (start == endIndex) {
			return;
		}

		List<Integer> adjNodes = adj.get(start);

		int child1 = adjNodes.get(0);
		int child2 = adjNodes.get(1);

		postOrder(adj, child1);
		if (child1 != endIndex) {
			System.out.print(convertToChar(child1));
		}
		postOrder(adj, child2);
		if (child2 != endIndex) {
			System.out.print(convertToChar(child2));
		}

		if (start == 1) {
			System.out.print(convertToChar(start));
		}
	}
}
