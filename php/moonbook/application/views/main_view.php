<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Welcome Moon::Book</title>

<style type="text/css">

body {
 background-color: #fefefe;
 margin: 20px;
 font-family: Lucida Grande, Verdana, Sans-serif;
 font-size: 14px;
 color: #4F5155;
}

a {
 color: #ec0;
 background-color: transparent;
 font-weight: normal;
}

h1 {
 color: #444;
 background-color: transparent;
 border-bottom: 1px solid #D0D0D0;
 font-size: 20px;
 font-weight: bold;
 margin: 24px 0 2px 0;
 padding: 5px 0 6px 0;
}

.title-header {
	color: #444;
	background-color: transparent;
	border-bottom: 1px solid #D0D0D0;
	font-size: 20px;
	font-weight: bold;
	margin: 24px 0 2px 0;
	padding: 5px 0 6px 0;
}

.title-right-header {
	font-size: 14px;
	font-weight: normal;
	float:right;
}

.main {
	width: 1000px;
	margin: 0 auto;
	border: 1px solid #fff;
}

/* -- comment  -- */

.comment-block {
	width: 400px;
	font-family: Monaco, Verdana, Sans-serif;
	font-size: 12px;
	background-color: #ffc;
	border: 5px solid #fe0;
	color: #002166;
	padding: 12px 10px 12px 20px;
	margin: 20px 0px 20px 0px;
}

.comment-block label {
	font-weight: bold;
	text-align: right;
	width: 60px;
	display: block;
	margin: 4px 6px 0 0;
	float: left;
}

.comment-block .button-indent {
	margin-left: 320px;
	width: auto;
}

.comment-time {
	float: right;
}

.comment-id {
	border-top: 1px solid #ccc;
	padding: 10px 0 10px 0;
}

.comment-count {
	font-weight: bold;
	float: right;
}

.comment-new {
	background-color: #FFF9D8;
}

.comment-name {
	font-size: 16px;
	color: #ec0;
}

/* -- login  -- */

.login-block {
	width: 230px;
	font-family: Monaco, Verdana, Sans-serif;
	font-size: 12px;
	font-weight: bold;
	background-color: #FF8080;
	border: 5px solid #e00;
	float: right;
	padding: 6px 10px 6px 10px;
	display: none;
}

.login-block label {
	font-weight: bold;
	text-align: right;
	width: 60px;
	display: block;
	margin: 4px 10px 0 0;
	float: left;
}

.login-block .button-indent {
	margin-left: 180px;
	padding: 5px 0 0 0;
}

.login-hint {
	margin: 0px 0 4px 70px;
	color: #fff;
}

.required {
	color: red;
}

