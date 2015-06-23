<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Comment extends CI_Controller {

	function __construct()
	{
		parent::__construct();
		$this->load->helper( array('form', 'date','url','html') );
		$this->load->helper('debug');
		
		$this->load->library('security');
		$this->load->model('comment_model', 'comment');
	}

	function index()
	{
		//$data['query'] = $this->comment->find(array('archive' => 0), 'comment_at desc');
		$data['query'] = $this->fetch();
		$data['logged'] = $this->session->userdata('logged');
		$this->load->view('main_view', $data);
	}
	
	function fetch($id = 0) {
		#$desc = ($this->input->is_ajax_request()) ? '' : ' desc'; 
		$query = $this->comment->find( array('id >' => $id, 'archive' => 0), 'id desc' );
		
		foreach($query->result() as $row) {
			$row->name = htmlspecialchars($row->name);
			$row->email = htmlspecialchars($row->email);
			$row->name = ($row->email) ? mailto($row->email, $row->name) : $row->name;
			$row->content = htmlspecialchars($row->content);
			$row->content = str_replace("\r\n", "", $row->content);
			$row->content = ($row->invisible) ? "<font color='#ccc'>(Invisible)</font>" : nl2br($row->content);
			$row->comment_at = mdate('%Y-%m-%d %H:%i', $row->comment_at);
		}
		//list_array($query);
		if($this->input->is_ajax_request()) {
			echo json_encode($query->result());
		} else {
			return $query;
		}
	}
	
	function send_action() {
		if(!$this->input->is_ajax_request()) return;
				
		// Get input data and Prevent XSS
    	$input = $this->security->xss_clean($_POST['Comment']);
		$input['ip'] = $this->input->ip_address();
		$input['name'] = trim($input['name']);
		$input['comment_at'] = now();
		$input['invisible'] = array_key_exists('invisible', $input);		
		$this->comment->create($input);
	}
	
	function delete_action($id = 0) {
		if(!$this->input->is_ajax_request()) return;
		if(!$id) return;
		if(!$this->session->userdata('logged')) return;
		$this->comment->archive($id);
	}
	
	function admin() {
		
	}
}

/* End of file comment.php */
/* Location: ./application/controllers/comment.php */