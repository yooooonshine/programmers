import java.util.*;
import java.io.*;

public class Main {
	public static boolean[] visit;
	public static List<List<Integer>> adj;
	public static int[] count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());

			adj.get(U).add(V);
			adj.get(V).add(U);
		}

		visit = new boolean[N + 1];
		count = new int[N + 1];

		dfs(R);

		for (int i = 1; i <= Q; i++) {
			int U = Integer.parseInt(br.readLine());
			System.out.println(count[U]);
		}
	}

	public static int dfs(int node) {
		if (visit[node]) {
			return 0;
		}

		visit[node] = true;

		int sum = 1;
		for (int tmp : adj.get(node)) {
			sum += dfs(tmp);
		}

		count[node] = sum;
		return sum;
	}
}

// 점정 U를 루트로 하는 서브트리의 점점 수

// N R Q(정점 수, 루트 번호, 퀴러 수)
// N - 1 줄 U V 간선
// Q 줄  U 하나씩

// 재귀 dfs로 트리 구성하자
// int 리턴
// visit 배열
// 루트노드부터
// 자식들 보며 재귀로 들어감
// 끝이면 1리턴
// 자식들 합을 더해서 리턴
// 마지막에 그 노드 저장

// 각 서브트리의 자식수를 구할 수 있나
// 자식수를 미리 구해둬야겠네

// 트리 순화?
// 트리를 모두 구성한 후 오른쪽부터 해서 arr[k] = arr[2k] + arr[2k + 1]
// 그럼 트리를 배열로 만들어야겠군
// 배열 크기는?

// 배열로 각 노드의 자식수 배열을 만들어보자
// dfs로 한다면 가능할지도?

// stack으로 하면?