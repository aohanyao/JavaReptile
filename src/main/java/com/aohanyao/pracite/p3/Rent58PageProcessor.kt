package com.aohanyao.pracite.p3

import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor

/**
 *
 * 58租房爬虫
 */
class Rent58PageProcessor : PageProcessor {


    private val site = Site.me().setRetryTimes(10000).setSleepTime(10000).setTimeOut(10000)


    override fun getSite(): Site {
        return site
    }

    override fun process(page: Page) {
        //----------------------------------分析详情页面 start
        val url = page.url.toString()
        if (url.contains("com/zufang")) {//进入了详情
            //标题
            val houseTitle = page.html.xpath("//div[@class='house-title']/h1/text()").get()
            //时间
            val time = page.html.xpath("//div[@class='house-title']/p/text()").replace("次浏览", "").get()
            //价格
            val price = page.html.xpath("//b[@class='f36']/text()").get()
            System.out.println("--------------------------开始----------------------------")
            System.out.println("\t\t链    接：${url.trim()}")
            System.out.println("\t\t名    称：${houseTitle.trim()}")
            System.out.println("\t\t时    间：${time.trim()}")
            System.out.println("\t\t价    格：${price.trim()}元/月")

            //获取ul
            page.html.xpath("//ul[@class='f14']/li/span/text()")
                    .nodes()
                    .map { it.get() }
                    .filter {
                        it != null && "null" != it && it.isNotBlank()
                    }
                    .filter { !it.contains("所在小区") }
                    .filter { !it.contains("所属区域") }
                    .forEach {
                        if (it.contains("：")) {
                            System.out.print("\t\t${it.trim()}")
                        } else {
                            System.out.println(it.trim())
                        }
                    }

            System.out.println("--------------------------结束----------------------------\n")

        }
        //----------------------------------分析详情页面 end

        //获取详情链接
        val detailLinks = page.html.xpath("//div[@class='des']/h2/a").links().all()
        //添加详情链接到爬虫中
        page.addTargetRequests(detailLinks)

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