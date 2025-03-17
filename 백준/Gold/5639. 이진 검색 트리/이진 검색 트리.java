import java.util.*;
import java.io.*;

public class Main {

	public static class Edge {
		int value;

		Edge left;
		Edge right;

		public Edge(int value) {
			this.value = value;
		}

		public void insert(int tmp) {
			if (tmp < this.value) {
				if (this.left == null) {
					this.left = new Edge(tmp);
				} else {
					this.left.insert(tmp);
				}
			} else {
				if (this.right == null) {
					this.right = new Edge(tmp);
				} else {
					this.right.insert(tmp);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<Integer> tmp = new ArrayList<>();
		while (true) {
			String input = br.readLine();
			if (input == null || input.equals("") || input.equals("\n")) {
				break;
			}
			tmp.add(Integer.parseInt(input));
		}

		Edge root = new Edge(tmp.get(0));

		for (int i = 1; i < tmp.size(); i++) {
			root.insert(tmp.get(i));
		}

		postOrder(root);
	}

	public static void postOrder(Edge edge) {
		if (edge.left != null) {
			postOrder(edge.left);
		}
		if (edge.right != null) {
			postOrder(edge.right);
		}
		System.out.println(edge.value);
	}
}

