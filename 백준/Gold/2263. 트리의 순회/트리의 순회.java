
import java.util.*;
import java.io.*;

public class Main {

	public static int[] inOrder;
	public static int[] postOrder;
	public static List<int[]> adj;
	public static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		inOrder = new int[n + 1];
		postOrder = new int[n + 1];
		adj = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adj.add(new int[2]);
		}

		// 인오더 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}

		// 포스트오더 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}

		int root = recursion(1, n, 1, n);

		preOrder(root);
	}


	// 리턴은루트
	public static int recursion(int is, int ie, int ps, int pe) {
		int root = postOrder[pe];

		int im = 0; // 중간 값
		for (int i = is; i <= ie; i++) {
			if (inOrder[i] == root) {
				im = i;
				break;
			}
		}

		int iRightCount = ie - im;
		if (iRightCount!= 0) {
			adj.get(postOrder[pe])[1] = postOrder[pe - 1];
			recursion(im + 1, ie, pe - iRightCount, pe - 1);
		}

		int iLeftCount = im - is;
		if (iLeftCount != 0) {
			adj.get(postOrder[pe])[0] = postOrder[pe - iRightCount - 1];
			recursion(is, im - 1, pe - iRightCount - iLeftCount, pe - 1 - iRightCount);
		}



		return root;
	}

	public static void preOrder(int root) {
		if (root == 0) {
			return;
		}

		System.out.print(root + " ");
		preOrder(adj.get(root)[0]);
		preOrder(adj.get(root)[1]);
	}
}