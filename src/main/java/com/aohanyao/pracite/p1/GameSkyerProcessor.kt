package com.aohanyao.pracite.p1

import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.pipeline.FilePipeline
import us.codecraft.webmagic.pipeline.JsonFilePipeline
import us.codecraft.webmagic.processor.PageProcessor

/**
 * Created by Administrator on 8/2/2017.
 */
class GameSkyerProcessor : PageProcessor {

    private val site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)

    override fun process(page: Page) {
        page.html
                .xpath("//a[@class='tt']/text()")
                .all()
                .forEach {
                    //System.out.println(it)
                }
        page.html
                .all()
                .forEach {
                    //                    System.out.println(it)
                }
        //like
        val xpath = page.html.xpath("//div[@class='tit'")
        xpath.all()
                .forEach {
                    System.out.println(it)
                }

        xpath.xpath("//a[@class='tt']").apply {
            xpath("/text()").all().forEach { System.out.println(it) }
        }


    }

    override fun getSite(): Site {
        return site
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            Spider.create(GameSkyerProcessor())
                    .addUrl("http://www.gamersky.com/ent/")
//                    .addPipeline( FilePipeline("D:\\webmagic\\"))
                    .thread(5)
                    .run()
        }
    }
}