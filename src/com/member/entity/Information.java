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
@Table(name="Information")
@org.hibernate.annotations.Table(comment="会员信息表",appliesTo="Information")
public class Information {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员登录ID */
	@Column(name="number")
	private String number;
	
	/**会员登录密码*/
	@Column(name="password")
	private String password;
	
	/**会员二级密码*/
	@Column(name="protectPassword")
	private String protectPassword;

	/**会员真实姓名 */
	@Column(name="name")
	private String name;
	
	/**性别 */
	@Column(name="gender")
	private String gender;
	
	/**年龄 */
	@Column(name="age")
	private String age;
	
	/**qq号码 */
	@Column(name="qq")
	private String qq;
	
	/**昵称 */
	@Column(name="nickname")
	private String nickname;
	
	/**身份证号码 */
	@Column(name="identity")
	private String identity;
	
	/**邮箱 */
	@Column(name="email")
	private String email;

	/**电话号码 */
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	/**银行名称 */
	@Column(name="bankName")
	private String bankName;
	
	/**银行省份 */
	@Column(name="bankProvince")
	private String bankProvince;
	
	/**银行市级*/
	@Column(name="bankCity")
	private String bankCity;
	
	/**银行区县 */
	@Column(name="bankCounty")
	private String bankCounty;
	
	/**银行支行地址 */
	@Column(name="bankAddress")
	private String bankAddress;
	
	/**银行卡号 */
	@Column(name="bankCard")
	private String bankCard;
	
	/**邮编*/
	@Column(name="postNumber")
	private String postNumber;
	
	/**联系人省份 */
	@Column(name="linkProvince")
	private String linkProvince;
	
	/**联系人市级 */
	@Column(name="linkCity")
	private String linkCity;

	/**联系人区县 */
	@Column(name="linkCounty")
	private String linkCounty;
	
	/**联系人地址 */
	@Column(name="linkAddress")
	private String linkAddress;
	
	/**注册时间 */
	@Column(name="registerDate")
	private Date registerDate;
	
	/**确认时间 */
	@Column(name="checkedDate")
	private Date checkedDate;
	
	/**推荐人在本表中的表ID */
	@Column(name="recommendId")
	private Integer recommendId;
	
	/**推荐人的登录ID */
	@Column(name="recommendNumber")
	private String recommendNumber;
	
	/**激活人在本表中的表ID */
	@Column(name="activateId")
	private Integer activateId;
	
	/**激活人的登录ID */
	@Column(name="activateNumber")
	private String activateNumber;
	
	/**是否是报单中心，1表示是报单中心，0表示普通会员 */
	@Column(name="isService",columnDefinition="INT default 0")
	private Integer isService;
	
	/**报单中心的表ID*/
	@Column(name="serviceId")
	private Integer serviceId;
	
	/**报单中心的登录ID */
	@Column(name="serviceNumber")
	private String serviceNumber;
	
	/**上级报单中心的表ID*/
	@Column(name="leaderServiceId")
	private Integer leaderServiceId;
	
	/**上级报单中心的登录ID */
	@Column(name="leaderServiceNumber")
	private String leaderServiceNumber;
	
	/**是否激活，1表示已激活，0表示未激活 */
	@Column(name="isActivate",columnDefinition="INT default 0")
	private Integer isActivate;
	
	/**激活时间 */
	@Column(name="activeDate")
	private Date activeDate;
	
	/**是否锁定(禁用)，1表示锁定，0表示未锁定 */
	@Column(name="isLock")
	private Integer isLock;
	
