package com.aohanyao.pracite.p2

import com.aohanyao.pracite.p1.Spider58
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.pipeline.JsonFilePipeline
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
        //page.addTargetRequests(allLinks)

        //获取房源信息---------------列表
        page.html
                .css("ul.listUl li")
                .nodes()
                .forEach {
                    val nameItem = it.xpath("//div[@class='des']/h2/a")
                    //获取名称
                    val name = it.xpath("//div[@class='des']/h2/a/text()").get()
                    //连接
                    val link = it.xpath("//div[@class='des']/h2/a").links().get()
                    //图片数量
                    val imageCount = it.xpath("//span[@class='picNum']/text()").get()
                    //获取房间
                    val room = it.xpath("//p[@class='room']/text()").toString()
                    //地址
                    val add = it.xpath("//p[@class='add']/text()").get()
                    //时间
                    val time = it.xpath("//div[@class='sendTime']/text()").get()
                    //价格
                    val money = it.xpath("//div[@class='money']/b/text()").get()
                    //来源
                    val geren = it.xpath("//p[@class='geren']/text()").get()
                    System.out.println("--------------------------开始----------------------------")
                    System.out.println("\t\t名    称：${name.trim()}")
                    System.out.println("\t\t房室面积：${room.trim()}")
                    System.out.println("\t\t地    址：${add.trim()}")
                    System.out.println("\t\t图    片：${imageCount.trim()}")
                    System.out.println("\t\t价    格：${money.trim()}元/月")
                    System.out.println("\t\t来    源：${geren.trim().replace("：", "")}")
                    System.out.println("\t\t更新时间：${time.trim()}")
                    System.out.println("\t\t链    接：${link.trim()}")
                    System.out.println("--------------------------结束----------------------------\n")
                }


    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Spider.create(Rent58PageProcessor())
                    .addUrl("http://sz.58.com/xixiangsz/chuzu/0/b9/")
//                    .addPipeline(JsonFilePipeline("D:\\webmagic\\"))
                    .thread(1)
                    .run()
        }
    }
}
