package yj.core.wipcurlzk.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipcurlzk.dto.Curlzk;

import java.util.Date;
import java.util.List;

public interface CurlzkMapper extends Mapper<Curlzk>{
    Curlzk selectById(@Param("line_id") Long line_id,@Param("classgrp") String classgrp);
    int updateZxhbar(Curlzk dto);

    List<Curlzk> selectAllLinesforZxhbar(Long line_id);
}