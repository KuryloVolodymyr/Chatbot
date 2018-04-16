package Bot.Controller;

import Bot.DTO.RequestDTO.Messaging;
import Bot.DTO.RequestDTO.RequestData;
import Bot.DTO.Template.TextMessageTemplate;
import Bot.Service.ApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiCallExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private ApiCaller apiCaller;

    @Value("${response.httpException}")
    private String httpExceptionMessage;

    @ExceptionHandler(value = HttpClientErrorException.class)
    public void handle(){
        //TODO
    }

}
