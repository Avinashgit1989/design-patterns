package com.learnwithak.user.service.config;

import com.learnwithak.common.service.queries.GetUserPaymentDetailsQuery;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {
    @Bean
    @Primary
    public XStreamSerializer xStreamSerializer() {
        XStream xStream = new XStream();
        // Enables all classes, use with caution
        xStream.addPermission(AnyTypePermission.ANY);


        // Allow specific classes, including GetUserPaymentDetailsQuery
        xStream.allowTypes(new Class[]{GetUserPaymentDetailsQuery.class});

        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }
}
