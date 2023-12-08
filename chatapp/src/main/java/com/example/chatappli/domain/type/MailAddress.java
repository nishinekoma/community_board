package com.example.chatappli.domain.type;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class MailAddress implements Serializable {
	@Email
	@NotNull
	private final String value;
	
	public static MailAddress from(String mailAddress) {
		return new MailAddress(mailAddress);
	}
	
	@Override
	public String toString() {
		if(Objects.isNull(value)) {
			return "";
		}
		return value;
	}

}
