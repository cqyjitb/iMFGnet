package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import yj.core.webservice_queryXhcard.components.QueryXhcardWebserviceUtil;
import yj.core.webservice_queryXhcard.dto.QueryXhcardParam;
import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.webservice_server.dto.Rec_queryXhcard;



import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.xml.ws.Endpoint;


/**
 * Created by 917110140 on 2018/8/31.
 */
@WebService
//@WebService(endpointInterface="yj.core.webservice_server.IQueryXhcard", serviceName="QueryXhcardImp")
public class QueryXhcardImp implements IQueryXhcard{
    @Override
    public QueryXhcardReturnResult QueryXhcard(Rec_queryXhcard rec_queryXhcard) {
        QueryXhcardReturnResult rs = new QueryXhcardReturnResult();
        QueryXhcardParam param = new QueryXhcardParam();
        param.setZxhbar(rec_queryXhcard.getZxhbar());
        param.setLgort(rec_queryXhcard.getLgort());
        param.setMatnr(rec_queryXhcard.getMatnr());
        param.setQtype(rec_queryXhcard.getQtype());
        QueryXhcardWebserviceUtil queryXhcardWebserviceUtil = new QueryXhcardWebserviceUtil();
        rs = queryXhcardWebserviceUtil.receiveConfirmation(param);
        return rs;
    }
}
