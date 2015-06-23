<?php 
class Comment_model extends CI_Model {

	// Table Name
	const TBL_COMMENTS = 'comments';

    function __construct()
    {
        parent::__construct();
    }
	
	// ------------------------------------------------------------------------
 	/**
 	* Create Account 建立新留言
    * 
    * @param <Array> $arr 資料陣列
    * @return integer insert_id
    */
	function create($arr)
	{
		$this->db->insert(self::TBL_COMMENTS, $arr); 
		return $this->db->insert_id();
	}
	
	// ------------------------------------------------------------------------
	/**
	 * 統計共有幾篇留言
	 * 
	 * @return Integer
	 */
	function count_all() {
		return $this->db->count_all(self::TBL_COMMENTS);
	}
	
	function find($arr = array(), $order_by = 0) {
		if($order_by) $this->db->order_by($order_by); 
		$this->db->where($arr);
		return $this->db->get(self::TBL_COMMENTS);
	}
	
	function archive($id) {
		$this->db->where('id', $id);
		$this->db->update(self::TBL_COMMENTS, array('archive' => 1));
	}
}

/* End of file comments_model.php */
/* Location: ./application/models/comments_model.php */
