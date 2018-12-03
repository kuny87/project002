package com.next.sample.mapper;

import org.springframework.stereotype.Repository;

import com.next.sample.model.ParsingBoard;

@Repository
public interface ParsingMapper {

	int insertParsingBoard(ParsingBoard parsingBoard);
	
}
