import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());



		List<SE> tmp = new ArrayList<>();
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());

			tmp.add(new SE(P, Q));
		}
		Collections.sort(tmp);

		int index = 0;
		int[] counts = new int[N + 1];
		// 하나씩 처리

		PriorityQueue<CanUse> canUses = new PriorityQueue<>();
		PriorityQueue<Node> cantUses = new PriorityQueue<>();
		for (SE se : tmp) {
			// 사용 불가에서 사용 가능한거 업데이트
			while (!cantUses.isEmpty() && cantUses.peek().e <= se.s) {
				Node n = cantUses.poll();
				canUses.add(new CanUse(n.index));
			}

			// 사용 가능한 것에서 있으면 업데이트 하고 사용불가에 넣기
			if (!canUses.isEmpty()) {
				CanUse cu = canUses.poll();
				counts[cu.index]++;
				cantUses.add(new Node(se.e, cu.index));
			} else {
				// 사용 가능한 것이 없으면 새로 사용 불가에 넎기
				cantUses.add(new Node(se.e, index));
				counts[index]++;
				index++;
			}
		}

		System.out.println(index);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < index; i++) {
			bw.write(counts[i] + " ");
		}
		bw.flush();
	}

	public static class SE implements Comparable<SE> {
		int s;
		int e;

		public SE(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(SE n) {
			return this.s - n.s;
		}
	}

	public static class Node implements Comparable<Node> {
		int e;

		int index;

		public Node(int e, int index) {
			this.e = e;
			this.index = index;

		}

		@Override
		public int compareTo(Node n) {
			return this.e - n.e;
		}
	}

	public static class CanUse implements Comparable<CanUse> {
		int index;

		public CanUse(int index) {
			this.index = index;
		}

		@Override
		public int compareTo(CanUse n) {
			return this.index - n.index;
		}
	}
}

// 컴퓨터 수 부족
// 모든 사람이 정해진 시간에 싸지방 ㅣ용
// 컴퓨터 1번부터, 비어있는 자리 중 가장 작은 자리에

// 컴퓨터의 최소 개수와 자리 별로 몇 명의 사람이 사용하는지


// 사람 수 N
// N 개의 줄에 시작 시간 P 종료시간 Q

// N 번 하면 될듯?

// PQ 사용

// 시작순 정렬
// PQ에 제일 빨리 끝나는 시간보다, 시작시간이 작으면
// 새로 저장
// 아니면 업데이트

// 새로 넣을 때는 순번하고
// 횟수 저장

// 사용 가능한 목록
// 사용 불가한 목록
// 시작시간 올 떄마다
// 사용 불가한 목록에서 사용가능한 것을 모두 빼고
// 사용가능한 목록에 넣기
