//
//  ClearStyleTableViewCellDelegate.h
//  ClearStyle
//
//  Created by Hank Wang on 2014/1/30.
//  Copyright (c) 2014年 MuBear. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ClearStyleToDo.h"

@protocol ClearStyleTableViewCellDelegate <NSObject>

- (void)toDoItemDeleted:(ClearStyleToDo *)todoItem;
- (void)toDoItemCompleted:(ClearStyleToDo *)todoItem;
@end
