import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Double N = Double.parseDouble(st.nextToken());
		Double M = Double.parseDouble(st.nextToken());
		
		Double sort = N * 7d / 100d;
		Double water = N - sort;
		
		double result = 100d * sort / (M + water + sort);
		result = Math.floor(result * 100) /100.0;
		System.out.println(String.format("%.2f",result));
	}
}

