package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ReplyDTO;
import com.service.ReplyService;


@RestController
public class ReplyController {

	@Autowired
	ReplyService rService;
	
	//댓글작성
	@RequestMapping(value="/replies/add")
	public String addReply(@RequestParam String writer, String e_code, String comments){
		System.out.println("파싱 : "+writer+e_code+comments);
		ReplyDTO rDTO = new ReplyDTO();
		rDTO.setE_code(e_code);
		rDTO.setComments(comments);
		rDTO.setWriter(writer);
		int num = rService.insert(rDTO);
		System.out.println(num);
		return "success";
	}
	//댓글삭제
	@RequestMapping(value="/replies/delReply")
	public String delReply(@RequestParam String c_code){
		System.out.println("파싱 : "+c_code);
		int num = rService.delete(c_code);
		System.out.println(num);
		return "success";
	}
	//댓글수정
	@RequestMapping(value="/replies/updateReply")
	public String updateReply(@RequestParam String c_code,String writer, String comment){
		System.out.println("파싱 : "+c_code+writer+comment);
		ReplyDTO rDTO = new ReplyDTO();
		rDTO.setC_code(c_code);
		rDTO.setComments(comment);
		rDTO.setWriter(writer);
		int num = rService.update(rDTO);
		System.out.println(num);
		return "success";
	}
	
	//댓글 리스트 
	@RequestMapping(value="/replies/getList", produces = "application/text; charset=UTF-8")
	public ResponseEntity getList(@RequestParam String e_code,HttpServletRequest request,HttpServletResponse response){
		System.out.println(e_code);
	    HttpHeaders responseHeaders = new HttpHeaders();
	    ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
	    response.setCharacterEncoding("UTF-8");
	        
	    // 해당 게시물 댓글
	        List<ReplyDTO> list = rService.list(e_code);
	        
	        if(list.size() > 0){
	            for(int i=0; i<list.size(); i++){
	                HashMap hm = new HashMap();
	                hm.put("c_code", list.get(i).getC_code());
	                hm.put("e_code", list.get(i).getE_code());
	                hm.put("comment", list.get(i).getComments());
	                hm.put("writer", list.get(i).getWriter());
	                
	                hmlist.add(hm);
	            }
	            
	        }
	        
	        JSONArray json = new JSONArray(hmlist);        
	        return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
	        
	}
}
	

	

