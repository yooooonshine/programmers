import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] myStrs = br.readLine().split("");

		List<String> expressions = new ArrayList<>();
		String expression = "";
		for (int i = 0; i < myStrs.length; i++) {
			if (Objects.equals(myStrs[i], "-")) {
				expressions.add(expression);
				expression = "";
				expressions.add("-");
			} else if (Objects.equals(myStrs[i], "+")) {
				expressions.add(expression);
				expression = "";
				expressions.add("+");
			} else {
				expression += myStrs[i];
			}
		}
		expressions.add(expression);

		int sum = Integer.parseInt(expressions.get(0));

		int partialSum = 0;

		for (int i = expressions.size() - 1; i >= 1; i--) {
			if (i == 1 && Objects.equals(expressions.get(i), "+")) {
				sum += partialSum;
				break;
			}

			if (Objects.equals(expressions.get(i), "-")) {
				sum -= partialSum;
				partialSum = 0;
			} else if (Objects.equals(expressions.get(i), "+")) {
				continue;
			} else {
				partialSum += Integer.parseInt(expressions.get(i));
			}
		}

		System.out.println(sum);
	}
}