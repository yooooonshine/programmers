import java.util.*;
import java.io.*;

public class Main {
	public static int[] isl;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// isl 초기화
		isl = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			isl[i] = i;
		}

		StringTokenizer st;
		for (int i = 1; i <= N - 2; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			union(a, b);
		}

		int result1 = find(1);

		for (int i = 2; i <= N; i++) {
			int tmp = find(i);

			if (result1 != tmp) {
				System.out.println(result1 + " " + tmp);
				return;
			}
		}
	}

	public static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);

		if (ar < br) {
			isl[br] = ar;
		} else if (ar > br) {
			isl[ar] = br;
		}
	}

	public static int find(int a) {
		if (isl[a] == a) {
			return a;
		}

		return isl[a] = find(isl[a]);
	}
}


// N번의 섬
// N - 1개의 다리
//  N
// N - 2 개 무너지지 않은 다라

// N은 300000
// NlogN까지는 가능

// 결국 어떤 섬이 이어지지 않았는지만 찾으면 되는 거 아닐까?
// 아 완전히 두개로 분리된 경우도 있구나

// 그럼 유니온 파인드를 두번하면 되지 않을까?

// 두 유니온 파인드의 결과를

// 케이스가 동떨어진경우와
// 두 집합으로 분리된 경우가 있겠다

// 일단 모든 숫자에 대해 유니온파인드를 한다.
// 다른 두 숫자를 찾는다.


// 유니온 파인드는 다를 수 있다.
// 모든 node에 대해 find를 한번씩 해야 하나?

// 1번부터 찾는다면 무조건 낮은 숫자를 기준으로하며 되긴하겠다.
// 마지막에 모두 find해서 체크해야겠네.
