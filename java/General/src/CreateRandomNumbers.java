import java.util.Random;


public class CreateRandomNumbers {

	public static void main(String[] args) {
		//----- method 1
		for (int i=0; i<=10; i++) {
			//Math.random()�|�۰ʲ���0.0 ~ 0.999999...���Ʀr
			System.out.println(1+(int)(Math.random()*10));
		}
		
		Random random = new Random();
		//----- method 2
		for (int i=0; i<=10; i++) {
			//random.nextInt()�۰ʲ���0~9�����
			System.out.println(random.nextInt(10));
		}
	}

}
