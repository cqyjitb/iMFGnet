package yj.core.wipdftrghlist.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.service.ICardtService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.wipdftrghlist.dto.Dftrghlist;
import yj.core.wipdftrghlist.service.IDftrghlistService;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.service.ILinesService;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.service.IZwipqService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DftrghlistController extends BaseController {

    @Autowired
    private IDftrghlistService service;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IXhcardService xhcardService;
    @Autowired
    private ILinesService linesService;
    @Autowired
    private IProductsCfgService productsCfgService;
    @Autowired
    private IInputLogService inputLogService;
    @Autowired
    private IAfvcService afvcService;
    @Autowired
    private IZwipqService zwipqService;

    @RequestMapping(value = "/wip/dftrghlist/query")
    @ResponseBody
    public ResponseData query(Dftrghlist dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/dftrghlist/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Dftrghlist> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/dftrghlist/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Dftrghlist> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    /**
     * 处理线边库不良品处理页面请求
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"/wip/dftrghlist/processBlmpcl"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData processBlmpcl(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        String matnr2 = request.getParameter("matnr");
        String maktx = request.getParameter("maktx");
        String zxhbar = request.getParameter("zxhbar");
        String zmpbar = request.getParameter("zmpbar");
        String line_id = request.getParameter("line_id");
        String classgrp = request.getParameter("classgrp");
        String gstrp = request.getParameter("gstrp");
        String code = request.getParameter("code");
        String tlevelcode = request.getParameter("tlevelcode");
        Integer yeild = Integer.valueOf(request.getParameter("yeild"));
        String sfflg = request.getParameter("sfflg");
        String diecd = request.getParameter("diecd");
        String shift = classgrp;
        String werks = "";
        String matnr = "";
        String recordid = "";
        String userId = request.getParameter("userId");
        String type = request.getParameter("otype");//操作类型
        int sum = 0;
        //获取箱号信息
        Xhcard xhcard = xhcardService.selectByBacode(zxhbar);
        if (xhcard == null){
            rs.setSuccess(false);
            rs.setMessage("未能获取箱号信息，请检查箱号是否输入正确！");
            return  rs;
        }

        //获取在制队列数量
        List<Zwipq> listzwipq = new ArrayList<>();
        listzwipq = zwipqService.selectByLineIdAndZxhbar(line_id,zxhbar);
        Long tmp = Long.valueOf(yeild) - (Long.valueOf(xhcard.getMenge().substring(0,xhcard.getMenge().indexOf("."))) - listzwipq.size());
        if (tmp > 0){
            rs.setSuccess(false);
            rs.setMessage("不良数量超出毛坯框剩余毛坯数量"+ tmp +"个！");
            return  rs;
        }


        //获取物料信息
        Marc marc = marcService.selectByMatnr(matnr2);
        //获取压铸流转卡信息
        Cardh cardh = cardhService.selectByZxhbar(xhcard.getAufnr(), xhcard.getZxhnum());
        if (cardh == null){
            rs.setSuccess(false);
            rs.setMessage("未能获取箱号对应的压铸流转卡，请检查箱号是否输入正确！");
            return  rs;
        }
        //取首工序号
        List<Afvc> list = new ArrayList<>();
        list = afvcService.selectByAufnr(cardh.getAufnr());

        InputLog inputLog = new InputLog();
        inputLog.setDispatch(cardh.getZpgdbar());
        inputLog.setOperation(list.get(list.size() - 1).getVornr());
         inputLog = inputLogService.queryByDispatchAndOperation(inputLog);
        //获取机加产线信息
        Lines lines = linesService.selectByIdForBlmpcl(Long.valueOf(line_id));
        if (lines == null){
            rs.setMessage("未能获取产线信息，请检查产线ID是否正确！");
            rs.setSuccess(false);
            return rs;
        }else{
             werks = lines.getWerks();
        }
        //获取机加产线产品配置信息
        ProductsCfg pcfg = productsCfgService.selectByLineidAndMatnr(line_id.toString(),matnr2);
        if (pcfg != null){
             matnr = pcfg.getPmatnr();
        }else{
            rs.setSuccess(false);
            rs.setMessage("未能读取生产线产品配置信息，请检查产线产品配置！");
            return rs;
        }
            //查询是否已经包含记录
            Dftrghlist dftrghlist = service.selectByCondition(werks,matnr,line_id,shift,gstrp);
            if (dftrghlist == null){
                dftrghlist = new Dftrghlist();
                //插入新的记录
                //生成记录id
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
                recordid = "J" + curdate + "0001";
                dftrghlist.setWerks(werks);
                dftrghlist.setMatnr(matnr);
                dftrghlist.setMatnr2(matnr2);
                dftrghlist.setLineId(line_id);
                dftrghlist.setZxhbar(zxhbar);
                dftrghlist.setZmpbar(zmpbar);
                dftrghlist.setZpgdbar(cardh.getZpgdbar());
                dftrghlist.setSfflg(sfflg);
                dftrghlist.setShift(shift);
                dftrghlist.setGstrp(new Date());
                dftrghlist.setYcharge(xhcard.getChargkc());
                dftrghlist.setDiecd(diecd);
                dftrghlist.setYzbanc(inputLog.getAttr4());
                dftrghlist.setCode(code);
                dftrghlist.setTlevelcode(tlevelcode);
                dftrghlist.setMenge(Long.valueOf(xhcard.getMenge().substring(0,xhcard.getMenge().indexOf("."))));
                dftrghlist.setDfectQty(Long.valueOf(yeild));
                dftrghlist.setGmein(marc.getMeins());
                dftrghlist.setCreatedBy(Long.valueOf(userId));
                dftrghlist.setCreationDate(new Date());
                dftrghlist.setYgstrp(cardh.getFprddat());
                dftrghlist.setYshift(cardh.getShift());
                dftrghlist.setCancelFlag("0");
                dftrghlist.setItem(1L);
                dftrghlist.setRecordid(recordid);
                sum = service.insertDftrghlist(dftrghlist);
            }else{
                //1 获取最大行号
                Long item = Long.valueOf(service.selectMaxItemByCondition(werks,matnr,line_id,shift,gstrp) + 1);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
                recordid = "J" + curdate + "0001";
                dftrghlist.setWerks(werks);
                dftrghlist.setMatnr(matnr);
                dftrghlist.setMatnr2(matnr2);
                dftrghlist.setLineId(line_id);
                dftrghlist.setZxhbar(zxhbar);
                dftrghlist.setZmpbar(zmpbar);
                dftrghlist.setZpgdbar(cardh.getZpgdbar());
                dftrghlist.setSfflg(sfflg);
                dftrghlist.setShift(shift);
                dftrghlist.setGstrp(new Date());
                dftrghlist.setYcharge(xhcard.getChargkc());
                dftrghlist.setDiecd(diecd);
                dftrghlist.setYzbanc(inputLog.getAttr4());
                dftrghlist.setCode(code);
                dftrghlist.setTlevelcode(tlevelcode);
                dftrghlist.setMenge(Long.valueOf(xhcard.getMenge().substring(0,xhcard.getMenge().indexOf("."))));
                dftrghlist.setDfectQty(Long.valueOf(yeild));
                dftrghlist.setGmein(marc.getMeins());
                dftrghlist.setCreatedBy(Long.valueOf(userId));
                dftrghlist.setCreationDate(new Date());
                dftrghlist.setYgstrp(cardh.getFprddat());
                dftrghlist.setYshift(cardh.getShift());
                dftrghlist.setCancelFlag("0");
                dftrghlist.setItem(item);
                dftrghlist.setRecordid(recordid);
                sum = service.insertDftrghlist(dftrghlist);
            }

            if (sum == 1){
                rs.setMessage("不良毛坯处理成功！");
                rs.setSuccess(true);
            }else{
                rs.setMessage("不良毛坯处理失败，请联系管理员！");
                rs.setSuccess(false);
            }

        return rs;
    }

    /**
     * 根据生产线ID 班组 毛坯箱号 查询不良毛坯处理记录明细
     * @param request
     * @return
     */
    @RequestMapping(value = {"/wip/dftrghlist/searchBlmpcl"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData searchBlmpcl(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String line_id = request.getParameter("line_id");
        String classgrp = request.getParameter("classgrp");
        String zxhbar = request.getParameter("zxhbar");
        List<Dftrghlist> list = service.selectByLindIdAndZxhbar(line_id,classgrp,zxhbar);
        if (list.size() > 0){
            rs.setSuccess(true);
            rs.setRows(list);
        }else{
            rs.setMessage("没有符合条件的记录！");
            rs.setSuccess(false);
        }
        return rs;
    }

    @RequestMapping(value = {"/wip/dftrghlist/selectXhcard"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectXhcard(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        List list = new ArrayList();
        Xhcard xhcard = new Xhcard();
        String zxhbar = request.getParameter("zxhbar");
        xhcard = xhcardService.selectByBacode(zxhbar);
        if (xhcard == null){

            rs.setSuccess(false);
            rs.setMessage("未能获取箱号信息！请重新扫描箱号条码！");
        }else{
            list.add(xhcard);
            Marc marc = marcService.selectByMatnr(xhcard.getMatnr());
            list.add(marc);
            rs.setSuccess(true);
            rs.setRows(list);
        }
        return rs;
    }

    /**
     * 处理取消不良毛坯处理的页面请求
     * @param request
     * @return
     */
    @RequestMapping(value = {"/wip/dftrghlist/cancelBlmpcl"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData cancelBlmpcl(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String recordid = request.getParameter("recordid");
        String item = request.getParameter("item");
        String userId = request.getParameter("userId");

        Dftrghlist dftrghlist = service.selectByIdAndItem(recordid,Long.valueOf(item));

        dftrghlist.setCancelFlag("1");
        dftrghlist.setCancelBy(Long.valueOf(userId));
        dftrghlist.setLastUpdateDate(new Date());
        dftrghlist.setCancelDate(new Date());
        dftrghlist.setLastUpdatedBy(Long.valueOf(userId));

        int sum = 0;
        sum = service.updateByIdAndItem(dftrghlist);
        if (sum == 1){
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
            rs.setMessage("更新数据库失败，请联系系统管理员！");
        }
        return  rs;
    }
}