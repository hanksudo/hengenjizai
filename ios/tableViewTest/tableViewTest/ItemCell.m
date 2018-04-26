//
//  ItemCell.m
//  tableViewTest
//
//  Created by Hank Wang on 12/2/29.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "ItemCell.h"

@implementation ItemCell

@synthesize name;
@synthesize comment;

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
