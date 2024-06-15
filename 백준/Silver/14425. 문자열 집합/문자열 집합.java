import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N개의 문자열
		int M = Integer.parseInt(st.nextToken()); // 비교할 주어진 문자열

		Node root = new Node(false);
		for (int i = 0; i < N; i++) {
			char[] str = br.readLine().toCharArray();

			int strSize = str.length;
			Node beforeNode = root;
			for (int j = 0; j < strSize; j++) {
				int index = convertToNum(str[j]);
				Node currentNode;
				if (beforeNode.childs[index] != null) {
					currentNode = beforeNode.childs[index];
				} else {
					currentNode = new Node(false);
					beforeNode.childs[index] = currentNode;
				}

				if (j == strSize - 1) {
					currentNode.isEnd = true;
				}
				beforeNode = currentNode;
			}
		}

		int count = 0;
		for (int i = 0; i < M; i++) {
			char[] str = br.readLine().toCharArray();

			int strSize = str.length;
			Node beforeNode = root;
			boolean existence = false;
			for (int j = 0; j < strSize; j++) {
				int index = convertToNum(str[j]);
				Node currentNode;
				if (beforeNode.childs[index] == null) {
					break;
				} else {
					currentNode = beforeNode.childs[index];
				}

				if (j == strSize - 1) {
					if (currentNode.isEnd) {
						existence = true;
					}
				}
				beforeNode = currentNode;
			}

			if (existence) {
				count++;
			}
		}

		System.out.println(count);
	}

	public static int convertToNum(char a) {
		return Integer.valueOf(a - 'a');
	}
}

class Node {
	public Node[] childs = new Node[26]; // a가 0

	public boolean isEnd; //단어인지

	public Node(boolean isEnd) {
		this.isEnd = isEnd;
	}
}
