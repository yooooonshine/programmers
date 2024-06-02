import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		Integer[] nums = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).toArray(Integer[]::new);
		// 정렬

		Arrays.sort(nums);

		int count = 0;
		int start = 0;
		int end = N - 1;
		while (start < end) {
			if (nums[start] + nums[end] < M) {
				start++;
			} else if (nums[start] + nums[end] > M) {
				end--;
			} else {
				count++;
				start++;
				end--;
			}
		}

		System.out.println(count);
	}
}