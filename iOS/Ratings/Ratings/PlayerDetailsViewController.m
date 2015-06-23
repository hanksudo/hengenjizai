//
//  PlayerDetailsViewController.m
//  Ratings
//
//  Created by Hank Wang on 12/1/31.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "PlayerDetailsViewController.h"
#import "Player.h"

@implementation PlayerDetailsViewController
{
    NSString *game;
}

@synthesize delegate;
@synthesize nameTextField;
@synthesize detailLabel;

- (id)initWithCoder:(NSCoder *)aDecoder
{
    if(self = [super initWithCoder:aDecoder])
    {
        game = @"Chess";
    }
    return self;
}

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.detailLabel.text = game;

    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
 
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)viewDidUnload
{
    [self setNameTextField:nil];
    [self setDetailLabel:nil];
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
}

- (void)viewWillDisappear:(BOOL)animated
{
    [super viewWillDisappear:animated];
}

- (void)viewDidDisappear:(BOOL)animated
{
    [super viewDidDisappear:animated];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}


#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0)
        [self.nameTextField becomeFirstResponder];
}

#pragma mark - GamePickerViewControllerDelegate

- (void)gamePickerViewController:(GamePickerViewController *)controller didSelectGame:(NSString *)theGame
{
    game = theGame;
    self.detailLabel.text = game;
    [self.navigationController popViewControllerAnimated:YES];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"PickGame"])
    {
        GamePickerViewController *gamePickerViewController = segue.destinationViewController;
        gamePickerViewController.delegate = self;
        gamePickerViewController.game = game;
    }
}

- (IBAction)cancel:(id)sender 
{
    [self.delegate playerDetailsViewControllerDidCancel:self];
}

- (IBAction)done:(id)sender
{
    Player *player = [[Player alloc] init];
    player.name = self.nameTextField.text;
    player.game = game;
    player.rating = 1;
    [self.delegate playerDetailsViewController:self didAddPlayer:player];
}

@end
