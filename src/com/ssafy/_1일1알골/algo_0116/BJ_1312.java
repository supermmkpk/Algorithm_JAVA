package com.ssafy._1일1알골.algo_0116;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ1312_�Ҽ�
 * </pre>
 * 
 * @author �ں���
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/71855571"></a>
 * 
 * ���: "�¾ҽ��ϴ�!!", 14136KB, 140ms
 */

/*
 * ������(����) A�� ����(�и�) B�� �ִ�. �� ���� �������� ��, �Ҽ��� �Ʒ� N��° �ڸ����� ���Ϸ��� �Ѵ�. 
 * Ex) A=3,B=4, N=1�̶��, A��B=0.75 �̹Ƿ� ��� ���� 7�̴�.

[�Է�]
1) A, B(1 �� A, B �� 100,000), N(1 �� N �� 1,000,000)

[���] 
A��B�� ���� ��, �Ҽ��� �Ʒ� N��° ���� ����Ѵ�.
 */
public class BJ_1312 {

	public static void main(String[] args) throws IOException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
				  
		//a, b, n�� �Է� �޽��ϴ�.
		int a = Integer.parseInt(st.nextToken()); 
		int b = Integer.parseInt(st.nextToken()); 
		int n = Integer.parseInt(st.nextToken());
				 
		//���п����� ������ ���� ���� �����մϴ�.
		int rem = a % b; //���� �������� ��Ÿ���ϴ�.
		int res = 0; //�� �ܰ迡���� ������ ����� ��Ÿ���ϴ�.
		for(int i = 0; i < n; i++) { //n���� �ܰ迡 ���Ͽ�
			rem *= 10; //���������� 10�� ���մϴ�.
			res = rem / b; //������ ����.
			rem = rem % b; //���� �������� Ȯ���մϴ�.
			
			//�������� 0�̶�� ������ ������ �����մϴ�.
			if(rem == 0) {
				break;
			}
		}
				
		System.out.println(res);
			
	}

}
