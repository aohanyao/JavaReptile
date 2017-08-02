package com.aohanyao.pracite.p1

import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor

/**
 * Created by Administrator on 8/2/2017.
 */
class GuaziWang : PageProcessor {
    override fun getSite(): Site {
        return Site.me().setRetryTimes(3).setTimeOut(10000).setSleepTime(1000)
    }

    override fun process(page: Page) {
        // .xpath("//a[@class='tt']/text()")
        page.html
                .xpath("//a[@class='car-a']/text()")
                .xpath(   "//a[@class='tt']/text()")
                .all()
                .forEach { System.out.println(it) }

        page.html.all().forEach { System.out.println(it) }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Spider.create(GuaziWang())
                    .addUrl("https://www.guazi.com/sz/buy/")
//                    .addPipeline( FilePipeline("D:\\webmagic\\"))
                    .thread(5)
                    .run()
        }
    }

}