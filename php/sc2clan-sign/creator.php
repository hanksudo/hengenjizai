<?php

header("Content-type: text/html; charset=utf-8"); 
preg_match("@([a-zA-Z\/]).*@", $_SERVER['DOCUMENT_ROOT'], $match);

if ( $match[1] != '/' ) {
	$ROOTDIR=explode("/",$_SERVER['PHP_SELF']);
	$ROOTDIR=$_SERVER['DOCUMENT_ROOT'] . "/" . $ROOTDIR[1] . "/";
} else {
	$ROOTDIR=$_SERVER['DOCUMENT_ROOT'] . "/";
}

$local_debug=false;

if (!$local_debug) {
	require_once($ROOTDIR.'core/core_func.php');
	require_once($ROOTDIR.'core/__config.php');	
}

# http://simplehtmldom.sourceforge.net/
require_once('simple_html_dom.php');

#基本選項與設定
$battleType = $_POST["battleType"];

if($local_debug) $battleType = '1對1_2';

$battleTypeMin = substr( $battleType , 0 , 1);
$battleTypeEn = str_replace( "對", "V" , $battleType);

#SOLO的第二選項
preg_match('/(1對1)(_2)/', $battleType , $SoloDislayTypes);
$battleTypeEn = str_replace( "_2", "" , $battleTypeEn);
$battleType = str_replace( "_2", "" , $battleType);

#官方個人角色資料之網址
$url = $_POST["url"];

if($local_debug) {
	$sample_url = array('http://tw.battle.net/sc2/zh/profile/717591/2/爆走漢克',
						'http://tw.battle.net/sc2/zh/profile/717591/2/爆走漢克/',
						'http://tw.battle.net/sc2/zh/profile/61442/2/GamaniaSen/',
						'http://us.battle.net/sc2/en/profile/3250689/1/ErBaoZouHanK/');

	$url = $sample_url[2];
}
// concat a slash to prevent url failed.
if(substr($url, -1, 1) != '/') $url .= '/';

// get server and gamer id 
preg_match('/^http:\/\/(?P<server_id>\w+)\.battle\.net\/sc2\/(.+)\/profile\/(.+)\/(.+)\/(?P<game_id>.+)(\/+?)/', $url, $matches);

// 伺服器名稱
$server_id = $matches['server_id'];
$base_url = 'http://'.$server_id.'.battle.net';

// 角色名稱
$game_id = urldecode($matches['game_id']);

$html = file_get_html($url);

if ($html) {
	// 生涯戰績
	$league_wins = $html->find('[class^=campaign-] h2', 0)->plaintext;
	
	// 常用種族
	$race = $html->find('[class^=race-]', 0)->plaintext;
	
	// 對戰排名
	$best1 = $html->find('[id=best-team-1]', 0)->children(1)->innertext;
	preg_match('/<strong>(.+)<\/strong>(.+)<strong>(.+)<\/strong>\s?(?P<rank>\d+)/', $best1, $best_matches);
	$rank = $best_matches['rank'];
	
	// 勝敗場數 (大師以下只有勝場)
	$ladder = $html->find('[data-tooltip=#best-team-1]', 0);
	$bars = $ladder->next_sibling()->next_sibling();
	$primary = $bars->find('[class=primary]', 0);
	$secondary = $bars->find('[class=secondary]', 0);
		
	if ($secondary) {
		$totals = get_totals($primary);
		$wins = get_totals($secondary);
	} else {
		$wins = get_totals($primary);
	}
	
	// 頭像
	$icon_str = $html->find('[id=profile-header]', 0)->children(0)->find('[class=icon-frame]', 0)->outertext;
	preg_match("/background:\s?url\('\/(?P<src>.+)'\)\s?(?P<x>.+)px\s(?P<y>.+)px\sno-repeat;/", $icon_str, $icon);
	
	// 階級圖
	# TODO : 根據積分顯示正確的圖案 medium 1~4
	preg_match('/<span class="badge badge-(?P<badge>.+) badge-medium-(.+)">/', $ladder->children(0)->innertext, $badge_match);
	$badge = $badge_match['badge'];
	
	// 積分 - 目前沒用
	//$point_html = file_get_html('http://'.$server_id.'.battle.net'.$html->find('[data-tooltip=#best-team-1] a', 0)->href);
	//$point = $point_html->find('[id=current-rank]', 0)->find('td', 3)->plaintext;
} else {
	echo "Something Failed.";
	die();
}

