package com.example.chatappli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//https://kanda-it-school-kensyu.com/java-spring-contents/sb_ch04/sb_0402/#:~:text=6%E8%A1%8C%E7%9B%AE%E3%81%AB%E4%BB%98%E3%81%84,%E3%81%82%E3%82%8B%E3%81%93%E3%81%A8%E3%82%92%E7%A4%BA%E3%81%97%E3%81%BE%E3%81%99%E3%80%82

@SpringBootApplication//他に設定ファイルなどを一切書かなくとも
public class ChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);//runはアプリケーションを起動する時のメソッド
	}
}
//Springbootにはmainメソッドが必須。ないとエラーになる。