//
//  ClearStyleTableViewCell.h
//  ClearStyle
//
//  Created by Hank Wang on 2014/1/27.
//  Copyright (c) 2014年 MuBear. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ClearStyleToDo.h"
#import "ClearStyleTableViewCellDelegate.h"

@interface ClearStyleTableViewCell : UITableViewCell

@property (strong, nonatomic) ClearStyleToDo *todoItem;

@property (strong, nonatomic) UIColor *uncompletedBackgroundColor;
@property (weak, nonatomic) id<ClearStyleTableViewCellDelegate> delegate;

- (void)markCompleted;

@end