function get_totals($obj) {
	$str = $obj->find('[class=totals]', 0)->plaintext;
	preg_match('/(?P<m>\w+)\s?(.+)/', $str, $match);
	return $match['m'];
}

if(isset($totals)) {
	$lose = (int)($totals - $wins);
	$font_size = 16;
} else {
	$font_size = 20;
}

if($local_debug) {
	echo '<pre>';
	echo 'Server: '.$server_id.'<br/>';
	echo 'Name: '.$game_id.'<br/>';
	echo '聯賽勝利: '.$league_wins.'<br/>';
	echo '常用種族: '.$race.'<br/>';
	echo '排名: '.$rank.'<br/>';
	echo '勝場: '.$wins.'<br/>';
	echo '階級: '.$badge.'<br/>';
	echo '敗場: '. $lose.'<br/>';
	echo '</pre>';
}

$imgfile = "imgs/" .md5($url) . ".png";

# 產生畫布
$nimage = imagecreatetruecolor(500, 125);
$trans = imagecolorallocate($nimage, 255, 0, 0); //指定透明
imagecolortransparent($nimage, $trans); //指定透明

#sig布景/圖示/頭像/種族圖
$simage = imageCreateFromPNG('sig_.png');

// 準備階級圖片
$badge_img = imageCreateFromPNG( $base_url.'/sc2/static/images/icons/league/'.$badge.'.png' );

// 準備種族圖
$raceIcon = imageCreateFromPNG( 'race-symbols.png' );

#將各式布景套用到畫布上
$icon_src = imagecreatefromjpeg($base_url.'/'.$icon['src']);
imagecopy($nimage, $icon_src , 28, 15, abs($icon['x']), abs($icon['y']), 90, 90); //塞頭像
imagecopy($nimage, $simage, 0, 0, 0, 0, 500, 125); //再塞布景(頭像框有挖洞)
imagecopy($nimage, $badge_img, 135, 48, 100, 150, 45, 50); //再塞天梯階級小圖
//再塞種族圖示
switch ($race){
	case "Terran":
	case "人類":		imagecopy($nimage, $raceIcon, 245, 45, 0, 0, 30, 30); break;
	case "Zerg":
	case "蟲族":		imagecopy($nimage, $raceIcon, 245, 42, 0, 55, 30, 30); break;
	case "Protoss":
	case "神族":		imagecopy($nimage, $raceIcon, 245, 42, 0, 110, 30, 30);	break;
	case "Randmo":
	case "隨機":		imagecopy($nimage, $raceIcon, 245, 40, 0, 160, 30, 30);	break;
}
#字型
if($local_debug) {
	$ROOT 		= realpath('.');
	$font 		= $ROOT . '/wt011.ttf';
	$fontbd 	= $ROOT . '/msyhbd.ttf';
	$sc2font 	= $ROOT . '/EurostileExt-Reg.otf';
} else {
// for server
	$font 		= ROOT . '/module/SigCreator/wt011.ttf';
	$fontbd 	= ROOT . '/module/SigCreator/msyhbd.ttf';
	$sc2font 	= ROOT . '/module/SigCreator/EurostileExt-Reg.otf';
}

#顏色(RGB)
$white		= imagecolorallocate($nimage,255,255,255);
$color		= imagecolorallocate($nimage,78,177,232);
$colorDescription = imagecolorallocate($nimage,59,139,183);

