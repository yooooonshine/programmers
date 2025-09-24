
import java.util.*;
import java.io.*;

public class Main {

	public static int[] As;
	public static int A;
	public static int[] Bs;
	public static int B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 값 초화
		A = Integer.parseInt(br.readLine());
		As = new int[A + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= A; n++) {
			As[n] = Integer.parseInt(st.nextToken());
		}

		B = Integer.parseInt(br.readLine());
		Bs = new int[B + 1];
		st = new StringTokenizer(br.readLine());
		for (int m = 1; m <= B; m++) {
			Bs[m] = Integer.parseInt(st.nextToken());
		}

		Queue<Integer> result = new LinkedList<>();

		int aI = 1;
		int bI = 1;

		while (true) {
			MaxIndex maxIndex = find(aI, bI);

			int v = maxIndex.v;
			if (v == 0) {
				break;
			}
			result.add(v);

			aI = maxIndex.aI + 1;
			bI = maxIndex.bI + 1;
		}

		if (result.size() == 0) {
			System.out.println(0);
		} else {
			System.out.println(result.size());
			while (!result.isEmpty()) {
				System.out.print(result.poll() + " ");
			}
		}
	}

	public static MaxIndex find(int aStart, int bStart) {
		int[] aIndexes = new int[101];
		int[] bIndexes = new int[101];

		for (int i = aStart; i <= A; i++) {
			if (aIndexes[As[i]] == 0) {
				aIndexes[As[i]] = i;
			}
		}

		for (int i = bStart; i <= B; i++) {
			if (bIndexes[Bs[i]] == 0) {
				bIndexes[Bs[i]] = i;
			}
		}

		for (int i = 100; i >= 1; i--) {
			if (aIndexes[i] != 0 && bIndexes[i] != 0) {
				return new MaxIndex(i, aIndexes[i], bIndexes[i]);
			}
		}

		return new MaxIndex(0, 0, 0);
	}

	public static class MaxIndex {
		int v;
		int aI;
		int bI;

		public MaxIndex(int v, int aI, int bI) {
			this.v = v;
			this.aI = aI;
			this.bI = bI;
		}
	}
}

// 배열 중 가장 크고 같은 갚을 앞에서부터 찾으면 된다.
// 재귀
// 없으면 그게 답


// 모든 공통 수열을 모두 찾아야 하나?

// 모두 찾는다.
// PQ에 넣어 뽑는다.
// 출력한다.

// A 기준 모든 시작점에서 부분 수열을 구한다.
// A 시점점과 일치하는 B를 찾으면 둘다 ++
// 일치하지 안

// 공통 수열을 어떻게 찾지?