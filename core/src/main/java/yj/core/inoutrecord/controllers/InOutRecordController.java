package yj.core.inoutrecord.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.inoutrecord.dto.InOutHj;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.qjcode.dto.Qjcode;
import yj.core.qjcode.service.IQjcodeService;
import yj.core.zrwklist.dto.Zrwklist;
import yj.core.zudlist.dto.Zudlist;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InOutRecordController extends BaseController {

    @Autowired
    private IInOutRecordService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IQjcodeService qjcodeService;
    @Autowired
    private IMarcService marcService;

    @RequestMapping(value = "/wip/in/out/record/query")
    @ResponseBody
    public ResponseData query(InOutRecord dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/in/out/record/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<InOutRecord> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/in/out/record/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<InOutRecord> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/inoutrecord/selectforjjhj")
    @ResponseBody
    public ResponseData selectforjjhj(HttpServletRequest request, String line_id,String qjcodeval,String lineiocfgval,String matnr,int hjtype) {
        ResponseData rs = new ResponseData();
        IRequest requestContext = createRequestContext(request);
        List<InOutRecord> list = service.selectforjjhj(line_id,qjcodeval,lineiocfgval,matnr,hjtype);
        if (list.size() > 0) {
            List<InOutHj> listhj = new ArrayList<>();
            for (int i=0;i<list.size();i++){
                InOutHj inOutHj = new InOutHj();
                inOutHj.setId(i);
                inOutHj.setSfflg(list.get(i).getSfflg());
                inOutHj.setVORNR(list.get(i).getVornr());
                inOutHj.setZOTYPE(list.get(i).getZotype());
                listhj.add(inOutHj);
            }

            rs.setSuccess(true);
            rs.setRows(listhj);
        } else {
            rs.setSuccess(false);
            rs.setMessage("取件记录中没有符合条件的取件记录!");

        }
        return rs;
    }

    @RequestMapping(value = "/inoutrecord/selectforZud")
    @ResponseBody

        public ResponseData selectforZud(HttpServletRequest request){
        String line_id = request.getParameter("line_id");
        String classgrp = request.getParameter("classgrp");
        List<InOutRecord> list = new ArrayList<>();
        List<Zudlist> listzuds = new ArrayList<>();
        IRequest requestContext = createRequestContext(request);
        listzuds = service.selectforZud(line_id,classgrp);
        return new ResponseData(listzuds);
    }

    @RequestMapping(value = "/inoutrecord/selectforZrwk")
    @ResponseBody
    public ResponseData selectforZrwk(HttpServletRequest request){
        String line_id = request.getParameter("line_id");
        String classgrp = request.getParameter("classgrp");
        String zotype = request.getParameter("zotype");
        List<InOutRecord> list = new ArrayList<>();
        List<Zrwklist> listzrwk = new ArrayList<>();
        IRequest requestContext = createRequestContext(request);
        list = service.selectforZrwk(line_id,classgrp,zotype,requestContext);
        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                Zrwklist zrwklist = new Zrwklist();
                zrwklist.setLineId(list.get(i).getLineId());
                zrwklist.setZbanz(list.get(i).getZbanz());
                zrwklist.setZbanc(list.get(i).getZbanc());
                zrwklist.setCharg(list.get(i).getCharg());
                zrwklist.setDiecd(list.get(i).getDiecd());
                zrwklist.setGmein(list.get(i).getGmein());
                zrwklist.setMatnr(list.get(i).getMatnr());
                zrwklist.setMatnr2(list.get(i).getMatnr2());
                zrwklist.setSfflg(list.get(i).getSfflg());
                zrwklist.setZpgdbar(list.get(i).getZpgdbar());
                zrwklist.setZxhbar(list.get(i).getZxhbar());
                zrwklist.setZgjbar(list.get(i).getZgjbar());
                zrwklist.setReviewc("G");
                zrwklist.setArbpr(list.get(i).getArbpr());
                zrwklist.setZotype(list.get(i).getZotype());
                zrwklist.setZqjjlh(list.get(i).getZqjjlh());
                zrwklist.setVornr(list.get(i).getVornr());
                zrwklist.setZpgdbar2(list.get(i).getZpgdbar2());
                //获取取件原因
                Qjcode qjcode = new Qjcode();
                qjcode = qjcodeService.selectById(Integer.valueOf(list.get(i).getZotype()));
                zrwklist.setRcode(qjcode.getRcode());
                //获取机加流转卡数据
                Cardh cardhjj = new Cardh();
                cardhjj = cardhService.selectByBarcode(list.get(i).getZpgdbar());
                Marc marc = marcService.selectByMatnr(cardhjj.getMatnr());
                zrwklist.setMaktx(marc.getMaktx());
                zrwklist.setGstrp(cardhjj.getGstrp());//机加的生产日期
                String gstrp = cardhjj.getGstrp().replace("-","");
                String charg2 = gstrp.substring(2,8) + "000" + list.get(i).getZbanc();
                zrwklist.setCharg2(charg2);//机加批次 根据机加生产订单来拼
                zrwklist.setVornr_old(list.get(i).getVornr());
                zrwklist.setZrnum(1L);
                listzrwk.add(zrwklist);
            }

        }
        return new ResponseData(listzrwk);
    }

}