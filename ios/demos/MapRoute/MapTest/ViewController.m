//
//  ViewController.m
//  MapTest
//
//  Created by Hank Wang on 12/10/25.
//  Copyright (c) 2012年 Company. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

@synthesize mapView;

- (void)viewDidLoad
{
    [super viewDidLoad];
    if(!self.mapView) {
		self.mapView = [[MapView alloc] initWithFrame:
						CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height)] ;
		[self.view addSubview:self.mapView];
	}
    
    Place* home = [[Place alloc] init] ;
	home.name = @"Home";
	home.description = @"Sweet home";
	home.latitude = 24.858052982277126;
	home.longitude = 121.82437777519226;
	
	Place* office = [[Place alloc] init] ;
	office.name = @"Office";
	office.description = @"Bad office";
	office.latitude = 23.999907;
	office.longitude = 120.59590890000004;
	
	[self.mapView showRouteFrom:home to:office];
}

- (void)viewWillAppear:(BOOL)animated
{

// 一中商圈
//    24.2332076,120.94173679999994
    
//    CLLocationCoordinate2D zoomLocation;
//    zoomLocation.latitude = 45.768898096673695;
//    zoomLocation.longitude= 126.62017894226073;
//
//    MKCoordinateRegion viewRegion = MKCoordinateRegionMakeWithDistance(zoomLocation, 0.5*METERS_PER_MILE, 0.5*METERS_PER_MILE);
//
//    MKCoordinateRegion adjustedRegion = [_mapView regionThatFits:viewRegion];
//
//    [_mapView setRegion:adjustedRegion animated:YES];
//    
//    MyLocation *annotation = [[MyLocation alloc] initWithName:@"myLocation" address:@"abcdefg" coordinate:zoomLocation] ;
//    [_mapView addAnnotation:annotation];
   
    
    // overlay test
//    CLLocation *distLocation = [[CLLocation alloc] initWithLatitude: 24.858052982277126 longitude:121.82437777519226];
//    
//    CLLocation * secondLocation = [[CLLocation alloc] initWithLatitude:23.999907 longitude:120.59590890000004];
//    CLLocationCoordinate2D newcordinate = secondLocation.coordinate;
//    CLLocationCoordinate2D oldcordinate = distLocation.coordinate;
//    
//    MKMapPoint * pointsArray =
//    malloc(sizeof(CLLocationCoordinate2D)*2);
//    
//    pointsArray[0]= MKMapPointForCoordinate(oldcordinate);
//    pointsArray[1]= MKMapPointForCoordinate(newcordinate);
//    
//    routeLine = [MKPolyline polylineWithPoints:pointsArray count:2];
//    free(pointsArray);
//    
//    [_mapView addOverlay:routeLine];
    
    
}

- (void)mapView:(MKMapView *)mapView didUpdateUserLocation:(MKUserLocation *)userLocation {
    
//    CLLocationCoordinate2D coordinate = mapView.userLocation.coordinate;
//    
//    MKCoordinateRegion mapRegion = MKCoordinateRegionMakeWithDistance(coordinate, 0.2 * METERS_PER_MILE, 0.2 * METERS_PER_MILE);
//    
//    [mapView setRegion:mapRegion animated:YES];
//    
//    MyLocation *annotation = [[MyLocation alloc] initWithName:@"myLocation" address:@"abcdefg" coordinate:coordinate];
//    [_mapView addAnnotation:annotation];
    
// 路徑規劃
//    //first create latitude longitude object
//    CLLocationCoordinate2D destCoordinate = CLLocationCoordinate2DMake(24.2332076, 120.94173679999994);
//    
//    //create MKMapItem out of coordinates
//    MKPlacemark* placeMark = [[MKPlacemark alloc] initWithCoordinate:destCoordinate addressDictionary:nil];
//    MKMapItem* destination =  [[MKMapItem alloc] initWithPlacemark:placeMark];
//    
//    if([destination respondsToSelector:@selector(openInMapsWithLaunchOptions:)])
//    {
//        //using iOS6 native maps app
//        [destination openInMapsWithLaunchOptions:@{MKLaunchOptionsDirectionsModeKey:MKLaunchOptionsDirectionsModeDriving}];
//        
//    } else{
//        
//        //using iOS 5 which has the Google Maps application
//        NSString* url = [NSString stringWithFormat: @"http://maps.google.com/maps?saddr=Current+Location&daddr=%f,%f", destCoordinate.latitude, destCoordinate.longitude];
//        [[UIApplication sharedApplication] openURL: [NSURL URLWithString: url]];
//    }
}

