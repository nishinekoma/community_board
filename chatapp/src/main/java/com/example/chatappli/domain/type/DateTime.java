package com.example.chatappli.domain.type;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTime {
	private final LocalDateTime value;
	private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	
	public static DateTime from(LocalDateTime dateTime) {
		return new DateTime(dateTime);
	}
	
	@Override
	public String toString() {//toString はClassName@Hashの無意味情報より意義ある情報を返すため時間を返す。
		System.out.println(value.format(formatter));
		return value.format(formatter);
	}
}
