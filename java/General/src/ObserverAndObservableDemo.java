import java.util.Observable;
import java.util.Observer;

/**
 * @comment: 觀察者->可觀察者 範例
 * @author Han-Hong Wang
 * @date 2007/5/10
 */
public class ObserverAndObservableDemo extends Object {
	MyView view;
	MyModel model;

	public ObserverAndObservableDemo() {
		view = new MyView();	// 建立一觀察者
		
		// 建立被觀察者並加入觀察者
		model = new MyModel();
		model.addObserver(view);
	}

	public static void main(String[] arg) {
		new ObserverAndObservableDemo().demo();
	}

	public void demo() {
		model.changeSomething();
	}

	/** 觀察者 */
	class MyView implements Observer {
		// 取得通知時
		public void update(Observable obs, Object x) {
			System.out.println("update(" + obs + "," + x + ");");
		}
	}

	/** 被觀察者 */
	class MyModel extends Observable {
		public void changeSomething() {
			// 通知觀察者變更
			setChanged();
			notifyObservers();
		}
	}
}
