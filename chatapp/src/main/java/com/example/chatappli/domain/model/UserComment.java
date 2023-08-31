package com.example.chatappli.domain.model;

import java.util.Random;

import  com.example.chatappli.domain.type.Comment;
import  com.example.chatappli.domain.type.MailAddress;
import  com.example.chatappli.domain.type.Name;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


/* ドメインオブジェクトを定義　ドメイン（問題領域：アプリやWebサービスが提供する活動そのもの）をあらわす　⇒　そのアプリを構成する機能それぞれを組み立てているロジックを表現するもの。
 * ドメインオブジェクトは　業務データ、すなわちバリューオブジェクトを使ってデータの判断、加工を行って機能を表現する。
 * ここでは　「ユーザが掲示板に書き込んだデータをあらわす　ＵｓｅｒＣｏｍｍｅｎｔモデルを作っている。」*/
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserComment {
	private final Name name;
	private final MailAddress mailAddress;
	private final Comment comment;
	
	public static UserComment from(String name, String mailAddress, String comment) {
		return new UserComment(
				Name.from(name),//返り値　new Name(String name)
				MailAddress.from(mailAddress),
				Comment.from(comment)
		);
	}
	/*
	 * 名前が[るきや　たかなお　ちばっち]　ならば　何かを返す
	 * そうでなければNameをそのまま返す。
	 * ＠return 名前
	 */
	  /**
	   * 名前が[!omikuji]ならばおみくじの結果を返す.
	   * そうでないならばNameをそのまま返す.
	   * @return なまえ
	   */
	  public Name getName() {
	    if(!name.equals("!omikuji")) return name;

	    int random = new Random().nextInt(3);

	    switch (random) {
	      case 0:
	        return Name.from("大吉");
	      case 1:
	        return Name.from("中吉");
	      default:
	        return Name.from("小吉");
	    }
	  }
}
