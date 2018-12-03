package com.next.sample.rest;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.next.sample.model.ParsingBoard;
import com.next.sample.rest.common.ResponseCode;
import com.next.sample.rest.common.RestResponse;
import com.next.sample.service.ParsingService;

@RestController
@RequestMapping("/rest")
public class ParsingRestController {

	@Autowired
	private ParsingService parsingService;
	
	
	@RequestMapping(value = "/parsing", method = RequestMethod.GET)
	public RestResponse parsing(
			@ModelAttribute("parsingBoard") ParsingBoard parsingBoard) throws IOException {

		RestResponse response = new RestResponse();
		response.setSuccess(true);
		
		int strIdx = 0;	// 파싱 할 시작 페이지
		int endIdx = 1;	// 파싱 할 마지막 페이지
		
		for(strIdx = 0; strIdx < endIdx; strIdx++) {
			
			String urladdr = "http://www.op.gg/ranking/ladder/page=" + strIdx;
			
			Document doc = (Document) Jsoup.connect(urladdr).get();
			Elements trElements = (Elements) doc.select("tr");
			
			int idx;
			
			for(idx = 1; idx < trElements.size(); idx++) {

				Elements realElements = trElements.get(idx).select("tr td");
				
				parsingBoard.setRanking(realElements.get(0).html());
				parsingBoard.setName(realElements.get(1).select("span").html());
				parsingBoard.setTear(realElements.get(2).html());
				parsingBoard.setLp(realElements.get(3).html());
				parsingBoard.setLv(realElements.get(4).html());
				parsingBoard.setShift(realElements.get(5).select("span").html());
				
				try {
					parsingService.registParsing(parsingBoard);
				} catch (Exception e) {
					e.printStackTrace();
					response.setSuccess(false);
					response.setResCode(ResponseCode.UNKOWN);
				}
				
			}
		}
		
		return response;
	}
	
}