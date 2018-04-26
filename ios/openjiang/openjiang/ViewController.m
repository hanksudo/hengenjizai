//
//  ViewController.m
//  openjiang
//
//  Created by Hank Wang on 12/8/14.
//  Copyright (c) 2012年 hankwang. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)openGhost:(id)sender
{
    UIApplication *app = [UIApplication sharedApplication];
    
    NSString *URLEncodedText = [@"跨丟鬼, check it out!" stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSString *ourPath = [@"getGhost://" stringByAppendingString:URLEncodedText];
    NSURL *ourURL = [NSURL URLWithString:ourPath];
    if ([app canOpenURL:ourURL]) {
        [app openURL:ourURL];
    }
    else {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"跨丟鬼" message:@"沒收功就罵髒話" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [alertView show];
    }

}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}

@end
