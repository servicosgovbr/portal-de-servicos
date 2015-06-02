package br.gov.servicos.piwik;

import br.gov.servicos.IOUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestTemplateErrorLogger implements ResponseErrorHandler {

    ResponseErrorHandler errorHandler;

    @Autowired
    public RestTemplateErrorLogger(DefaultResponseErrorHandler handler) {
        errorHandler = handler;
    }

    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    public void handleError(ClientHttpResponse response) throws IOException {
        log.debug("Status code: {}", response.getStatusCode());
        log.debug("header", response.getHeaders());
        log.debug("Body: {}", IOUtils.toString(response.getBody()));
    }

}