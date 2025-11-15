package com.yourssu.resolver;

import com.yourssu.annotation.PieceHeader;
import com.yourssu.domain.Piece;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PieceHeaderResolver implements HandlerMethodArgumentResolver {

    private static final String HEADER_NAME = "X-Player";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PieceHeader.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        PieceHeader annotation = parameter.getParameterAnnotation(PieceHeader.class);
        String headerValue = webRequest.getHeader(HEADER_NAME);

        if (headerValue == null) {
            if (annotation.required()) {
                throw new IllegalArgumentException("Missing required header: " + HEADER_NAME);
            }
            return null;
        }

        return Piece.from(headerValue);
    }
}