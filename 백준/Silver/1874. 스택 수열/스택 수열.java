import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		List<String> results = new ArrayList<>();
		Stack<Integer> myStack = new Stack<>();
		int point = 0;
		for (int i = 1; i <= n; i++) {
			myStack.push(i);
			results.add("+");
			while (point < n && !myStack.empty() && A[point] == myStack.peek()) {
				myStack.pop();
				results.add("-");
				point++;
			}
		}

		if (myStack.empty()) {
			for (String result : results) {
				System.out.println(result);
			}
		} else {
			System.out.println("NO");
		}
	}
}
