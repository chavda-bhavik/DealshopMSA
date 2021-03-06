/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.businessmsa.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bhavi
 */
@Entity
@Table(name = "businesstb")
@NamedQueries({
    @NamedQuery(name = "Businesstb.findAll", query = "SELECT b FROM Businesstb b"),
    @NamedQuery(name = "Businesstb.findByBusinessID", query = "SELECT b FROM Businesstb b WHERE b.businessID = :businessID"),
    @NamedQuery(name = "Businesstb.findByBusinessName", query = "SELECT b FROM Businesstb b WHERE b.businessName = :businessName"),
    @NamedQuery(name = "Businesstb.findByEmailID", query = "SELECT b FROM Businesstb b WHERE b.emailID = :emailID"),
    @NamedQuery(name = "Businesstb.findByAddress", query = "SELECT b FROM Businesstb b WHERE b.address = :address"),
    @NamedQuery(name = "Businesstb.findByCustomerCarePhoneNo", query = "SELECT b FROM Businesstb b WHERE b.customerCarePhoneNo = :customerCarePhoneNo"),
    @NamedQuery(name = "Businesstb.findByReservationPhoneNo", query = "SELECT b FROM Businesstb b WHERE b.reservationPhoneNo = :reservationPhoneNo"),
    @NamedQuery(name = "Businesstb.findByDaysOfOperation", query = "SELECT b FROM Businesstb b WHERE b.daysOfOperation = :daysOfOperation"),
    @NamedQuery(name = "Businesstb.findByHoursOfOperation", query = "SELECT b FROM Businesstb b WHERE b.hoursOfOperation = :hoursOfOperation"),
    @NamedQuery(name = "Businesstb.findByLocation", query = "SELECT b FROM Businesstb b WHERE b.location = :location"),
    @NamedQuery(name = "Businesstb.findByIsVerified", query = "SELECT b FROM Businesstb b WHERE b.isVerified = :isVerified"),
    @NamedQuery(name = "Businesstb.findByBankAccountNo", query = "SELECT b FROM Businesstb b WHERE b.bankAccountNo = :bankAccountNo"),
    @NamedQuery(name = "Businesstb.findByBankName", query = "SELECT b FROM Businesstb b WHERE b.bankName = :bankName"),
    @NamedQuery(name = "Businesstb.findByIFSCCode", query = "SELECT b FROM Businesstb b WHERE b.iFSCCode = :iFSCCode")})
