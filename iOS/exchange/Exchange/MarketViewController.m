//
//  MarketViewController.m
//  Exchange
//
//  Created by Hank Wang on 12/8/6.
//  Copyright (c) 2012年 Hank Wang. All rights reserved.
//

#import "MarketViewController.h"

@interface MarketViewController ()

@end

@implementation MarketViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.

    
    for (int i=0; i<16; i++) {
        GridView *gridView = [[GridView alloc] initWithFrame:CGRectMake(10+(i%4)*75, (i/4)*75, 75, 75)];
        [gridView.itemImageView setImage:[UIImage imageNamed:[NSString stringWithFormat:@"item%d", i]]];
        
        [gridView.distanceLabel setText:[NSString stringWithFormat:@"%d.0 km", i]];
        [self.view addSubview:gridView];
    }
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

@end
