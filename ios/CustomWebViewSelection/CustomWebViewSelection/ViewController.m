//
//  ViewController.m
//  CustomWebViewSelection
//
//  Created by Hank Wang on 12/2/29.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "ViewController.h"

@implementation ViewController

@synthesize webView;

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Release any cached data, images, etc that aren't in use.
}

- (void)customAction1:(id)sender {
    NSString *selection = [webView stringByEvaluatingJavaScriptFromString:@"window.getSelection().toString()"];
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Selected Text" message:selection delegate:nil cancelButtonTitle:@"Okay" otherButtonTitles:nil];
    [alert show];
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    [webView loadHTMLString:@"<br />https://hanksudo.com<br />" baseURL:nil];
    
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    UIMenuItem *customMenuItem1 = [[UIMenuItem alloc] initWithTitle:@"a" action:@selector(customAction1:)];
    UIMenuItem *customMenuItem2 = [[UIMenuItem alloc] initWithTitle:@"b" action:@selector(customAction2:)];
    [[UIMenuController sharedMenuController] setMenuItems:[NSArray arrayWithObjects:customMenuItem1, customMenuItem2, nil]];
    
}

- (void)viewWillDisappear:(BOOL)animated
{
	[super viewWillDisappear:animated];
}

- (void)viewDidDisappear:(BOOL)animated
{
	[super viewDidDisappear:animated];
    
    [[UIMenuController sharedMenuController] setMenuItems:nil];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}

- (BOOL)canPerformAction:(SEL)action withSender:(id)sender {

    if (webView.superview != nil) {
        NSLog(@"%@", NSStringFromSelector(action));
        if (action == @selector(customAction1:) || action == @selector(customAction2:)) {
            return YES;
        } else if (action == @selector(copy:)) {
            return NO;
        }
    }
    
    return [super canPerformAction:action withSender:sender];
}

@end
