package com.rfjavacertified.utils;

import com.rfjavacertified.beans.QuestionAnswer;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonResolver {

	/**
	 * Mehtod to resolve question answer for json file
	 * 
	 * @param path
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static QuestionAnswer resolveQuestionAnswer(String path)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		QuestionAnswer questionAnswer = mapper.readValue(new File(path), QuestionAnswer.class);
		return questionAnswer;
	}

	/**
	 * Mehtod to resolve question answer for json file url
	 * 
	 * @param path
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static QuestionAnswer resolveQuestionAnswer(URL path)
			throws JsonParseException, JsonMappingException, IOException {
		return resolveQuestionAnswer(path.getFile());
	}

}
