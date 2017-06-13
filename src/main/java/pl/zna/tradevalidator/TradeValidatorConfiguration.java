package pl.zna.tradevalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.http.Http;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Zbyszek Nazimek on 13.06.2017.
 */
@Configuration
@EnableIntegration
public class TradeValidatorConfiguration {

    @Bean(name="requestChannel")
    public MessageChannel requestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel responseChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow validationFlow() {
        return IntegrationFlows.
                from(Http.inboundGateway("/service/validate").
                        requestChannel(requestChannel()).
                        replyChannel("responseChannel").
                        requestPayloadType(String.class)).
                get();

    }

    @Bean
    @Transformer(inputChannel = "requestChannel", outputChannel = "transChannel")
    public RequestTranslator transformRequest() {
        return new RequestTranslator();
    }

    @Bean
    @Transformer(inputChannel = "transChannel", outputChannel = "responseChannel")
    public ResponseTranslator transformREsponse() {
        return new ResponseTranslator();
    }

}