public class Businesstb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BusinessID")
    private Integer businessID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "BusinessName")
    private String businessName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EmailID")
    private String emailID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CustomerCarePhoneNo")
    private String customerCarePhoneNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ReservationPhoneNo")
    private String reservationPhoneNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DaysOfOperation")
    private String daysOfOperation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "HoursOfOperation")
    private String hoursOfOperation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "NeedToKnow")
    private String needToKnow;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "AwardsRecognition")
    private String awardsRecognition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isVerified")
    private int isVerified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BankAccountNo")
    private long bankAccountNo;
    @Size(max = 200)
    @Column(name = "BankName")
    private String bankName;
    @Size(max = 100)
    @Column(name = "IFSCCode")
    private String iFSCCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussinessID")
    private Collection<Businesslinkstb> businesslinkstbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessID")
    private Collection<Businessphotostb> businessphotostbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussinessID")
    private Collection<Businessinfotb> businessinfotbCollection;
    @JoinColumn(name = "CityID", referencedColumnName = "CityID")
    @ManyToOne(optional = false)
    private Citytb cityID;
    @JoinColumn(name = "StateID", referencedColumnName = "StateID")
    @ManyToOne(optional = false)
    private Statetb stateID;
    @JoinColumn(name = "BusinessCategoryID", referencedColumnName = "CategoryID")
    @ManyToOne(optional = false)
    private Businesscategorytb businessCategoryID;
    @JoinColumn(name = "BusinessTypeID", referencedColumnName = "BusinessTypeID")
    @ManyToOne
    private Businesstypetb businessTypeID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Businesstb() {
    }

    public Businesstb(Integer businessID) {
        this.businessID = businessID;
    }

    public Businesstb(Integer businessID, String businessName, String emailID, String address, String customerCarePhoneNo, String reservationPhoneNo, String daysOfOperation, String hoursOfOperation, String location, String needToKnow, String awardsRecognition, int isVerified, long bankAccountNo) {
        this.businessID = businessID;
        this.businessName = businessName;
        this.emailID = emailID;
        this.address = address;
        this.customerCarePhoneNo = customerCarePhoneNo;
        this.reservationPhoneNo = reservationPhoneNo;
        this.daysOfOperation = daysOfOperation;
        this.hoursOfOperation = hoursOfOperation;
        this.location = location;
        this.needToKnow = needToKnow;
        this.awardsRecognition = awardsRecognition;
        this.isVerified = isVerified;
        this.bankAccountNo = bankAccountNo;
    }

    public Integer getBusinessID() {
        return businessID;
    }

    public void setBusinessID(Integer businessID) {
        this.businessID = businessID;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerCarePhoneNo() {
        return customerCarePhoneNo;
    }

    public void setCustomerCarePhoneNo(String customerCarePhoneNo) {
        this.customerCarePhoneNo = customerCarePhoneNo;
    }

    public String getReservationPhoneNo() {
        return reservationPhoneNo;
    }

    public void setReservationPhoneNo(String reservationPhoneNo) {
        this.reservationPhoneNo = reservationPhoneNo;
    }

    public String getDaysOfOperation() {
        return daysOfOperation;
    }

    public void setDaysOfOperation(String daysOfOperation) {
        this.daysOfOperation = daysOfOperation;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNeedToKnow() {
        return needToKnow;
    }

    public void setNeedToKnow(String needToKnow) {
        this.needToKnow = needToKnow;
    }

    public String getAwardsRecognition() {
        return awardsRecognition;
    }

    public void setAwardsRecognition(String awardsRecognition) {
        this.awardsRecognition = awardsRecognition;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public long getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(long bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIFSCCode() {
        return iFSCCode;
    }

    public void setIFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    @JsonbTransient
    public Collection<Businesslinkstb> getBusinesslinkstbCollection() {
        return businesslinkstbCollection;
    }

    public void setBusinesslinkstbCollection(Collection<Businesslinkstb> businesslinkstbCollection) {
        this.businesslinkstbCollection = businesslinkstbCollection;
    }

    @JsonbTransient
    public Collection<Businessphotostb> getBusinessphotostbCollection() {
        return businessphotostbCollection;
    }

    public void setBusinessphotostbCollection(Collection<Businessphotostb> businessphotostbCollection) {
        this.businessphotostbCollection = businessphotostbCollection;
    }

    @JsonbTransient
    public Collection<Businessinfotb> getBusinessinfotbCollection() {
        return businessinfotbCollection;
    }

    public void setBusinessinfotbCollection(Collection<Businessinfotb> businessinfotbCollection) {
        this.businessinfotbCollection = businessinfotbCollection;
    }

    public Citytb getCityID() {
        return cityID;
    }

    public void setCityID(Citytb cityID) {
        this.cityID = cityID;
    }

    public Statetb getStateID() {
        return stateID;
    }

    public void setStateID(Statetb stateID) {
        this.stateID = stateID;
    }

    public Businesscategorytb getBusinessCategoryID() {
        return businessCategoryID;
    }

    public void setBusinessCategoryID(Businesscategorytb businessCategoryID) {
        this.businessCategoryID = businessCategoryID;
    }

    public Businesstypetb getBusinessTypeID() {
        return businessTypeID;
    }

    public void setBusinessTypeID(Businesstypetb businessTypeID) {
        this.businessTypeID = businessTypeID;
    }

    public Usertb getUserID() {
        return userID;
    }

    public void setUserID(Usertb userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (businessID != null ? businessID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businesstb)) {
            return false;
        }
        Businesstb other = (Businesstb) object;
        if ((this.businessID == null && other.businessID != null) || (this.businessID != null && !this.businessID.equals(other.businessID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.businessmsa.entity.Businesstb[ businessID=" + businessID + " ]";
    }
    
}
