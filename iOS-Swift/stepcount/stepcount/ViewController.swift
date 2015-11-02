//
//  ViewController.swift
//  stepcount
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
        
        let cal = NSCalendar.currentCalendar()
        cal.timeZone = NSTimeZone.systemTimeZone()
        
        let comps = cal.components([.Year, .Month, .Day, .Hour, .Minute, .Second], fromDate: NSDate())
        comps.hour = 0
        comps.minute = 0
        comps.second = 0
        
        let midnightOfToday = cal.dateFromComponents(comps)!
        
        if (CMMotionActivityManager.isActivityAvailable()) {
            self.activityManager.startActivityUpdatesToQueue(NSOperationQueue.mainQueue()) { data in
                if let data = data {
                    dispatch_async(dispatch_get_main_queue()) {
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
            let fromDate = NSDate(timeIntervalSinceNow: -86400 * 7)
            self.pedoMeter.queryPedometerDataFromDate(fromDate, toDate: NSDate()) { data, error in
                print(data)
                dispatch_async(dispatch_get_main_queue(), { () -> Void in
                    if (error == nil) {
                        self.steps.text = "\(data!.numberOfSteps)"
                    }
                })
            }
            
            self.pedoMeter.startPedometerUpdatesFromDate(midnightOfToday) { data, error in
                print(data)
                dispatch_async(dispatch_get_main_queue(), { () -> Void in
                    if (error == nil) {
                        self.steps.text = "\(data!.numberOfSteps)"
                        self.distance.text = "\(data!.distance!)"
                    }
                })
            }
        }
        
//        if(CMPedometer.isStepCountingAvailable()){
//            let fromDate = NSDate(timeIntervalSinceNow: -86400 * 7)
//            self.pedoMeter.queryPedometerDataFromDate(fromDate, toDate: NSDate()) { (data : CMPedometerData!, error) -> Void in
//                println(data)
//                dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                    if(error == nil){
//                        self.steps.text = "\(data.numberOfSteps)"
//                    }
//                })
//                
//            }
//            
//            self.pedoMeter.startPedometerUpdatesFromDate(midnightOfToday) { (data: CMPedometerData!, error) -> Void in
//                dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                    if(error == nil){
//                        self.steps.text = "\(data.numberOfSteps)"
//                    }
//                })
//            }
//        }

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

