package com.aohanyao.pracite.p2

import com.aohanyao.pracite.p1.GameSkyerProcessor
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor

/**
 * Created by Administrator on 2017/8/7.
 */
class GithubPageProcessor : PageProcessor {

    private val site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)


    override fun getSite(): Site {
        return site
    }

    override fun process(page: Page) {
        //获取所有的分页连接
        val allLinks = page.html.css("div.pagination").links().all()
        //添加所有连接
        page.addTargetRequests(allLinks)
        //获取UI
        page.html.css("ul.repo-list")
//                .css("div.repo-list-item")
                .css("a.v-align-middle", "text").all().forEach { System.out.print("name:$it \n") }

    }


    companion object {

        @JvmStatic fun main(args: Array<String>) {
            Spider.create(GithubPageProcessor())
                    .addUrl("https://github.com/search?l=Java&p=1&q=stars%3A%3E1&s=stars&type=Repositories")
                    .thread(10)
                    .run()
        }
    }
}
