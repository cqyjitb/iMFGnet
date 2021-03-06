package yj.core.qpcd.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.qpcd.dto.Qpcd;
import yj.core.qpcd.mapper.QpcdMapper;
import yj.core.qpcd.service.IQpcdService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QpcdServiceImpl extends BaseServiceImpl<Qpcd> implements IQpcdService{
    @Autowired
    private QpcdMapper qpcdMapper;
    @Override
    public List<Qpcd> selectAllForBlcl() {
        return qpcdMapper.selectAllForBlcl();
    }
}