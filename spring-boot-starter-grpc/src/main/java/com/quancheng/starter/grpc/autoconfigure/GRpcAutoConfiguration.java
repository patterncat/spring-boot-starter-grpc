package com.quancheng.starter.grpc.autoconfigure;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quancheng.starter.grpc.GRpcReferenceRunner;
import com.quancheng.starter.grpc.GRpcServerRunner;
import com.quancheng.starter.grpc.GRpcService;

@Configuration
@ConditionalOnProperty(prefix = "grpc", name = "consulIp")
@EnableConfigurationProperties(GRpcServerProperties.class)
public class GRpcAutoConfiguration {

    private final GRpcServerProperties grpcProperty;

    public GRpcAutoConfiguration(GRpcServerProperties grpcProperty){
        this.grpcProperty = grpcProperty;
    }

    @Bean
    @ConditionalOnBean(value = GRpcServerProperties.class, annotation = GRpcService.class)
    public GRpcServerRunner grpcServerRunner() {
        return new GRpcServerRunner();
    }

    @Bean
    public BeanPostProcessor grpcReferenceRunner() {
        return new GRpcReferenceRunner(grpcProperty);
    }

}