package com.example.shatapp;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class HelloController {
	
	@GetMapping("/hello")
	public String hellopage(Model model) {
		return "hello";//hello.thmlのテンプレート名
	}
}
//最初の参考サイト
//https://zenn.dev/angelica/books/52be1e365c61ea/viewer/7b55e1
