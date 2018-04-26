//
//  ClearStyleViewController.m
//  ClearStyle
//
//  Created by Hank Wang on 2014/1/27.
//  Copyright (c) 2014年 MuBear. All rights reserved.
//

#import "ClearStyleViewController.h"
#import "ClearStyleTableViewCell.h"

@interface ClearStyleViewController ()
@property (strong, nonatomic) NSMutableArray *toDoItems;
@property (nonatomic) int indexOfFirstCompleted;
@end
    
@implementation ClearStyleViewController


- (NSMutableArray *)toDoItems {
    if (!_toDoItems) _toDoItems = [[NSMutableArray alloc] init];
    return _toDoItems;
}

-(UIStatusBarStyle)preferredStatusBarStyle{
    return UIStatusBarStyleLightContent;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.indexOfFirstCompleted = -1;
    
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.extendedLayoutIncludesOpaqueBars = NO;
    self.automaticallyAdjustsScrollViewInsets = NO;
    
    [self.toDoItems addObject:[ClearStyleToDo toDoItemWithText:@"Banna" completed:NO]];
    [self.toDoItems addObject:[ClearStyleToDo toDoItemWithText:@"Apple" completed:NO]];
    [self.toDoItems addObject:[ClearStyleToDo toDoItemWithText:@"Orange" completed:NO]];
    [self.toDoItems addObject:[ClearStyleToDo toDoItemWithText:@"Cat food" completed:NO]];
    [self.toDoItems addObject:[ClearStyleToDo toDoItemWithText:@"ToItem" completed:NO]];
    [self.toDoItems addObject:[ClearStyleToDo toDoItemWithText:@"Meat" completed:YES]];
    
    self.tableView.dataSource = self;
    self.tableView.delegate = self;
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    self.tableView.backgroundColor = [UIColor blackColor];
    [self.tableView registerClass:[ClearStyleTableViewCell class]
           forCellReuseIdentifier:@"cell"];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (UIColor *)colorForIndex:(NSInteger)index {
    NSUInteger itemCount = [self.toDoItems count] -1;
    float val = ((float)index / (float)itemCount)*0.6;
    return [UIColor colorWithRed:1.0 green:val blue:0.0 alpha:1.0];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {

    NSString *ident = @"cell";
    ClearStyleTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:ident forIndexPath:indexPath];
    
    int index = [indexPath row];
    ClearStyleToDo *item = self.toDoItems[index];
    cell.textLabel.text = item.text;
    cell.textLabel.backgroundColor = [UIColor clearColor];
    cell.delegate = self;
    cell.todoItem = item;
    
    if (self.indexOfFirstCompleted == -1 && cell.todoItem.completed) {
        self.indexOfFirstCompleted = index;
    }

    return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.toDoItems count];
}

//- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
//    return @"My Todo List";
//}
//
//- (void)tableView:(UITableView *)tableView willDisplayHeaderView:(UIView *)view forSection:(NSInteger)section {
//    if ([view isKindOfClass:[UITableViewHeaderFooterView class]]) {
//
//        UITableViewHeaderFooterView *tableViewHeaderFooterView = (UITableViewHeaderFooterView *)view;
//        tableViewHeaderFooterView.contentView.backgroundColor = [UIColor blackColor];
//        tableViewHeaderFooterView.textLabel.textColor = [UIColor whiteColor];
//        tableViewHeaderFooterView.textLabel.textAlignment = NSTextAlignmentCenter;
//    }
//}

# pragma mark - UITableViewDelegate protocol methods
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 50.0f;
}

- (void)tableView:(UITableView *)tableView willDisplayCell:(ClearStyleTableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath {
    cell.textLabel.textColor = [UIColor whiteColor];
    cell.textLabel.font = [UIFont preferredFontForTextStyle:UIFontTextStyleBody];
    cell.backgroundColor = [self colorForIndex:indexPath.row];
    cell.uncompletedBackgroundColor = cell.backgroundColor;
    
    if (cell.todoItem.completed) {
        [cell markCompleted];
        cell.backgroundColor = [UIColor blackColor];
    };
}

# pragma mark ClearStyleTableViewCellDelegate methods
- (void)toDoItemDeleted:(ClearStyleToDo *)todoItem {
    NSUInteger index = [self.toDoItems indexOfObject:todoItem];

    if (index != NSNotFound) {
        [self.tableView beginUpdates];
        [self.toDoItems removeObject:todoItem];
        [self.tableView deleteRowsAtIndexPaths:@[[NSIndexPath indexPathForRow:index inSection:0]]
                              withRowAnimation:UITableViewRowAnimationLeft];
        [self.tableView endUpdates];
    }
}

- (void)toDoItemCompleted:(ClearStyleToDo *)todoItem {
    
}


@end
