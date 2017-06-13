package pl.zna.tradevalidator.splitters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import pl.zna.tradevalidator.model.TradeInfo;
import pl.zna.tradevalidator.model.TradeInfoJob;

import java.util.Date;

/**
 * Created by Zbyszek Nazimek on 13.06.2017.
 * Create TradeInfo objects from the request
 */
public class RequestSplitter {

    @Autowired
    private MessageChannel splittedChannel;

    private static Logger log = LoggerFactory.getLogger(RequestSplitter.class);

    public void translate(Message<TradeInfo[]> message ) {

        TradeInfo[] trades = message.getPayload();
        Long id = (new Date()).getTime();
        int count = trades.length;

        for ( int i = 0; i < trades.length; i++) {
            splittedChannel.send(MessageBuilder.withPayload( new TradeInfoJob(id,trades[i],count) ).
                    copyHeaders(message.getHeaders()).build());
        }
        log.debug("------------------ splitted " + trades.length + " id: " + id);
    }


}
