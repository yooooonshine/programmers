
import java.util.*;
import java.io.*;

public class Main {

	public static int[][] adj = new int[52][52];
	public static List<Node> result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Set<String> set = new HashSet<>();

		for (int n = 1; n <= N; n++) {
			String tmpS = br.readLine();

			if (set.contains(tmpS)) {
				continue;
			}
			set.add(tmpS);

			String[] tmp = tmpS.split(" => ");
			String s = tmp[0];
			String e = tmp[1];

			if (s.equals(e)) {
				continue;
			}

			int intS = StringToInt(s);
			int intE = StringToInt(e);

			adj[intS][intE] = 1;
		}


		result = new ArrayList<>();

		for (int m = 0; m < 52; m++) {
			for (int s = 0; s < 52; s++) {
				for (int e = 0; e < 52; e++) {
					if (adj[s][m] == 1 && adj[m][e] == 1) {
						adj[s][e] = 1;
					}
				}
			}
		}

		List<String> output = new ArrayList<>();

		for (int i = 0; i < 52; i++) {
			for (int j = 0; j < 52; j++) {
				if (i == j) continue;
				if (adj[i][j] == 1) {
					output.add(intToString(i) + " => " + intToString(j));
				}
			}
		}

		Collections.sort(output);

		System.out.println(output.size());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (String line : output) {
			bw.write(line);
			bw.newLine();
		}
		bw.flush();
	}


	public static class Node implements Comparable<Node> {
		int s;
		int e;

		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Node n) {
			int resultS = compare(this.s, n.s);
			if (resultS == 0) {
				return compare(this.e, n.e);
			} else {
				return resultS;
			}
		}
	}

	public static int compare(int a, int b) {
		if (a < 26 && b < 26) {
			return a - b;
		} else if (a < 26 && b >= 26) {
			return 1;
		} else if (a >= 26 && b < 26) {
			return -1;
		} else {
			return a - b;
		}
	}

	public static int StringToInt(String s) {
		char tmp = s.charAt(0);
		if (Character.isUpperCase(tmp)) {
			return tmp - 'A'; // 0~25
		} else {
			return tmp - 'a' + 26; // 26~51
		}
	}

	public static String intToString(int i) {
		if (0 <= i && i < 26) {
			return String.valueOf((char)(i + 'A')); // A~Z
		} else {
			return String.valueOf((char)(i - 26 + 'a')); // a~z
		}
	}
}