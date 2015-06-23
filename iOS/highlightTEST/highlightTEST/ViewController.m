//
//  ViewController.m
//  highlightTEST
//
//  Created by Hank Wang on 12/3/26.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

@synthesize preloadWebView;
@synthesize touchState;
@synthesize prevTouchState;

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    touchState = TouchEnd;
    prevTouchState = TouchEnd;
    
    NSString *url = @"http://api.thequeue.org/v1/clear?format=json&url=http://foobarpig.com/iphone/touchxml-installation-guide.html";
    
    // Create the request.
    NSURLRequest *theRequest=[NSURLRequest requestWithURL:[NSURL URLWithString:url]
                                              cachePolicy:NSURLRequestUseProtocolCachePolicy
                                          timeoutInterval:60.0];
    // create the connection with the request
    // and start loading the data
    NSURLConnection *theConnection=[[NSURLConnection alloc] initWithRequest:theRequest delegate:self];
    if (theConnection) {
        receivedData = [NSMutableData data];
    } else {
        // Inform the user that the connection failed.
    }
    
//    UIMenuItem *customMenuItem0 = [[UIMenuItem alloc] initWithTitle:@"Copy" action:@selector(selectionCopy:)];
    UIMenuItem *customMenuItem1 = [[UIMenuItem alloc] initWithTitle:@"Highlight" action:@selector(selectionHighlight:)];
    
    [UIMenuController sharedMenuController].menuVisible = YES;
    [[UIMenuController sharedMenuController] setMenuItems:[NSArray arrayWithObjects:customMenuItem1, nil]];
}

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    [receivedData appendData:data];
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection
{
    NSString *htmlString = [[[self parseJSON:receivedData] objectForKey:@"item"] objectForKey:@"description"];
    
    htmlString = [htmlString stringByReplacingOccurrencesOfString:@"&lt;" withString:@"<"];
    htmlString = [htmlString stringByReplacingOccurrencesOfString:@"&gt;" withString:@">"];
    htmlString = [htmlString stringByReplacingOccurrencesOfString:@"&quot;" withString:@"\""];
    htmlString = [htmlString stringByReplacingOccurrencesOfString:@"&amp;amp;" withString:@"&"];
    htmlString = [htmlString stringByReplacingOccurrencesOfString:@"&amp;#13;" withString:@""];
    
    NSString *path = [[NSBundle mainBundle] bundlePath];
	NSURL *baseURL = [NSURL fileURLWithPath:path];
    
    [preloadWebView loadHTMLString:[self renderTemplateWithTitle:@"TEST"
                                                             url:@""
                                                         content:htmlString]
                    baseURL:baseURL];
}

     

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}



- (NSMutableDictionary *)parseJSON:(NSData *)data
{
    NSError* jsonParsingError;
    return [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:&jsonParsingError];
}

- (NSString *)renderTemplateWithTitle:(NSString *)title url:(NSString *)url content:(NSString *)content
{
    NSString *template = @"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\
    <html xmlns=\"http://www.w3.org/1999/xhtml\">\
    <head>\
    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\
    <script type=\"text/javascript\" src=\"jquery-1.7.1.min.js\"></script>\
    <script type=\"text/javascript\" src=\"jquery.highlight.js\"></script>\
    <script type=\"text/javascript\" src=\"main.js\"></script>\
    <title>Sample Layout</title>\
    <link rel=\"stylesheet\" href=\"reading.css\" type=\"text/css\" />\
    <link rel=\"stylesheet\" href=\"highlighter.css\" type=\"text/css\" />\
    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\
    </head>\
    <body>\
    <div id=\"titlebar\">\
    <h1>%@</h1>\
    </div>\
    <div id=\"story\">\
    <div>%@</div>\
    </div>\
    </body>\
    </html>";
    NSLog(@"render finish");
    return [NSString stringWithFormat:template, title, content];
}

#pragma mark - UIWebView delegate

- (void)webViewDidFinishLoad:(UIWebView *)webView
{
    NSLog(@"webview finish");
    
    NSString *quote = @"TouchXML is a libxml API wrapper written in Objective-C and usually helps with all your project XML needs.";
    
    [webView stringByEvaluatingJavaScriptFromString:[NSString stringWithFormat:@"highlight('%@')", quote]];
    [webView stringByEvaluatingJavaScriptFromString:@"bindEvent()"];
}


- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType
{
    NSString *callerPrefix = @"call?";
    
    NSString *target = [[request URL] absoluteString];
    NSRange check = [target rangeOfString:callerPrefix];

//    NSLog(@"Catch: %@",target);
    
    if( check.location != NSNotFound) {
        NSString *touchCall = [target substringFromIndex:check.location];
        
//        NSLog(@"%@", touchCall);
        
        prevTouchState = touchState;
        
        if ([[callerPrefix stringByAppendingString:@"touchstart"] isEqualToString:touchCall]) {
            touchState = 0;
        } else if ([[callerPrefix stringByAppendingString:@"longpress"] isEqualToString:touchCall]) {
            touchState = 1;
        } else {
            touchState = 2;
        }
        
        if (touchState==TouchEnd) {
            if (prevTouchState==LongPress) {
                NSLog(@"longPress");
            } else if (prevTouchState==TouchStart) {
                NSLog(@"Tap");
            }
        }
    
        return NO;
    }
    return YES;
}

- (BOOL)canPerformAction:(SEL)action withSender:(id)sender {
    if (action == @selector(selectionHighlight:)) {
        return YES;
    }
    return NO;
}

- (void)selectionHighlight:(id)sender {
    NSLog(@"highlight");
}

- (NSString *)prepareLoadJSfromName:(NSString *)name extName:(NSString *)ext
{
    NSString *filePath = [[NSBundle mainBundle] pathForResource:name ofType:ext inDirectory:@""];
    NSData *fileData = [NSData dataWithContentsOfFile:filePath];
    return [[NSMutableString alloc] initWithData:fileData encoding:NSUTF8StringEncoding];
}

@end
