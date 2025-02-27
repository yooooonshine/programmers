
import java.util.*;
import java.io.*;

public class Main {
	public static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int G = Integer.parseInt(br.readLine()); // 게이트 수
		int P = Integer.parseInt(br.readLine()); // 비행기 수

		int[] gs = new int[P + 1];

		for (int i = 1; i <= P; i++) {
			gs[i] = Integer.parseInt(br.readLine()); // i번째 비행기는 1번부터 gs중 하나
		}

		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}

		// 비행기 수 만큼
		int count = 0;
		for (int i = 1; i <= P; i++) {
			int tmp = find(gs[i]);

			if (tmp == 0) {
				break;
			}

			union(tmp, tmp - 1);

			count++;
		}

		System.out.println(count);
	}

	public static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);

		if (ar < br) {
			parent[br] = ar;
		} else if (ar > br) {
			parent[ar] = br;
		}
	}

	public static int find(int a) {
		if (a == parent[a]) {
			return a;
		}

		return parent[a] = find(parent[a]);
	}
}
