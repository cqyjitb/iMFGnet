package yj.core.plpo.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "sap_plpo")
public class Plpo extends BaseDTO {
     @NotEmpty
      private String matnr;

     @Id
     @GeneratedValue
      private String werks;

      private String plnty;

      private String plnnr;

      private String plnal;

      private String zkriz;

      private String zaehl;

      private Date datuv;

      private Date validTo;

      private String verwe;

      private String statu;

      private String loekz;

      private String plnkn;

      private String vornr;

      private String steus;

      private String arbid;

      private String arbpl;

      private String ktsch;

      private String ltxa1;

      private String vge01;

      private Double vgw01;

      private String attr1;

      private String attr2;

      private String attr3;

      private String attr4;

      private String attr5;

      private String attr6;

      private String attr7;


     public void setMatnr(String matnr){
         this.matnr = matnr;
     }

     public String getMatnr(){
         return matnr;
     }

     public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
     }

     public void setPlnty(String plnty){
         this.plnty = plnty;
     }

     public String getPlnty(){
         return plnty;
     }

     public void setPlnnr(String plnnr){
         this.plnnr = plnnr;
     }

     public String getPlnnr(){
         return plnnr;
     }

     public void setPlnal(String plnal){
         this.plnal = plnal;
     }

     public String getPlnal(){
         return plnal;
     }

     public void setZkriz(String zkriz){
         this.zkriz = zkriz;
     }

     public String getZkriz(){
         return zkriz;
     }

     public void setZaehl(String zaehl){
         this.zaehl = zaehl;
     }

     public String getZaehl(){
         return zaehl;
     }

     public void setDatuv(Date datuv){
         this.datuv = datuv;
     }

     public Date getDatuv(){
         return datuv;
     }

     public void setValidTo(Date validTo){
         this.validTo = validTo;
     }

     public Date getValidTo(){
         return validTo;
     }

     public void setVerwe(String verwe){
         this.verwe = verwe;
     }

     public String getVerwe(){
         return verwe;
     }

     public void setStatu(String statu){
         this.statu = statu;
     }

     public String getStatu(){
         return statu;
     }

     public void setLoekz(String loekz){
         this.loekz = loekz;
     }

     public String getLoekz(){
         return loekz;
     }

     public void setPlnkn(String plnkn){
         this.plnkn = plnkn;
     }

     public String getPlnkn(){
         return plnkn;
     }

     public void setVornr(String vornr){
         this.vornr = vornr;
     }

     public String getVornr(){
         return vornr;
     }

     public void setSteus(String steus){
         this.steus = steus;
     }

     public String getSteus(){
         return steus;
     }

     public void setArbid(String arbid){
         this.arbid = arbid;
     }

     public String getArbid(){
         return arbid;
     }

     public void setArbpl(String arbpl){
         this.arbpl = arbpl;
     }

     public String getArbpl(){
         return arbpl;
     }

     public void setKtsch(String ktsch){
         this.ktsch = ktsch;
     }

     public String getKtsch(){
         return ktsch;
     }

     public void setLtxa1(String ltxa1){
         this.ltxa1 = ltxa1;
     }

     public String getLtxa1(){
         return ltxa1;
     }

     public void setVge01(String vge01){
         this.vge01 = vge01;
     }

     public String getVge01(){
         return vge01;
     }

     public void setVgw01(Double vgw01){
         this.vgw01 = vgw01;
     }

     public Double getVgw01(){
         return vgw01;
     }

     public void setAttr1(String attr1){
         this.attr1 = attr1;
     }

     public String getAttr1(){
         return attr1;
     }

     public void setAttr2(String attr2){
         this.attr2 = attr2;
     }

     public String getAttr2(){
         return attr2;
     }

     public void setAttr3(String attr3){
         this.attr3 = attr3;
     }

     public String getAttr3(){
         return attr3;
     }

     public void setAttr4(String attr4){
         this.attr4 = attr4;
     }

     public String getAttr4(){
         return attr4;
     }

     public void setAttr5(String attr5){
         this.attr5 = attr5;
     }

     public String getAttr5(){
         return attr5;
     }

     public void setAttr6(String attr6){
         this.attr6 = attr6;
     }

     public String getAttr6(){
         return attr6;
     }

     public void setAttr7(String attr7){
         this.attr7 = attr7;
     }

     public String getAttr7(){
         return attr7;
     }

     }