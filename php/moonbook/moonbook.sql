-- phpMyAdmin SQL Dump
-- version 3.3.8
-- http://www.phpmyadmin.net
--
-- 主機: localhost
-- 建立日期: Mar 09, 2011, 12:08 AM
-- 伺服器版本: 5.1.53
-- PHP 版本: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 資料庫: `moonbook`
--

-- --------------------------------------------------------

--
-- 資料表格式： `ci_sessions`
--

CREATE TABLE IF NOT EXISTS `ci_sessions` (
  `session_id` varchar(40) NOT NULL DEFAULT '0',
  `ip_address` varchar(16) NOT NULL DEFAULT '0',
  `user_agent` varchar(50) NOT NULL,
  `last_activity` int(10) unsigned NOT NULL DEFAULT '0',
  `user_data` text,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 資料表格式： `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `comment_at` int(11) NOT NULL,
  `invisible` tinyint(4) NOT NULL DEFAULT '0',
  `ip` varchar(16) NOT NULL,
  `archive` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=111 ;

-- --------------------------------------------------------

--
-- 資料表格式： `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL COMMENT '會員帳號',
  `password` varchar(45) NOT NULL COMMENT '會員密碼',
  `email` varchar(255) NOT NULL COMMENT '會員E-mail',
  `fullname` varchar(255) NOT NULL,
  `registered_at` int(20) DEFAULT NULL COMMENT '註冊時間',
  `lastlogin_at` int(20) DEFAULT NULL COMMENT '最近登入時間',
  `lastlogin_ip` mediumtext COMMENT '最近登入IP',
  `activation` tinyint(4) NOT NULL DEFAULT '0' COMMENT '啟用狀態',
  `permalink` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;
