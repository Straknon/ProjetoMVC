package br.com.group.sicar.exception;

import java.io.Serializable;
import java.util.List;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer error;
	private List<String> messages;
	private String path;

	
	public StandardError(Long timestamp, Integer error, List<String> messages,String path) {
		super();
		this.timestamp = timestamp;
		this.error = error;
		this.messages = messages;
		this.path = path;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}
