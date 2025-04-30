import java.util.*;
import java.io.*;

public class Main {

	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		List<Pillar> ps = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			ps.add(new Pillar(l, h));
		}

		Collections.sort(ps);

		int rl = ps.get(0).l;
		int ll = ps.get(0).l;

		int result = ps.get(0).h;

		for (int i = 1; i < ps.size(); i++) {
			int l = ps.get(i).l;
			int h = ps.get(i).h;

			if (l < ll) {
				result += (ll - l) * h;
				ll = l;
			} else if (l > rl) {
				result += (l - rl) * h;
				rl = l;
			}
		}

		System.out.println(result);
	}

	public static class Pillar implements Comparable<Pillar> {
		int l;
		int h;

		public Pillar(int l, int h) {
			this.l = l;
			this.h = h;
		}

		@Override
		public int compareTo(Pillar p) {
			return p.h - this.h;
		}
	}
}