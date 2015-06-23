<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
// ------------------------------------------------------------------------

/**
 * Debug Helper
 *
 * @category Hanker 
 * @package Hanker_Helper
 * @subpackage Debug
 * @author Hank Wang (drapho@gmail.com)
 * @link http://whhnote.blogspot.com
 * @version 0.1a 2011-01-21
 */

 
// ------------------------------------------------------------------------
 
/**
 * List all array data.
 * 
 * @access public
 * @param array
 * @param String $ip  限制IP瀏覽
 * @return void
 */
if( ! function_exists('list_array'))
{
  function list_array($arr, $ip="")
  {
    if($ip!=="" && (getenv("REMOTE_ADDR")!==$ip)) return;
    
    echo "<pre>";
    print_r($arr);
    echo "</pre>";
  }
}


// ------------------------------------------------------------------------

/**
 * List Sessions
 * 輸出所有Session資料
 * 
 * @return void
 */
if ( ! function_exists('list_session'))
{
  function list_session()
  {
    $CI =& get_instance();
    list_array($CI->session->all_userdata());
  }
}


// ------------------------------------------------------------------------

/**
 * 非法操作時的指向與訊息
 * 
 * @param string $msg 要顯示的錯誤訊息
 * @param string $redirect_url  要轉向的網址
 * @return void
 */
if( ! function_exists('go_error'))
{
  function go_error($msg, $redirect_url="backstage_user/listing")
  {
    // Set the config file options
    $CI =& get_instance();
    
    $CI->session->set_flashdata('msg', $msg);
    redirect($redirect_url);
  }
}

/* End of file debug_helper.php */
/* Location: ./system/application/helpers/debug_helper.php */