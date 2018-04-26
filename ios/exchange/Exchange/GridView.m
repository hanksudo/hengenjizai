//
//  GridView.m
//  Exchange
//
//  Created by Hank Wang on 12/8/6.
//  Copyright (c) 2012年 Hank Wang. All rights reserved.
//

#import "GridView.h"

#define itemImageViewWidth      75
#define itemImageViewHeight     75

@implementation GridView

@synthesize itemImageView;
@synthesize grayCoverView;
@synthesize distanceLabel;

- (void) initialize {
    itemImageView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, itemImageViewWidth, itemImageViewHeight)];
    grayCoverView = [[UIView alloc] initWithFrame:CGRectMake(0, itemImageViewHeight - 15, itemImageViewWidth, 15)];
    [grayCoverView setBackgroundColor:[UIColor colorWithRed:51/255.0f green:51/255.0f blue:51/255.0f alpha:0.8f]];
    distanceLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, itemImageViewHeight - 15, itemImageViewWidth, 15)];
    [distanceLabel setTextColor:[UIColor whiteColor]];
    [distanceLabel setTextAlignment:UITextAlignmentCenter];
    [distanceLabel setFont:[UIFont fontWithName:@"Arial Bold" size:12.0f]];
    [distanceLabel setBackgroundColor:[UIColor clearColor]];

    [self addSubview:itemImageView];
    [self addSubview:grayCoverView];
    [self addSubview:distanceLabel];
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        [self initialize];
    }
    return self;
}



/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
