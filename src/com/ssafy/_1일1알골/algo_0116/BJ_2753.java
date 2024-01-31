package com.ssafy._1일1알골.algo_0116;

import java.util.*;

/**
 * <pre>
 * BJ2753_����
 * </pre>
 * 
 * @author �ں���
 * @since JDK17
 *
 * ����: ������ 4�� ��� && (!(100�� ���) || 400�� ���)
 * ������ �־����� ��, �����̸� 1, �ƴϸ� 0�� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
1) ���� (1<= ���� <= 4000)

[���]
ù° �ٿ� �����̸� 1, �ƴϸ� 0
 */
public class BJ_2753 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt(); //������ �Է� �޽��ϴ�.
		
		//���� ���ǿ� ���� ���� ���θ� Ȯ���մϴ�.
		if((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
			System.out.println("1"); //�����̸� 1�� ����մϴ�.
		}
		else {
			System.out.println("0"); //������ �ƴϸ� 0�� ����մϴ�.
		}
	}

}
