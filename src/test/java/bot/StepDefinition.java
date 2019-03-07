package bot;

import bot.dto.Elements.test.TestGenericElement;
import bot.dto.RequestDTO.RequestData;
import bot.dto.Template.test.TestTemplate;
import bot.service.BotMessageHolder;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class StepDefinition extends MarvelBotTest {

    @Autowired
    private BotMessageHolder messageHolder;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${test.value}")
    private Boolean testValue;

    @When("^i write \"(.*)\"")
    public void callResourceWithRequest(String resource) {

//        RequestData data = new RequestData(resource);
        RequestData data = RequestData.buildTextRequest(resource);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestData> entity = new HttpEntity<>(data, headers);
        restTemplate.postForObject("https://a2d91605.ngrok.io/test/message", entity, String.class);
    }


    @Then("^i get \"(.*)\"")
    public void receiveResponse(String response) {
        Assert.assertEquals(messageHolder.getMessage().getMessage().getText(), response);
    }

    @When("^i click quick reply with title \"(.*)\" and  payload \"(.*)\"")
    public void clickQuickReply(String title, String payload) {
        RequestData data = RequestData.buildQuickReplyRequest(title, payload);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestData> entity = new HttpEntity<>(data, headers);
        restTemplate.postForObject("https://a2d91605.ngrok.io/test/message", entity, String.class);
    }

    @Then("i get template with title \"(.*)\" and \"(.*)\" button and \"(.*)\" button and \"(.*)\" button")
    public void receiveTemplateHeroResponse(String title, String firstButton, String secondButton, String thirdButton) {
        List<TestGenericElement> testTemplates = messageHolder.getMessage().getMessage().getAttachment().getPayload().getElements();
        Assert.assertTrue(testTemplates.get(0).getTitle().equals(title) &&
                testTemplates.get(0).getButtons().get(0).getTitle().equals(firstButton) &&
                testTemplates.get(0).getButtons().get(1).getTitle().equals(secondButton) &&
                testTemplates.get(0).getButtons().get(2).getTitle().equals(thirdButton));
    }

}
