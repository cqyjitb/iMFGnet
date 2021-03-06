package yj.core.webservice_newbg.sender;

        import java.util.ArrayList;
        import java.util.List;
        import javax.xml.bind.annotation.XmlAccessType;
        import javax.xml.bind.annotation.XmlAccessorType;
        import javax.xml.bind.annotation.XmlElement;
        import javax.xml.bind.annotation.XmlType;

/**
 * ����������ݽṹ
 *
 * <p>
 * Java class for DT_BAOGONG_Send_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_BAOGONG_Send_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PWERK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="XMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="REVERSE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR6" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ATTR7" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPRTP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ARBPL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUART" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FSTVOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LSTVOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ITEMQP" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SUBRSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SUBRSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BDMNG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_BAOGONG_Send_Req", propOrder = { "item", "itemqp" })
public class DTBAOGONGSendReq {

    @XmlElement(name = "ITEM", required = true)
    protected List<DTBAOGONGSendReq.ITEM> item;
    @XmlElement(name = "ITEMQP")
    protected List<DTBAOGONGSendReq.ITEMQP> itemqp;

    /**
     * Gets the value of the item property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the item property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getITEM().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTBAOGONGSendReq.ITEM }
     *
     *
     */
    public List<DTBAOGONGSendReq.ITEM> getITEM() {
        if (item == null) {
            item = new ArrayList<DTBAOGONGSendReq.ITEM>();
        }
        return this.item;
    }

    /**
     * Gets the value of the itemqp property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the itemqp property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getITEMQP().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTBAOGONGSendReq.ITEMQP }
     *
     *
     */
    public List<DTBAOGONGSendReq.ITEMQP> getITEMQP() {
        if (itemqp == null) {
            itemqp = new ArrayList<DTBAOGONGSendReq.ITEMQP>();
        }
        return this.itemqp;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PWERK" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="GMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="XMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZSCX" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZPGDBH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="REVERSE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ATTR1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ATTR2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ATTR3" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ATTR4" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ATTR5" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ATTR6" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ATTR7" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZPRTP" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ARBPL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AUART" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FSTVOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LSTVOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "pwerk", "aufnr", "vornr", "budat",
            "gmnga", "xmnga", "rmnga", "zscbc", "zscx", "zmnum", "datum",
            "zpgdbar", "zpgdbh", "rsnum", "rspos", "reverse", "username",
            "attr1", "attr2", "attr3", "attr4", "attr5", "attr6", "attr7",
            "zprtp", "arbpl", "ztpbar", "auart", "charg", "fstvor", "lstvor" })
    public static class ITEM {

        @XmlElement(name = "PWERK", required = true)
        protected String pwerk;
        @XmlElement(name = "AUFNR", required = true)
        protected String aufnr;
        @XmlElement(name = "VORNR", required = true)
        protected String vornr;
        @XmlElement(name = "BUDAT", required = true)
        protected String budat;
        @XmlElement(name = "GMNGA", required = true)
        protected String gmnga;
        @XmlElement(name = "XMNGA", required = true)
        protected String xmnga;
        @XmlElement(name = "RMNGA", required = true)
        protected String rmnga;
        @XmlElement(name = "ZSCBC", required = true)
        protected String zscbc;
        @XmlElement(name = "ZSCX", required = true)
        protected String zscx;
        @XmlElement(name = "ZMNUM", required = true)
        protected String zmnum;
        @XmlElement(name = "DATUM", required = true)
        protected String datum;
        @XmlElement(name = "ZPGDBAR", required = true)
        protected String zpgdbar;
        @XmlElement(name = "ZPGDBH", required = true)
        protected String zpgdbh;
        @XmlElement(name = "RSNUM", required = true)
        protected String rsnum;
        @XmlElement(name = "RSPOS", required = true)
        protected String rspos;
        @XmlElement(name = "REVERSE", required = true)
        protected String reverse;
        @XmlElement(name = "USERNAME", required = true)
        protected String username;
        @XmlElement(name = "ATTR1", required = true)
        protected String attr1;
        @XmlElement(name = "ATTR2", required = true)
        protected String attr2;
        @XmlElement(name = "ATTR3", required = true)
        protected String attr3;
        @XmlElement(name = "ATTR4", required = true)
        protected String attr4;
        @XmlElement(name = "ATTR5", required = true)
        protected String attr5;
        @XmlElement(name = "ATTR6", required = true)
        protected String attr6;
        @XmlElement(name = "ATTR7", required = true)
        protected String attr7;
        @XmlElement(name = "ZPRTP", required = true)
        protected String zprtp;
        @XmlElement(name = "ARBPL", required = true)
        protected String arbpl;
        @XmlElement(name = "ZTPBAR", required = true)
        protected String ztpbar;
        @XmlElement(name = "AUART", required = true)
        protected String auart;
        @XmlElement(name = "CHARG", required = true)
        protected String charg;
        @XmlElement(name = "FSTVOR", required = true)
        protected String fstvor;
        @XmlElement(name = "LSTVOR", required = true)
        protected String lstvor;

        /**
         * Gets the value of the pwerk property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getPWERK() {
            return pwerk;
        }

        /**
         * Sets the value of the pwerk property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setPWERK(String value) {
            this.pwerk = value;
        }

        /**
         * Gets the value of the aufnr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getAUFNR() {
            return aufnr;
        }

        /**
         * Sets the value of the aufnr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setAUFNR(String value) {
            this.aufnr = value;
        }

        /**
         * Gets the value of the vornr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getVORNR() {
            return vornr;
        }

        /**
         * Sets the value of the vornr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setVORNR(String value) {
            this.vornr = value;
        }

        /**
         * Gets the value of the budat property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getBUDAT() {
            return budat;
        }

        /**
         * Sets the value of the budat property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setBUDAT(String value) {
            this.budat = value;
        }

        /**
         * Gets the value of the gmnga property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getGMNGA() {
            return gmnga;
        }

        /**
         * Sets the value of the gmnga property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setGMNGA(String value) {
            this.gmnga = value;
        }

        /**
         * Gets the value of the xmnga property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getXMNGA() {
            return xmnga;
        }

        /**
         * Sets the value of the xmnga property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setXMNGA(String value) {
            this.xmnga = value;
        }

        /**
         * Gets the value of the rmnga property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRMNGA() {
            return rmnga;
        }

        /**
         * Sets the value of the rmnga property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRMNGA(String value) {
            this.rmnga = value;
        }

        /**
         * Gets the value of the zscbc property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZSCBC() {
            return zscbc;
        }

        /**
         * Sets the value of the zscbc property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZSCBC(String value) {
            this.zscbc = value;
        }

        /**
         * Gets the value of the zscx property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZSCX() {
            return zscx;
        }

        /**
         * Sets the value of the zscx property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZSCX(String value) {
            this.zscx = value;
        }

        /**
         * Gets the value of the zmnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZMNUM() {
            return zmnum;
        }

        /**
         * Sets the value of the zmnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZMNUM(String value) {
            this.zmnum = value;
        }

        /**
         * Gets the value of the datum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDATUM() {
            return datum;
        }

        /**
         * Sets the value of the datum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setDATUM(String value) {
            this.datum = value;
        }

        /**
         * Gets the value of the zpgdbar property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZPGDBAR() {
            return zpgdbar;
        }

        /**
         * Sets the value of the zpgdbar property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZPGDBAR(String value) {
            this.zpgdbar = value;
        }

        /**
         * Gets the value of the zpgdbh property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZPGDBH() {
            return zpgdbh;
        }

        /**
         * Sets the value of the zpgdbh property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZPGDBH(String value) {
            this.zpgdbh = value;
        }

        /**
         * Gets the value of the rsnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRSNUM() {
            return rsnum;
        }

        /**
         * Sets the value of the rsnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRSNUM(String value) {
            this.rsnum = value;
        }

        /**
         * Gets the value of the rspos property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRSPOS() {
            return rspos;
        }

        /**
         * Sets the value of the rspos property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRSPOS(String value) {
            this.rspos = value;
        }

        /**
         * Gets the value of the reverse property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getREVERSE() {
            return reverse;
        }

        /**
         * Sets the value of the reverse property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setREVERSE(String value) {
            this.reverse = value;
        }

        /**
         * Gets the value of the username property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getUSERNAME() {
            return username;
        }

        /**
         * Sets the value of the username property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setUSERNAME(String value) {
            this.username = value;
        }

        /**
         * Gets the value of the attr1 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getATTR1() {
            return attr1;
        }

        /**
         * Sets the value of the attr1 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setATTR1(String value) {
            this.attr1 = value;
        }

        /**
         * Gets the value of the attr2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getATTR2() {
            return attr2;
        }

        /**
         * Sets the value of the attr2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setATTR2(String value) {
            this.attr2 = value;
        }

        /**
         * Gets the value of the attr3 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getATTR3() {
            return attr3;
        }

        /**
         * Sets the value of the attr3 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setATTR3(String value) {
            this.attr3 = value;
        }

        /**
         * Gets the value of the attr4 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getATTR4() {
            return attr4;
        }

        /**
         * Sets the value of the attr4 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setATTR4(String value) {
            this.attr4 = value;
        }

        /**
         * Gets the value of the attr5 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getATTR5() {
            return attr5;
        }

        /**
         * Sets the value of the attr5 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setATTR5(String value) {
            this.attr5 = value;
        }

        /**
         * Gets the value of the attr6 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getATTR6() {
            return attr6;
        }

        /**
         * Sets the value of the attr6 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setATTR6(String value) {
            this.attr6 = value;
        }

        /**
         * Gets the value of the attr7 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getATTR7() {
            return attr7;
        }

        /**
         * Sets the value of the attr7 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setATTR7(String value) {
            this.attr7 = value;
        }

        /**
         * Gets the value of the zprtp property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZPRTP() {
            return zprtp;
        }

        /**
         * Sets the value of the zprtp property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZPRTP(String value) {
            this.zprtp = value;
        }

        /**
         * Gets the value of the arbpl property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getARBPL() {
            return arbpl;
        }

        /**
         * Sets the value of the arbpl property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setARBPL(String value) {
            this.arbpl = value;
        }

        /**
         * Gets the value of the ztpbar property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPBAR() {
            return ztpbar;
        }

        /**
         * Sets the value of the ztpbar property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPBAR(String value) {
            this.ztpbar = value;
        }

        /**
         * Gets the value of the auart property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getAUART() {
            return auart;
        }

        /**
         * Sets the value of the auart property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setAUART(String value) {
            this.auart = value;
        }

        /**
         * Gets the value of the charg property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCHARG() {
            return charg;
        }

        /**
         * Sets the value of the charg property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setCHARG(String value) {
            this.charg = value;
        }

        /**
         * Gets the value of the fstvor property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getFSTVOR() {
            return fstvor;
        }

        /**
         * Sets the value of the fstvor property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setFSTVOR(String value) {
            this.fstvor = value;
        }

        /**
         * Gets the value of the lstvor property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getLSTVOR() {
            return lstvor;
        }

        /**
         * Sets the value of the lstvor property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setLSTVOR(String value) {
            this.lstvor = value;
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SUBRSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SUBRSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BDMNG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "subrsnum", "subrspos", "matnr", "bdmng",
            "meins", "charg", "werks", "lgort" })
    public static class ITEMQP {

        @XmlElement(name = "SUBRSNUM", required = true)
        protected String subrsnum;
        @XmlElement(name = "SUBRSPOS", required = true)
        protected String subrspos;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "BDMNG", required = true)
        protected String bdmng;
        @XmlElement(name = "MEINS", required = true)
        protected String meins;
        @XmlElement(name = "CHARG", required = true)
        protected String charg;
        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "LGORT", required = true)
        protected String lgort;

        /**
         * Gets the value of the subrsnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSUBRSNUM() {
            return subrsnum;
        }

        /**
         * Sets the value of the subrsnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSUBRSNUM(String value) {
            this.subrsnum = value;
        }

        /**
         * Gets the value of the subrspos property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSUBRSPOS() {
            return subrspos;
        }

        /**
         * Sets the value of the subrspos property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSUBRSPOS(String value) {
            this.subrspos = value;
        }

        /**
         * Gets the value of the matnr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMATNR() {
            return matnr;
        }

        /**
         * Sets the value of the matnr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMATNR(String value) {
            this.matnr = value;
        }

        /**
         * Gets the value of the bdmng property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getBDMNG() {
            return bdmng;
        }

        /**
         * Sets the value of the bdmng property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setBDMNG(String value) {
            this.bdmng = value;
        }

        /**
         * Gets the value of the meins property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMEINS() {
            return meins;
        }

        /**
         * Sets the value of the meins property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMEINS(String value) {
            this.meins = value;
        }

        /**
         * Gets the value of the charg property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCHARG() {
            return charg;
        }

        /**
         * Sets the value of the charg property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setCHARG(String value) {
            this.charg = value;
        }

        /**
         * Gets the value of the werks property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getWERKS() {
            return werks;
        }

        /**
         * Sets the value of the werks property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setWERKS(String value) {
            this.werks = value;
        }

        /**
         * Gets the value of the lgort property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getLGORT() {
            return lgort;
        }

        /**
         * Sets the value of the lgort property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setLGORT(String value) {
            this.lgort = value;
        }

    }

}
