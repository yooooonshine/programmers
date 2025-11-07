import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<Integer> rooms = new ArrayList<>();

		List<Time> times = new ArrayList<>();
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			times.add(new Time(s, e));
		}
		Collections.sort(times);

		for (Time t : times) {
			boolean exist = false;
			int index = 0;
			for (int i = 0; i < rooms.size(); i++) {
				if (rooms.get(i) <= t.s) {
					index = i;
					exist = true;
					break;
				}
			}

			if (exist) {
				rooms.set(index, t.e);
			} else {
				rooms.add(t.e);
			}
		}

		System.out.println(rooms.size());
	}

	public static class Time implements Comparable<Time> {
		int s;
		int e;

		public Time(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Time t) {
			return this.s - t.s;
		}
	}
}