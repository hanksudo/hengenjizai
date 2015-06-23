//
//  RecommendViewController.h
//  autocompleteTest
//
//  Created by Hank Wang on 12/3/15.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RecommendViewController : UIViewController {
    NSMutableArray *users;
    NSMutableArray *filtered_users;
}

@property (nonatomic, strong) IBOutlet UIView *infoView;
@property (nonatomic, strong) IBOutlet UITextField *toTextField;
@property (nonatomic, strong) IBOutlet UITextView *contentTextView;
@property (nonatomic, strong) IBOutlet UITableView *followingTableView;


- (void)textFieldChanged:(id)sender;

@end
