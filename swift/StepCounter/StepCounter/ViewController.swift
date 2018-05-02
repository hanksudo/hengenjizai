//
//  ViewController.swift
//  StepCounter
//
//  Created by Hank Wang on 10/22/15.
//  Copyright © 2015 hanksudo. All rights reserved.
//

import UIKit
import CoreMotion

class ViewController: UIViewController {
    
    @IBOutlet weak var activityState: UILabel!
    @IBOutlet weak var steps: UILabel!
    @IBOutlet weak var distance: UILabel!
    
    var days:[String] = []
    var stepsTaken:[Int] = []
    
    let activityManager = CMMotionActivityManager()
    let pedoMeter = CMPedometer()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        if (CMMotionActivityManager.isActivityAvailable()) {
            self.activityManager.startActivityUpdates(to: OperationQueue.main) { data in
                if let data = data {
                    DispatchQueue.main.async() {
                        if (data.stationary) {
                            self.activityState.text = "Stationary"
                        } else if (data.walking) {
                            self.activityState.text = "Walking"
                        } else if (data.running) {
                            self.activityState.text = "Running"
                        } else if (data.automotive) {
                            self.activityState.text = "Automotive"
                        } else if (data.cycling) {
                            self.activityState.text = "Cycling"
                        } else {
                            self.activityState.text = "Unknown"
                        }
                        print(self.activityState.text!)
                    }
                }
            }
        }
        if (CMPedometer.isStepCountingAvailable()) {
            // 7 days ago
            let fromDate = Date(timeIntervalSinceNow: -60 * 60 * 24 * 7)
            self.pedoMeter.queryPedometerData(from: fromDate, to: Date()) { (data, error) in
                guard let data = data else { return }
                DispatchQueue.main.async {
                    self.steps.text = "\(data.numberOfSteps)"
                }
            }
            
            var cal = Calendar.current
            cal.timeZone = NSTimeZone.system
            let midnightOfToday = cal.dateComponents([.year, .month, .day], from: Date())
            
            self.pedoMeter.startUpdates(from: cal.date(from: midnightOfToday)!) { (data, error) in
                guard let data = data else { return }
                DispatchQueue.main.async {
                    self.steps.text = "\(data.numberOfSteps)"
                    self.distance.text = "\(data.distance!)"
                }
            }
        }
    }
}

