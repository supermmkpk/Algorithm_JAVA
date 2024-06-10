import java.io.*;
import java.util.*;

/*
 * 산성 용액과 알칼리성 용액이 있다. 각 용액에는 특성값이 존재.
 * 세 용액을 혼합한 용액의 특성값 = 각 용액의 특성값의 합
 * 세 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다. 
 * 서로 다른 세 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내시오.
[입력]
1) 전체 용액의 수 N. (3 <= N <= 5,000)
2)용액의 특성값을 나타내는 N개의 정수. (-1e9 ~ 1e9, 특성값은 모두 다르고, 산성/알칼리성 용액만으로만 입력이 주어지는 경우도 있다)
[출력]
1) 세 용액의 특성값. (오름차순, 여러개: 아무거나)
 */

//NlogN 이분탐색에서 mid로 해도 되지만 left, right로 줘서 3개에 대해서도 할 수 있다
public class BJ_2473_세용액 {
	static final long INF = (long) 1e18;
	
	static int N; //용액 수
	static long[] sol; //특성값(이분탐색은 long으로)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
        sol = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			sol[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(sol); //이분탐색은 sortedArray
		
		//각 용액에 대하여 오른쪽 구간을 이분탐색하고, 최적을 찾아낸다
		//long diff = INF; //0과의 거리
		long minDiff = INF; //0과의 최소 거리
		Tuple minPos = new Tuple();
		for(int i = 0; i < N - 2; i++) {
			long now = i;
			long left = i + 1;
			long right = N - 1;
			
			//이분탐색
			while(left < right) {
				long sum = sol[(int)now] + sol[(int)left] + sol[(int)right];
				long diff = Math.abs(sum - 0);

				//0과의 거리 체크, 최소 거리 갱신
				if(minDiff > diff) {
					minDiff = diff;
					minPos.first = now;
					minPos.second = left;
					minPos.third = right;
				}

				//0과의 거리를 좁혀나갑니다
				if(sum > 0) { //0보다 크니까 더 작은 쪽 보자
					--right; //왼쪽(작은쪽)
				}
				else { //0보다 작으니까 더 큰 쪽 보다;
					++left;
				}
			}
		}
		bw.write(String.valueOf(sol[(int)minPos.first]) + " ");
		bw.write(String.valueOf(sol[(int)minPos.second]) + " ");
		bw.write(String.valueOf(sol[(int)minPos.third]) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Tuple {
		long first, second, third;
		Tuple() {}
		Tuple(long first, long second, long third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}
	}

}
