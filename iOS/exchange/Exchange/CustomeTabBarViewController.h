//
//  CustomeTabBarViewController.h
//  Exchange
//
//  Created by Hank Wang on 12/8/6.
//  Copyright (c) 2012年 Hank Wang. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CustomeTabBarViewController : UITabBarController

- (void)addCenterButtonWithImage:(UIImage *)buttonImage highlightImage:(UIImage *)highlightImage target:(id)target action:(SEL)action;

@end
