//
//  ViewController.m
//  ShareDemo
//
//  Created by Hank Wang on 8/2/16.
//  Copyright © 2016 hanksudo. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (IBAction)shareApp:(id)sender {
    NSString *textToShare = @"Hello, It's a demo for share app.";
    NSURL *link = [NSURL URLWithString:@"https://www.health2sync.com/apps?hl=tw"];

    UIActivityViewController *activityVC = [[UIActivityViewController alloc] initWithActivityItems:@[textToShare, link] applicationActivities:nil];
    
//    activityVC.excludedActivityTypes = ex
    [self presentViewController:activityVC animated:YES completion:nil];
    
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
