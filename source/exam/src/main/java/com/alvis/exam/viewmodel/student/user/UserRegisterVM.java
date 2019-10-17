package com.alvis.exam.viewmodel.student.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterVM {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String code;

    private Integer userLevel;

    private String telphone;

    private String duty;

	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
}