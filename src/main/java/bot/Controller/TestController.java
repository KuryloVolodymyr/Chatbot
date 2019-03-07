package bot.controller;

import bot.dto.RequestDTO.Entry;
import bot.dto.RequestDTO.Messaging;
import bot.dto.RequestDTO.RequestData;
import bot.dto.Template.test.TestTemplate;
import bot.service.ApiCaller;
import bot.service.BotMessageHolder;
import bot.service.test.TestMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private BotMessageHolder botMessageHolder;

    @Autowired
    private ApiCaller testApiCaller;

    @Autowired
    private TestMessageService messageService;


    // on test
    @RequestMapping(value = "/test/receive", method = RequestMethod.POST)
    public void getTestResponse(@RequestBody TestTemplate response) {
        botMessageHolder.setMessage(response);
    }


//    on deployed

    @RequestMapping(value = "/test/message", method = RequestMethod.POST)
    public void processTestMessage(@RequestBody RequestData data) {
        System.out.println("\n\n\nKEK\n\n\n");
        for (Entry entry : data.getEntry()) {
            for (Messaging request : entry.getMessaging()) {
                messageService.processRequest(request);
            }
        }
    }


}
