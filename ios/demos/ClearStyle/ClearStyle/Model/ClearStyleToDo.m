//
//  ClearStyleToDo.m
//  ClearStyle
//
//  Created by Hank Wang on 2014/1/27.
//  Copyright (c) 2014年 MuBear. All rights reserved.
//

#import "ClearStyleToDo.h"

@interface ClearStyleToDo ()

@end

@implementation ClearStyleToDo


- (instancetype)initWithText:(NSString *)text completed:(BOOL)completed {
    self = [super init];

    if (self) {
        self.text = text;
        self.completed = completed;
    }
    return self;

}

+ (id)toDoItemWithText:(NSString *)text completed:(BOOL)completed {
    return [[ClearStyleToDo alloc] initWithText:text completed:completed];
}

@end
