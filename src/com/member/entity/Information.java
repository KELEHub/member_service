package com.member.entity;

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

	/**条件，意义不明 */
	@Column(name="qualification")
	private String qualification;
	
	/**网格，意义不明 */
	@Column(name="grid")
	private String grid;
	
	/**网格名字，意义不明 */
	@Column(name="gridname")
	private String gridname;
	
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
	
	/**联系人表ID，目前未使用 */
	@Column(name="contactId")
	private String contactId;
	
	/**联系人的登录ID，目前未使用 */
	@Column(name="contactNumber")
	private String contactNumber;
	
	/**上级表ID，目前未使用 */
	@Column(name="topId")
	private Integer topId;
	
	/**上级的登录ID，目前未使用 */
	@Column(name="topNumber")
	private String topNumber;
	
	/**是否是报单中心，1表示是报单中心，0表示普通会员 */
	@Column(name="isService")
	private Integer isService;
	
	/**是否是ServiceGrid，目前使用未知 */
	@Column(name="isServiceGrid")
	private String isServiceGrid;

	/**上级报单中心的表ID*/
	@Column(name="serviceId")
	private Integer serviceId;
	
	/**上级报单中心的登录ID */
	@Column(name="serviceNumber")
	private String serviceNumber;
	
	/**是否激活，1表示已激活，0表示未激活 */
	@Column(name="isActivate")
	private Date isActivate;
	
	/**意义不明 */
	@Column(name="isNull")
	private Integer isNull;
	
	/**是否锁定，1表示锁定，0表示未锁定 */
	@Column(name="isLock")
	private Integer isLock;
	
	/**意义不明 */
	@Column(name="outCount")
	private String outCount;
	
	/**葛粮币 */
	@Column(name="crmMoney")
	private Long crmMoney;
	
	/**葛粮币累计 */
	@Column(name="crmAccumulative")
	private Long crmAccumulative;
	
	/**分红 */
	@Column(name="bonusMoney")
	private Long bonusMoney;
	
	/**分红累计 */
	@Column(name="bonusAccumulative")
	private Long bonusAccumulative;

	/**服务积分 */
	@Column(name="repeatedMoney")
	private Long repeatedMoney;
	
	/**服务积分累计*/
	@Column(name="repeatedAccumulative")
	private Long repeatedAccumulative;
	
	/**意义不明 */
	@Column(name="shoppingMoney")
	private Long shoppingMoney;
	
	/**意义不明 */
	@Column(name="shoppingAccumulative")
	private Long shoppingAccumulative;
	
	/**推荐人数 */
	@Column(name="recommendCount")
	private Integer recommendCount;
	
	/**本月新报单人数统计 */
	@Column(name="serviceCount")
	private Integer serviceCount;
	
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getGridname() {
		return gridname;
	}

	public void setGridname(String gridname) {
		this.gridname = gridname;
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

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	public String getTopNumber() {
		return topNumber;
	}

	public void setTopNumber(String topNumber) {
		this.topNumber = topNumber;
	}

	public Integer getIsService() {
		return isService;
	}

	public void setIsService(Integer isService) {
		this.isService = isService;
	}

	public String getIsServiceGrid() {
		return isServiceGrid;
	}

	public void setIsServiceGrid(String isServiceGrid) {
		this.isServiceGrid = isServiceGrid;
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

	public Date getIsActivate() {
		return isActivate;
	}

	public void setIsActivate(Date isActivate) {
		this.isActivate = isActivate;
	}

	public Integer getIsNull() {
		return isNull;
	}

	public void setIsNull(Integer isNull) {
		this.isNull = isNull;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public String getOutCount() {
		return outCount;
	}

	public void setOutCount(String outCount) {
		this.outCount = outCount;
	}

	public Long getCrmMoney() {
		return crmMoney;
	}

	public void setCrmMoney(Long crmMoney) {
		this.crmMoney = crmMoney;
	}

	public Long getCrmAccumulative() {
		return crmAccumulative;
	}

	public void setCrmAccumulative(Long crmAccumulative) {
		this.crmAccumulative = crmAccumulative;
	}

	public Long getBonusMoney() {
		return bonusMoney;
	}

	public void setBonusMoney(Long bonusMoney) {
		this.bonusMoney = bonusMoney;
	}

	public Long getBonusAccumulative() {
		return bonusAccumulative;
	}

	public void setBonusAccumulative(Long bonusAccumulative) {
		this.bonusAccumulative = bonusAccumulative;
	}

	public Long getRepeatedMoney() {
		return repeatedMoney;
	}

	public void setRepeatedMoney(Long repeatedMoney) {
		this.repeatedMoney = repeatedMoney;
	}

	public Long getRepeatedAccumulative() {
		return repeatedAccumulative;
	}

	public void setRepeatedAccumulative(Long repeatedAccumulative) {
		this.repeatedAccumulative = repeatedAccumulative;
	}

	public Long getShoppingMoney() {
		return shoppingMoney;
	}

	public void setShoppingMoney(Long shoppingMoney) {
		this.shoppingMoney = shoppingMoney;
	}

	public Long getShoppingAccumulative() {
		return shoppingAccumulative;
	}

	public void setShoppingAccumulative(Long shoppingAccumulative) {
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

	/**历史报单人数统计 */
	@Column(name="serviceSum")
	private Integer serviceSum;
	
	/**推荐人表id，重复定义，可去除*/
//	@Column(name="recommendService")
//	private Long recommendService;
	
}
