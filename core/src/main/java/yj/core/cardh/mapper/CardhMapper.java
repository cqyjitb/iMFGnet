package yj.core.cardh.mapper;

import com.hand.hap.mybatis.common.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import yj.core.cardh.dto.Cardh;

public  interface CardhMapper
        extends Mapper<Cardh>
{
     List<Cardh> selectByAufnr(String paramString);

     int insertCardh(Cardh paramCardh);

     List<Cardh> queryAfterSort(Cardh paramCardh);

     int updateCardhStatus(Cardh paramCardh);

     int deleteCardh(Cardh paramCardh);

     String selectByAufnrMxno(String aufnr);

     List<Cardh> queryZuheAfterSort(Cardh paramCardh);

     Cardh selectByBarcode(String zpgdbar);

     Cardh selectByZxhbar(@Param("aufnr") String aufnr,@Param("zxhnum") String zxhnum);

     int updateCardhShiftAndSfflg(Cardh cardh);

     int updateDatforLsvor(String zpgdbar);

     int updateDatforFsvor(String zpgdbar);

     int updateForLgort(Cardh cardh);
}
