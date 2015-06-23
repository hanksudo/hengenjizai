<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
// ------------------------------------------------------------------------

/**
 * HTML Helper
 *
 * @category Hanker 
 * @package Hanker_Helper
 * @subpackage HTML
 * @author Hank Wang (drapho@gmail.com)
 * @link http://whhnote.blogspot.com
 * @version 0.1a 2011-03-06
 */

 
// ------------------------------------------------------------------------
 
/**
 * convert br to \n
 * 
 * @access public
 * @param String
 * @return String
 */
if( ! function_exists('br2nl'))
{
	function br2nl($str)
	{
		return preg_replace( '!&lt;br.*&gt;!iU', "<br>", $str );
	}
}
    


/* End of file html_helper.php */
/* Location: ./system/application/helpers/html_helper.php */