
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Set<Integer> set = new HashSet<>();

		for (int i = 1111; i <= 9999; i++) {
			int[] nums = new int[4];
			nums[0] = i / 1000;
			nums[1] = (i / 100) % 10;
			nums[2] = (i / 10) % 10;
			nums[3] = i % 10;

			if (nums[0] == 0 || nums[1] == 0 || nums[2] == 0 || nums[3] == 0) {
				continue; // 0이 포함된 경우는 제외
			}

			int result = calc(nums);
			set.add(result);
		}

		int[] timeNums = new int[set.size()];
		int index = 0;
		for (int i : set) {
			timeNums[index] = i;
			index++;
		}

		Arrays.sort(timeNums);

		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int target = calc(tmp);

		for (int i = 0; i < timeNums.length; i++) {
			if (target == timeNums[i]) {
				System.out.println(i + 1);
				break;
			}
		}
	}

	public static int calc(int[] nums) {
		int[] result = new int[4];

		result[0] = nums[0] * 1000 + nums[1] * 100 + nums[2] * 10 + nums[3];
		result[1] = nums[1] * 1000 + nums[2] * 100 + nums[3] * 10 + nums[0];
		result[2] = nums[2] * 1000 + nums[3] * 100 + nums[0] * 10 + nums[1];
		result[3] = nums[3] * 1000 + nums[0] * 100 + nums[1] * 10 + nums[2];

		Arrays.sort(result);

		return result[0];
	}
}

// 카드 네 모서리 1~9, 중복 가능
// 시계방향으로 읽었을 때 가장 작은 수 = 시계수
// 시계수 계산, 모든 시계수 중 몇 번째로 작은 시계수인지

// 시계수를 모두 구해야 돼??..

// 구하면되지
// 1111~9999의 시계수

// Set으로 모으고, 배열로 만들고 정렬,

// 시계수 함수