#寫字
preg_match( "/[\w]*/" , $game_id , $match);
if( !empty($match[0]) ){
	ImageTTFText($nimage, 17, 0, 135, 23, $white, $sc2font, $game_id);
} else {
	ImageTTFText($nimage, 17, 0, 135, 23, $white, $font, $game_id);
}

if($server_id=='us') {
	ImageTTFText($nimage,14,0,457,21,$colorDescription,$font,"美服");
} else {
	#插入國旗？
	$TWflag = imageCreateFromPNG( 'flag/Taiwan.png' );
	$chinaflag = imageCreateFromPNG( 'flag/China.png' );
	$hkflag = imageCreateFromPNG( 'flag/Hong-Kong.png' );
	$sgpflag = imageCreateFromPNG( 'flag/Singapore.png' );
	$koreaflag = imageCreateFromPNG( 'flag/South-Korea.png' );
	$mlsaflag = imageCreateFromPNG( 'flag/Malaysia.png' );
	switch( $_POST["InnerJoin_Flag"] ){
		case 'tw':
			imagecopy($nimage,$TWflag,472,4,0,0,24,24);
			ImageTTFText($nimage,14,0,433,21,$colorDescription,$font,"臺灣");
		break;
		case 'china':
			imagecopy($nimage,$chinaflag,472,4,0,0,24,24);
			ImageTTFText($nimage,14,0,433,21,$colorDescription,$font,"臺服");
		break;
		case 'hk':
			imagecopy($nimage,$hkflag,472,4,0,0,24,24);
			ImageTTFText($nimage,14,0,433,21,$colorDescription,$font,"臺服");
		break;
		case 'Singapore':
			imagecopy($nimage,$sgpflag,472,4,0,0,24,24);
			ImageTTFText($nimage,14,0,433,21,$colorDescription,$font,"臺服");
		break;
		case 'Malaysia':
			imagecopy($nimage,$mlsaflag,472,4,0,0,24,24);
			ImageTTFText($nimage,14,0,433,21,$colorDescription,$font,"臺服");
		break;
		case 'korea':
			imagecopy($nimage,$koreaflag,472,4,0,0,24,24);
			ImageTTFText($nimage,14,0,433,21,$colorDescription,$font,"臺服");
		break;
		default:
			ImageTTFText($nimage,14,0,457,21,$colorDescription,$font,"臺灣");
	}
}
ImageTTFText($nimage,10,0, 398, 68,$colorDescription,$font,"生涯天梯勝利");
ImageTTFText($nimage,10,0, 185, 65,$colorDescription,$font,"排名#");
ImageTTFText($nimage,10,0, 223, 65,$white ,$sc2font, $rank);

####################
#####SOLO戰績排版####
###################
ImageTTFText($nimage,16,0,185,89,$color,$sc2font, $battleTypeEn);//1對1

switch (strlen($wins)) { #SOLO勝場
	case 1:		ImageTTFText($nimage,$font_size, 0, 276, 90, $white, $font, $wins );	break;
	case 2:		ImageTTFText($nimage,$font_size, 0, 266, 90, $white, $font, $wins );	break;
	case 3:		ImageTTFText($nimage,$font_size, 0, 257, 90, $white, $font, $wins );	break;
	case 4:		ImageTTFText($nimage,$font_size, 0, 248, 90, $white, $font, $wins );	break;
	default:	ImageTTFText($nimage,$font_size, 0, 266, 90, $white, $font, $wins);
}
ImageTTFText($nimage, $font_size, 0, 295, 89, $colorDescription, $font, "勝");

