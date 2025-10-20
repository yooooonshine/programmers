import java.util.*;
import java.io.*;

public class Main {

	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 꽃의 수

		PriorityQueue<Calender> pq = new PriorityQueue<>();
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());

			pq.add(new Calender(sm, sd, em, ed));
		}

		int nm = 3;
		int nd = 1;

		int count = 0;

		while (!pq.isEmpty()) {

			// 목표 달성하면 끝
			if (nm >= 12) {
				break;
			}

			int maxM = nm;
			int maxD = nd;

			boolean isChange = false;
			while (true) {
				if (pq.isEmpty()) {
					break;
				}

				Calender tmp = pq.peek();
				int tmpSm = tmp.sm;
				int tmpSd = tmp.sd;
				int tmpEm = tmp.em;
				int tmpEd = tmp.ed;

				// 범위넘어가면 끝
				if (nm == tmpSm) {
					if (nd < tmpSd) {
						break;
					}
				} else if (nm < tmpSm) {
					break;
				}

				pq.poll();
				// 가장 큰 em,ed구하기
				if (maxM == tmpEm) {
					if (maxD < tmpEd) {
						maxD = tmpEd;
						isChange = true;
					}
				} else if (maxM < tmpEm) {
					maxM = tmpEm;
					maxD = tmpEd;
					isChange = true;
				}
			}

			if (!isChange) {
				count = 0;
				break;
			} else {
				nm = maxM;
				nd = maxD;
				count++;
			}
		}
		
		if (nm < 12) {
			count = 0;
		}

		System.out.println(count);
	}

	public static class Calender implements Comparable<Calender> {
		int sm;
		int sd;
		int em;
		int ed;

		public Calender(int sm, int sd, int em, int ed) {
			this.sm = sm;
			this.sd = sd;
			this.em = em;
			this.ed = ed;
		}

		@Override
		public int compareTo(Calender c) {
			if (this.sm == c.sm) {
				return this.sd - c.sd;
			} else {
				return this.sm - c.sm;
			}
		}
	}
}

// 작은 정원
// N개의 꽃, 같은해 ㅣ고, 같은해진다.
// 5월 8일~ 6월 13일(13일은 꽃 없음)
// 3월 1일부터, 11월 30일까지 매일 한 가지 이상 피어있게
// 정원에 심는 꽃들의 수를 가장 적게
// 꽃 최소 개수

// 시작순으로 정렬한다.
// 현재 끝보다 작은  중에서 최대를 고른다.
// count ++, 날짜 업데이트

// 꽃 지는 날 잘 고려해야 한다.
