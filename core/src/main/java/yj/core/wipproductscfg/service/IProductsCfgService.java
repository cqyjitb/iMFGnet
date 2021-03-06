package yj.core.wipproductscfg.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipproductscfg.dto.ProductsCfg;

import java.util.List;

public interface IProductsCfgService extends IBaseService<ProductsCfg>, ProxySelf<IProductsCfgService>{
    List<ProductsCfg> selectById(long line_id);

    int selectMaxByLineidAndMatnr(ProductsCfg dto);

    int selectMaxByLineidAndMatnrLgort(ProductsCfg dto);

    /**
     * 按照机加物料编码查询 生产线ID
     * @param line_id
     * @param matnr
     * @return
     */
    ProductsCfg selectByLineidAndPMatnr(String line_id,String matnr);

    /**
     * 按照压铸毛坯物料 生产线ID 查询
     * @param line_id
     * @param matnr
     * @return
     */

    ProductsCfg selectByLineidAndMatnr(String line_id,String matnr);
}