//
//  CustomeTabBarViewController.m
//  Exchange
//
//  Created by Hank Wang on 12/8/6.
//  Copyright (c) 2012年 Hank Wang. All rights reserved.
//

#import "CustomeTabBarViewController.h"

@interface CustomeTabBarViewController ()

@end

@implementation CustomeTabBarViewController

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
    
    [self addCenterButtonWithImage:[UIImage imageNamed:@"capture-button"] highlightImage:[UIImage imageNamed:@"capture-button"] target:self action:@selector(buttonPressed:)];
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

- (void)addCenterButtonWithImage:(UIImage *)buttonImage highlightImage:(UIImage *)highlightImage target:(id)target action:(SEL)action
{
    UIButton *centerButton = [UIButton buttonWithType:UIButtonTypeCustom];
    centerButton.autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleBottomMargin | UIViewAutoresizingFlexibleTopMargin;
    centerButton.frame = CGRectMake(0.0, 0.0, buttonImage.size.width, buttonImage.size.height);
    [centerButton setBackgroundImage:buttonImage forState:UIControlStateNormal];
    [centerButton setBackgroundImage:highlightImage forState:UIControlStateHighlighted];
    
    CGFloat heightDifference = buttonImage.size.height  - self.tabBar.frame.size.height;
    
    if (heightDifference < 0) {
        centerButton.center = self.tabBar.center;
    } else {
        CGPoint center = self.tabBar.center;
        center.y = center.y - heightDifference / 2.0;
        centerButton.center = center;
    }
    
    [centerButton addTarget:target action:action forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:centerButton];
}

- (void)buttonPressed:(id)sender
{
    NSLog(@"camera");
//    [self setSelectedIndex:2];
}

@end
