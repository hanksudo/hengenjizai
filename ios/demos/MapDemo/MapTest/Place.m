//
//  Place.m
//
//  Created by sugartin.info on 2/6/10.
//  Copyright 2010 http://www.sugartin.info. All rights reserved.
//

#import "Place.h"


@implementation Place

@synthesize name;
@synthesize description;
@synthesize latitude;
@synthesize longitude;

- (void) dealloc
{
	[name release];
	[description release];
	[super dealloc];
}

@end
