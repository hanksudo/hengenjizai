import java.util.Random;


public class CreateRandomNumbers {

	public static void main(String[] args) {
		//----- method 1
		for (int i=0; i<=10; i++) {
			//Math.random()會自動產生0.0 ~ 0.999999...之數字
			System.out.println(1+(int)(Math.random()*10));
		}
		
		Random random = new Random();
		//----- method 2
		for (int i=0; i<=10; i++) {
			//random.nextInt()自動產生0~9之整數
			System.out.println(random.nextInt(10));
		}
	}

}
