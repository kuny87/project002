package com.next.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.sample.mapper.ParsingMapper;
import com.next.sample.model.ParsingBoard;
import com.next.sample.service.ParsingService;

@Service
public class ParsingServiceImpl implements ParsingService {

	@Autowired
	private ParsingMapper parsingMapper;
	
	@Override
	public boolean registParsing(ParsingBoard parsingBoard) {
		return parsingMapper.insertParsingBoard(parsingBoard) == 1;
	}

}
