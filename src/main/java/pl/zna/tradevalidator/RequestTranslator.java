package pl.zna.tradevalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.util.LinkedMultiValueMap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zbyszek Nazimek on 13.06.2017.
 */
public class RequestTranslator {

    public List<TradeInfo> translate(LinkedMultiValueMap request) {


        System.out.println("------------------ " + request.keySet());
        return new LinkedList<TradeInfo>();
    }


}
