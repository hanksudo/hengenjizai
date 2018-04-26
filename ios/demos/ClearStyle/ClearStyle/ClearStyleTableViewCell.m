//
//  ClearStyleTableViewCell.m
//  ClearStyle
//
//  Created by Hank Wang on 2014/1/27.
//  Copyright (c) 2014年 MuBear. All rights reserved.
//

#import "ClearStyleTableViewCell.h"
#import <QuartzCore/QuartzCore.h>

#define COMPLETED_BACKGROUNDCOLOR [[UIColor alloc] initWithRed:0.0 green:0.6 blue:0.0 alpha:1.0]

@implementation ClearStyleTableViewCell {
    CAGradientLayer *_gradientLayer;
    CGPoint _originalCenter;
    BOOL _deleteOnDragRelease;
    BOOL _markCompleteOnDragRelease;
}

- (void)markCompleted {
    NSMutableAttributedString *attrStr = [[NSMutableAttributedString alloc] initWithString:self.textLabel.text];
    [attrStr addAttribute:NSStrikethroughStyleAttributeName
                    value:@1
                    range:NSMakeRange(0, [attrStr length])];
    self.textLabel.attributedText = attrStr;
    self.backgroundColor = COMPLETED_BACKGROUNDCOLOR;
}

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
        _gradientLayer = [CAGradientLayer layer];
        _gradientLayer.frame = self.bounds;
        _gradientLayer.colors = @[(id)[[UIColor colorWithWhite:1.0f alpha:0.2f] CGColor],
                                  (id)[[UIColor colorWithWhite:1.0f alpha:0.1f] CGColor],
                                  (id)[[UIColor clearColor] CGColor],
                                  (id)[[UIColor colorWithWhite:0.0f alpha:0.1f] CGColor]];
        _gradientLayer.locations = @[@0.00f, @0.01f, @0.95f, @1.00f];
        [self.layer insertSublayer:_gradientLayer atIndex:1];
    }
    self.selectionStyle = UITableViewCellEditingStyleNone;
    
    // add a pan recognizer
    UIGestureRecognizer *recognizer = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(handlePan:)];
    recognizer.delegate = self;
    [self addGestureRecognizer:recognizer];

    return self;
}

- (void)layoutSubviews {
    [super layoutSubviews];
    _gradientLayer.frame = self.bounds;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

# pragma mark - horizontal pan gesture methods
- (BOOL)gestureRecognizerShouldBegin:(UIPanGestureRecognizer *)gestureRecognizer {
    if ([gestureRecognizer isKindOfClass:[UIPanGestureRecognizer class]]) {
        CGPoint translation = [gestureRecognizer translationInView:[self superview]];
        // Check horizontal getsture
        if (fabsf(translation.x) > fabsf(translation.y)) {
            return YES;
        }
    }
    
    return NO;
}

- (void)handlePan:(UIPanGestureRecognizer *)gestureRecognizer {
    if (gestureRecognizer.state == UIGestureRecognizerStateBegan) {
        _originalCenter = self.center;
    }
    
    if (gestureRecognizer.state == UIGestureRecognizerStateChanged) {
        CGPoint translation = [gestureRecognizer translationInView:self];
        self.center = CGPointMake(_originalCenter.x + translation.x, _originalCenter.y);
        
        // determine whether the item has been dragged far enough to initiate a delete/ complete
        _deleteOnDragRelease = self.frame.origin.x < -self.frame.size.width / 5;
        _markCompleteOnDragRelease = self.frame.origin.x > self.frame.size.width / 5;
        
        if (_markCompleteOnDragRelease) {
            if (!self.todoItem.completed) {
                [self markCompleted];
            } else {
                self.textLabel.attributedText = [[NSAttributedString alloc] initWithString:self.textLabel.text];
                self.backgroundColor = self.uncompletedBackgroundColor;
            }
        } else {
            if (!self.todoItem.completed) {
                self.textLabel.attributedText = [[NSAttributedString alloc] initWithString:self.textLabel.text];
                self.backgroundColor = self.uncompletedBackgroundColor;
            } else {
                [self markCompleted];
            }
        }
    }
    
    if (gestureRecognizer.state == UIGestureRecognizerStateEnded) {
        
        if (_markCompleteOnDragRelease) {
            self.todoItem.completed = !self.todoItem.completed;
        }

        if (!_deleteOnDragRelease) {
            // if the item is not being deleted, snap back to the original location
        }
        
        void (^ FinishedBlock)(BOOL) = ^(BOOL f) {
            if (self.todoItem.completed) {
                [UIView animateWithDuration:0.5 animations:^{
                    self.backgroundColor = [UIColor blackColor];
                }];
            }
        };
        
        [self bounceAnimation:FinishedBlock];
        
        if (_deleteOnDragRelease) {
            [self.delegate toDoItemDeleted:self.todoItem];
        }
    }
    
}

- (void) bounceAnimation:(void (^)(BOOL))finishedBlock {
    
    // Start animation -> Complete1 -> Complete2 -> finishedBlock
    
    CGRect originalFrame = CGRectMake(0, self.frame.origin.y, self.bounds.size.width, self.bounds.size.height);

    // Complete2
    void (^ Complete2)(BOOL) = ^(BOOL f){
        [UIView animateWithDuration:0.1
                              delay:0
                            options:UIViewAnimationOptionCurveLinear
                         animations:^{
                             self.frame = CGRectOffset(originalFrame, 0, 0);
                         }
                         completion: finishedBlock];
    };

    
    // Complete1
    void (^ Complete1)(BOOL) = ^(BOOL f){
        [UIView animateWithDuration:0.1
                              delay:0
                            options:UIViewAnimationOptionCurveLinear
                         animations:^{
                             self.frame = CGRectOffset(originalFrame, 2, 0);
                         }
                         completion: Complete2];
    };
    
    // Start animation
    [UIView animateWithDuration:0.2
                          delay: 0
                        options: UIViewAnimationOptionCurveEaseOut | UIViewAnimationOptionAllowUserInteraction
                     animations:^{
                         self.frame = CGRectOffset(originalFrame, -5, 0);
                     }
                     completion:Complete1
     ];
}

@end
