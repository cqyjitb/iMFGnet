package yj.core.zwipq.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.webservice_migo.components.MigoWebserviceUtil;
import yj.core.webservice_migo.dto.DTMIGOParam;
import yj.core.webservice_migo.dto.DTMIGOReturn;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.mapper.XhcardMapper;
import yj.core.ztbc0018.dto.Ztbc0018;
import yj.core.ztbc0018.mapper.Ztbc0018Mapper;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.mapper.ZwipqMapper;
import yj.core.zwipq.service.IZwipqService;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ZwipqServiceImpl extends BaseServiceImpl<Zwipq> implements IZwipqService{
    @Autowired
    private ZwipqMapper zwipqMapper;
    @Autowired
    private MigoWebserviceUtil migo;
    @Autowired
    private XhcardMapper xhcardMapper;
    @Autowired
    private Ztbc0018Mapper ztbc0018Mapper;
    @Autowired
    private MarcMapper marcMapper;
    @Autowired
    private CardhMapper cardhMapper;

    @Override
    public List<Zwipq> selectByLineIdAndZxhbar(String line_id, String zxhbar) {
        return zwipqMapper.selectByLineIdAndZxhbar(line_id,zxhbar);
    }

    @Override
    public Map selectMaxQsenq(Map m) {

                zwipqMapper.selectMaxQsenq(m);
                return  m;
    }

    @Override
    public int InsertIntoZwipq(List<Zwipq> list) {
        int num = 0;
        for (int i = 0;i<list.size();i++){
            num = num + 1;
            zwipqMapper.InsertIntoZwipq(list.get(i));
        }
        return num;
    }

    @Override
    public DTMIGOReturn callMigo(String zxhbar, int cynum, String line_id, String bwart,int createBy,String zpgdbar) {
        DTMIGOParam param = new DTMIGOParam();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newPostingdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        Xhcard xhcard = xhcardMapper.selectByBacode(zxhbar);
        Cardh cardh = cardhMapper.selectByZxhbar(xhcard.getAufnr(),xhcard.getZxhnum());
        Marc marc = marcMapper.selectByMatnr(xhcard.getMatnr());
        Integer cy = cynum;
        param.setACTION("");
        param.setREFDOC("");
        param.setAUFNR(xhcard.getAufnr());
        param.setBKTXT("");
        param.setWERKS(xhcard.getWerks());
        param.setBLDAT(newPostingdate);
        param.setBUDAT(newPostingdate);
        param.setBWART(bwart);
        param.setCHARG(xhcard.getChargkc());
        param.setERFME(marc.getMeins());//单位
        param.setLGORT(xhcard.getLgort());
        param.setERFMG(cy.toString());//数量
        param.setMATNR(xhcard.getMatnr());
        param.setRSNUM("");
        param.setRSPOS("");
        param.setSJAHR("");
        param.setSMBLN("");
        param.setSMBLP("");
        param.setTAKEIT("");
        param.setUMCHA("");
        param.setUMLGO("");
        DTMIGOReturn rs = migo.receiveConfirmation(param);

        //将调账记录保存到表sap_ztbc0018中
        Ztbc0018 ztbc0018 = new Ztbc0018();
        UUID uuid = java.util.UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-","");
        ztbc0018.setId(uuidstr);
        ztbc0018.setMblnr(rs.getMBLNR());
        ztbc0018.setLineId(line_id);
        ztbc0018.setMjahr(rs.getMJAHR());
        Long bmenge = Long.parseLong(xhcard.getMenge().substring(0,xhcard.getMenge().length() -2));
        ztbc0018.setBmenge(bmenge);
        if (bwart.equals("702")){
            Long smenge = Long.parseLong(xhcard.getMenge().substring(0,xhcard.getMenge().length() -2)) - cynum;
            ztbc0018.setSmenge(smenge);
        }else{
            Long smenge =  Long.parseLong(xhcard.getMenge().substring(0,xhcard.getMenge().length() -2)) + cynum;
            ztbc0018.setSmenge(smenge);
        }
        ztbc0018.setLgort(xhcard.getLgort());
        ztbc0018.setMeins(xhcard.getMeins());
        ztbc0018.setZxhbar(xhcard.getZxhbar());
        ztbc0018.setZpgdbar2(cardh.getZpgdbar());
        ztbc0018.setCreatedBy(Long.valueOf(createBy));
        ztbc0018.setCreationDate(new Date());
        ztbc0018.setZdlty("");
        ztbc0018.setZspbz("");
        ztbc0018.setZspty("");
        ztbc0018Mapper.insertZtbc0018(ztbc0018);
        return rs;
    }

    @Override
    public List<Zwipq> selectBylineidAndZxhbarAndZpgdbar(String line_id, String zxhbar, String zpgdbar) {
        return zwipqMapper.selectBylineidAndZxhbarAndZpgdbar(line_id,zxhbar,zpgdbar);
    }

    @Override
    public List<Zwipq> selectBylineforjjqj(String line_id) {
        return zwipqMapper.selectBylineforjjqj(line_id);
    }

    @Override
    public List<Zwipq> selectForqj(String line_id, String sfflg) {
        return zwipqMapper.selectForqj(line_id,sfflg);
    }

    @Override
    public int updateForQj(List<Zwipq> zwipqs) {
        int sum = 0;
        for (int i =0;i<zwipqs.size();i++){
            sum = sum + zwipqMapper.updateForQj(zwipqs.get(i));
        }
        return sum;
    }

    @Override
    public Zwipq selectById(String zwipqid) {
        return zwipqMapper.selectById(zwipqid);
    }

    @Override
    public List<Zwipq> selectByLineid(String line_id) {
        return zwipqMapper.selectByLineid(line_id);
    }

    @Override
    public int updateZqjklAndQenq(Zwipq zwipq) {
        return zwipqMapper.updateZqjklAndQenq(zwipq);
    }

    @Override
    public int updateQsenqBatch(List<Zwipq> list) {
        int sum = 0;
        for (int i= 0;i<list.size();i++){
            if(zwipqMapper.updateQsenq(list.get(i)) == 1){
                sum = sum + 1;
            }
        }
        return sum;
    }

    @Override
    public List<Zwipq> selectForJjxx(String line_id, String classgrp) {
        return zwipqMapper.selectForJjxx(line_id,classgrp);
    }

    @Override
    public int updateZoffl(List<Zwipq> list) {
        int sum = 0;
        for (int i = 0;i<list.size();i++){
            if (zwipqMapper.updateZoffl(list.get(i)) == 1){
                sum = sum + 1;
            }
        }
        return sum;
    }
}