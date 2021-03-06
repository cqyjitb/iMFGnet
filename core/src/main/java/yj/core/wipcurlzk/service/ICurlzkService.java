package yj.core.wipcurlzk.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipcurlzk.dto.Curlzk;

import java.util.Date;
import java.util.List;

public interface ICurlzkService extends IBaseService<Curlzk>, ProxySelf<ICurlzkService>{
    Curlzk selectById(Long line_id,String classgrp);
    int updateZxhbar(Curlzk dto);

    List<Curlzk> selectAllLinesforZxhbar(Long line_id);
}