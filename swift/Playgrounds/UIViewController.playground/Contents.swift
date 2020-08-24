//: A UIKit based Playground for presenting user interface
  
import UIKit
import PlaygroundSupport

final class MyViewController: UIViewController {
    override func loadView() {
        let view = ButtonView()
        view.button.addTarget(self, action: #selector(buttonDidTap), for: .touchDown)
        self.view = view
        
    }
    
    @objc
    private func buttonDidTap() {
        let controller = DetailViewController()
        present(controller, animated: true, completion: nil)
    }
    
}

class ButtonView: UIView {
    let button = UIButton(type: .custom)
    override init(frame: CGRect) {
        super.init(frame: frame)
        createSubviews()
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        createSubviews()
    }

    func createSubviews() {
        self.backgroundColor = .green
        
        button.frame = CGRect(x: 0, y: 0, width: 200, height: 20)
        button.setTitle("Tap to go to Detail View", for: .normal)
        button.setTitleColor(.black, for: .normal)
        button.isUserInteractionEnabled = true
        self.addSubview(button)
        
        button.translatesAutoresizingMaskIntoConstraints = false
        button.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        button.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
    }
    

}

final class DetailViewController: UIViewController {
    
    override func loadView() {
        let view = DetailUIView()
        view.button.addTarget(self, action: #selector(buttonDidTap), for: .touchDown)
        self.view = view
    }
    
    @objc
    private func buttonDidTap() {
        dismiss(animated: true, completion: nil)
    }
    
}

class DetailUIView: UIView {
    let button = UIButton(type: .custom)

    override init(frame: CGRect) {
        super.init(frame: frame)
        createSubviews()
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        createSubviews()
    }

    func createSubviews() {
        self.backgroundColor = .orange
        button.frame = CGRect(x: 0, y: 0, width: 200, height: 20)
        button.setTitle("Tap to dismiss view", for: .normal)
        button.setTitleColor(.black, for: .normal)
        button.isUserInteractionEnabled = true
        
        self.addSubview(button)
        
        button.translatesAutoresizingMaskIntoConstraints = false
        button.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        button.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
        
        self.addSubview(button)
    }
}

// set the view and indefiniteexecution
PlaygroundPage.current.needsIndefiniteExecution = true
PlaygroundPage.current.liveView = MyViewController()
