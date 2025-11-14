import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 트리 크기 구하기

		PriorityQueue<Node> pq = new PriorityQueue<>();

		int[] arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
			pq.add(new Node(n, arr[n]));
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int M = Integer.parseInt(br.readLine());
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());

			int type = Integer.parseInt(st.nextToken());
			if (type == 2) {
				// 출력하기
				Node now = pq.peek();
				while (now.v != arr[now.i]) {
					pq.poll();
					now = pq.peek();
				}

				bw.write(now.i + "\n");

			} else if (type == 1) {
				int index = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				// index 값을 v로 바꾸기
				arr[index] = v;
				pq.add(new Node(index, v));
			}
		}

		bw.flush();
	}

	public static class Node implements Comparable<Node> {
		int i;
		int v;

		public Node(int i, int v) {
			this.i = i;
			this.v = v;
		}

		@Override
		public int compareTo(Node n) {
			if (this.v != n.v) {
				return this.v - n.v;
			} else {
				return this.i - n.i;
			}
		}
	}
}


// pq 사용,
// 배열 저장
// 변경되면 pq에 새로 저장,
// pq값과, 배열 값 다르면 제거


// 길이 N인 수열 A1~An

//  1 i v =? Ai를 v로 변경
// 수열에서 크기가 가장 작은 값의 인덱스


// 트리 세그멘테이션
//

// 최대 트리
// 세그멘테이
