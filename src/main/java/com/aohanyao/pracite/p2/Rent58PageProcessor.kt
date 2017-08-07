package com.aohanyao.pracite.p2

import com.aohanyao.pracite.p1.Spider58
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor

/**
 * Created by Administrator on 2017/8/7.
 */
class Rent58PageProcessor : PageProcessor {

    private val site = Site.me().setRetryTimes(3).setSleepTime(4000).setTimeOut(10000)


    override fun getSite(): Site {
        return site
    }


    override fun process(page: Page) {
        //获取分页
        val allLinks = page.html.css("div.pager").links().all()
        page.addTargetRequests(allLinks)

        //获取房源信息ul
        page.html.css("ul.listUl").nodes()
                .apply {

                }


    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Spider.create(Rent58PageProcessor())
                    .addUrl("http://sz.58.com/xixiangsz/chuzu/0/b9/")
                    .thread(1)
                    .run()
        }
    }
}
