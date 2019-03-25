package com.myFood.order.application.filter;

import com.myFood.order.application.util.Token;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.Filter;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author reinaldo.andre
 */
@Configuration
public class AbstractAuthenticationFilter  implements Filter {

    private final String BEARER_TYPE = "Bearer";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RequestMappingHandlerMapping mappings;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        boolean authentication = false;
        try {
            if (httpServletRequest.getRequestURI().contains("swagger")
                    || httpServletRequest.getRequestURI().contains("actuator")
                    || httpServletRequest.getRequestURI().equals("/v2/api-docs")
                    || httpServletRequest.getRequestURI().equals("/favicon.ico")
                    || (mappings.getHandler(httpServletRequest)!=null && ((HandlerMethod) mappings.getHandler(httpServletRequest).getHandler()).getMethod().getDeclaredAnnotationsByType(PermitAll.class).length > 0)) {
                authentication = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AbstractAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpServletResponse httpResponse = asHttp(response);
        httpResponse.addHeader("Content-Type: text/html", "charset=utf-8");

        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        Token token = null;

        if ((authorization != null) && (authorization.startsWith(BEARER_TYPE + " "))) {
            String stringToken = authorization.substring(BEARER_TYPE.length()).trim();
            token = Token.validateToken(stringToken);
        }

        if (token == null && authentication == false) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            chain.doFilter(request, response);
        }

    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

}
