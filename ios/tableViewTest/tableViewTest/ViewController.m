//
//  ViewController.m
//  tableViewTest
//
//  Created by Hank Wang on 12/2/29.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "ViewController.h"
#import "ItemCell.h"

@implementation ViewController

@synthesize items;
@synthesize comments;

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    items = [NSMutableArray arrayWithCapacity:0];
    [items addObject:@"Hank Wang"];
    [items addObject:@"iOS 5"];
    
    comments = [NSMutableArray arrayWithCapacity:0];
    [comments addObject:@"This is a long comment. I can't handle that. So maybe I can calculate the height."];
    [comments addObject:@"This is short comment."];
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
}

- (void)viewWillDisappear:(BOOL)animated
{
	[super viewWillDisappear:animated];
}

- (void)viewDidDisappear:(BOOL)animated
{
	[super viewDidDisappear:animated];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return [items count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    ItemCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    cell.name.text = [items objectAtIndex:indexPath.row];
    cell.comment.text = [comments objectAtIndex:indexPath.row];
    [cell.comment sizeToFit];
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSString *cellText = [comments objectAtIndex:indexPath.row];
    UIFont *cellFont = [UIFont fontWithName:@"Helvetica" size:17.0];
    CGSize constraintSize = CGSizeMake(314.0f, MAXFLOAT);
    CGSize labelSize = [cellText sizeWithFont:cellFont constrainedToSize:constraintSize lineBreakMode:UILineBreakModeWordWrap];
    
    NSLog(@"%f", labelSize.height);
    
    return labelSize.height + 44.0f;
}


@end
