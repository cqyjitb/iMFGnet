package yj.core.marc.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.marc.dto.Marc;

import java.util.List;

public interface IMarcService extends IBaseService<Marc>, ProxySelf<IMarcService>{

    int isExit(String matnr);

    int updateByMatnr(Marc dto);

    int insertByMatnr(Marc dto);

    Marc selectByMatnr(String matnr);

    int saveChange(List<Marc> list);
}