	/**葛粮币 */
	@Column(name="crmMoney",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal crmMoney;
	
	/**葛粮币累计 */
	@Column(name="crmAccumulative",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal crmAccumulative;
	
	/**分红 */
	@Column(name="bonusMoney",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal bonusMoney;
	
	/**分红累计 */
	@Column(name="bonusAccumulative",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal bonusAccumulative;

	/**服务积分 */
	@Column(name="repeatedMoney",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal repeatedMoney;
	
	/**服务积分累计*/
	@Column(name="repeatedAccumulative",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal repeatedAccumulative;
	
	/**积分 */
	@Column(name="shoppingMoney",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal shoppingMoney;
	
	/**积分累计 */
	@Column(name="shoppingAccumulative",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal shoppingAccumulative;
	
	/**推荐人数 */
	@Column(name="recommendCount")
	private Integer recommendCount;
	
	/**本月新报单人数统计 */
	@Column(name="serviceCount")
	private Integer serviceCount;
	
	/**历史报单人数统计 */
	@Column(name="serviceSum")
	private Integer serviceSum;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProtectPassword() {
		return protectPassword;
	}

	public void setProtectPassword(String protectPassword) {
		this.protectPassword = protectPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getBankCounty() {
		return bankCounty;
	}

	public void setBankCounty(String bankCounty) {
		this.bankCounty = bankCounty;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}

	public String getLinkProvince() {
		return linkProvince;
	}

	public void setLinkProvince(String linkProvince) {
		this.linkProvince = linkProvince;
	}

	public String getLinkCity() {
		return linkCity;
	}

	public void setLinkCity(String linkCity) {
		this.linkCity = linkCity;
	}

	public String getLinkCounty() {
		return linkCounty;
	}

	public void setLinkCounty(String linkCounty) {
		this.linkCounty = linkCounty;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getCheckedDate() {
		return checkedDate;
	}

	public void setCheckedDate(Date checkedDate) {
		this.checkedDate = checkedDate;
	}

	public Integer getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}

	public String getRecommendNumber() {
		return recommendNumber;
	}

	public void setRecommendNumber(String recommendNumber) {
		this.recommendNumber = recommendNumber;
	}

	public Integer getIsService() {
		return isService;
	}

	public void setIsService(Integer isService) {
		this.isService = isService;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public Integer getIsActivate() {
		return isActivate;
	}

	public void setIsActivate(Integer isActivate) {
		this.isActivate = isActivate;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public BigDecimal getCrmMoney() {
		return crmMoney;
	}

	public void setCrmMoney(BigDecimal crmMoney) {
		this.crmMoney = crmMoney;
	}

	public BigDecimal getCrmAccumulative() {
		return crmAccumulative;
	}

	public void setCrmAccumulative(BigDecimal crmAccumulative) {
		this.crmAccumulative = crmAccumulative;
	}

	public BigDecimal getBonusMoney() {
		return bonusMoney;
	}

	public void setBonusMoney(BigDecimal bonusMoney) {
		this.bonusMoney = bonusMoney;
	}

	public BigDecimal getBonusAccumulative() {
		return bonusAccumulative;
	}

	public void setBonusAccumulative(BigDecimal bonusAccumulative) {
		this.bonusAccumulative = bonusAccumulative;
	}

	public BigDecimal getRepeatedMoney() {
		return repeatedMoney;
	}

	public void setRepeatedMoney(BigDecimal repeatedMoney) {
		this.repeatedMoney = repeatedMoney;
	}

	public BigDecimal getRepeatedAccumulative() {
		return repeatedAccumulative;
	}

	public void setRepeatedAccumulative(BigDecimal repeatedAccumulative) {
		this.repeatedAccumulative = repeatedAccumulative;
	}

	public BigDecimal getShoppingMoney() {
		return shoppingMoney;
	}

	public void setShoppingMoney(BigDecimal shoppingMoney) {
		this.shoppingMoney = shoppingMoney;
	}

	public BigDecimal getShoppingAccumulative() {
		return shoppingAccumulative;
	}

	public void setShoppingAccumulative(BigDecimal shoppingAccumulative) {
		this.shoppingAccumulative = shoppingAccumulative;
	}

	public Integer getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(Integer recommendCount) {
		this.recommendCount = recommendCount;
	}

	public Integer getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}

	public Integer getServiceSum() {
		return serviceSum;
	}

	public void setServiceSum(Integer serviceSum) {
		this.serviceSum = serviceSum;
	}

	public Integer getLeaderServiceId() {
		return leaderServiceId;
	}

	public void setLeaderServiceId(Integer leaderServiceId) {
		this.leaderServiceId = leaderServiceId;
	}

	public String getLeaderServiceNumber() {
		return leaderServiceNumber;
	}

	public void setLeaderServiceNumber(String leaderServiceNumber) {
		this.leaderServiceNumber = leaderServiceNumber;
	}

	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	public Integer getActivateId() {
		return activateId;
	}

	public void setActivateId(Integer activateId) {
		this.activateId = activateId;
	}

	public String getActivateNumber() {
		return activateNumber;
	}

	public void setActivateNumber(String activateNumber) {
		this.activateNumber = activateNumber;
	}
	
}
