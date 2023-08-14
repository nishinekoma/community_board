package com.example.shatapp;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
@Controller//SpringFrameworkのDIコンテナ(インスタンスを予め生成し、保管している箱)へこのクラスを登録する
@RequiredArgsConstructor
public class HelloController {
	
	@GetMapping("/hello")//HttpメソッドとURLのパスが一致した時にControllerが採用さえる。　localhost8080/hello
	public String hellopage(Model model) {//メソッドの名前は任意。
		return "hello";//この文字列は　文字列.htmlになる。　どのURLを返すかを指す。
	}
}
//最初の参考サイト
//https://zenn.dev/angelica/books/52be1e365c61ea/viewer/7b55e1
