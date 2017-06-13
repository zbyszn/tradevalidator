package pl.zna.tradevalidator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.http.Http;
import org.springframework.messaging.MessageChannel;
import pl.zna.tradevalidator.splitters.RequestSplitter;
import pl.zna.tradevalidator.ResponseTranslator;
import pl.zna.tradevalidator.model.TradeInfo;

/**
 * Created by Zbyszek Nazimek on 13.06.2017.
 */
@Configuration
@EnableIntegration
public class TradeValidatorConfiguration {

    @Bean
    public MessageChannel requestChannel() {
        return MessageChannels.direct().get();
    }
    @Bean
    public MessageChannel splittedChannel() {
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
                        requestPayloadType(TradeInfo[].class)).
                get();

    }

    @Bean
    @Splitter(inputChannel = "requestChannel", outputChannel = "splittedChannel")
    public RequestSplitter splitRequest() {
        return new RequestSplitter();
    }

    @Bean
    @Transformer(inputChannel = "splittedChannel", outputChannel = "responseChannel")
    public ResponseTranslator transformResponse() {
        return new ResponseTranslator();
    }

}
