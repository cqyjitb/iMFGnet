package yj.core.afvc.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@ExtensionAttribute(disable=true)
@Table(name="sap_afvc")
public class Afvc
        extends BaseDTO
{
    @Id
    @GeneratedValue
    private String aufpl;
    @NotEmpty
    private String aplzl;
    private String werks;
    private String vornr;
    private String steus;
    private String ltxa1;
    private String arbpl;
    private String ktext;
    private String ktsch;
    private String flag;

    public void setAufpl(String aufpl)
    {
        this.aufpl = aufpl;
    }

    public String getAufpl()
    {
        return this.aufpl;
    }

    public void setAplzl(String aplzl)
    {
        this.aplzl = aplzl;
    }

    public String getAplzl()
    {
        return this.aplzl;
    }

    public void setWerks(String werks)
    {
        this.werks = werks;
    }

    public String getWerks()
    {
        return this.werks;
    }

    public void setVornr(String vornr)
    {
        this.vornr = vornr;
    }

    public String getVornr()
    {
        return this.vornr;
    }

    public void setSteus(String steus)
    {
        this.steus = steus;
    }

    public String getSteus()
    {
        return this.steus;
    }

    public void setLtxa1(String ltxa1)
    {
        this.ltxa1 = ltxa1;
    }

    public String getLtxa1()
    {
        return this.ltxa1;
    }

    public void setArbpl(String arbpl)
    {
        this.arbpl = arbpl;
    }

    public String getArbpl()
    {
        return this.arbpl;
    }

    public void setKtext(String ktext)
    {
        this.ktext = ktext;
    }

    public String getKtext()
    {
        return this.ktext;
    }

    public String getFlag()
    {
        return this.flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getKtsch() {
        return ktsch;
    }

    public void setKtsch(String ktsch) {
        this.ktsch = ktsch;
    }
}
