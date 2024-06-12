import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //사람수
		int M = Integer.parseInt(st.nextToken()); //파티수

		int[] A = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			A[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int count = Integer.parseInt(st.nextToken());

		if (count == 0) {
			System.out.println(M);
			return;
		}
		
		int firstPerson = Integer.parseInt(st.nextToken());
		for (int i = 0; i < count - 1; i++) {
			union(A, firstPerson, Integer.parseInt(st.nextToken()));
		}

		int speaker = find(A, firstPerson);

		List<Integer> parties = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			count = Integer.parseInt(st.nextToken());
			if (count == 0) {
				continue;
			}
			firstPerson = Integer.parseInt(st.nextToken());
			parties.add(firstPerson);
			for (int k = 0; k < count - 1; k++) {
				union(A, firstPerson, Integer.parseInt(st.nextToken()));
			}
		}

		int totalCount = 0;

		int speakerNode = find(A, speaker);
		for (int i = 0; i < M; i++) {
			int node = parties.get(i);
			int node1 = find(A, node);

			if (speakerNode != node1) {
				totalCount++;
			}
		}
		System.out.println(totalCount);
	}

	public static void union(int[] A, int node1, int node2) {
		int p1 = find(A, node1);
		int p2 = find(A, node2);

		if (p1 == p2) {
			return;
		} else if (p1 > p2) {
				A[p1] = p2;
		} else {
				A[p2] = p1;
		}
	}

	public static int find(int[] A, int node) {
		if (node == A[node]) {
			return node;
		} else {
			return A[node] = find(A, A[node]);
		}
	}
}