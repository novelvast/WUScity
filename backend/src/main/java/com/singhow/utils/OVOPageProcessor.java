package com.singhow.utils;

import com.singhow.POJO.OVOData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Random;
/**
 * 鸥维数据
 */
@Service
public class OVOPageProcessor implements PageProcessor {


    OVOData ovoData = new OVOData();

    //    抓取⽹站的相关配置，包括编码、抓取间隔、重试次数、代理、UserAgent等
    private Site site=Site.me()
            .setCharset("utf-8")
            .setSleepTime(new Random().nextInt(20)*1000)
            .setRetryTimes(3);


    @Override
    public void process(Page page) {
        Selectable node = page.getHtml().xpath("//div[@class='citywt']");
        String intro = node.xpath("//li[@class='cs']/text()").get();
        String gdp = node.xpath("//li/t[1]/text()").get();
        String population = node.xpath("//li/t[2]/text()").get();
        String collage = node.xpath("//li/t[3]/text()").get();
        String hospital = node.xpath("//li/t[4]/text()").get();
        String cityQuantity = node.xpath("//li/n/text()").get();
        String economy = page.getHtml().xpath("//div[@class='cityjs']/div[3]/h3/text()").get();
        intro = intro.replace("</p>", "......");

        ovoData.setIntro(intro);
        ovoData.setGdp(gdp);
        ovoData.setPopulation(population);
        ovoData.setCollage(collage);
        ovoData.setHospital(hospital);
        ovoData.setCityQuantity(cityQuantity);
        ovoData.setEconomy(economy);

//        System.out.println("成功爬取ovo页面" + gdp);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public OVOData getOVODate(){
//        System.out.println("成功获取ovo页面" + ovoData.getGdp());

        return ovoData;
    }

}
