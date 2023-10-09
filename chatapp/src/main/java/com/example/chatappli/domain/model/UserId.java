package com.example.chatappli.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)//private コンストラクタ
@Getter
public class UserId {
    private final String value;

    public static UserId form(String userName) {
        return new UserId(userName);
    }

    @Override
    public String toString() {
     return value;
    }
    /*
    * fnv132ハッシュによる変換
    * @return fnv132ハッシュされた文字列
    * */
    public String toHash() {
        final int FNV_32_PRIME = 0x01000193;
        int hval = 0x811c9dc5;

        byte[] bytes = value.getBytes();//文字をバイト・シーケンスにエンコード化　結果を新規バイト配列に格納

        int size = bytes.length;

        for (byte aByte : bytes) {
            hval *= FNV_32_PRIME;
            hval ^= aByte;
        }
        System.out.println(hval);
        return Integer.toHexString(hval);//基数16にして返す
    }
}
