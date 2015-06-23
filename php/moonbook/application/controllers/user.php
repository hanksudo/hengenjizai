<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
/**
 * @author	PigiBank development team
 * @package	PigiBank
 * @subpackage User
 * @category Application
 * @since v0.1
 * 
 * User Controller
 */
class User extends CI_Controller {

	function __construct()
	{
		parent::__construct();
		$this->load->helper( array('form', 'url', 'html', 'date') );
		$this->load->helper('debug');
		
		$this->load->library('security');
		
		$this->load->model('User_model', 'user');
	}

	function index()
	{
		echo "You have wrong way.";
		//$this->load->view('');
	}
	
	
	/**
	 * 個人公開頁面
	 */
	function info($username = 0)
	{
		if(!$username) return;
		
		$user = $this->user->get( array('username' => $username) );
		
		if(!$user) {
			echo "User not exist!";
			return;
		}
		
		list_array($user);
	}
	
	/**
	 * 個人設置頁面
	 */ 
	function settings()
	{
		$this->load->view('user/settings_view');
	}
	
	/**
	 * 登出
	 */
	function logout()
	{
		$sessionData = array(
			'username'	=> '',
			'logged'	=> '',
		);
		
		$this->session->unset_userdata($sessionData);
		$this->session->set_flashdata('error_msg', 'Logout successful');
		redirect('/');
	}
	
	/**
	 * 登入動作
	 */ 
	function login_action()
	{
		if(!isset($_POST['Login'])) return;
		
		// Get input data and Prevent XSS
    	$input = $this->security->xss_clean($_POST['Login']);
		$ip = $this->input->ip_address();
		
		// Remember log in 
		if(isset($input['remember'])) echo 1;
				
		// Do Login and get user
		$user = $this->user->do_login($input['username'], $input['password'], $ip);
		
		// Can't find user
		if(!$user) {
			$this->session->set_flashdata('error_msg', 'Login failed!');
			redirect($input['callback']);
		}
		
		// Save Session
		$newSessionData = array(
			'username'	=> $user->username,
			'logged'	=> True,
		);
		$this->session->set_userdata($newSessionData);
		
		$this->session->set_flashdata('error_msg', 'Login successful!');
		redirect('/');
	}
	
	/**
	 * 註冊頁面
	 */
	function signup()
	{
		//$this->load->view('user/signup_view');
	}
	
	/**
	 * 註冊動作
	 */
	function signup_action() {
		if(!isset($_POST['Signup'])) return;
		
		$input = $this->security->xss_clean($_POST['Signup']);
		$ip = $this->input->ip_address();
		
		$input['password'] = md5($input['password']);
		$input['registered_at'] = now();
		$input['lastlogin_ip'] = $ip;
		$input['permalink'] = md5(uniqid($input['username']));
				
		if($this->user->is_exist($input['username']))
		{
			$this->session->set_flashdata('error_msg', 'This account is exist!');
			redirect('user/signup');
		}
		
		// Create User
		$user = $this->user->create($input);
		
		// Save Session
		$newSessionData = array(
			'username'	=> $user->username,
			'logged'	=> True,
		);
		$this->session->set_userdata($newSessionData);
		
		redirect('/');
	}
	
}

/* End of file uesr.php */
/* Location: ./application/controllers/user.php */