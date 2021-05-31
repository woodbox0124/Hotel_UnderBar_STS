package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.CommentDTO;
import com.dto.EventDTO;
import com.dto.EventPageDTO;
import com.service.EventService;


@Controller
public class EventController {
	@Autowired
	EventService eService;
	//시작점
	
	
	//EVENT 글쓰기 클릭 
	@RequestMapping("/loginCheck/eventWrite")
	public String eve_write() {
		return "redirect:../eventWrite";
	}
	
	//EVENT 글쓰기  
	@RequestMapping("/loginCheck/eventInsert")
	public String eve_insert(EventDTO dto) {
		eService.eventInsert(dto);
		System.out.println("eventInsert : "+dto);
		return "redirect:../event";
	}
	
	@RequestMapping("/event")
	public ModelAndView event(@RequestParam(required=false, defaultValue="1") String curPage) {
		//event 불러오기 + 페이징
		System.out.println("이벤트 curpage" + curPage);
		EventPageDTO epDTO = eService.eventList(Integer.parseInt(curPage));
		ModelAndView mav = new ModelAndView();
		mav.addObject("epDTO",epDTO);
		mav.setViewName("event");
		return mav;			
	}
	
	@RequestMapping("/eventRetrieve")
	public ModelAndView event_retrieve(@RequestParam String code) {
		//event retrieve
		EventDTO eDTO = eService.eventRetrieve(code);
		System.out.println(eDTO+": event_retrieve");
		ModelAndView mav = new ModelAndView();
		mav.addObject("eDTO",eDTO);
		mav.setViewName("eventRetrieve");
		return mav;			
	}
	
	@ResponseBody
	@RequestMapping("/event/addComment")
	public String ajax_addComment(CommentDTO cDTO, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		System.out.println(cDTO);
		
			
			eService.addCommnet(cDTO);
			System.out.println(cDTO);
		
		
		return "success";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/event/commentList", produces="application/json; charset=utf8")
	public ResponseEntity ajax_commentList(CommentDTO commentDTO, HttpServletRequest request) throws Exception{
		
		HttpHeaders responseHeaders = new HttpHeaders();
		ArrayList<HashMap> hmList = new ArrayList<HashMap>();
		String e_code = commentDTO.getE_code();
		
		//해당 게시물 댓글
		List<CommentDTO> cList = eService.commentListByCode(e_code);
		

        if(cList.size() > 0){
            for(int i=0; i<cList.size(); i++){
                HashMap map = new HashMap();
                map.put("c_code", cList.get(i).getC_code());
                map.put("comment", cList.get(i).getComments());
                map.put("writer", cList.get(i).getWriter());
                map.put("regdate", cList.get(i).getRegdate());
                
                hmList.add(map);
            }
            
        }
        
        JSONArray json = new JSONArray(hmList);        
        return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
		
	}
	
	
	
	
	
}
