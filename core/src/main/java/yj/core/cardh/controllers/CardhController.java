package yj.core.cardh.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.apache.bcel.generic.IF_ACMPEQ;
import org.apache.http.protocol.HTTP;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardhst.dto.Cardhst;
import yj.core.cardhst.service.ICardhstService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.cardt.service.impl.CardtServiceImpl;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.sccl.dto.Sccl;
import yj.core.sccl.service.IScclService;
import yj.core.wiparea.dto.Area;
import yj.core.wiparea.service.IAreaService;
import yj.core.wiptrasfer.dto.Trasfer;
import yj.core.wiptrasfer.service.ITrasferService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;

@Controller
public class CardhController
        extends BaseController {
    @Autowired
    private ICardhService service;
    @Autowired
    private IAfvcService afvcService;
    @Autowired
    private ICardtService cardtService;
    @Autowired
    private IXhcardService xhcardService;
    @Autowired
    private IAfkoService afkoService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private ICardhstService cardhstService;
    @Autowired
    private IScclService scclService;
    @Autowired
    private IInputLogService inputLogService;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private ITrasferService trasferService;


    @RequestMapping({"/sap/cardh/query"})
    @ResponseBody
    public ResponseData query(Cardh dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(this.service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/cardh/queryAfterSort"})
    @ResponseBody
    public ResponseData queryAfterSort(Cardh dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(this.service.queryAfterSort(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/cardh/queryZuheAfterSort"})
    @ResponseBody
    public ResponseData queryZuheAfterSort(Cardh dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(this.service.queryZuheAfterSort(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/cardh/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Cardh> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(this.service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/sap/cardh/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Cardh> dto) {
        this.service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping({"/sap/cardh/makecard"})
    @ResponseBody
    public ResponseData makeCard(Afko dto, HttpServletRequest request) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Afko afko = dto;

        Afko afkotmp = afkoService.selectByAufnr(dto.getAufnr());
        String status = afkotmp.getStatus();
        if (status.indexOf("CRTD") != -1){
            ResponseData rs = new ResponseData();
            rs.setSuccess(false);
            rs.setMessage("订单状态为CRTD不允许创建流转卡!");
            return rs;
        }

        if (status.indexOf("TECO") != -1){
            ResponseData rs = new ResponseData();
            rs.setSuccess(false);
            rs.setMessage("订单状态为TECO不允许创建流转卡!");
            return rs;
        }

        if (status.indexOf("结算") != -1){
            ResponseData rs = new ResponseData();
            rs.setSuccess(false);
            rs.setMessage("订单状态为结算不允许创建流转卡!");
            return rs;
        }

        Marc marc = new Marc();
        marc = marcService.selectByMatnr(afko.getPlnbez());
        String aufpl = afko.getAufpl();
        String aufnr = afko.getAufnr();
        String maxno = afko.getMaxno();
        int no_length = 4;
        int no = 0;
        if (maxno == null) {
            no = 0;
        } else {
            no = Integer.parseInt(maxno);
        }
        int cansum = afko.getCansum().intValue();
        int cursum = afko.getCursum().intValue();
        List<Cardh> listCardh = new ArrayList();
        for (int i = 0; i < cursum; i++) {
            no += 1;
            String nostr = String.valueOf(no);
            int len = nostr.length();

            int k = 4 - len;
            for (int j = 0; j < k; j++) {
                nostr = "0" + nostr;
            }
            Cardh cardh = new Cardh();
            cardh.setZpgdbar(afko.getWerks() + afko.getAufnr() + nostr);
            cardh.setZpgdbh(nostr);
            cardh.setZxhnum(nostr);
            cardh.setWerks(afko.getWerks());
            cardh.setBukrs(afko.getBukrs());
            cardh.setAufnr(afko.getAufnr());
            cardh.setAuart(afko.getAuart());
            cardh.setMatnr(afko.getPlnbez());
            cardh.setStdqt(afko.getUmrez());
            cardh.setMenge(afko.getUmrez());
            cardh.setGamng(afko.getGamng());
            cardh.setGmein(afko.getGmein());
            cardh.setGstrp(afko.getGstrp().substring(0,10));//流转卡创建日期
            cardh.setSchst(afko.getGstrp().substring(11,19));
            cardh.setGltrp(afko.getGltrp().substring(0, 10));
            cardh.setSchdt(afko.getGltrp().substring(11, 19));
            cardh.setAufpl(afko.getAufpl());
            cardh.setCharg(afko.getCharg());
            cardh.setPlqty(afko.getUmrez());//流转卡实际创建数量
            cardh.setMtlbd(afko.getGroes());//铝材牌号
            cardh.setFlgrg(marc.getFlgrg() == null ? 0.0D : marc.getFlgrg());//浮动报工率
            cardh.setStatus("CRTD");
            cardh.setStatus2("");
            listCardh.add(cardh);
        }
        return new ResponseData(listCardh);
    }

    @RequestMapping({"/sap/cardh/makecardjj"})
    @ResponseBody
    public ResponseData makeCardJJ(Afko dto, HttpServletRequest request) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Afko afko = dto;

        Afko afkotmp = afkoService.selectByAufnr(dto.getAufnr());
        String status = afkotmp.getStatus();
        if (status.indexOf("CRTD") != -1){
            ResponseData rs = new ResponseData();
            rs.setSuccess(false);
            rs.setMessage("订单状态为CRTD不允许创建流转卡!");
            return rs;
        }

        if (status.indexOf("TECO") != -1){
            ResponseData rs = new ResponseData();
            rs.setSuccess(false);
            rs.setMessage("订单状态为TECO不允许创建流转卡!");
            return rs;
        }

        if (status.indexOf("结算") != -1){
            ResponseData rs = new ResponseData();
            rs.setSuccess(false);
            rs.setMessage("订单状态为结算不允许创建流转卡!");
            return rs;
        }


        Marc marc = new Marc();
        marc = marcService.selectByMatnr(afko.getPlnbez());
        Sccl sccl = scclService.selectByMatnr(afko.getPlnbez(), afko.getWerks());
        if (dto.getIsA() == null) {
            dto.setIsA("");
        }
        if (dto.getIsB() == null) {
            dto.setIsB("");
        }
        if (dto.getIsC() == null) {
            dto.setIsC("");
        }

        if (sccl == null) {
            ResponseData rs = new ResponseData();
            rs.setSuccess(false);
            rs.setMessage("未维护产品：" + afko.getPlnbez() + "的班产量数据！无法创建流转卡！");
            return rs;
        } else {
            String aufpl = afko.getAufpl();
            String aufnr = afko.getAufnr();
            String maxno = afko.getMaxno();
            int cursum = 0;
            int no_length = 4;
            int no = 0;
            if (maxno == null) {
                no = 0;
            } else {
                no = Integer.parseInt(maxno);
            }
            int cansum = afko.getCansum().intValue();
            if (afko.getIsA().equals("Y")) {
                cursum = cursum + 1;
            }
            if (afko.getIsB().equals("Y")) {
                cursum = cursum + 1;
            }
            if (afko.getIsC().equals("Y")) {
                cursum = cursum + 1;
            }

            if (cansum < cursum){
                ResponseData rs = new ResponseData();
                rs.setSuccess(false);
                rs.setMessage("本次创建流转卡数量超过最大可创建数量，本次仅可创建:"+ cansum +"张流转卡！");
                return rs;
            }
            List<Cardh> listCardh = new ArrayList();
            if (afko.getIsA().equals("Y")) {
                no += 1;
                String nostr = String.valueOf(no);
                int len = nostr.length();

                int k = 4 - len;
                for (int j = 0; j < k; j++) {
                    nostr = "0" + nostr;
                }
                Cardh cardh = new Cardh();
                cardh.setZpgdbar(afko.getWerks() + afko.getAufnr() + nostr);
                cardh.setZpgdbh(nostr);
                cardh.setZxhnum(nostr);
                cardh.setWerks(afko.getWerks());
                cardh.setBukrs(afko.getBukrs());
                cardh.setAufnr(afko.getAufnr());
                cardh.setAuart(afko.getAuart());
                cardh.setMatnr(afko.getPlnbez());
                cardh.setStdqt(afko.getUmrez());
                cardh.setMenge(afko.getUmrez());
                cardh.setGamng(afko.getGamng());
                cardh.setGmein(afko.getGmein());
                cardh.setGstrp(afko.getGstrp().substring(0,10));//流转卡创建日期
                cardh.setSchst(afko.getGstrp().substring(11,19));
                cardh.setGltrp(afko.getGltrp().substring(0, 10));
                cardh.setSchdt(afko.getGltrp().substring(11, 19));
                cardh.setAufpl(afko.getAufpl());
                //cardh.setCharg(afko.getCharg());
                cardh.setPlqty(afko.getUmrez());//流转卡实际创建数量
                //cardh.setMtlbd(afko.getGroes());//铝材牌号
                cardh.setFlgrg(marc.getFlgrg() == null ? 0.0D : marc.getFlgrg());//浮动报工率
                cardh.setStatus("CRTD");
                cardh.setStatus2("");
                cardh.setShift("A");
                cardh.setAttr1(afko.getProductdate().substring(0,10));
                listCardh.add(cardh);
            }

            if (afko.getIsB().equals("Y")) {
                no += 1;
                String nostr = String.valueOf(no);
                int len = nostr.length();

                int k = 4 - len;
                for (int j = 0; j < k; j++) {
                    nostr = "0" + nostr;
                }
                Cardh cardh = new Cardh();
                cardh.setZpgdbar(afko.getWerks() + afko.getAufnr() + nostr);
                cardh.setZpgdbh(nostr);
                cardh.setZxhnum(nostr);
                cardh.setWerks(afko.getWerks());
                cardh.setBukrs(afko.getBukrs());
                cardh.setAufnr(afko.getAufnr());
                cardh.setAuart(afko.getAuart());
                cardh.setMatnr(afko.getPlnbez());
                cardh.setStdqt(afko.getUmrez());
                cardh.setMenge(afko.getUmrez());
                cardh.setGamng(afko.getGamng());
                cardh.setGmein(afko.getGmein());
                cardh.setGstrp(afko.getGstrp().substring(0,10));//流转卡创建日期
                cardh.setSchst(afko.getGstrp().substring(11,19));
                cardh.setGltrp(afko.getGltrp().substring(0, 10));
                cardh.setSchdt(afko.getGltrp().substring(11, 19));
                cardh.setAufpl(afko.getAufpl());
                //cardh.setCharg(afko.getCharg());
                cardh.setPlqty(afko.getUmrez());//流转卡实际创建数量
                //cardh.setMtlbd(afko.getGroes());//铝材牌号
                cardh.setFlgrg(marc.getFlgrg() == null ? 0.0D : marc.getFlgrg());//浮动报工率
                cardh.setStatus("CRTD");
                cardh.setStatus2("");
                cardh.setShift("B");
                cardh.setAttr1(afko.getProductdate().substring(0,10));
                listCardh.add(cardh);
            }
            if (afko.getIsC().equals("Y")) {
                no += 1;
                String nostr = String.valueOf(no);
                int len = nostr.length();

                int k = 4 - len;
                for (int j = 0; j < k; j++) {
                    nostr = "0" + nostr;
                }
                Cardh cardh = new Cardh();
                cardh.setZpgdbar(afko.getWerks() + afko.getAufnr() + nostr);
                cardh.setZpgdbh(nostr);
                cardh.setZxhnum(nostr);
                cardh.setWerks(afko.getWerks());
                cardh.setBukrs(afko.getBukrs());
                cardh.setAufnr(afko.getAufnr());
                cardh.setAuart(afko.getAuart());
                cardh.setMatnr(afko.getPlnbez());
                cardh.setStdqt(afko.getUmrez());
                cardh.setMenge(afko.getUmrez());
                cardh.setGamng(afko.getGamng());
                cardh.setGmein(afko.getGmein());
                cardh.setGstrp(afko.getGstrp().substring(0,10));//流转卡创建日期
                cardh.setSchst(afko.getGstrp().substring(11,19));
                cardh.setGltrp(afko.getGltrp().substring(0, 10));
                cardh.setSchdt(afko.getGltrp().substring(11, 19));
                cardh.setAufpl(afko.getAufpl());
                //cardh.setCharg(afko.getCharg());
                cardh.setPlqty(afko.getUmrez());//流转卡实际创建数量
                //cardh.setMtlbd(afko.getGroes());//铝材牌号
                cardh.setFlgrg(marc.getFlgrg() == null ? 0.0D : marc.getFlgrg());//浮动报工率
                cardh.setStatus("CRTD");
                cardh.setStatus2("");
                cardh.setShift("C");
                cardh.setAttr1(afko.getProductdate().substring(0,10));
                listCardh.add(cardh);
            }
            return new ResponseData(listCardh);
        }
    }

    @RequestMapping({"/sap/cardh/makecardZuhe"})
    @ResponseBody
    public ResponseData makeCardZuhe(Afko dto, HttpServletRequest request) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Afko afko = dto;
        Afko afko2 = new Afko();
        Marc marc1 = new Marc();
        Marc marc2 = new Marc();
        String aufpl = afko.getAufpl();
        String aufnr = afko.getAufnr();
        String maxno1;
        String maxno2;
        int no_length = 4;
        int cansum = afko.getCansum().intValue();
        int cursum = afko.getCursum().intValue();
        int no1 = 0;
        int no2 = 0;
        List<Cardh> listCardhyz = new ArrayList();//压铸流转卡
        List<Cardh> listCardhjj = new ArrayList();//机加流转卡
        //1:生成压铸流转卡
        //获取机加生产订单信息,物料信息，
        afko2 = afkoService.selectByAufnr(afko.getQaufnr());
        marc1 = marcService.selectByMatnr(afko.getPlnbez());
        marc2 = marcService.selectByMatnr(afko2.getPlnbez());
        maxno1 = service.selectByAufnrMxno(afko.getAufnr());
        maxno2 = service.selectByAufnrMxno(afko2.getAufnr());
        if (maxno1 != null) {
            no1 = Integer.parseInt(service.selectByAufnrMxno(afko.getAufnr()));//压铸流转卡的最大序列号
        }

        if (maxno2 != null) {
            no2 = Integer.parseInt(service.selectByAufnrMxno(afko2.getAufnr()));//机加流转卡的最大序列号
        }

        for (int i = 0; i < cursum; i++) {
            no1 += 1;
            String nostr = String.valueOf(no1);
            int len = nostr.length();
            int k = 4 - len;
            for (int j = 0; j < k; j++) {
                nostr = "0" + nostr;
            }
            //压铸流转卡
            Cardh cardh = new Cardh();
            cardh.setZpgdbar(afko.getWerks() + afko.getAufnr() + nostr);//工厂+订单+流水号
            cardh.setZpgdbh(nostr);
            cardh.setZxhnum(nostr);
            cardh.setWerks(afko.getWerks());
            cardh.setBukrs(afko.getBukrs());
            cardh.setAufnr(afko.getAufnr());
            cardh.setAufnr2(afko2.getAufnr());
            cardh.setAuart(afko.getAuart());
            cardh.setAuart2(afko2.getAuart());
            cardh.setMatnr(afko.getPlnbez());
            cardh.setStdqt(afko.getUmrez());//标准装框数
            cardh.setMenge(afko.getUmrez());
            cardh.setGamng(afko.getGamng());
            cardh.setGamng2(afko.getGamng());
            cardh.setGmein(afko.getGmein());
            cardh.setGstrp(afko.getGstrp().substring(0,10));//流转卡创建日期
            cardh.setSchst(afko.getGstrp().substring(11,19));
            cardh.setGltrp(afko.getGltrp().substring(0, 10));
            cardh.setSchdt(afko.getGltrp().substring(11, 19));
            cardh.setAufpl(afko.getAufpl());
            cardh.setCharg(afko.getCharg());
            cardh.setPlqty(afko.getUmrez());//流转卡实际创建数量
            cardh.setStatus("CRTD");
            //cardh.setLgort();
            cardh.setStwks("");//首工序工作中心
            cardh.setMtlbd(afko.getGroes());//铝材牌号
            cardh.setFlgrg(marc1.getFlgrg() == null ? 0.0D : marc1.getFlgrg());//浮动报工率

            //机加流转卡
            no2 += 1;
            String nostr2 = String.valueOf(no2);
            int len2 = nostr2.length();
            int k2 = 4 - len;
            for (int j2 = 0; j2 < k2; j2++) {
                nostr2 = "0" + nostr2;
            }
            Cardh cardh1 = new Cardh();
            cardh1.setZpgdbar(afko2.getWerks() + afko2.getAufnr() + nostr2);//工厂+订单+流水号
            cardh1.setZpgdbh(nostr2);
            cardh1.setZxhnum(nostr2);
            cardh1.setWerks(afko2.getWerks());
            cardh1.setBukrs(afko2.getBukrs());
            cardh1.setAufnr(afko2.getAufnr());
            cardh1.setAuart(afko2.getAuart());
            cardh1.setAufnr2(afko.getAufnr());
            cardh1.setAuart2(afko.getAuart());
            cardh1.setMatnr(afko2.getPlnbez());
            cardh1.setStdqt(afko2.getUmrez());//标准装框数
            cardh1.setMenge(cardh.getMenge());//与压铸流转卡绑定数量一致。
            cardh1.setGamng(afko2.getGamng());
            cardh1.setGamng2(afko2.getGamng());
            cardh1.setGmein(afko2.getGmein());
            cardh1.setGstrp(afko.getGstrp().substring(0,10));//流转卡创建日期
            cardh1.setSchst(afko.getGstrp().substring(11,19));
            cardh1.setGltrp(afko2.getGltrp().substring(0, 10));
            cardh1.setSchdt("00:00:00");
            cardh1.setAufpl(afko2.getAufpl());
            cardh1.setCharg(afko2.getCharg());
            cardh1.setPlqty(cardh.getPlqty());//流转卡实际创建数量
            cardh1.setStatus("CRTD");
            //cardh.setLgort();
            cardh1.setStwks("");//首工序工作中心

            cardh1.setMtlbd(afko2.getGroes() == null ? "" : afko.getGroes());//铝材牌号
            cardh1.setFlgrg(marc2.getFlgrg() == null ? 0.0D : marc1.getFlgrg());//浮动报工率

            cardh.setRelzpgdbar(cardh1.getZpgdbar());//压铸关联机加流转卡
            cardh.setRelaufnr(afko.getAufnr());//压铸流转卡关联机加生产订单

            cardh1.setRelaufnr(afko.getAufnr());//机加关联压铸流转卡
            cardh1.setRelzpgdbar(cardh.getZpgdbar());//机加流转卡关联压铸生产订单

            listCardhyz.add(cardh);
            listCardhyz.add(cardh1);
            //listCardhjj.add(cardh1);

        }
        //eturn new ResponseData(lsitCardh);
        return new ResponseData(listCardhyz);
    }

    @RequestMapping(value = {"/sap/cardh/updateCardhStatus"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public ResponseData updateChardStatus(HttpServletRequest request, @RequestBody List<Cardh> dto) {
        ResponseData result = new ResponseData();
        int i = service.updateCardhStatus(dto);
        if (i == dto.size()) {
            result.setSuccess(true);
            result.setCode("S");
        } else {
            result.setSuccess(true);
            result.setCode("E");
            result.setMessage("未完全更新流转卡状态，请联系管理员检查流转卡状态！");
        }
        return result;
    }

    @RequestMapping(value = {"/sap/cardh/deleteZuheCardh"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public ResponseData deleteZuheCardh(HttpServletRequest request, @RequestBody List<Cardh> dto) {
        int resultcardt = 0;
        int resultcardh = 0;
        int resultxh = 0;
        ResponseData result = new ResponseData();
        List<Xhcard> xhcards = new ArrayList();
        List<Cardt> cardts = new ArrayList();
        List<Cardh> cardhs = new ArrayList();
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                Xhcard xhcard = new Xhcard();
                Cardh relcardh = service.selectByBarcode(dto.get(i).getRelzpgdbar());
                xhcard.setWerks(dto.get(i).getWerks());
                xhcard.setMatnr(relcardh.getMatnr());
                xhcard.setZxhnum(relcardh.getZxhnum());
                xhcard.setAufnr(relcardh.getAufnr());
                xhcard.setCharg(relcardh.getCharg());
                xhcard.setZxhzt("");
                xhcard.setZxhzt2("");
                xhcard.setChargkc("");
                xhcard.setZjyy("");
                xhcard.setLgort("");
                xhcard.setMenge("");
                xhcard.setMeins("");
                xhcard.setZxhwz("");
                xhcard.setZxhbar("");
                xhcard.setZscx("");
                xhcard.setZmnum("");
                xhcard.setZscbc("");
                xhcard.setZsctptm("");
                xhcard.setZtxt("");
                xhcard.setZbqbd("D");
                xhcards.add(xhcard);

                Cardt cardt = new Cardt();
                cardt.setWerks(dto.get(i).getWerks());
                cardt.setZpgdbar(dto.get(i).getZpgdbar());
                cardts.add(cardt);

                Cardt cardt1 = new Cardt();
                cardt1.setWerks(relcardh.getWerks());
                cardt1.setZpgdbar(relcardh.getZpgdbar());
                cardts.add(cardt1);

                cardhs.add(relcardh);//添加关联流转卡到删除队列
                cardhs.add(dto.get(i));//添加流转卡到删除队列
            }
            resultxh = xhcardService.deleteXhcard(xhcards);
            if (resultxh == dto.size()) {

                resultcardt = cardtService.deleteCardt(cardts);


                resultcardh = service.deleteCardh(cardhs);

                result.setSuccess(true);
                result.setCode("S");
                result.setMessage("共删除流转卡" + resultcardh + "条，工序条码" + resultcardt + "条，箱号" + resultxh + "条");
                return result;
            }
            result.setSuccess(false);
            result.setCode("E");
            result.setMessage("接口删除SAP箱号失败！请联系管理员检查数据！");
        }
        return result;
    }

    @RequestMapping(value = {"/sap/cardh/deleteCardh"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public ResponseData deleteCardh(HttpServletRequest request, @RequestBody List<Cardh> dto) {
        int resultcardt = 0;
        int resultcardh = 0;
        int resultxh = 0;
        ResponseData result = new ResponseData();
        List<Xhcard> xhcards = new ArrayList<>();
        List<Cardt> cardts = new ArrayList<>();
        List<Cardhst> listcardhst = new ArrayList<>();
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                Cardhst cardhst = new Cardhst();
                cardhst.setZpgdbar(dto.get(i).getZpgdbar());
                listcardhst.add(cardhst);
                String auart = dto.get(i).getAuart().substring(0, 1);
                if (auart.equals("Y")) {
                    Xhcard xhcard = new Xhcard();
                    xhcard.setWerks(dto.get(i).getWerks());
                    xhcard.setMatnr(dto.get(i).getMatnr());
                    xhcard.setZxhnum(dto.get(i).getZxhnum());
                    xhcard.setAufnr(dto.get(i).getAufnr());
                    xhcard.setCharg(dto.get(i).getCharg());
                    xhcard.setZxhzt("");
                    xhcard.setZxhzt2("");
                    xhcard.setChargkc("");
                    xhcard.setZjyy("");
                    xhcard.setLgort("");
                    xhcard.setMenge("");
                    xhcard.setMeins("");
                    xhcard.setZxhwz("");
                    xhcard.setZxhbar("");
                    xhcard.setZscx("");
                    xhcard.setZmnum("");
                    xhcard.setZscbc("");
                    xhcard.setZsctptm("");
                    xhcard.setZtxt("");
                    xhcard.setZbqbd("D");
                    xhcards.add(xhcard);
                }
                Cardt cardt = new Cardt();
                cardt.setWerks(dto.get(i).getWerks());
                cardt.setZpgdbar(dto.get(i).getZpgdbar());
                cardts.add(cardt);
            }
            if (xhcards.size() > 0) {
                resultxh = xhcardService.deleteXhcard(xhcards);
                if (resultxh == dto.size()) {
                    resultcardt = cardtService.deleteCardt(cardts);
                    resultcardh = service.deleteCardh(dto);
                    cardhstService.deleteAll(listcardhst);

                    result.setSuccess(true);
                    result.setCode("S");
                    result.setMessage("共删除流转卡" + resultcardh + "条，工序条码" + resultcardt + "条，箱号" + resultxh + "条");
                    return result;
                }
                result.setSuccess(false);
                result.setCode("E");
                result.setMessage("接口删除SAP箱号失败！请联系管理员检查数据！");
            }else{
                resultcardt = cardtService.deleteCardt(cardts);
                resultcardh = service.deleteCardh(dto);
                cardhstService.deleteAll(listcardhst);
                result.setSuccess(true);
                result.setCode("S");
                result.setMessage("共删除流转卡" + resultcardh + "条，工序条码" + resultcardt + "条，箱号" + resultxh + "条");
                return result;
            }

        }
        return result;
    }

    @RequestMapping(value = {"/sap/cardh/createcardZuhe"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public ResponseData createCardZuhe(HttpServletRequest request, @RequestBody List<Cardh> dto) {
        List<Cardh> listCardh = new ArrayList();
        List<Cardt> listCardt = new ArrayList();
        List<Xhcard> listXhcard = new ArrayList();
        List<String> listmsg = new ArrayList();
        ResponseData result = new ResponseData();
        int resultCardh = 0;
        int resultCardt = 0;
        int resultXhcard = 0;
        String msg1 = "";
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                Cardh cardh = new Cardh();
                cardh.setZpgdbar(dto.get(i).getZpgdbar());//工厂+订单+流水号
                cardh.setZpgdbh(dto.get(i).getZpgdbh());
                cardh.setZxhnum(dto.get(i).getZxhnum());
                cardh.setWerks(dto.get(i).getWerks());
                cardh.setBukrs(dto.get(i).getBukrs());
                cardh.setAufnr(dto.get(i).getAufnr());
                cardh.setAufnr2(dto.get(i).getAufnr2());
                cardh.setAuart(dto.get(i).getAuart());
                cardh.setAuart2(dto.get(i).getAuart2());
                cardh.setMatnr(dto.get(i).getMatnr());
                cardh.setStdqt(dto.get(i).getStdqt());//标准装框数
                cardh.setMenge(dto.get(i).getMenge());
                cardh.setGamng(dto.get(i).getGamng());
                cardh.setGamng2(dto.get(i).getGamng2());
                cardh.setGmein(dto.get(i).getGmein());
                cardh.setGstrp(dto.get(i).getGstrp());//流转卡创建日期
                cardh.setSchst(dto.get(i).getSchst());
                cardh.setGltrp(dto.get(i).getGltrp());
                cardh.setSchdt(dto.get(i).getSchdt());
                cardh.setAufpl(dto.get(i).getAufpl());
                cardh.setCharg(dto.get(i).getCharg());
                cardh.setPlqty(dto.get(i).getPlqty());//流转卡实际创建数量
                cardh.setLgort(dto.get(i).getLgort());
                cardh.setStatus("CRTD");
                //cardh.setLgort();
                cardh.setStwks("");//首工序工作中心
                cardh.setMtlbd(dto.get(i).getMtlbd());//铝材牌号
                cardh.setFlgrg(dto.get(i).getFlgrg());//浮动报工率
                cardh.setRelzpgdbar(dto.get(i).getRelzpgdbar());//压铸关联机加流转卡
                cardh.setRelaufnr(dto.get(i).getRelaufnr());//压铸流转卡关联机加生产订单
                cardh.setZtype("组合");
                listCardh.add(cardh);

                String auart = dto.get(i).getAuart();
                if ((auart.equals("YZ01")) || (auart.equals("YZ02")) ||
                        (auart.equals("YZ03")) || (auart.equals("YZ04")) ||
                        (auart.equals("YZ06"))) {
                    Xhcard xhcard = new Xhcard();
                    xhcard.setWerks(cardh.getWerks());
                    xhcard.setMatnr(cardh.getMatnr());
                    xhcard.setCharg(dto.get(i).getCharg());
                    xhcard.setZxhnum(cardh.getZxhnum());
                    xhcard.setZxhzt("1");
                    xhcard.setMeins(cardh.getGmein());
                    xhcard.setAufnr(cardh.getAufnr());
                    xhcard.setZxhbar(cardh.getMatnr() + "_" + cardh.getAufnr() + "_" + cardh.getZxhnum());
                    xhcard.setMenge("");
                    xhcard.setZbqbd("");
                    xhcard.setZtxt("");
                    xhcard.setZsctptm("");
                    xhcard.setZxhwz("");
                    xhcard.setZscx("");
                    xhcard.setZscbc("");
                    xhcard.setZmnum("");
                    xhcard.setZxhzt2("");
                    xhcard.setLgort("");
                    xhcard.setChargkc("");
                    xhcard.setZjyy("");
                    listXhcard.add(xhcard);
                }
                List<Afvc> listAfvc = afvcService.selectByAufpl(dto.get(i).getAufpl());
                if (listAfvc.size() > 0) {
                    for (int j = 0; j < listAfvc.size(); j++) {
                        String steus = listAfvc.get(j).getSteus();
                       // if (!steus.equals("ZP03")) {
                            Cardt cardt = new Cardt();
                            cardt.setZpgdbar(cardh.getZpgdbar());
                            cardt.setZpgdbh(cardh.getZpgdbh());
                            cardt.setZgxbar(cardh.getZpgdbar() + listAfvc.get(j).getVornr());
                            cardt.setZgxbh(listAfvc.get(j).getVornr());
                            cardt.setArbpl(listAfvc.get(j).getArbpl());
                            cardt.setKtext(listAfvc.get(j).getKtext());
                            cardt.setVornr(listAfvc.get(j).getVornr());
                            cardt.setLtxa1(listAfvc.get(j).getLtxa1());
                            cardt.setSteus(listAfvc.get(j).getSteus());
                            cardt.setWerks(listAfvc.get(j).getWerks());
                            cardt.setAufnr(listCardh.get(i).getAufnr());
                            cardt.setKtsch(listAfvc.get(j).getKtsch());
                            cardt.setConfirmed("");
                            listCardt.add(cardt);
                       // }

                    }
                }
            }
            if (listXhcard.size() > 0) {
                resultXhcard = xhcardService.insertXhcard(listXhcard);
                if (resultXhcard == 0) {
                    msg1 = "SAP同步生成箱号失败,请检查接口状态！";
                    listmsg.add(msg1);
                    result.setRows(listmsg);
                } else {
                    resultCardh = service.insertCardh(listCardh);
                    resultCardt = cardtService.insertCardt(listCardt);
                    msg1 = "生成流转卡主记录" + resultCardh + "条";
                    listmsg.add(msg1);
                    result.setRows(listmsg);
                }
            } else {
                resultCardh = service.insertCardh(listCardh);
                resultCardt = cardtService.insertCardt(listCardt);
                msg1 = "生成流转卡主记录" + resultCardh + "条";
                listmsg.add(msg1);
                result.setRows(listmsg);
            }
        }
        return result;
    }

    @RequestMapping(value = {"/sap/cardh/createcard"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public ResponseData createCard(HttpServletRequest request, @RequestBody List<Cardh> dto) {
        String createdBy = "" + request.getSession().getAttribute("userId");
        List<Cardh> listCardh = new ArrayList<>();
        List<Cardt> listCardt = new ArrayList<>();
        List<Xhcard> listXhcard = new ArrayList<>();
        List<String> listmsg = new ArrayList<>();
        List<Cardhst> listCardhst = new ArrayList<>();
        ResponseData result = new ResponseData();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(2,10).replaceAll("-","");

        Integer maxChargnum = 0;
        int resultCardh = 0;
        int resultCardt = 0;
        int resultXhcard = 0;
        String msg1 = "";
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                Marc marc = marcService.selectByMatnr(dto.get(i).getMatnr());
                Cardh cardh = new Cardh();
                cardh.setWerks(dto.get(i).getWerks());
                cardh.setZpgdbar(dto.get(i).getZpgdbar());
                cardh.setZpgdbh(dto.get(i).getZpgdbh());
                cardh.setZxhnum(dto.get(i).getZxhnum());
                cardh.setWerks(dto.get(i).getWerks());
                cardh.setBukrs(dto.get(i).getBukrs());
                cardh.setAufnr(dto.get(i).getAufnr());
                cardh.setAuart(dto.get(i).getAuart());
                cardh.setMatnr(dto.get(i).getMatnr());
                cardh.setStdqt(dto.get(i).getStdqt());//标准装框数
                cardh.setMenge(dto.get(i).getMenge());
                cardh.setGamng(dto.get(i).getGamng());
                cardh.setGmein(dto.get(i).getGmein());
                cardh.setGstrp(dto.get(i).getGstrp());//流转卡创建日期
                cardh.setSchst(dto.get(i).getSchst());
                cardh.setGltrp(dto.get(i).getGltrp());
                cardh.setSchdt(dto.get(i).getSchdt());
                cardh.setAufpl(dto.get(i).getAufpl());
                cardh.setPlqty(dto.get(i).getPlqty());//流转卡实际创建数量
                cardh.setLgort(dto.get(i).getLgort());
                cardh.setStatus("CRTD");
                cardh.setZdybs("N");
                cardh.setStatus2(dto.get(i).getStatus2());
                cardh.setMtlbd(dto.get(i).getMtlbd());//铝材牌号
                cardh.setFlgrg(dto.get(i).getFlgrg());//浮动报工率
                cardh.setZtype("独立");
                cardh.setCreatedBy(Long.valueOf(createdBy));
                cardh.setCreationDate(new Date());
                cardh.setStdqt(marc.getXumrez());
                Cardhst cardhst = new Cardhst();
                cardhst.setZpgdbar(dto.get(i).getZpgdbar());
                cardhst.setId(1L);
                cardhst.setStatus("CRTD");
                cardhst.setIsactive("X");

                listCardhst.add(cardhst);

                    //获取箱号中间日期序列最大值

                    String charg = curdate + "%";
                    String maxCharg = xhcardService.selectMaxCharg(cardh.getMatnr(),charg);

                    if (maxCharg == null){
                        maxCharg = curdate + "0000";
                        maxChargnum = Integer.valueOf(maxCharg);
                    }else{
                        maxChargnum = Integer.valueOf(maxCharg) + 1;
                    }

                cardh.setCharg(maxChargnum.toString());

                String auart = dto.get(i).getAuart();
                if ((auart.equals("YZ01")) || (auart.equals("YZ02")) ||
                        (auart.equals("YZ03")) || (auart.equals("YZ04")) ||
                        (auart.equals("YZ06"))) {
                    Xhcard xhcard = new Xhcard();
                    xhcard.setWerks(cardh.getWerks());
                    xhcard.setMatnr(cardh.getMatnr());
                    xhcard.setCharg(maxChargnum.toString());
                    xhcard.setZxhnum(cardh.getZxhnum());
                    xhcard.setZxhzt("1");
                    xhcard.setMeins(cardh.getGmein());
                    xhcard.setAufnr(cardh.getAufnr());
                    xhcard.setZxhbar(cardh.getMatnr() + "_" + maxChargnum.toString() + "_" + cardh.getZxhnum());
                    xhcard.setMenge("");
                    xhcard.setZbqbd("");
                    xhcard.setZtxt("");
                    xhcard.setZsctptm("");
                    xhcard.setZxhwz("");
                    xhcard.setZscx("");
                    xhcard.setZscbc("");
                    xhcard.setZmnum("");
                    xhcard.setZxhzt2("");
                    xhcard.setLgort("");
                    xhcard.setChargkc("");
                    xhcard.setZsxwc("");
                    xhcard.setZjyy("");
                    xhcard.setCreatedBy(Long.valueOf(createdBy));
                    xhcard.setCreationDate(new Date());
                    listXhcard.add(xhcard);
                }
                List<Afvc> listAfvc = afvcService.selectByAufpl(dto.get(i).getAufpl());
                if (listAfvc.size() > 0) {
                    for (int j = 0; j < listAfvc.size(); j++) {
                        //if (!listAfvc.get(j).getSteus().equals("ZP03")) {
                            Cardt cardt = new Cardt();
                            if (j ==  0){
                                cardh.setStwks(listAfvc.get(j).getArbpl());
                            }
                            cardt.setZpgdbar(cardh.getZpgdbar());
                            cardt.setZpgdbh(cardh.getZpgdbh());
                            cardt.setZgxbar(cardh.getZpgdbar() + listAfvc.get(j).getVornr());
                            cardt.setZgxbh(listAfvc.get(j).getVornr());
                            cardt.setArbpl(listAfvc.get(j).getArbpl());
                            cardt.setKtext(listAfvc.get(j).getKtext());
                            cardt.setVornr(listAfvc.get(j).getVornr());
                            cardt.setLtxa1(listAfvc.get(j).getLtxa1());
                            cardt.setWerks(listAfvc.get(j).getWerks());
                            cardt.setSteus(listAfvc.get(j).getSteus());
                            cardt.setAufnr(cardh.getAufnr());
                            cardt.setKtsch(listAfvc.get(j).getKtsch());
                            cardt.setConfirmed("");
                            cardt.setCreatedBy(Long.valueOf(createdBy));
                            cardt.setCreationDate(new Date());
                            listCardt.add(cardt);
                       // }

                    }
                }
                listCardh.add(cardh);
            }
            if (listXhcard.size() > 0) {
                resultXhcard = xhcardService.insertXhcard(listXhcard);
                if (resultXhcard == 0) {
                    msg1 = "SAP同步生成箱号失败,请检查接口状态！";
                    listmsg.add(msg1);
                    result.setRows(listmsg);
                } else {
                    resultCardh = service.insertCardh(listCardh);
                    cardhstService.insertStatus(listCardhst);
                    resultCardt = cardtService.insertCardt(listCardt);
                    msg1 = "生成流转卡主记录" + resultCardh + "条";
                    listmsg.add(msg1);
                    result.setRows(listmsg);
                }
            } else {
                resultCardh = service.insertCardh(listCardh);
                resultCardt = cardtService.insertCardt(listCardt);
                msg1 = "生成流转卡主记录" + resultCardh + "条";
                listmsg.add(msg1);
                result.setRows(listmsg);
            }
        }
        return result;
    }

    @RequestMapping(value = {"/sap/cardh/createcardjj"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public ResponseData createCardJJ(HttpServletRequest request, @RequestBody List<Cardh> dto) {
        String createdBy = "" + request.getSession().getAttribute("userId");
        List<Cardh> listCardh = new ArrayList<>();
        List<Cardt> listCardt = new ArrayList<>();
        List<Xhcard> listXhcard = new ArrayList<>();
        List<String> listmsg = new ArrayList<>();
        List<Cardhst> listCardhst = new ArrayList<>();
        ResponseData result = new ResponseData();
        int resultCardh = 0;
        int resultCardt = 0;
        int resultXhcard = 0;
        String msg1 = "";
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                Cardh cardh = new Cardh();
                cardh.setWerks(dto.get(i).getWerks());
                cardh.setZpgdbar(dto.get(i).getZpgdbar());
                cardh.setZpgdbh(dto.get(i).getZpgdbh());
                cardh.setZxhnum(dto.get(i).getZxhnum());
                cardh.setWerks(dto.get(i).getWerks());
                cardh.setBukrs(dto.get(i).getBukrs());
                cardh.setAufnr(dto.get(i).getAufnr());
                cardh.setAuart(dto.get(i).getAuart());
                cardh.setMatnr(dto.get(i).getMatnr());
                cardh.setStdqt(dto.get(i).getStdqt());//标准装框数
                cardh.setMenge(dto.get(i).getMenge());
                cardh.setGamng(dto.get(i).getGamng());
                cardh.setGmein(dto.get(i).getGmein());
                cardh.setGstrp(dto.get(i).getGstrp());//流转卡创建日期
                cardh.setSchst(dto.get(i).getSchst());
                cardh.setGltrp(dto.get(i).getGltrp());
                cardh.setSchdt(dto.get(i).getSchdt());
                cardh.setAufpl(dto.get(i).getAufpl());
                //cardh.setCharg(dto.get(i).getCharg());
                cardh.setPlqty(dto.get(i).getPlqty());//流转卡实际创建数量
                cardh.setLgort(dto.get(i).getLgort());
                cardh.setStatus("CRTD");
                cardh.setZdybs("N");
                cardh.setStatus2(dto.get(i).getStatus2());
                cardh.setStwks("");//首工序工作中心
                //cardh.setMtlbd(dto.get(i).getMtlbd());//铝材牌号
                cardh.setFlgrg(dto.get(i).getFlgrg());//浮动报工率
                cardh.setZtype("独立");
                cardh.setShift(dto.get(i).getShift());
                cardh.setCreatedBy(Long.valueOf(createdBy));
                cardh.setCreationDate(new Date());
                cardh.setAttr1(dto.get(i).getAttr1());

                Cardhst cardhst = new Cardhst();
                cardhst.setZpgdbar(dto.get(i).getZpgdbar());
                cardhst.setId(1L);
                cardhst.setStatus("CRTD");
                cardhst.setIsactive("X");

                listCardhst.add(cardhst);
                List<Afvc> listAfvc = afvcService.selectByAufpl(dto.get(i).getAufpl());
                if (listAfvc.size() > 0) {
                    for (int j = 0; j < listAfvc.size(); j++) {
                        //if (!listAfvc.get(j).getSteus().equals("ZP03")) {

                        if (j ==  0){
                            cardh.setStwks(listAfvc.get(j).getArbpl());
                        }
                         if (listAfvc.get(j).getSteus().equals("ZP04")){
                            Cardt cardt = new Cardt();
                            cardt.setZpgdbar(cardh.getZpgdbar());
                            cardt.setZpgdbh(cardh.getZpgdbh());
                            cardt.setZgxbar(cardh.getZpgdbar() + listAfvc.get(j).getVornr());
                            cardt.setZgxbh(listAfvc.get(j).getVornr());
                            cardt.setArbpl(listAfvc.get(j).getArbpl());
                            cardt.setKtext(listAfvc.get(j).getKtext());
                            cardt.setVornr(listAfvc.get(j).getVornr());
                            cardt.setLtxa1(listAfvc.get(j).getLtxa1());
                            cardt.setWerks(listAfvc.get(j).getWerks());
                            cardt.setAufnr(cardh.getAufnr());
                            cardt.setKtsch(listAfvc.get(j).getKtsch());
                            cardt.setSteus(listAfvc.get(j).getSteus());
                            cardt.setCreatedBy(Long.valueOf(createdBy));
                            cardt.setCreationDate(new Date());
                            cardt.setConfirmed("");
                            listCardt.add(cardt);
                        }
                    }
                }
                listCardh.add(cardh);
            }

            resultCardh = service.insertCardh(listCardh);
            cardhstService.insertStatus(listCardhst);
            resultCardt = cardtService.insertCardt(listCardt);
            msg1 = "生成流转卡主记录" + resultCardh + "条";
            listmsg.add(msg1);
            result.setRows(listmsg);
        }

        return result;
    }

    @RequestMapping(value = "/sap/cardh/readyForPrintZH", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData readyForPrintZH(HttpServletRequest request, Cardh dto) {
        String zpgdbar = dto.getZpgdbar();
        String relzpgdbar = dto.getRelzpgdbar();
        Marc marc = new Marc();
        Marc relmarc = new Marc();

        Cardh cardh = new Cardh();
        Cardh relcardh = new Cardh();

        List<Cardt> jjcardt = new ArrayList();
        List<Cardt> yzcardt = new ArrayList();
        List<Cardt> cardts = new ArrayList();
        cardh = service.selectByBarcode(zpgdbar);
        relcardh = service.selectByBarcode(relzpgdbar);

        jjcardt = cardtService.selectBybarcode(zpgdbar);
        yzcardt = cardtService.selectBybarcode(relzpgdbar);

        marc = marcService.selectByMatnr(cardh.getMatnr());
        relmarc = marcService.selectByMatnr(relcardh.getMatnr());
        List list = new ArrayList();
        list.add(cardh);
        list.add(relcardh);
        list.add(marc);
        list.add(relmarc);
        for (int i = 0; i < yzcardt.size(); i++) {
            Cardt cardttmp = new Cardt();
            cardttmp = yzcardt.get(i);
            cardts.add(cardttmp);
        }
        for (int j = 0; j < jjcardt.size(); j++) {
            Cardt cardttmp1 = new Cardt();
            cardttmp1 = jjcardt.get(j);
            cardts.add(cardttmp1);
        }
        list.add(cardts);
        ResponseData rs = new ResponseData(list);
        rs.setCode("S");
        return rs;
    }

    @RequestMapping(value = "/sap/cardh/readyForPrintDL", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData readyForPrintDL(HttpServletRequest request, Cardh dto) {

        List list = new ArrayList();
        String zpgdbar = dto.getZpgdbar();
        Marc marc = new Marc();
        Cardh cardh = new Cardh();
        List<Cardt> cardts = new ArrayList();
        Xhcard xhcard = new Xhcard();
        cardh = service.selectByBarcode(zpgdbar);
        cardts = cardtService.selectBybarcode(zpgdbar);
        marc = marcService.selectByMatnr(cardh.getMatnr());
        String zxhbar = dto.getMatnr() + "_" + dto.getCharg() + "_" + dto.getZpgdbh();
        xhcard = xhcardService.selectByXhAndAufnr(zxhbar);
        ResponseData rs = new ResponseData(list);
        list.add(cardh);
        list.add(cardts);
        list.add(marc);
        if (xhcard != null){
            list.add(xhcard);
        }
        rs.setCode("S");
        return rs;
    }

    /*
    **查询流转卡信息，状态信息，物料信息，报工记录（暂存区转移功能）
     */
    @RequestMapping(value = "/sap/cardh/selectForHczy", method = RequestMethod.GET)
    @ResponseBody
    ResponseData selectForHczy(HttpServletRequest request,String zpgdbar,String userName){
        ResponseData rs = new ResponseData();
        Cardh cardh = new Cardh();
        cardh = service.selectByBarcode(zpgdbar);
        if ( cardh == null){
            rs.setMessage("流转卡不存在！");
            rs.setSuccess(false);
            return rs;
        }

        switch (cardh.getStatus()){
            case "FCNF":
                break;
            case "CNF":
                break;
            case "ECNF":
                break;
            case "HOLD":
                rs.setSuccess(false);
                rs.setMessage("该流转卡状态为:HOLD，请先解冻流转卡再做暂存区转移操作！");
                return rs;
            case "PRNT":
                rs.setSuccess(false);
                rs.setMessage("该流转卡尚未报工！");
                return rs;
            case "CRTD":
                rs.setSuccess(false);
                rs.setMessage("该流转卡尚未报工！");
                return rs;
        }

        Cardhst cardhst = new Cardhst();
        cardhst = cardhstService.selectForHczy(zpgdbar,cardh.getStatus());

        Marc marc = new Marc();
        marc = marcService.selectByMatnr(cardh.getMatnr());

        //获取暂存区基础数据
        List<Area> listarea = areaService.selectByUserName(userName);
        if (listarea.size() == 0){
            rs.setSuccess(false);
            rs.setMessage("没有获取到用户:"+ userName + "权限范围内的暂存区");
            return rs;
        }
        InputLog inputLog = new InputLog();
        inputLog.setDispatch(zpgdbar);
        inputLog.setOperation(cardhst.getOperation());
        inputLog = inputLogService.selectConfirmationSuccess(inputLog);
        List list = new ArrayList();
        list.add(cardh);
        list.add(cardhst);
        list.add(marc);
        list.add(inputLog);
        list.add(listarea);
        rs.setRows(list);
        rs.setSuccess(true);
        return rs;
    }

    /*
    **处理暂存区转移
     */
    @RequestMapping(value = "/sap/cardh/sumbitHczy", method = RequestMethod.GET)
    @ResponseBody
    ResponseData selectForHczy(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        UUID uuid = java.util.UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-", "");
        String id = uuidstr;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0,10);
        String curtim  = df.format(new Date()).substring(11,19);
        Trasfer trasfer = new Trasfer();
        String aufnr = request.getParameter("aufnr");
        String matnr = request.getParameter("matnr");
        String werks = request.getParameter("werks");
        String zpgdbar = request.getParameter("zpgdbar");
        String fromloc = request.getParameter("modidn");
        String fromloctxt = request.getParameter("modidntxt");
        String fromloctp = request.getParameter("mvtype");
        String fromlocno = "";
        if (fromloctp.equals("1")){
             fromlocno = "";
        }
        Double gamng = Double.valueOf(request.getParameter("yeild"));
        String toloc = request.getParameter("mididn");
        String toloctxt = request.getParameter("mididntxt");
        String toloctp = "1";
        if ( toloctxt.equals("工序")){
             toloctp = "1";
        }else{
             toloctp = "2";
        }
        String tolocno = request.getParameter("locno");
        String transdat = curdate;
        String transtim = curtim;
        String userId = request.getParameter("userId");
        trasfer.setWerks(werks);
        trasfer.setZpgdbar(zpgdbar);
        trasfer.setAufnr(aufnr);
        trasfer.setMatnr(matnr);
        trasfer.setFromloc(fromloc);
        trasfer.setFromloctxt(fromloctxt);
        trasfer.setFromloctp(fromloctp);
        trasfer.setFromlocno(fromlocno);
        trasfer.setGamng(gamng);
        trasfer.setToloc(toloc);
        trasfer.setToloctxt(toloctxt);
        trasfer.setToloctp(toloctp);
        trasfer.setTolocno(tolocno);
        trasfer.setId(id);
        trasfer.setTransdat(transdat);
        trasfer.setTranstim(transtim);
        trasfer.setCreatedBy(Long.valueOf(userId));
        trasfer.setCreationDate(new Date());
        int sum = trasferService.insertTrasfer(trasfer);
        Cardh cardh = service.selectByBarcode(zpgdbar);
        cardh.setCreationDate(new Date());
        cardh.setCreatedBy(Long.valueOf(userId));
        if (fromloctp.equals("2")){
            cardh.setLgort("");
        }else{
            cardh.setLgort(toloc);
        }
        sum = sum + service.updateForLgort(cardh);
        if (sum == 2){

            rs.setSuccess(true);
            rs.setMessage("转移完成！");
            return rs;
        }else{
            rs.setMessage("转移失败，请联系管理员！");
            rs.setSuccess(false);
        }
        return rs;
    }

    /*
    **查询流转卡信息，流转卡状态信息（品质挂起功能）
     */
    @RequestMapping(value = "/sap/cardh/selectForPzcl", method = RequestMethod.GET)
    @ResponseBody
    ResponseData selectForPzcl(HttpServletRequest request,String zpgdbar){
        ResponseData rs = new ResponseData();
        Cardh cardh = new Cardh();
        cardh = service.selectByBarcode(zpgdbar);
        if (cardh == null){
            rs.setSuccess(false);
            rs.setMessage("流转卡信息不存在！");
            return  rs;
        }

        Marc marc = new Marc();
        marc = marcService.selectByMatnr(cardh.getMatnr());

        List<Cardhst> cardhstList = new ArrayList<>();
        cardhstList = cardhstService.selectByBarcode(zpgdbar);
        if (cardhstList.size() == 0){
            rs.setSuccess(false);
            rs.setMessage("流转卡状态异常，请联系系统管理员！");
            return rs;
        }

        for (int i=0;i<cardhstList.size();i++){
            if (cardhstList.get(i).getStatus().equals("HOLD") && cardhstList.get(i).getIsactive().equals("X")){
                //该流转卡已经处于挂起状态
                rs.setCode("HOLD");
            }else{
                rs.setCode("UNHOLD");
            }
        }
        List list = new ArrayList();
        list.add(cardh);
        list.add(marc);
        rs.setRows(list);
        rs.setSuccess(true);
        return rs;
    }

    /*
    **品质处理提交（品质挂起功能）
     */
    @RequestMapping(value = "/sap/cardh/sumbitPzcl", method = RequestMethod.GET)
    @ResponseBody
    ResponseData subitPzcl(HttpServletRequest request,String zpgdbar,String userId,String status){
        ResponseData rs = new ResponseData();
        Cardh cardh = new Cardh();
        cardh = service.selectByBarcode(zpgdbar);
        if (cardh == null){
            rs.setSuccess(false);
            rs.setMessage("该流转卡信息不存在，请确认！");
            return rs;
        }
        cardh.setStatus2(cardh.getStatus());
        cardh.setStatus(status);
        cardh.setLastUpdatedBy(Long.valueOf(userId));
        cardh.setLastUpdatedDate(new Date());
        List<Cardh> list = new ArrayList<>();
        list.add(cardh);
        int sum = service.updateCardhStatus(list);
        String done = "";
        if (status.equals("HOLD")){
            List<Cardhst> cardhstList = cardhstService.selectByBarcode(zpgdbar);
            for (int i=0;i<cardhstList.size();i++){
                if (cardhstList.get(i).getStatus().equals("HOLD")){
                    cardhstList.get(i).setIsactive("X");
                    sum = sum + cardhstService.updateStatus(cardhstList.get(i));
                    done = "X";
                }
            }
            if (!done.equals("X")){
                int mxno = cardhstService.getMaxNo(zpgdbar);
                Cardhst cardhst = new Cardhst();
                cardhst.setZpgdbar(zpgdbar);
                cardhst.setId(Long.valueOf(mxno + 1));
                cardhst.setStatus("HOLD");
                cardhst.setIsactive("X");
                sum = sum + cardhstService.insertSingerStatus(cardhst);
            }
        }else{
            List<Cardhst> cardhstList = cardhstService.selectByBarcode(zpgdbar);
            for (int i=0;i<cardhstList.size();i++){
                if (cardhstList.get(i).getStatus().equals("HOLD")){
                    cardhstList.get(i).setIsactive("");
                    sum = sum + cardhstService.updateStatus(cardhstList.get(i));
                    done = "X";
                }
            }
        }

        if (sum == 2){
            rs.setSuccess(true);
            rs.setMessage("提交成功！");
            return rs;
        }else{
            rs.setSuccess(false);
            rs.setMessage("修改流转卡状态失败！");
            return rs;
        }

    }
}
