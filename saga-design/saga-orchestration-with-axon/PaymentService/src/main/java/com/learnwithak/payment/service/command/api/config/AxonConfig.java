package com.learnwithak.payment.service.command.api.config;

import com.learnwithak.common.service.queries.GetUserPaymentDetailsQuery;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {
    @Bean
    @Primary
    public XStreamSerializer xStreamSerializer() {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);  // Enable all permissions, but refine as needed
        xStream.allowTypes(new Class[]{GetUserPaymentDetailsQuery.class });
        return XStreamSerializer.builder().xStream(xStream).build();
    }
}
