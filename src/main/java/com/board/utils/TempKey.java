/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-16
 Time: 오후 7:15
 desc: 인증메일 인증키 생성 클래스

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.utils;

import java.util.Random;

public class TempKey {
    private int size;
    private boolean lowerCheck;
    
    public String getKey(int size, boolean lowerCheck) {
        this.size = size;
        this.lowerCheck = lowerCheck;
        return init();
    }

    private String init() {
        Random random = new Random();
        StringBuffer str = new StringBuffer();
        int num = 0;

        do {
            num = random.nextInt(75) + 48;
            if (isAbleRandom(num)){
                str.append((char)num);
            } else {
                continue;
            }
        } while (str.length() < size);

        if(lowerCheck)
            return str.toString().toLowerCase();
        return str.toString();
    }

    private boolean isAbleRandom(int random) {
        return (random >= 48 && random <= 57) || (random >= 65 && random <= 90) || (random >= 97 && random <= 122);
    }
}