.sendButton {
	color: #fff;
	background: #99D100;
	background: -webkit-gradient(linear, left top, left bottom, from(#99D100), to(#A7E300));
	background: -moz-linear-gradient(top,  #99D100,  #A7E300);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#99D100', endColorstr='#A7E300');
	border-radius: 5px;
	border-top-left-radius: 5px 5px;
	border-top-right-radius: 5px 5px;
	border-bottom-right-radius: 5px 5px;
	border-bottom-left-radius: 5px 5px;
	border: 1px solid #87B800;
	cursor: pointer;
	padding: 0px 10px;
	height: 30px;
}

.sendButton:hover {
	background: #b2eb14;
	background: -webkit-gradient(linear, left top, left bottom, from(#b2eb14), to(#a4da14));
	background: -moz-linear-gradient(top,  #b2eb14,  #a4da14);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#b2eb14', endColorstr='#a4da14');
}

#hint-message {
	width: 200px;
	font-weight: bold;
	border: 1px solid #000;
	background-color: #ff0;
	margin-left: 70px;
	margin-top: 10px;
	display: none;
	float:left;
}

.clear {
	clear: both;
}

.doDelete {
	margin: -3px 0 0 0;
}

</style>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="<?php echo base_url();?>js/jquery.timer.js"></script>
<?php echo link_tag('css/css3buttons.css'); ?>
</head>
<body>


<div class="main">
	<div class="title-header">
	Moon::Book<a href="#" id="AdminButton" class="title-right-header negative button">Admin</a>
	</div>
	Tell me what is on your mind.
	
	<div class="login-block">
	<?php if(!$logged): ?>
		<?php echo form_open('user/login_action', array('id' => 'login-form')); ?>
			<div class="login-hint">
				<?php echo $this->session->flashdata('error_msg'); ?>		
			</div>
			<div>
				<label for="login-user">Username</label>
				<input id="login-user" type="text" name="Login[username]">
			</div>
			<div>
				<label for="login-password">Password</label>
				<input id="login-password" type="password" name="Login[password]">
			</div>
			<div class="button-indent">
				<input type="submit" value="Login">
			</div>
			<input type="hidden" name="callback" value="/<?php echo uri_string(); ?>">
		</form>
	<?php
		else:
			echo "Welcome! ".$this->session->userdata('username')."<br />";
			echo anchor( 'user/logout', 'Logout', array('class' => 'button') ); 
		endif ?>
	</div>
	
	<div class="comment-block">
	<?php echo form_open('comment/send_action', array('id' => 'comment-form')); ?>
		<div>
			<label for="comment-form-name"><span class="required">*</span>Name</label>
			<input id="comment-form-name" type="text" name="Comment[name]">
		</div>
		<div>
			<label for="comment-email">Email</label>
			<input id="comment-email" type="text" name="Comment[email]">
		</div>
		<div>
			<label for="comment-content"><span class="required">*</span>Content</label>
			<textarea id="comment-content" rows="5" cols="35" name="Comment[content]"></textarea>
		</div>
		<div>
			<label for="comment-invisible">Hide?</label>
			<input id="comment-invisible" type="checkbox" name="Comment[invisible]">
		</div>
		<div id="hint-message"></div>
		<div class="button-indent">
			<input class="sendButton" type="submit" value="Send">
		</div>
	</form>
	</div>
	
	<span class="comment-count"><?php echo $query->num_rows; ?> Comments</span>
	<div class="clear"></div>
	
	<?php foreach($query->result() as $row): ?>
	<div class="comment-id" value="<?php echo $row->id; ?>">
		<span class="comment-name"><?php echo $row->name; ?></span>
		<?php if($row->invisible) echo "<font color='#ccc'>(Invisible)</font> "; ?>
		<?php if($logged || !$row->invisible) echo $row->content; ?>
		
		<span class="comment-time">
			<?php echo $row->comment_at; ?>
			<?php if($logged): ?>
				<a class="doDelete negative button" value="<?php echo $row->id; ?>"><span class="cross icon"></span>Delete</a>
			<?php endif ?>
		</span>
	</div>
	<?php endforeach ?>

	<hr id="underline">
	<p><font color="#fff">Page rendered in {elapsed_time} seconds</font></p>
</div>
<script type="text/javascript">
	var baseurl = '<?php echo base_url();?>';

	$('#AdminButton').click(function() {
		if($('.login-block').css('display') == 'none') {
			$('.login-block').css('display', 'block');
		} else {
			$('.login-block').fadeOut('slow');
		}
	});

	$('.doDelete').click(function() {
		var $delItem = $(this);
		event.preventDefault();
		$.get(baseurl+'comment/delete_action/'+$(this).attr('value'), function(data) {
			$delItem.parent().parent().fadeOut('slow');
		});
	});

	$('#comment-form').submit(function(event) {
		// stop from form submit normally
		event.preventDefault();
		var name = $('#comment-form-name').attr('value'),
			email = $('#comment-email').attr('value'),
			invisible = $('#comment-invisible').attr('checked') ? 1 : 0,
			content = $('#comment-content').val();
		
		if( name.trim().length == 0 ) {
			showHint('Please fill all required field.', 'comment-name');
		} else if( content == '' ) {
			showHint('Please fill all required field.', 'comment-content');
		} else {
			$.post(baseurl+'comment/send_action', $('#comment-form').serialize(), function() {
				showHint('Send comment successful.', 'comment-name');
				fetchNewData();
				$('#comment-content').val('');
			}).error(function() {
				console.log('error');
			});
		}
	});
	
	function fetchNewData() {
		$.get(baseurl+'comment/fetch/'+$('.comment-id:first').attr('value'), function(data) {
			data = data.reverse();
			for(var key in data) {
				html = '<div class="comment-id comment-new" value="'+data[key].id+'">';
				html+= '<span class="comment-name">'+data[key].name+'</span> ';
				html+= data[key].content;
				html+= '<span class="comment-time">'+data[key].comment_at+'</span></div>';
				if($('.comment-new').length == 0) $('.comment-id:first').css('border-top', '2px solid #ccc');
				
				if ($('.comment-id:first').length != 0) $('.comment-id:first').before(html); else $('#underline').before(html);
				$('.comment-id:first').hide().fadeIn(2000);
			}
			
		}, "json").error(function() {
			console.log('error');
		});
	}
	
	function showHint(msg, focusObj) {
		$('#hint-message').text(msg);
		$('#hint-message').fadeIn('slow').css('display', 'block').fadeOut('slow');
		if(focusObj)$('#'+focusObj).focus();
	}
	
	// Fetch new data every 30 seconds
	$.timer(30000, function (timer) {
    	fetchNewData();
    	//timer.stop();
	});
	
</script>
</body>
</html>