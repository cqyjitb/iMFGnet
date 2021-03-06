package yj.core.ztbc0002.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "sap_ztbc0002")
public class Ztbc0002 extends BaseDTO {
     @NotEmpty
      private String mandt; //集团

     @NotEmpty
      private String werks; //工厂

      private Date zdate; //日期

     @NotEmpty
      private String ztpnum; //托盘号

     @NotEmpty
      private String matnr; //物料编号

     @NotEmpty
      private String charg; //日期序列

     @Id
     @GeneratedValue
      private String zxhnum; //箱号

      private String zxhbar; //箱号条码

      private String ztpzt; //托盘条码最新状态 

      private String ztpzt2; //托盘条码上一状态 

      private String lgort; //库存地点

      private Double menge; //标准装箱数量

      private Double ameng; //实际装箱数量

      private String meins; //基本计量单位

      private String ztpwz; //托盘位置

      private String ztpbar; //托盘条码

      private String zmnum; //模号

      private String zscbc; //班次

      private String ztxt; //备注

      private String ztxt2; //托盘备注

      private String zbqbd; //标志（X 或空白）

      private String crnam; //创建者

      private Date crdat; //创建日期

      private Date crtim; //创建时间

      private String chnam; //最后修改者 

      private Date chdat; //修改日期

      private Date chtim; //修改时间


     public void setMandt(String mandt){
         this.mandt = mandt;
     }

     public String getMandt(){
         return mandt;
     }

     public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
     }

     public void setZdate(Date zdate){
         this.zdate = zdate;
     }

     public Date getZdate(){
         return zdate;
     }

     public void setZtpnum(String ztpnum){
         this.ztpnum = ztpnum;
     }

     public String getZtpnum(){
         return ztpnum;
     }

     public void setMatnr(String matnr){
         this.matnr = matnr;
     }

     public String getMatnr(){
         return matnr;
     }

     public void setCharg(String charg){
         this.charg = charg;
     }

     public String getCharg(){
         return charg;
     }

     public void setZxhnum(String zxhnum){
         this.zxhnum = zxhnum;
     }

     public String getZxhnum(){
         return zxhnum;
     }

     public void setZxhbar(String zxhbar){
         this.zxhbar = zxhbar;
     }

     public String getZxhbar(){
         return zxhbar;
     }

     public void setZtpzt(String ztpzt){
         this.ztpzt = ztpzt;
     }

     public String getZtpzt(){
         return ztpzt;
     }

     public void setZtpzt2(String ztpzt2){
         this.ztpzt2 = ztpzt2;
     }

     public String getZtpzt2(){
         return ztpzt2;
     }

     public void setLgort(String lgort){
         this.lgort = lgort;
     }

     public String getLgort(){
         return lgort;
     }

     public void setMenge(Double menge){
         this.menge = menge;
     }

     public Double getMenge(){
         return menge;
     }

     public void setAmeng(Double ameng){
         this.ameng = ameng;
     }

     public Double getAmeng(){
         return ameng;
     }

     public void setMeins(String meins){
         this.meins = meins;
     }

     public String getMeins(){
         return meins;
     }

     public void setZtpwz(String ztpwz){
         this.ztpwz = ztpwz;
     }

     public String getZtpwz(){
         return ztpwz;
     }

     public void setZtpbar(String ztpbar){
         this.ztpbar = ztpbar;
     }

     public String getZtpbar(){
         return ztpbar;
     }

     public void setZmnum(String zmnum){
         this.zmnum = zmnum;
     }

     public String getZmnum(){
         return zmnum;
     }

     public void setZscbc(String zscbc){
         this.zscbc = zscbc;
     }

     public String getZscbc(){
         return zscbc;
     }

     public void setZtxt(String ztxt){
         this.ztxt = ztxt;
     }

     public String getZtxt(){
         return ztxt;
     }

     public void setZtxt2(String ztxt2){
         this.ztxt2 = ztxt2;
     }

     public String getZtxt2(){
         return ztxt2;
     }

     public void setZbqbd(String zbqbd){
         this.zbqbd = zbqbd;
     }

     public String getZbqbd(){
         return zbqbd;
     }

     public void setCrnam(String crnam){
         this.crnam = crnam;
     }

     public String getCrnam(){
         return crnam;
     }

     public void setCrdat(Date crdat){
         this.crdat = crdat;
     }

     public Date getCrdat(){
         return crdat;
     }

     public void setCrtim(Date crtim){
         this.crtim = crtim;
     }

     public Date getCrtim(){
         return crtim;
     }

     public void setChnam(String chnam){
         this.chnam = chnam;
     }

     public String getChnam(){
         return chnam;
     }

     public void setChdat(Date chdat){
         this.chdat = chdat;
     }

     public Date getChdat(){
         return chdat;
     }

     public void setChtim(Date chtim){
         this.chtim = chtim;
     }

     public Date getChtim(){
         return chtim;
     }

     }
