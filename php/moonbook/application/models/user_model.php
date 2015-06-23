<?php 
class User_model extends CI_Model {

	// Table Name
	const TBL_USERS = 'users';

    function __construct()
    {
        parent::__construct();
    }
	
	// ------------------------------------------------------------------------
 	/**
 	* Create Account 建立新帳號
    * 
    * @param <Array> $arr 資料陣列
    * @return integer insert_id
    */
	function create($arr)
	{
		$this->db->insert(self::TBL_USERS, $arr); 
		return $this->get( array('id' => $this->db->insert_id()) );
	}
	
	// ------------------------------------------------------------------------
	/**
	 * 取得登入資訊
	 * 
	 * @param String $username 可以用Username or Email
	 * @param String $password
	 * @param String $ip
	 * @return Object rows
	 */
	function do_login($username, $password, $ip)
	{
		// 可使用 E-mail或 ID登入
		$login_name = (preg_match('/@/', $username)) ? "email" : "username";
	  
	 	$this->db->select('id, username');
	 	$query = $this->db->get_where(
	 		self::TBL_USERS, 
	 		array( $login_name => $username, 'password' => md5($password) )
		);
			
	 	if(!$query->num_rows) return 0;
	  
	 	// 更新最後登入時間
	 	$this->db->update(
	 		self::TBL_USERS, 
	 		array( 'lastlogin_at' => time(), 'lastlogin_ip' => $ip ), 
	 		array( 'id' => $query->row('id') )
		);
		
	 	return $query->row();
	}
	
	// ------------------------------------------------------------------------
	/**
	 * 檢驗帳號是否存在
	 * 
	 * @param String $username
	 * @return Boolean ( 1/0 )
	 */
	function is_exist($username) {
		$query = $this->db->get_where( self::TBL_USERS, array('username' => $username) );
		return ( $query->num_rows > 0 );
	}
	
	// ------------------------------------------------------------------------
	/**
	 * 統計共有幾個使用者
	 * 
	 * @return Integer
	 */
	function count_all() {
		return $this->db->count_all(self::TBL_USERS);
	}
	
	// ------------------------------------------------------------------------
	/**
	 * 取得一筆使用者資料 
	 * 
	 * get( array('username' => 'Hank') )
	 * get( array('id' => '1') )
	 * get( array('permalink' => '#$%#@^$#GERG$@%@') )
	 * 
	 * @param Array $arr
	 * @return Object row
	 */
	function get($arr) {
		$this->db->select('username, email, fullname, registered_at, lastlogin_at, lastlogin_ip, activation');
		$this->db->where($arr);
		$query = $this->db->get( self::TBL_USERS );
		
		if( !$query->num_rows ) return 0;
		
		return $query->row();
	}
	
	function find($arr = array()) {
		return $this->db->get_where(self::TBL_USERS, $arr);
	}
}

/* End of file user_model.php */
/* Location: ./application/models/user_model.php */
