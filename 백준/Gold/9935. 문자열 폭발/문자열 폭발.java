import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split("");
		String[] boom = br.readLine().split("");

		Stack<String> myS = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			myS.add(arr[i]);

			if (myS.size() >= boom.length) {
				boolean isBoom = true;
				for (int j = 0; j < boom.length; j++) {
					if (!myS.get(myS.size() - boom.length + j).equals(boom[j])) {
						isBoom = false;
						break;
					}
				}

				if (isBoom) {
					for (int j = 0; j < boom.length; j++) {
						myS.pop();
					}
				}
			}
		}

		if (myS.isEmpty()) {
			System.out.println("FRULA");
		}

		StringBuilder sb = new StringBuilder();
		while (!myS.isEmpty()) {
			String c = myS.pop();
			sb.append(c);
		}
		System.out.println(sb.reverse());

	}

}