- (MKOverlayView *)mapView:(MKMapView *)mapView viewForOverlay:(id <MKOverlay>)overlay
{
    MKOverlayView* overlayView = nil;
    
    
    MKPolylineView  * _routeLineView = [[MKPolylineView alloc] initWithPolyline:routeLine];
    _routeLineView.fillColor = [UIColor redColor];
    _routeLineView.strokeColor = [UIColor redColor];
    _routeLineView.lineWidth = 15;
    _routeLineView.lineCap = kCGLineCapSquare;
    
    
    overlayView = _routeLineView;
    
    return overlayView;
    
}

//- (MKAnnotationView *)mapView:(MKMapView *)mapView viewForAnnotation:(id <MKAnnotation>)annotation {
//    
//    static NSString *identifier = @"MyLocation";
//    if ([annotation isKindOfClass:[MyLocation class]]) {
//        
//        MKPinAnnotationView *annotationView = (MKPinAnnotationView *) [_mapView dequeueReusableAnnotationViewWithIdentifier:identifier];
//        if (annotationView == nil) {
//            annotationView = [[MKPinAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:identifier];
//        } else {
//            annotationView.annotation = annotation;
//        }
//        
//        annotationView.enabled = YES;
//        annotationView.canShowCallout = YES;
//        CGRect frame = annotationView.frame;
//        frame.size.width = 31;
//        frame.size.height = 60;
//        annotationView.frame = frame;
//        annotationView.image = [UIImage imageNamed:@"r.png"];
//        
//        UIView *plusView = [[UIView alloc] initWithFrame:CGRectMake(-62, -100, 120, 100)];
//        
//        UIImageView *backImageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"g_block.png"]];
//        [backImageView setFrame:CGRectMake(0, 0, 180, 100)];
//        [plusView addSubview:backImageView];
//        
//        UILabel *titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(15, 5, 100, 30)];
//        titleLabel.backgroundColor = [UIColor clearColor];
//        titleLabel.font = [UIFont boldSystemFontOfSize:17.0f];
//        titleLabel.text = @"台北101";
//        [plusView addSubview:titleLabel];
//        
//        UILabel *typeLabel = [[UILabel alloc] initWithFrame:CGRectMake(15, 30, 100, 30)];
//        typeLabel.backgroundColor = [UIColor clearColor];
//        typeLabel.textColor = [UIColor colorWithRed:112/255.0f green:112/255.0f blue:112/255.0f alpha:1.0f];
//        typeLabel.font = [UIFont systemFontOfSize:13.0f];
//        typeLabel.text = @"觀光饗宴";
//        [plusView addSubview:typeLabel];
//        
//        UILabel *distanceLabel = [[UILabel alloc] initWithFrame:CGRectMake(110, 30, 100, 30)];
//        distanceLabel.backgroundColor = [UIColor clearColor];
//        distanceLabel.textColor = [UIColor colorWithRed:112/255.0f green:112/255.0f blue:112/255.0f alpha:1.0f];
//        distanceLabel.font = [UIFont systemFontOfSize:13.0f];
//        distanceLabel.text = @"距28公尺";
//        [plusView addSubview:distanceLabel];
//        
//        [annotationView addSubview:plusView];
//        
//        return annotationView;
//    }
//    
//    return nil;    
//}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
