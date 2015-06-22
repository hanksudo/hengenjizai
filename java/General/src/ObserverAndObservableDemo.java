import java.util.Observable;
import java.util.Observer;

/**
 * @comment: �[���->�i�[��� �d��
 * @author Han-Hong Wang
 * @date 2007/5/10
 */
public class ObserverAndObservableDemo extends Object {
	MyView view;
	MyModel model;

	public ObserverAndObservableDemo() {
		view = new MyView();	// �إߤ@�[���
		
		// �إ߳Q�[��̨å[�J�[���
		model = new MyModel();
		model.addObserver(view);
	}

	public static void main(String[] arg) {
		new ObserverAndObservableDemo().demo();
	}

	public void demo() {
		model.changeSomething();
	}

	/** �[��� */
	class MyView implements Observer {
		// ���o�q����
		public void update(Observable obs, Object x) {
			System.out.println("update(" + obs + "," + x + ");");
		}
	}

	/** �Q�[��� */
	class MyModel extends Observable {
		public void changeSomething() {
			// �q���[����ܧ�
			setChanged();
			notifyObservers();
		}
	}
}
