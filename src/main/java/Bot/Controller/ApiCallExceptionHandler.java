package Bot.Controller;

import Bot.DTO.Template.TextMessageTemplate;
import Bot.Service.ApiCaller;
import Bot.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiCallExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${response.httpException}")
    private String httpExceptionMessage;

    @Autowired
    private ApiCaller apiCaller;

    @Autowired
    private MessageService messageService;

    @ExceptionHandler(value = HttpClientErrorException.class)
    public void handle(){
        apiCaller.callSendAPI(new TextMessageTemplate(messageService.getRecepientId(), httpExceptionMessage));
    }

}
