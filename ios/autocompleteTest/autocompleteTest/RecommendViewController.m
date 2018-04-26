//
//  RecommendViewController.m
//  autocompleteTest
//
//  Created by Hank Wang on 12/3/15.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "RecommendViewController.h"
#import <QuartzCore/QuartzCore.h>
#import "FollowingCell.h"

@interface RecommendViewController ()

@end

@implementation RecommendViewController

@synthesize infoView;
@synthesize toTextField;
@synthesize contentTextView;
@synthesize followingTableView;

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
    
    infoView.layer.borderColor = [UIColor colorWithRed:196/255.0f green:196/255.0f blue:196/255.0f alpha:1.0f].CGColor;
    infoView.layer.borderWidth = 1.0f;
    toTextField.placeholder = @"Username, name, or email";
    [toTextField becomeFirstResponder];
    
    users = [NSMutableArray arrayWithCapacity:0];
    
    NSMutableDictionary *user = [NSMutableDictionary dictionaryWithCapacity:0];
    [user setObject:@"hank" forKey:@"username"];
    [user setObject:@"Hank Wang" forKey:@"name"];
    
    [users addObject:user];
    
    user = [NSMutableDictionary dictionaryWithCapacity:0];
    [user setObject:@"atjcprice" forKey:@"username"];
    [user setObject:@"晴王子" forKey:@"name"];
    [users addObject:user];
    
    filtered_users = nil;
    
    [[NSNotificationCenter defaultCenter] 
     addObserver:self 
     selector:@selector(textFieldChanged:)
     name:UITextFieldTextDidChangeNotification 
     object:toTextField];
    
}

- (void)textFieldChanged:(id)sender
{
    NSString *regexp = @"(username contains[cd] %@) OR (name contains[cd] %@)";
    filtered_users = [NSMutableArray arrayWithArray: [users filteredArrayUsingPredicate:[NSPredicate predicateWithFormat:regexp, toTextField.text, toTextField.text]]];
    
    [self.followingTableView reloadData];
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


#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [filtered_users count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    FollowingCell *cell = [tableView dequeueReusableCellWithIdentifier:@"FollowingCell"];

    cell.usernameLabel.text = [[filtered_users objectAtIndex:indexPath.row] objectForKey:@"username"];
    cell.nameLabel.text = [[filtered_users objectAtIndex:indexPath.row] objectForKey:@"name"];

    return cell;
}

@end
