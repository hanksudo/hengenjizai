import java.util.ArrayList;


public class ArrayListDemo {

	/**
	 * ArrayList Demo
	 * 2008/12/12
	 */
	public static void main(String[] args) {
		// <�A��> �N��غc�XString���O��List - �ѼƤƫ��O (parameterized)
		ArrayList<String> myList = new ArrayList<String>(); // �s��ArrayList����|�غc�bheap�W
		
		// �[�J����
		String s1 = "Java";
		String s2 = "Ruby";
		myList.add(s1);
		myList.add(s2);
		
		// �d�ߤj�p
		int listSize = myList.size();
		System.err.println(listSize);
		
		// �d�߯S�w����
		boolean isIn1 = myList.contains("PHP");
		boolean isIn2 = myList.contains("Java");
		System.err.println(isIn1);
		System.err.println(isIn2);
		
		// �d�߯S�w��������m
		int idx = myList.indexOf("Ruby");
		System.err.println(idx);
		
		// �P�_���X�O�_����
		boolean empty = myList.isEmpty();
		System.err.println(empty);
		
		// �R������
		myList.remove(s1);
		myList.remove(s2);
	}

}
