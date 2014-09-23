package com.chu.dao.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * es-user-mapping
 * 
 * @author chuhanlong
 *
 */

@Document(indexName = "userIndex")
public class AuthUserEntity {
	   /** null */
	@Id
    private Integer id;

    /** null */
	@Field
    private String cname;

    /** null */
	@Field
    private String email;

    /** null */
    @Field
    private String mobile;

    /** null */
    private String pos;

    /** null */
    private String department;

    /** null */
    private Date ctime;

    /** null */
    private Date mtime;

    /** null */
    private Boolean status;

    /** null */
    private String groupIds;

    /** null */
    private String roleIds;

    /** null */
    private Integer cityId;

    /** null */
    private String passwd;

    /** 拥有供应商数量限制 */
    private Integer supplierLimit;

    /** 直属上司的员工ID */
    private Integer leaderId;

    /** 座机号 */
    private String phone;

    /** 部门id,来自ERP中cux_n_employee_v.deptid */
    private String deptId;

    /** 入职时间 */
    private Date hireDt;

    /** 离职时间 */
    private Date terDt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getSupplierLimit() {
        return supplierLimit;
    }

    public void setSupplierLimit(Integer supplierLimit) {
        this.supplierLimit = supplierLimit;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Date getHireDt() {
        return hireDt;
    }

    public void setHireDt(Date hireDt) {
        this.hireDt = hireDt;
    }

    public Date getTerDt() {
        return terDt;
    }

    public void setTerDt(Date terDt) {
        this.terDt = terDt;
    }
}
