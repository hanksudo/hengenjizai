//
//  FollowingCell.m
//  autocompleteTest
//
//  Created by Hank Wang on 12/3/15.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "FollowingCell.h"

@implementation FollowingCell

@synthesize usernameLabel;
@synthesize nameLabel;

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
