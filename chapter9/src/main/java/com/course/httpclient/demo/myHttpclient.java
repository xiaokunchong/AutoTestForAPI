package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class myHttpclient {

    @Test
    public void test1() throws IOException {

        //占位，用来存放返回结果
        String result;

        HttpGet get = new HttpGet("http://www.baidu.com");

//       使用 HttpClientBuilder.create().build()替换DefaultHttpClient的方法
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(get);

        result = EntityUtils.toString(response.getEntity());
        System.out.println("响应信息是：" + result);



        }






}
