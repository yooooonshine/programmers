import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());

		long count = max - min + 1;
		boolean[] isNotPowNums = new boolean[(int) count]; // 인덱스 i가 min 즉 index + min이 값


		for (long i = 2L; i * i <= max; i++) {
			long powNum = i * i;

			long start = min / powNum;
			long index = 0;
			while (powNum * (start + index) <= max) {
				if (min <= powNum * (start + index)) {
					isNotPowNums[(int) (powNum * (start + index) - min)] = true;
				}
				index++;
			}
		}

		int resultCount = 0;
		for (boolean isNotPowNum : isNotPowNums) {
			if (!isNotPowNum) {
				resultCount++;
			}
		}

		System.out.println(resultCount);
	}
}
