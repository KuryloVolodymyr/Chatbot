package bot.service.test;

import bot.service.ApiCaller;
import bot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("testMessageService")
public class TestMessageService extends MessageService {

    @Autowired
    @Qualifier("testApiCaller")
    private void setApiCaller(ApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

}
