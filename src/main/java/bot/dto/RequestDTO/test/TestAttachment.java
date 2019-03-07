package bot.dto.RequestDTO.test;

import bot.dto.Elements.test.TestPayload;

public class TestAttachment {
    String type;
    TestPayload payload;

    public TestAttachment(String type, TestPayload payload){
        this.type = type;
        this.payload = payload;
    }

    public TestAttachment(){super();}


    public String getType() {
        return type;
    }

    public TestPayload getPayload() {
        return payload;
    }

    public void setPayload(TestPayload payload) {
        this.payload = payload;
    }

    public void setType(String type) {
        this.type = type;
    }
}
