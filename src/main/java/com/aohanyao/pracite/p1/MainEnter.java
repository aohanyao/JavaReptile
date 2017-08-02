package com.aohanyao.pracite.p1;

import us.codecraft.webmagic.Spider;

/**
 * Created by Administrator on 8/2/2017.
 */
public class MainEnter {
    public static void main(String[] args) {
        Spider.create(new GameSkyerProcessor())
//                .addUrl("https://github.com/code4craft")
                .addUrl("http://www.gamersky.com/ent/")
                .thread(5)
                .run();
    }
}
