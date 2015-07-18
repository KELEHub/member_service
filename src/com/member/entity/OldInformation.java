package com.member.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity@DynamicUpdate(true)@DynamicInsert(true)
//@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,optimisticLock=org.hibernate.annotations.OptimisticLockType.VERSION)
@Table(name="OldInformation")
@org.hibernate.annotations.Table(comment="会员信息表",appliesTo="OldInformation")
public class OldInformation {
	
	@Column(name="i_id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员登录ID */
	@Column(name="i_number")
	private String i_number;
	
	/**会员登录ID */
	@Column(name="i_password")
	private String i_password;
	
	/**会员登录ID */
	@Column(name="i_protectpassword")
	private String i_protectpassword;
	
	/**会员登录ID */
	@Column(name="i_qualification")
	private String i_qualification;
	
	
	/**会员登录ID */
	@Column(name="i_grid")
	private String i_grid;
	
	/**会员登录ID */
	@Column(name="i_gridname")
	private String i_gridname;
	
	/**会员登录ID */
	@Column(name="i_name")
	private String i_name;
	
	/**会员登录ID */
	@Column(name="i_gender")
	private String i_gender;
	
	/**会员登录ID */
	@Column(name="i_age")
	private String i_age;
	
	/**会员登录ID */
	@Column(name="i_nickname")
	private String i_nickname;
	
	/**会员登录ID */
	@Column(name="i_identity")
	private String i_identity;
	
	
	/**会员登录ID */
	@Column(name="i_email")
	private String i_email;
	
	
	/**会员登录ID */
	@Column(name="i_phonenumber")
	private String i_phonenumber;
	
	/**会员登录ID */
	@Column(name="i_bankname")
	private String i_bankname;
	
	/**会员登录ID */
	@Column(name="i_bankprovince")
	private String i_bankprovince;
	
	
	/**会员登录ID */
	@Column(name="i_bankcity")
	private String i_bankcity;

	/**会员登录ID */
	@Column(name="i_bankcounty")
	private String i_bankcounty;
	
	/**会员登录ID */
	@Column(name="i_bankaddress")
	private String i_bankaddress;
	
	/**会员登录ID */
	@Column(name="i_bankcard")
	private String i_bankcard;
	
	/**会员登录ID */
	@Column(name="i_postnumber")
	private String i_postnumber;
	
	/**会员登录ID */
	@Column(name="i_linkprovince")
	private String i_linkprovince;
	
	/**会员登录ID */
	@Column(name="i_linkcity")
	private String i_linkcity;
	
	/**会员登录ID */
	@Column(name="i_linkcounty")
	private String i_linkcounty;

	/**会员登录ID */
	@Column(name="i_linkaddress")
	private String i_linkaddress;
	
	/**会员登录ID */
	@Column(name="i_registerdate")
	private Date i_registerdate;
	
	/**会员登录ID */
	@Column(name="i_checkeddate")
	private Date i_checkeddate;

	/**会员登录ID */
	@Column(name="i_recommend")
	private int i_recommend;
	
	/**会员登录ID */
	@Column(name="i_recommendnumber")
	private String i_recommendnumber;
	
	/**会员登录ID */
	@Column(name="i_contact")
	private int i_contact;
	
	/**会员登录ID */
	@Column(name="i_contactnumber")
	private String i_contactnumber;
	
	/**会员登录ID */
	@Column(name="i_top")
	private int i_top;
	
	/**会员登录ID */
	@Column(name="i_topnumber")
	private String i_topnumber;
	
	/**会员登录ID */
	@Column(name="i_isservice")
	private int i_isservice;
	
	/**会员登录ID */
	@Column(name="i_isservicegrid")
	private int i_isservicegrid;
	
	/**会员登录ID */
	@Column(name="i_serviceid")
	private int i_serviceid;
	
	/**会员登录ID */
	@Column(name="i_servicename")
	private String i_servicename;
	
	/**会员登录ID */
	@Column(name="i_isactivate")
	private int i_isactivate;
	
	/**会员登录ID */
	@Column(name="i_isnull")
	private int i_isnull;
	
	/**会员登录ID */
	@Column(name="i_islock")
	private int i_islock;
	
	/**会员登录ID */
	@Column(name="i_outcount")
	private int i_outcount;
	
	/**会员登录ID */
	@Column(name="i_crmmoney")
	private BigDecimal i_crmmoney;
	
	/**会员登录ID */
	@Column(name="i_crmaccumulative")
	private BigDecimal i_crmaccumulative;
	
	/**会员登录ID */
	@Column(name="i_bonusmoney")
	private BigDecimal i_bonusmoney;
	
	/**会员登录ID */
	@Column(name="i_bonusaccumulative")
	private BigDecimal i_bonusaccumulative;

	/**会员登录ID */
	@Column(name="i_repeatedmoney")
	private BigDecimal i_repeatedmoney;
	
	/**会员登录ID */
	@Column(name="i_repeatedaccumulative")
	private BigDecimal i_repeatedaccumulative;
	
	/**会员登录ID */
	@Column(name="i_shoppingmoney")
	private BigDecimal i_shoppingmoney;
	
	/**会员登录ID */
	@Column(name="i_shoppingaccumulative")
	private BigDecimal i_shoppingaccumulative;
	
	/**会员登录ID */
	@Column(name="i_recommendcount")
	private BigDecimal i_recommendcount;
	
	/**会员登录ID */
	@Column(name="i_servicecount")
	private BigDecimal i_servicecount;
	
	/**会员登录ID */
	@Column(name="i_servicesum")
	private int i_servicesum;
	
	/**会员登录ID */
	@Column(name="i_recommendservice")
	private int i_recommendservice;
	
	/**会员登录ID */
	@Column(name="i_obligate1")
	private String i_obligate1;
	
	/**会员登录ID */
	@Column(name="i_obligate2")
	private String i_obligate2;
	
	
	/**会员登录ID */
	@Column(name="i_obligate3")
	private String i_obligate3;
	
	/**会员登录ID */
	@Column(name="i_obligate4")
	private BigDecimal i_obligate4;
	
	/**会员登录ID */
	@Column(name="i_obligate5")
	private BigDecimal i_obligate5;
	
	/**会员登录ID */
	@Column(name="i_obligate6")
	private BigDecimal i_obligate6;
	
	/**会员登录ID */
	@Column(name="i_obligate7")
	private int i_obligate7;

	/**会员登录ID */
	@Column(name="i_obligate8")
	private int i_obligate8;
	
	/**会员登录ID */
	@Column(name="i_obligate9")
	private BigDecimal i_obligate9;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getI_number() {
		return i_number;
	}

	public void setI_number(String iNumber) {
		i_number = iNumber;
	}

	public String getI_password() {
		return i_password;
	}

	public void setI_password(String iPassword) {
		i_password = iPassword;
	}

	public String getI_protectpassword() {
		return i_protectpassword;
	}

	public void setI_protectpassword(String iProtectpassword) {
		i_protectpassword = iProtectpassword;
	}

	public String getI_qualification() {
		return i_qualification;
	}

	public void setI_qualification(String iQualification) {
		i_qualification = iQualification;
	}

	public String getI_grid() {
		return i_grid;
	}

	public void setI_grid(String iGrid) {
		i_grid = iGrid;
	}

	public String getI_gridname() {
		return i_gridname;
	}

	public void setI_gridname(String iGridname) {
		i_gridname = iGridname;
	}

	public String getI_name() {
		return i_name;
	}

	public void setI_name(String iName) {
		i_name = iName;
	}

	public String getI_gender() {
		return i_gender;
	}

	public void setI_gender(String iGender) {
		i_gender = iGender;
	}

	public String getI_age() {
		return i_age;
	}

	public void setI_age(String iAge) {
		i_age = iAge;
	}

	public String getI_nickname() {
		return i_nickname;
	}

	public void setI_nickname(String iNickname) {
		i_nickname = iNickname;
	}

	public String getI_identity() {
		return i_identity;
	}

	public void setI_identity(String iIdentity) {
		i_identity = iIdentity;
	}

	public String getI_email() {
		return i_email;
	}

	public void setI_email(String iEmail) {
		i_email = iEmail;
	}

	public String getI_phonenumber() {
		return i_phonenumber;
	}

	public void setI_phonenumber(String iPhonenumber) {
		i_phonenumber = iPhonenumber;
	}

	public String getI_bankname() {
		return i_bankname;
	}

	public void setI_bankname(String iBankname) {
		i_bankname = iBankname;
	}

	public String getI_bankprovince() {
		return i_bankprovince;
	}

	public void setI_bankprovince(String iBankprovince) {
		i_bankprovince = iBankprovince;
	}

	public String getI_bankcity() {
		return i_bankcity;
	}

	public void setI_bankcity(String iBankcity) {
		i_bankcity = iBankcity;
	}

	public String getI_bankcounty() {
		return i_bankcounty;
	}

	public void setI_bankcounty(String iBankcounty) {
		i_bankcounty = iBankcounty;
	}

	public String getI_bankaddress() {
		return i_bankaddress;
	}

	public void setI_bankaddress(String iBankaddress) {
		i_bankaddress = iBankaddress;
	}

	public String getI_postnumber() {
		return i_postnumber;
	}

	public void setI_postnumber(String iPostnumber) {
		i_postnumber = iPostnumber;
	}

	public String getI_linkprovince() {
		return i_linkprovince;
	}

	public void setI_linkprovince(String iLinkprovince) {
		i_linkprovince = iLinkprovince;
	}

	public String getI_linkcity() {
		return i_linkcity;
	}

	public void setI_linkcity(String iLinkcity) {
		i_linkcity = iLinkcity;
	}

	public String getI_linkcounty() {
		return i_linkcounty;
	}

	public void setI_linkcounty(String iLinkcounty) {
		i_linkcounty = iLinkcounty;
	}

	public String getI_linkaddress() {
		return i_linkaddress;
	}

	public void setI_linkaddress(String iLinkaddress) {
		i_linkaddress = iLinkaddress;
	}

	public Date getI_registerdate() {
		return i_registerdate;
	}

	public void setI_registerdate(Date iRegisterdate) {
		i_registerdate = iRegisterdate;
	}

	public Date getI_checkeddate() {
		return i_checkeddate;
	}

	public void setI_checkeddate(Date iCheckeddate) {
		i_checkeddate = iCheckeddate;
	}

	public int getI_recommend() {
		return i_recommend;
	}

	public void setI_recommend(int iRecommend) {
		i_recommend = iRecommend;
	}

	public String getI_recommendnumber() {
		return i_recommendnumber;
	}

	public void setI_recommendnumber(String iRecommendnumber) {
		i_recommendnumber = iRecommendnumber;
	}

	public int getI_contact() {
		return i_contact;
	}

	public void setI_contact(int iContact) {
		i_contact = iContact;
	}

	public String getI_contactnumber() {
		return i_contactnumber;
	}

	public void setI_contactnumber(String iContactnumber) {
		i_contactnumber = iContactnumber;
	}

	public int getI_top() {
		return i_top;
	}

	public void setI_top(int iTop) {
		i_top = iTop;
	}

	public String getI_topnumber() {
		return i_topnumber;
	}

	public void setI_topnumber(String iTopnumber) {
		i_topnumber = iTopnumber;
	}

	public int getI_isservice() {
		return i_isservice;
	}

	public void setI_isservice(int iIsservice) {
		i_isservice = iIsservice;
	}

	public int getI_isservicegrid() {
		return i_isservicegrid;
	}

	public void setI_isservicegrid(int iIsservicegrid) {
		i_isservicegrid = iIsservicegrid;
	}

	public int getI_serviceid() {
		return i_serviceid;
	}

	public void setI_serviceid(int iServiceid) {
		i_serviceid = iServiceid;
	}

	public String getI_servicename() {
		return i_servicename;
	}

	public void setI_servicename(String iServicename) {
		i_servicename = iServicename;
	}

	public int getI_isactivate() {
		return i_isactivate;
	}

	public void setI_isactivate(int iIsactivate) {
		i_isactivate = iIsactivate;
	}

	public int getI_isnull() {
		return i_isnull;
	}

	public void setI_isnull(int iIsnull) {
		i_isnull = iIsnull;
	}

	public int getI_islock() {
		return i_islock;
	}

	public void setI_islock(int iIslock) {
		i_islock = iIslock;
	}

	public int getI_outcount() {
		return i_outcount;
	}

	public void setI_outcount(int iOutcount) {
		i_outcount = iOutcount;
	}

	public BigDecimal getI_crmmoney() {
		return i_crmmoney;
	}

	public void setI_crmmoney(BigDecimal iCrmmoney) {
		i_crmmoney = iCrmmoney;
	}

	public BigDecimal getI_crmaccumulative() {
		return i_crmaccumulative;
	}

	public void setI_crmaccumulative(BigDecimal iCrmaccumulative) {
		i_crmaccumulative = iCrmaccumulative;
	}

	public BigDecimal getI_bonusmoney() {
		return i_bonusmoney;
	}

	public void setI_bonusmoney(BigDecimal iBonusmoney) {
		i_bonusmoney = iBonusmoney;
	}

	public BigDecimal getI_bonusaccumulative() {
		return i_bonusaccumulative;
	}

	public void setI_bonusaccumulative(BigDecimal iBonusaccumulative) {
		i_bonusaccumulative = iBonusaccumulative;
	}

	public BigDecimal getI_repeatedmoney() {
		return i_repeatedmoney;
	}

	public void setI_repeatedmoney(BigDecimal iRepeatedmoney) {
		i_repeatedmoney = iRepeatedmoney;
	}

	public BigDecimal getI_shoppingmoney() {
		return i_shoppingmoney;
	}

	public void setI_shoppingmoney(BigDecimal iShoppingmoney) {
		i_shoppingmoney = iShoppingmoney;
	}

	public BigDecimal getI_shoppingaccumulative() {
		return i_shoppingaccumulative;
	}

	public void setI_shoppingaccumulative(BigDecimal iShoppingaccumulative) {
		i_shoppingaccumulative = iShoppingaccumulative;
	}

	public BigDecimal getI_recommendcount() {
		return i_recommendcount;
	}

	public void setI_recommendcount(BigDecimal iRecommendcount) {
		i_recommendcount = iRecommendcount;
	}

	public BigDecimal getI_servicecount() {
		return i_servicecount;
	}

	public void setI_servicecount(BigDecimal iServicecount) {
		i_servicecount = iServicecount;
	}

	public int getI_servicesum() {
		return i_servicesum;
	}

	public void setI_servicesum(int iServicesum) {
		i_servicesum = iServicesum;
	}

	public int getI_recommendservice() {
		return i_recommendservice;
	}

	public void setI_recommendservice(int iRecommendservice) {
		i_recommendservice = iRecommendservice;
	}

	public String getI_obligate1() {
		return i_obligate1;
	}

	public void setI_obligate1(String iObligate1) {
		i_obligate1 = iObligate1;
	}

	public String getI_obligate2() {
		return i_obligate2;
	}

	public void setI_obligate2(String iObligate2) {
		i_obligate2 = iObligate2;
	}

	public String getI_obligate3() {
		return i_obligate3;
	}

	public void setI_obligate3(String iObligate3) {
		i_obligate3 = iObligate3;
	}

	public BigDecimal getI_obligate4() {
		return i_obligate4;
	}

	public void setI_obligate4(BigDecimal iObligate4) {
		i_obligate4 = iObligate4;
	}

	public BigDecimal getI_obligate5() {
		return i_obligate5;
	}

	public void setI_obligate5(BigDecimal iObligate5) {
		i_obligate5 = iObligate5;
	}

	public BigDecimal getI_obligate6() {
		return i_obligate6;
	}

	public void setI_obligate6(BigDecimal iObligate6) {
		i_obligate6 = iObligate6;
	}

	public int getI_obligate7() {
		return i_obligate7;
	}

	public void setI_obligate7(int iObligate7) {
		i_obligate7 = iObligate7;
	}

	public int getI_obligate8() {
		return i_obligate8;
	}

	public void setI_obligate8(int iObligate8) {
		i_obligate8 = iObligate8;
	}

	public BigDecimal getI_obligate9() {
		return i_obligate9;
	}

	public void setI_obligate9(BigDecimal iObligate9) {
		i_obligate9 = iObligate9;
	}

	public String getI_bankcard() {
		return i_bankcard;
	}

	public void setI_bankcard(String iBankcard) {
		i_bankcard = iBankcard;
	}

	public BigDecimal getI_repeatedaccumulative() {
		return i_repeatedaccumulative;
	}

	public void setI_repeatedaccumulative(BigDecimal iRepeatedaccumulative) {
		i_repeatedaccumulative = iRepeatedaccumulative;
	}
	
	
	

}
