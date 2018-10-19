package yj.core.wiptrasfer.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "wip_trasfer")
public class Trasfer extends BaseDTO {
     @Id
     @GeneratedValue
      private String id;

     @NotEmpty
      private String werks;

     @NotEmpty
      private String zpgdbar;

      private String transdat;

      private String transtim;

      private String aufnr;

      private String matnr;

      private String fromloc;

      private String fromloctxt;

      private String fromloctp;

      private String fromlocno;

      private Double gamng;

      private String toloc;

      private String toloctxt;

      private String toloctp;

      private String tolocno;


     public void setId(String id){
         this.id = id;
     }

     public String getId(){
         return id;
     }

     public void setWerks(String werks){
         this.werks = werks;
     }

     public String getWerks(){
         return werks;
     }

     public void setZpgdbar(String zpgdbar){
         this.zpgdbar = zpgdbar;
     }

     public String getZpgdbar(){
         return zpgdbar;
     }

    public String getTransdat() {
        return transdat;
    }

    public void setTransdat(String transdat) {
        this.transdat = transdat;
    }

    public String getTranstim() {
        return transtim;
    }

    public void setTranstim(String transtim) {
        this.transtim = transtim;
    }

    public void setAufnr(String aufnr){
         this.aufnr = aufnr;
     }

     public String getAufnr(){
         return aufnr;
     }

     public void setMatnr(String matnr){
         this.matnr = matnr;
     }

     public String getMatnr(){
         return matnr;
     }

     public void setFromloc(String fromloc){
         this.fromloc = fromloc;
     }

     public String getFromloc(){
         return fromloc;
     }

     public void setFromloctxt(String fromloctxt){
         this.fromloctxt = fromloctxt;
     }

     public String getFromloctxt(){
         return fromloctxt;
     }

     public void setFromloctp(String fromloctp){
         this.fromloctp = fromloctp;
     }

     public String getFromloctp(){
         return fromloctp;
     }

     public void setFromlocno(String fromlocno){
         this.fromlocno = fromlocno;
     }

     public String getFromlocno(){
         return fromlocno;
     }

     public void setGamng(Double gamng){
         this.gamng = gamng;
     }

     public Double getGamng(){
         return gamng;
     }

     public void setToloc(String toloc){
         this.toloc = toloc;
     }

     public String getToloc(){
         return toloc;
     }

     public void setToloctxt(String toloctxt){
         this.toloctxt = toloctxt;
     }

     public String getToloctxt(){
         return toloctxt;
     }

     public void setToloctp(String toloctp){
         this.toloctp = toloctp;
     }

     public String getToloctp(){
         return toloctp;
     }

     public void setTolocno(String tolocno){
         this.tolocno = tolocno;
     }

     public String getTolocno(){
         return tolocno;
     }

     }
