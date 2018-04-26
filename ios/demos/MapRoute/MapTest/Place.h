//
//  Place.h
//
//  Created by sugartin.info on 2/6/10.
//  Copyright 2010 http://www.sugartin.info. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Place : NSObject {

	NSString* name;
	NSString* description;
	double latitude;
	double longitude;
}

@property (nonatomic, retain) NSString* name;
@property (nonatomic, retain) NSString* description;
@property (nonatomic) double latitude;
@property (nonatomic) double longitude;

@end
