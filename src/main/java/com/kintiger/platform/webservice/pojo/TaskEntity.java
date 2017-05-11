package com.kintiger.platform.webservice.pojo;

import java.io.Serializable;

/**
 *
 * @author hfwu
 *
 */
public class TaskEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String rev;
	private String execution_id;
	private String proc_inst_id;
	private String proc_def_id;
	private String name;
	private String parent_task_id;
	private String description;
	private String task_def_key;
	private String owner;
	private String assignee;
	private String priority;
	private String create_time;
	private String due_date_;
	private String suspension_state;
	private String businessKey;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public String getExecution_id() {
		return execution_id;
	}
	public void setExecution_id(String execution_id) {
		this.execution_id = execution_id;
	}
	public String getProc_inst_id() {
		return proc_inst_id;
	}
	public void setProc_inst_id(String proc_inst_id) {
		this.proc_inst_id = proc_inst_id;
	}
	public String getProc_def_id() {
		return proc_def_id;
	}
	public void setProc_def_id(String proc_def_id) {
		this.proc_def_id = proc_def_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent_task_id() {
		return parent_task_id;
	}
	public void setParent_task_id(String parent_task_id) {
		this.parent_task_id = parent_task_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTask_def_key() {
		return task_def_key;
	}
	public void setTask_def_key(String task_def_key) {
		this.task_def_key = task_def_key;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getDue_date_() {
		return due_date_;
	}
	public void setDue_date_(String due_date_) {
		this.due_date_ = due_date_;
	}
	public String getSuspension_state() {
		return suspension_state;
	}
	public void setSuspension_state(String suspension_state) {
		this.suspension_state = suspension_state;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
}
