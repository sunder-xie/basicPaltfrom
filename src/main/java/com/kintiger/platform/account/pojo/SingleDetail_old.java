package com.kintiger.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * ±¨ÏúÃ÷Ï¸
 * @author 
 *
 */
public class SingleDetail_old extends SearchInfo {

	private static final long serialVersionUID = 855967196343376618L;
	
	private Long detail_id;
	private Long plan_id;
	private String description;
	private BigDecimal detail_money;
	private Long BUDGET_ID;
	private Date depart_date;
	private String depart_place;
	private Date arrive_date;
	private String arrive_place;
	private String vehicle;
	private BigDecimal vehicle_expense;
	private Long lodge_days;
	private BigDecimal lodge_expense;
	private BigDecimal board_expense;
	private BigDecimal taxi_expense;
	private BigDecimal extras_expense;
	private String remark;
	private String detail_flag;
	private Date create_date;
	private Long creator_id;
	private Date modify_date;
	private Long operator_id;
	private String zuonr;
	private String costcenter_id;
	private String costcenter_name;
	private String sap_costcenter_id;
	private String invoicetypes;
	private String taxrate;
	private BigDecimal expensemoney; 
	private BigDecimal taxmoney;
	
	public Long getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}
	public Long getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(Long plan_id) {
		this.plan_id = plan_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getDetail_money() {
		return detail_money;
	}
	public void setDetail_money(BigDecimal detail_money) {
		this.detail_money = detail_money;
	}
	public Long getBUDGET_ID() {
		return BUDGET_ID;
	}
	public void setBUDGET_ID(Long bUDGET_ID) {
		BUDGET_ID = bUDGET_ID;
	}
	public Date getDepart_date() {
		return depart_date;
	}
	public void setDepart_date(Date depart_date) {
		this.depart_date = depart_date;
	}
	public String getDepart_place() {
		return depart_place;
	}
	public void setDepart_place(String depart_place) {
		this.depart_place = depart_place;
	}
	public Date getArrive_date() {
		return arrive_date;
	}
	public void setArrive_date(Date arrive_date) {
		this.arrive_date = arrive_date;
	}
	public String getArrive_place() {
		return arrive_place;
	}
	public void setArrive_place(String arrive_place) {
		this.arrive_place = arrive_place;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public BigDecimal getVehicle_expense() {
		return vehicle_expense;
	}
	public void setVehicle_expense(BigDecimal vehicle_expense) {
		this.vehicle_expense = vehicle_expense;
	}
	public Long getLodge_days() {
		return lodge_days;
	}
	public void setLodge_days(Long lodge_days) {
		this.lodge_days = lodge_days;
	}
	public BigDecimal getLodge_expense() {
		return lodge_expense;
	}
	public void setLodge_expense(BigDecimal lodge_expense) {
		this.lodge_expense = lodge_expense;
	}
	public BigDecimal getBoard_expense() {
		return board_expense;
	}
	public void setBoard_expense(BigDecimal board_expense) {
		this.board_expense = board_expense;
	}
	public BigDecimal getTaxi_expense() {
		return taxi_expense;
	}
	public void setTaxi_expense(BigDecimal taxi_expense) {
		this.taxi_expense = taxi_expense;
	}
	public BigDecimal getExtras_expense() {
		return extras_expense;
	}
	public void setExtras_expense(BigDecimal extras_expense) {
		this.extras_expense = extras_expense;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDetail_flag() {
		return detail_flag;
	}
	public void setDetail_flag(String detail_flag) {
		this.detail_flag = detail_flag;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Long getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(Long creator_id) {
		this.creator_id = creator_id;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public Long getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(Long operator_id) {
		this.operator_id = operator_id;
	}
	public String getZuonr() {
		return zuonr;
	}
	public void setZuonr(String zuonr) {
		this.zuonr = zuonr;
	}
	public String getCostcenter_id() {
		return costcenter_id;
	}
	public void setCostcenter_id(String costcenter_id) {
		this.costcenter_id = costcenter_id;
	}
	public String getCostcenter_name() {
		return costcenter_name;
	}
	public void setCostcenter_name(String costcenter_name) {
		this.costcenter_name = costcenter_name;
	}
	public String getSap_costcenter_id() {
		return sap_costcenter_id;
	}
	public void setSap_costcenter_id(String sap_costcenter_id) {
		this.sap_costcenter_id = sap_costcenter_id;
	}
	public String getInvoicetypes() {
		return invoicetypes;
	}
	public void setInvoicetypes(String invoicetypes) {
		this.invoicetypes = invoicetypes;
	}
	public String getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}
	public BigDecimal getExpensemoney() {
		return expensemoney;
	}
	public void setExpensemoney(BigDecimal expensemoney) {
		this.expensemoney = expensemoney;
	}
	public BigDecimal getTaxmoney() {
		return taxmoney;
	}
	public void setTaxmoney(BigDecimal taxmoney) {
		this.taxmoney = taxmoney;
	}
	
	
}
