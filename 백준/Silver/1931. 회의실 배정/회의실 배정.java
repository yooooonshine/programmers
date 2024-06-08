import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		List<Reference> references = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long start = Long.parseLong(st.nextToken());
			long end = Long.parseLong(st.nextToken());

			Reference reference = new Reference(start, end);

			references.add(reference);
		}
		Collections.sort(references, (o1, o2) -> {
			long start1 = o1.getStart();
			long start2 = o2.getStart();
			long end1 = o1.getEnd();
			long end2 = o2.getEnd();

			if (end1 - end2 > 0L) {
				return 1;
			} else if (end1 - end2 == 0L) {
				if (start1 > start2) {
					return 1;
				} else if (start1 == start2) {
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}

		});

		int count = 0;
		long finalEnd = 0;
		for (Reference r : references) {
			long start = r.getStart();
			long end = r.getEnd();

			if (finalEnd <= start) {
				count++;
				finalEnd = end;
			}
		}
		System.out.println(count);
	}
}

class Reference {
	public long start;
	public long end;

	public Reference(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public long getStart() {
		return start;
	}

	public long getEnd() {
		return end;
	}
}