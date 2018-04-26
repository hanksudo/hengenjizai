//
//  ClearStyleViewController.h
//  ClearStyle
//
//  Created by Hank Wang on 2014/1/27.
//  Copyright (c) 2014年 MuBear. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ClearStyleToDo.h"
#import "ClearStyleTableViewCellDelegate.h"

@interface ClearStyleViewController : UIViewController<UITableViewDataSource, UITableViewDelegate, ClearStyleTableViewCellDelegate>
@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end
