package com.mayoreo.cojutepeque.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseLogin {
    private long iduser;
    private long idrol;
    private String firstname;
	private String username;
	private String dateborn;
	private String rolname;
}
