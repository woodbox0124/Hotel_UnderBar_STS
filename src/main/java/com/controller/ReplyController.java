package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ReplyDTO;
import com.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	@Autowired
	ReplyService rService;
	//시작점
	
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyDTO replyDTO){
		ResponseEntity<String> entity = null;
		
		try {
			rService.addReply(replyDTO);
			entity = new ResponseEntity<>("regSuccess", HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/list/{e_code}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable("e_code") String e_code){
		ResponseEntity<List<ReplyDTO>> entity = null;
		try {
			entity = new ResponseEntity<>(rService.replyList(e_code), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/{c_code}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("c_code") String c_code, @RequestBody ReplyDTO replyDTO){
		ResponseEntity<String> entity = null;
		try {
			replyDTO.setC_code(c_code);
			rService.updateReply(replyDTO);
			entity = new ResponseEntity<>("updateSuccess", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	
	
//	@ResponseBody
//	@RequestMapping("/event/addComment")
//	public String ajax_addComment(CommentDTO cDTO, HttpServletRequest request){
//		
//			eService.addCommnet(cDTO);
//			System.out.println(cDTO);
//		
//		
//		return "success";
//		
//	}
//	@ResponseBody
//	@RequestMapping("/event/delComment")
//	public String ajax_delComment(@RequestParam String code){
//		System.out.println("파싱값 : "+code);
//		int c_code = Integer.parseInt(code);
//		System.out.println(c_code);
//		eService.delCommnet(c_code);
//		
//		
//		return "success";
//		
//	}
//	@ResponseBody
//	@RequestMapping("/event/updateComment")
//	public ModelAndView ajax_updateComment(@RequestParam Map<String, String> map) {
//		
//		 Iterator<String> keys = map.keySet().iterator();
//	        while( keys.hasNext() ){
//	            String key = keys.next();
//	            String value = map.get(key);
//	            System.out.println("키 : "+key+", 값 : "+value);
//	        }
//	        String e_code = map.get("e_code");
//	        System.out.println(e_code);
//	        
//	        int num = eService.updateComment(map);
//	        System.out.println("업데이트 댓글 : " +  num);
//	        
//	        //객체를 뿌려주기위함
//	        //CommentDTO cDTO = eService.commentListByCode(e_code);
////	        ModelAndView mav = new ModelAndView();
////	        mav.addObject("cDTO", cDTO);
////	        System.out.println(cDTO);
//
//	        return null;
//		
//	}
//	
//	
//	
//	@ResponseBody
//	@RequestMapping(value = "/event/commentList", produces="application/json; charset=utf8")
//	public ResponseEntity ajax_commentList(CommentDTO commentDTO, HttpServletRequest request) throws Exception{
//		
//		HttpHeaders responseHeaders = new HttpHeaders();
//		ArrayList<HashMap> hmList = new ArrayList<HashMap>();
//		String e_code = commentDTO.getE_code();
//		System.out.println(e_code);
//		
//		//해당 게시물 댓글
//		List<CommentDTO> cList = eService.commentListByCode(e_code);
//		
//
//        if(cList.size() > 0){
//            for(int i=0; i<cList.size(); i++){
//                HashMap map = new HashMap();
//                map.put("c_code", cList.get(i).getC_code());
//                map.put("e_code", cList.get(i).getE_code());
//                map.put("comments", cList.get(i).getComments());
//                map.put("writer", cList.get(i).getWriter());
//                map.put("regdate", cList.get(i).getRegdate());
//                
//                hmList.add(map);
//            }
//            
//        }
//        
//        JSONArray json = new JSONArray(hmList);        
//        return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
//		
//	}
//	
//	
	
	
	
}
