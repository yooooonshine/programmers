
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			boolean result = true;
			Num start = new Num(false);
			String[] arr = new String[N];
			for (int n = 0; n < N; n++) {
				arr[n] = br.readLine();
			}
			Arrays.sort(arr, (a, b) -> {
				return a.length() - b.length();
			});
			for (int n = 0; n < N; n++) {
				int[] pn = Arrays.stream(String.valueOf(arr[n]).split("")).mapToInt(Integer::parseInt).toArray();

				boolean tmpR = start.add(pn, 0);
				if (!tmpR) {
					result = false;
					break;
				}
			}

			if (result) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	public static class Num {
		Num[] arr = new Num[10];

		boolean exist;

		public Num(boolean exist) {
			this.exist = exist;
		}

		public boolean add(int[] pn, int index) {
			if (index  >= pn.length) {
				if (this.exist) {
					return false;
				}
				this.exist = true;
				return true;
			}

			if (this.exist) {
				return false;
			}

			if (arr[pn[index]] == null) {
				arr[pn[index]] = new Num(false);
			}

			return arr[pn[index]].add(pn, index + 1);
		}
	}
}