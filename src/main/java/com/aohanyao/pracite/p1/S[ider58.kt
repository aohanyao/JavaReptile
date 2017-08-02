package com.aohanyao.pracite.p1

import javafx.scene.control.Slider
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor

/**
 * Created by Administrator on 8/2/2017.
 */
class Spider58 : PageProcessor {


    override fun getSite(): Site {
        return Site.me().setRetryTimes(3).setTimeOut(10000).setSleepTime(1000)
    }

    override fun process(page: Page) {
        //                   .xpath("//a[@class='car-a']/text()")
        page.html.css("ul.listUl")
                .nodes()
                .forEach {

                    System.out.println(it)

                }
//        System.out.println(page.html.toString())
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Spider.create(Spider58())
                    .addUrl("http://sz.58.com/xixiangsz/chuzu/b9/")
                    .thread(3)
                    .run()
        }
    }

}