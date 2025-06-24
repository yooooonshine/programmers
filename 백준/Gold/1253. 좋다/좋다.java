import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(br.readLine());
		int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Arrays.sort(nums);

		int count = 0;
		for (int i = 0; i < N; i++) {
			int start = 0;
			int end = N - 1;

			while (start < end) {
				if (nums[start] + nums[end] < nums[i]) {
					start++;
				} else if (nums[start] + nums[end] > nums[i]) {
					end--;
				} else if (nums[start] + nums[end] == nums[i]) {
					if (start != i && end != i) {
						count++;
						break;
					} else if (start == i) {
						start++;
					} else {
						end--;
					}
				}
			}
		}

		System.out.println(count);
	}
}