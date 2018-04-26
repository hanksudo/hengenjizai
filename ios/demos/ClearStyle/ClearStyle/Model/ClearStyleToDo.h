//
//  ClearStyleToDo.h
//  ClearStyle
//
//  Created by Hank Wang on 2014/1/27.
//  Copyright (c) 2014年 MuBear. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ClearStyleToDo : NSObject

@property (copy, nonatomic) NSString *text;
@property (nonatomic) BOOL completed;


- (instancetype)initWithText:(NSString *)text completed:(BOOL)completed;
+ (id)toDoItemWithText:(NSString *)text completed:(BOOL)completed;

@end
