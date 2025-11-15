package com.yourssu.config;

import com.yourssu.annotation.VersionedRestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class VersionedRestControllerConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new VersionedRequestMappingHandlerMapping();
    }

    private static class VersionedRequestMappingHandlerMapping
            extends RequestMappingHandlerMapping {

        @Override
        protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
            RequestMappingInfo info = super.getMappingForMethod(method, handlerType);

            VersionedRestController annotation =
                    AnnotationUtils.findAnnotation(handlerType, VersionedRestController.class);

            if (annotation != null && info != null) {
                String prefix = String.format("/api/v%d%s",
                        annotation.version(),
                        annotation.basePath().startsWith("/") ?
                                annotation.basePath() : "/" + annotation.basePath());

                return RequestMappingInfo.paths(prefix).build().combine(info);
            }

            return info;
        }
    }
}