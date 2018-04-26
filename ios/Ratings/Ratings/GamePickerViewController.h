//
//  GamePickerViewController.h
//  Ratings
//
//  Created by Hank Wang on 12/2/9.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@class GamePickerViewController;

@protocol GamePickerViewControllerDelegate <NSObject>
- (void)gamePickerViewController:(GamePickerViewController *)controller didSelectGame:(NSString *)theGame;
@end


@interface GamePickerViewController : UITableViewController

@property (nonatomic, weak) id <GamePickerViewControllerDelegate> delegate;
@property (nonatomic, strong) NSString *game;

@end
