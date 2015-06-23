//
//  ViewController.h
//  highlightTEST
//
//  Created by Hank Wang on 12/3/26.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController <UIWebViewDelegate> {
    NSMutableData *receivedData;
}

typedef enum TouchStates {
    TouchStart = 0,
    LongPress = 1,
    TouchEnd = 2
}TouchStates;

@property (nonatomic, readwrite) TouchStates touchState;
@property (nonatomic, readwrite) TouchStates prevTouchState;
@property (nonatomic, strong) IBOutlet UIWebView *preloadWebView;

- (NSMutableDictionary *)parseJSON:(NSData *)data;

@end
