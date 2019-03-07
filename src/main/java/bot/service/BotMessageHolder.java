package bot.service;

import bot.dto.Template.MessageTemplate;
import bot.dto.Template.test.TestTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotMessageHolder {

    private TestTemplate message;

    public void setMessage(TestTemplate message) {
        this.message = message;
    }

    public TestTemplate getMessage() {
        return this.message;
    }

    public String getMock() {
        return "Mock";
    }

}
