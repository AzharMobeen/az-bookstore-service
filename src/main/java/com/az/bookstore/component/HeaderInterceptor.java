package com.az.bookstore.component;

import com.az.bookstore.exception.CustomRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.az.bookstore.constant.Constants.API_ACCESS_KEY;

/**
 * @author Azhar Mobeen
 * @since 29/09/2022
 */

@Slf4j
@Component
public class HeaderInterceptor implements HandlerInterceptor {

    @Value("${accessToken}")
    private String accessToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(request.getHeader(API_ACCESS_KEY) != null && StringUtils.hasText(request.getHeader(API_ACCESS_KEY))) {
            String apiKey = request.getHeader(API_ACCESS_KEY);
            log.info("API_ACCESS_KEY received {}", apiKey);
            validateApiKey(apiKey);
        }
        return true;
    }

    private void validateApiKey(String apiKey) {

        if(!accessToken.equals(apiKey)){
            throw new CustomRuntimeException("Invalid API Key", "Provided API key { "+ apiKey +" } invalid",
                    HttpStatus.BAD_REQUEST);
        }
    }
}