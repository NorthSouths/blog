package com.myren.submit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HDUSubmitter {

    private Submission submission;
    private Result result;
    private String userName;
    private String password;
    private CloseableHttpClient httpClient = HttpClients.custom().setConnectionManagerShared(true).build();
    private RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)
            .setConnectionRequestTimeout(500)
            .setSocketTimeout(10 * 1000)
            .build();

    public HDUSubmitter(String userName, String password) throws IOException {
        this.userName = userName;
        this.password = password;
        login();
    }
    private void login() throws IOException {
        HttpPost httpPost = new HttpPost("https://acm.hdu.edu.cn/userloginex.php?action=login&cid=0&notice=0");
        ArrayList<NameValuePair> params = new ArrayList<>();
        //创建表单的Entity对象,第一个参数就是封装好的表单数据，第二个参数就是编码
        params.add(new BasicNameValuePair("username", userName));
        params.add(new BasicNameValuePair("userpass", password));
        params.add(new BasicNameValuePair("login", "Sign In"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");
        httpPost.setEntity(formEntity);
        httpClient.execute(httpPost);
    }

    private void submit() throws IOException {
        HttpPost httpPost = new HttpPost("https://acm.hdu.edu.cn/submit.php?action=submit");
        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("check", "0"));
        params.add(new BasicNameValuePair("language", "0"));
        params.add(new BasicNameValuePair("problemid", submission.getOriginProblemId()));
        params.add(new BasicNameValuePair("usercode", submission.getSourceCode()));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "utf8");
            System.out.println(content);
        } else {
            System.out.println(response.getStatusLine());
        }
    }


    public void getAns() throws Exception {
        URIBuilder uriBuilder = new URIBuilder("http://acm.hdu.edu.cn/status.php");
        uriBuilder.setParameter("user", userName);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        int count = 0;
        while (true) {
            Thread.sleep(1000);
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                // 当获取失败时，等待两秒后重新尝试
                if (response.getStatusLine().getStatusCode() != 200) {
                    Thread.sleep(2000);
                    count++;
                    continue;
                }
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                Document doc = Jsoup.parse(content);
                Elements tds = doc.select("#fixed_table > table > tbody > tr:nth-child(3) > td");
                String status = tds.get(2).text();

                // 当处于等待或者判题状态的时候，就循环等待
                if (status.contains("ing")) {
                    continue;
                }
                String problemId = tds.get(3).text();
                String time = tds.get(4).text();
                String memory = tds.get(5).text();
                String language = tds.get(7).text();

                result = new Result();
                result.setLanguage(language).setMemory(memory).setTime(time).setStatus(status).setProblemId(problemId);
                System.out.println(result);
                System.out.println(submission.getOriginProblemId());
                System.out.println(result.getStatus());
                break;
            } catch (Exception e) {

            } finally {
                // 当累计四次失败后，直接抛出异常， 可能是网络出现了问题。或者hdu服务器出现问题。
                if (count > 4) {
                    throw new Exception();
                }
            }
        }
    }
    private void wait2SubmitTimeLimit() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void work() {
        try {
            try {
                submit();
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(2000);
                // 长时间未提交会自动注销，所以第一次提交失败后，先尝试登录一下。
                login();
                Thread.sleep(2000);
                submit();
            }
            getAns();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

