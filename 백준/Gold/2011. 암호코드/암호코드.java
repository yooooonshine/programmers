import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] v = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int length = v.length;

		long[][] result = new long[length][4];

		if (v[0] == 0) {
			System.out.println(0 + "");
			return;
		}


		if (v[0] == 1) {
			result[0][1]= 1L;
		} else if (v[0] == 2) {
			result[0][2]= 1L;
		} else {
			result[0][0]= 1L;
		}

		for (int i = 1; i < length; i++) {
			if (v[i] == 0) {
				result[i][3] += (result[i - 1][1]) % 1000000;
				result[i][3] += (result[i - 1][2]) % 1000000;
			} else if (v[i] > 2) {
				// 끝이 2보다 큰 경우
				result[i][0] = (result[i - 1][0] + result[i - 1][1] + result[i - 1][2] + result[i - 1][3]) % 1000000;
				result[i][0] += (result[i - 1][1]) % 1000000;

				if (v[i] <= 6) {
					result[i][0] += result[i - 1][2] % 1000000;
				}
			} else {
				// 끝이 2이하인 경우

				if (v[i] == 1) {
					result[i][1] = (result[i - 1][0] + result[i - 1][1] + result[i - 1][2] + result[i - 1][3]) % 1000000;
					result[i][0] += (result[i - 1][1] + result[i - 1][2]) % 1000000;
				} else {
					result[i][2] = (result[i - 1][0] + result[i - 1][1] + result[i - 1][2] + result[i - 1][3]) % 1000000;
					result[i][0] += (result[i - 1][1] + result[i - 1][2]) % 1000000;
				}
			}
		}

		System.out.println((result[length - 1][0] + result[length - 1][1] + result[length - 1][2] + result[length - 1][3]) % 1000000 + "");
	}
}

// 되는 경우를 다 저장하면?