package com.bees.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean externallyConfiguredFlag;

    private final ResponseMessage m201 = customMessage01();
    private final ResponseMessage m204put = simpleMessage(204, "Atualização OK!");
    private final ResponseMessage m204del = simpleMessage(204, "Deleção OK!");
    private final ResponseMessage m403 = simpleMessage(403, "Não Autorizado!");
    private final ResponseMessage m404 = simpleMessage(404, "Não Encontrado!");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de Validação!");
    private final ResponseMessage m500 = simpleMessage(500, "Erro Inesperado!");

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }

    private ResponseMessage customMessage01() {
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI do novo recurso criado", new ModelRef("string")));

        return new ResponseMessageBuilder()
                .code(201)
                .message("Recurso criado!")
                .headersWithDescription(map)
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(externallyConfiguredFlag)

                .useDefaultResponseMessages(false)

                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))

//				.host("http://localhost:8081/api/")

                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bees.controllers"))
//				.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Challenge | Locadora de Carros - Documentação da API",
                "Estas API's são utilizadas no projeto Challenge | Locadora de Carros :: Bees Brasil",
                "Versão API 1.0",
                "https://www.bees.com/terms",
                new Contact(
                        "Bees Brasil",
                        "bees.com/user/glaucio-teixeira",
                        "glaucio.teixeira@outlook.com"),
                "Uso permitido sob licença.",
                "https://www.bees.com/terms",
                Collections.emptyList());
    }
}
