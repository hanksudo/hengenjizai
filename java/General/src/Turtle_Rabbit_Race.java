/************************************************
*  Comment: 龜兔賽跑			
*  1. 請輸入跑道長度(跑道長度 介於10-30):
*  2. 若跑道長度沒有介於10-30之間，請使用者重新輸入
*  3. 烏龜一次走動的長度為1-2，
*  	  兔子一次跳躍的長度為0-3
*  4. 每到一個定點，即於定點上填入1,其餘則為 0
*  5. 並於烏龜或兔子到達終點時，顯示出如範例所示之比賽過程，並告知烏龜或兔子贏的訊息(若平手即顯示平手訊息)

	輸出範例：
	
	----95年龜兔賽跑----
	請輸入跑道長度(10-30):5
	請重新輸入跑道長度(10-30):35
	請重新輸入跑道長度(10-30):20

	比賽過程
	烏龜 :0 1 1 0 1 0 1 1 1 1 0 1 1 0 1 1 0 1 1 1
	兔子 :0 0 1 0 1 0 1 0 1 0 1 0 0 1 1 0 0 1 1 1
	平手

	烏龜 :0 1 1 0 1 0 1 1 1 1 0 1 1 0 1 1 0 1 1 0
	兔子 :0 0 1 0 1 0 1 0 1 0 1 0 0 1 1 0 0 1 1 1
	兔子獲勝
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
		System.out.println("----95年龜兔賽跑----");
		int RunwayRange=0;
		
		//Get Runway Range
		System.out.print("請輸入跑道長(10-30): ");
		while (RunwayRange==0) {	
			RunwayRange = scanner.nextInt();
			if ((RunwayRange<10)||(RunwayRange>30)) {
				System.out.print("請重新輸入跑道長度 (10-30): ");
				RunwayRange=0;
			}
		}
		
		//Run Start
		int rabbitStep[] = new int[RunwayRange+1];	//兔子步數記錄(0-3)
		int turtleStep[] = new int[RunwayRange+1];	//烏龜步數記錄(1-2)
		int rabStep=0;	//兔子步數(0-3)
		int turStep=0;	//烏龜步數(1-2)
		
		while (rabStep<RunwayRange && turStep<RunwayRange) {	
			rabStep += (int)(Math.random()*4);	//兔子這次跑的步數
			if (rabStep>RunwayRange) {rabStep=RunwayRange;}
			if (rabStep!=0) {rabbitStep[rabStep-1] = 1;}	//記錄兔子這次跑的位置
			
			turStep += 1+(int)(Math.random()*2);	//烏龜這次跑的步數
			if (turStep>RunwayRange) {turStep=RunwayRange;}
			turtleStep[turStep-1] = 1;	//記錄烏龜這次跑的位置
		}
		
		int i;
		System.out.print("兔子 : ");
		for (i=0;i<RunwayRange;i++) {
			System.out.print(rabbitStep[i]);
		}

		System.out.println();
		System.out.print("烏龜 : ");
		for (i=0;i<RunwayRange;i++) {
			System.out.print(turtleStep[i]);
		}
		
		//分出勝負
		System.out.println();
		if (rabStep > turStep) {
			System.out.println("兔子獲勝!!");
		} else {
			if (rabStep == turStep) {
				System.out.println("雙方平手!!");
			} else {
				System.out.println("烏龜獲勝!!");
			}
		}
	}
}