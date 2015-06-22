package collectionSample;

public class SimpleCollection {
	private Object[] objArr;
	private int index = 0;
	
	public SimpleCollection() {
		objArr = new Object[10]; // �w�]10�Ӫ���Ŷ�
	}
	
	public SimpleCollection(int capacity) {
		objArr = new Object[capacity];
	}
	
	public void add(Object o) {
		objArr[index] = o;
		index++;
	}
	
	public int getLength() {
		return index;
	}
	
	public Object get(int i) {
		return objArr[i];
	}
}
