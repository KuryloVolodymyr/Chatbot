package bot.service.test;

import bot.service.ApiCaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("testApiCaller")
public class TestApiCaller extends ApiCaller {

    @Value("${url.test.sendAPIURL}")
    private void setSendApiUrl(String url) {
        this.sendAPIURL = url;
    }

}
