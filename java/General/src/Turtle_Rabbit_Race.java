/************************************************
*  Comment: �t���ɶ]			
*  1. �п�J�]�D����(�]�D���� ����10-30):
*  2. �Y�]�D���רS������10-30�����A�ШϥΪ̭��s��J
*  3. �Q�t�@�����ʪ����׬�1-2�A
*  	  �ߤl�@�����D�����׬�0-3
*  4. �C��@�өw�I�A�Y��w�I�W��J1,��l�h�� 0
*  5. �é�Q�t�Ψߤl��F���I�ɡA��ܥX�p�d�ҩҥܤ����ɹL�{�A�çi���Q�t�ΨߤlĹ���T��(�Y����Y��ܥ���T��)

	��X�d�ҡG
	
	----95�~�t���ɶ]----
	�п�J�]�D����(10-30):5
	�Э��s��J�]�D����(10-30):35
	�Э��s��J�]�D����(10-30):20

	���ɹL�{
	�Q�t :0 1 1 0 1 0 1 1 1 1 0 1 1 0 1 1 0 1 1 1
	�ߤl :0 0 1 0 1 0 1 0 1 0 1 0 0 1 1 0 0 1 1 1
	����

	�Q�t :0 1 1 0 1 0 1 1 1 1 0 1 1 0 1 1 0 1 1 0
	�ߤl :0 0 1 0 1 0 1 0 1 0 1 0 0 1 1 0 0 1 1 1
	�ߤl���
*  
*  
*  Author: Han-Hong Wang						
*  Website: http://whhnote.blogspot.com				
*  E-mail: phodra@hotmail.com	
*  Develop Environment-Compiler: J2SE 1.6.0	
*		      -Editor: Eclipse 3.2.0			
*		      -OS: Windows XP SP2			
*  Creation Date: 2006/12/27					
*  Last Update: 2010/07/02						
*************************************************/

import java.util.Scanner;

public class Turtle_Rabbit_Race {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----95�~�t���ɶ]----");
		int RunwayRange=0;
		
		//Get Runway Range
		System.out.print("�п�J�]�D��(10-30): ");
		while (RunwayRange==0) {	
			RunwayRange = scanner.nextInt();
			if ((RunwayRange<10)||(RunwayRange>30)) {
				System.out.print("�Э��s��J�]�D���� (10-30): ");
				RunwayRange=0;
			}
		}
		
		//Run Start
		int rabbitStep[] = new int[RunwayRange+1];	//�ߤl�B�ưO��(0-3)
		int turtleStep[] = new int[RunwayRange+1];	//�Q�t�B�ưO��(1-2)
		int rabStep=0;	//�ߤl�B��(0-3)
		int turStep=0;	//�Q�t�B��(1-2)
		
		while (rabStep<RunwayRange && turStep<RunwayRange) {	
			rabStep += (int)(Math.random()*4);	//�ߤl�o���]���B��
			if (rabStep>RunwayRange) {rabStep=RunwayRange;}
			if (rabStep!=0) {rabbitStep[rabStep-1] = 1;}	//�O���ߤl�o���]����m
			
			turStep += 1+(int)(Math.random()*2);	//�Q�t�o���]���B��
			if (turStep>RunwayRange) {turStep=RunwayRange;}
			turtleStep[turStep-1] = 1;	//�O���Q�t�o���]����m
		}
		
		int i;
		System.out.print("�ߤl : ");
		for (i=0;i<RunwayRange;i++) {
			System.out.print(rabbitStep[i]);
		}

		System.out.println();
		System.out.print("�Q�t : ");
		for (i=0;i<RunwayRange;i++) {
			System.out.print(turtleStep[i]);
		}
		
		//���X�ӭt
		System.out.println();
		if (rabStep > turStep) {
			System.out.println("�ߤl���!!");
		} else {
			if (rabStep == turStep) {
				System.out.println("���襭��!!");
			} else {
				System.out.println("�Q�t���!!");
			}
		}
	}
}