if( isset($totals) ) {
	switch (strlen($lose)) { #SOLO敗場
		case 1:		ImageTTFText($nimage, 16, 0, 346, 90, $white, $font, $lose);	break;
		case 2:		ImageTTFText($nimage, 16, 0, 336, 90, $white, $font, $lose);	break;
		case 3:		ImageTTFText($nimage, 16, 0, 327, 90, $white, $font, $lose);	break;
		case 4:		ImageTTFText($nimage, 16, 0, 316, 90, $white, $font, $lose);	break;
		default:	ImageTTFText($nimage, 16, 0, 346, 90, $white,$font, $lose);
	}
	ImageTTFText($nimage, 16, 0, 360, 89,$colorDescription,$font, "敗"); #
}
#######################
#####常用種族排版/積分#####
#######################
// ImageTTFText($nimage,10,0,180,113,$colorDescription,$font, "種族: "); #
// ImageTTFText($nimage,10,0,237,113,$color,$font, $user_race); #
// if( $SoloDislayTypes[2] == "_2" ){
	// switch (strlen($solo_point)){ #SOLO勝率與積分
		// case 4:		ImageTTFText($nimage,10,0,267,114,$white,$font, $solo_point); 	break;
		// case 3:		ImageTTFText($nimage,10,0,277,114,$white,$font, $solo_point); 	break;
		// case 2:		ImageTTFText($nimage,10,0,287,114,$white,$font, $solo_point); 	break;
		// default:		ImageTTFText($nimage,15,0,324,84,$white,$font, $solo_point);
		// }
	// ImageTTFText($nimage,10,0,328,98,$color,$sc2font, "[". $勝率 . "%]"); #
	// ImageTTFText($nimage,10,0,302,113,$color,$font, "分"); #
// }else if($battleTypeMin ==1){
	// switch (strlen($solo_point)){ #SOLO僅積分
		// case 4:		ImageTTFText($nimage,10,0,267,114,$white,$font, $solo_point); 	break;
		// case 3:		ImageTTFText($nimage,10,0,277,114,$white,$font, $solo_point); 	break;
		// case 2:		ImageTTFText($nimage,10,0,287,114,$white,$font, $solo_point); 	break;
		// default:		ImageTTFText($nimage,15,0,324,84,$white,$font, $solo_point);
		// }
	// ImageTTFText($nimage,10,0,302,113,$color,$font, "分"); #
// }else{
	// #其他團戰
	// ImageTTFText($nimage,10,0,328,98,$color,$sc2font, "[". $勝率 . "%]"); #
// }

#####################
#####生涯勝敗場排版#####
#####################
switch (strlen($league_wins)){ #勝場
	case 5:		ImageTTFText($nimage,15,0,394,100,$white,$fontbd, $league_wins); 	break;
	case 4:		ImageTTFText($nimage,15,0,402,100,$white,$fontbd, $league_wins); 	break;
	case 3:		ImageTTFText($nimage,15,0,410,100,$white,$fontbd, $league_wins); 	break;
	case 2:		ImageTTFText($nimage,15,0,418,100,$white,$fontbd, $league_wins); 	break;
	case 1:		ImageTTFText($nimage,15,0,430,100,$white,$fontbd, $league_wins); 	break;
}
ImageTTFText($nimage, 15, 0, 450, 99, $colorDescription, $font,"勝");

imagepng($nimage, $imgfile);
imagedestroy($nimage);
imagedestroy($simage);

if($local_debug) {
	echo "<img id='your_sig_img' src='" . $imgfile . "' width='600' height='150' /><br />\n<br />";
} else {
	// for server 
	$cmd->sql("DELETE FROM `index_sigcreator` WHERE (`url` LIKE '{$url}');");
	$cmd->sql("INSERT INTO `index_sigcreator` VALUES (null,  '{$url}',  '{$game_id}',  '{$league_wins}',  NOW());");
	echo "<img id='your_sig_img' src='". URL . "/module/SigCreator/" . $imgfile . "' /><br />\n<br />";
	echo "複製論壇BBcode程式碼(相容巴哈姆特簽名檔)：<br /><div style='width: 815px; padding: 5px; background: #513769'>[url=http://sc2clan.tw/][img]". URL . "/module/SigCreator/" . $imgfile . '[/img][/url]</div>';
}
?>