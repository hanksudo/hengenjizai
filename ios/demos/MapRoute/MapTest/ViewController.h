//
//  ViewController.h
//  MapTest
//
//  Created by Hank Wang on 12/10/25.
//  Copyright (c) 2012年 Company. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import "MyLocation.h"
#import "MapView.h"
#import "Place.h"

#define METERS_PER_MILE 1609.344

@interface ViewController : UIViewController<MKMapViewDelegate> {
    MKPolyline *routeLine;
}

@property (strong, nonatomic) IBOutlet MapView *mapView;

@end
