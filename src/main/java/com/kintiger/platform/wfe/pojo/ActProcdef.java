package com.kintiger.platform.wfe.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 对应Activiti中流程定义表act_re_procdef
 *
 */
public class ActProcdef extends SearchInfo{

	private static final long serialVersionUID = 1727376056602610379L;
	
	/** 流程ID */
	private String id;
	
	private Integer rev;
	
	/** 流程命名空间  */
	private String category;
	
	/** 流程名称  */
	private String name;
	
	/** 流程编号  */
	private String key;
	
	/** 流程版本号  */
	private Integer version;
	
	/** 部署编号  */
	private String deployment_id;
	
	/** 资源文件名称  */
	private String resource_name;
	
	/** 图片资源文件名称  */
	private String dgrm_resource_name;
	
	/** 描述  */
	private String description;
	
	private Integer has_start_form_key;
	
	private Integer suspension_state;
	
	private String[] models;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDeployment_id() {
		return deployment_id;
	}

	public void setDeployment_id(String deployment_id) {
		this.deployment_id = deployment_id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getDgrm_resource_name() {
		return dgrm_resource_name;
	}

	public void setDgrm_resource_name(String dgrm_resource_name) {
		this.dgrm_resource_name = dgrm_resource_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHas_start_form_key() {
		return has_start_form_key;
	}

	public void setHas_start_form_key(Integer has_start_form_key) {
		this.has_start_form_key = has_start_form_key;
	}

	public Integer getSuspension_state() {
		return suspension_state;
	}

	public void setSuspension_state(Integer suspension_state) {
		this.suspension_state = suspension_state;
	}

	public String[] getModels() {
		return models;
	}

	public void setModels(String[] models) {
		this.models = models;
	}
}
