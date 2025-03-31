import java.util.*;
import java.io.*;

public class Main {
	public final static String P = "+";
	public final static String M = "-";
	public final static String S = " ";

	public static List<String> result = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			int tc = Integer.parseInt(br.readLine());

			String[] nums = new String[tc];
			for (int k = 1; k <= tc; k++) {
				nums[k - 1] = String.valueOf(k);
			}

			String[] operators = new String[tc - 1];
			dfs(nums, operators, 0);

			Collections.sort(result);

			for (String r : result) {
				System.out.println(r);
			}
			System.out.println();

			result.clear();
		}


	}

	public static void dfs(String[] nums, String[] operators, int index) {
		if (index >= operators.length) {
			if (isZero(nums, operators)) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < operators.length; i++) {
					sb.append(nums[i]);
					sb.append(operators[i]);
				}
				sb.append(nums[nums.length - 1]);

				result.add(sb.toString());
				return;
			}
			return;
		}

		operators[index] = P;
		dfs(nums, operators, index + 1);
		operators[index] = M;
		dfs(nums, operators, index + 1);
		operators[index] = S;
		dfs(nums, operators, index + 1);

		return;
	}

	public static boolean isZero(String[] nums, String[] operators) {
		int sum = 0;

		List<String> numL = new ArrayList<>(List.of(nums));
		List<String> opL = new ArrayList<>(List.of(operators));

		for (int i = 0; i < opL.size(); i++) {
			if (opL.get(i).equals(S)) {
				numL.set(i, numL.get(i) + numL.get(i + 1));
				numL.remove(i + 1);
				opL.remove(i);
				i--;
			}
		}

		sum = Integer.parseInt(numL.get(0));
		for (int i = 0; i < opL.size(); i++) {
			if (opL.get(i).equals(P)) {
				sum += Integer.parseInt(numL.get(i + 1));
			} else if (opL.get(i).equals(M)) {
				sum -= Integer.parseInt(numL.get(i + 1));
			}
		}

		if (sum == 0) {
			return true;
		} else {
			return false;
		}
	}
}

// 각 tc는 1~tc이다.

// 이 수식에 +, -, 공백으로 수식을 만든다.

// 10 * 3^8 = 81 * 81 * 10
// 모든 케이스 다 찾아도 무관하다.
// 각 위치에 모든 연산을 넣어본다.
// 각 위치는 연산자가 짝수 위치.
// string으로 갖고 있는다.
//
// 숫자가 N이면
// 연산자는 N - 1개
// N - 1개를 채운다.
// 채우면 이에 따라 0인지 체크

// dfs
// 길이보다 크면 zero 체크해서 count++
// 아니면 특정 연산자 넣고 다음

// 첫문자를 sb에 넣어둔다.
// 공백이면 다음 숫자와 합친다.
// 덧셈이면 둘 다 int화 해서 더하고 다시 sb 만들어 넣는다.