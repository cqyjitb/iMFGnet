package yj.core.inoutrecord.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.mapper.InOutRecordMapper;
import yj.core.inoutrecord.service.IInOutRecordService;
import org.springframework.transaction.annotation.Transactional;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.qjcode.dto.Qjcode;
import yj.core.qjcode.mapper.QjcodeMapper;
import yj.core.zudlist.dto.Zudlist;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InOutRecordServiceImpl extends BaseServiceImpl<InOutRecord> implements IInOutRecordService {

    @Autowired
    private InOutRecordMapper inOutRecordMapper;
    @Autowired
    private MarcMapper marcMapper;
    @Autowired
    private CardhMapper cardhMapper;
    @Autowired
    private QjcodeMapper qjcodeMapper;
    @Override
    public int insertQjrecode(List<InOutRecord> list) {
        int sum = 0;
        for (int i =0;i<list.size();i++){
        sum = sum +   inOutRecordMapper.insertQjrecode(list.get(i));
        }
        return sum;
    }

    @Override
    public List<InOutRecord> selectforjjhj(String line_id,String qjcodeval,String lineiocfgval,String matnr,int hjtype) {
        return inOutRecordMapper.selectforjjhj(line_id,qjcodeval,lineiocfgval,matnr,hjtype);
    }

    @Override
    public List<InOutRecord> selectNoReflg(String line_id, String zotype, String vornr, String sfflg, String matnr,int hjtype) {
        return inOutRecordMapper.selectNoReflg(line_id,zotype,vornr,matnr,sfflg,hjtype);
    }

    @Override
    public int updateReflag(InOutRecord inOutRecord) {
        return inOutRecordMapper.updateReflag(inOutRecord);
    }

    @Override
//    public List<Zudlist> selectforZud(String line_id, String classgrp, int page, int pagesize, IRequest iRequest) {
    public List<Zudlist> selectforZud(String line_id, String classgrp) {
//        PageHelper.startPage(page, pagesize);
        List<InOutRecord> list = inOutRecordMapper.selectforZud(line_id,classgrp);
        List<Zudlist> listzuds = new ArrayList<>();
        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                Zudlist zudlist = new Zudlist();
                zudlist.setLineId(list.get(i).getLineId());
                zudlist.setZbanz(list.get(i).getZbanz());
                zudlist.setZbanc(list.get(i).getZbanc());
                zudlist.setArbpr(list.get(i).getArbpr());
                zudlist.setCharg(list.get(i).getCharg());
                zudlist.setCharg2("");
                zudlist.setDiecd(list.get(i).getDiecd());
                zudlist.setGmein(list.get(i).getGmein());
                zudlist.setMatnr(list.get(i).getMatnr());
                zudlist.setMatnr2(list.get(i).getMatnr2());
                Marc marc2 = new Marc();
                marc2 = marcMapper.selectByMatnr(list.get(i).getMatnr2());
                zudlist.setMaktx(marc2.getMaktx());
                zudlist.setSfflg(list.get(i).getSfflg());
                zudlist.setUdtype("1");
                zudlist.setZdnum(1L);
                zudlist.setZxhbar(list.get(i).getZxhbar());
                zudlist.setZpgdbar(list.get(i).getZpgdbar());
                zudlist.setZpgdbar2(list.get(i).getZpgdbar2());
                //获取机加流转卡数据
                Cardh cardhjj = new Cardh();
                cardhjj = cardhMapper.selectByBarcode(list.get(i).getZpgdbar());
                zudlist.setGstrp(cardhjj.getGstrp());//机加的生产日期
                String gstrp = cardhjj.getGstrp().replace("-","");
                String charg2 = gstrp.substring(2,8) + "000" + list.get(i).getZbanc();
                zudlist.setCharg2(charg2);//机加批次 根据机加生产订单来拼
                //获取取件原因
                Qjcode qjcode = new Qjcode();
                qjcode = qjcodeMapper.selectById(Integer.valueOf(list.get(i).getZotype()));
                zudlist.setRcode(qjcode.getRcode());
                zudlist.setVornr_old(list.get(i).getVornr());
                zudlist.setZgjbar(list.get(i).getZgjbar());
                zudlist.setZqjjlh(list.get(i).getZqjjlh());
                zudlist.setReviewc("F");
                zudlist.setZqxdm(list.get(i).getZqxdm());
                zudlist.setZissuetxt(list.get(i).getZissuetxt());
                listzuds.add(zudlist);
            }
        }

        return listzuds;
    }

    @Override
    public InOutRecord selectById(String zqjjlh) {
        return inOutRecordMapper.selectById(zqjjlh);
    }

    @Override
    public int batchUpdateReflag(List<InOutRecord> list) {
        int sum = 0;
        for (int i = 0;i<list.size();i++){
            sum = sum + inOutRecordMapper.updateReflag(list.get(i));
        }
        return sum;
    }

    @Override
    public List<InOutRecord> selectforZrwk(String line_id, String classgrp, String zotype,IRequest iRequest) {
        return inOutRecordMapper.selectforZrwk(line_id,classgrp,zotype);
    }
}