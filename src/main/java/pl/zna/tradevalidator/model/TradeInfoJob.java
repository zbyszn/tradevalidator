package pl.zna.tradevalidator.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zbyszek Nazimek on 13.06.2017.
 */
public class TradeInfoJob {
    private Long id;
    private TradeInfo info;
    private List<String> errors;
    private int objectsInRequest;

    public TradeInfoJob(Long id, TradeInfo info, int objectsInRequest ) {
        this.id = id;
        this.info = info;
        this.objectsInRequest = objectsInRequest;
        errors = new LinkedList<String>();
    }

    public Long getId() {
        return id;
    }

    public TradeInfo getInfo() {
        return info;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getObjectsInRequest() {
        return objectsInRequest;
    }

}
