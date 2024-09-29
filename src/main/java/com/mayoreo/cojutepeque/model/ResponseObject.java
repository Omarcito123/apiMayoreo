package com.mayoreo.cojutepeque.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseObject {
	private String message;
    private boolean success;
    private Object data;
}
