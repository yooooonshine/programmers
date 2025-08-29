import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 시작점
		int K = Integer.parseInt(st.nextToken()); // 도착점

		bfs(N, K);
	}

	public static void bfs(int s, int e) {
		Queue<Node> myQ = new LinkedList<>();
		myQ.add(new Node(s, 0, s));

		int[] before = new int[100001]; // 0부터
		Arrays.fill(before, -1);
		int[] dist = new int[100001];
		boolean[] visit = new boolean[100001];

		while (!myQ.isEmpty()) {
			Node nowNode = myQ.poll();
			int nowN = nowNode.n;
			int nowD = nowNode.d;
			int nowB = nowNode.b;

			if (visit[nowN]) {
				continue;
			}
			visit[nowN] = true;

			dist[nowN] = nowD;
			before[nowN] = nowB;

			if (nowN == e) {
				break;
			}



			int next = nowN - 1;
			if (next >= 0) {
				myQ.add(new Node(next, nowD + 1, nowN));
				
			}

			next = nowN + 1;
			if (next <= 100000) {
				myQ.add(new Node(next, nowD + 1, nowN));
			}

			next = nowN * 2;
			if (next <= 100000) {
				myQ.add(new Node(next, nowD + 1, nowN));
				
			}
		}

		System.out.println(dist[e]);

		int now = e;
		Stack<Integer> myS = new Stack<>();
		while(true) {
			myS.add(now);

			if (now == s) {
				break;
			}

			now = before[now];
		}

		while(!myS.isEmpty()) {
			System.out.print(myS.pop() + " ");
		}
	}

	public static class Node {
		int n;
		int d;
		int b;

		public Node(int n, int d, int b) {
			this.n = n;
			this.d = d;
			this.b = b;
		}
	}
}


// 걷기 X-1, X+1
// 순간이동 2 * X

// bfs하면되겠느데?

// 이동?
// 이전 노드 저장

