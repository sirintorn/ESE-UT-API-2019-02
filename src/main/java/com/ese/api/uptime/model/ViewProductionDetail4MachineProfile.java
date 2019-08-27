package com.ese.api.uptime.model;

import javax.persistence.*;

@Entity
@Table(name = "view_production_detail_4_machine_profile")
public class ViewProductionDetail4MachineProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "production_id")
    private Long productionId;

    @Column(name = "machine_register_id")
    private Long machineRegisterId;

    @Column(name = "machine")
    private String machine;

    @Column(name = "production_event")
    private String productionEvent;

    @Column(name = "starttime")
    private java.util.Date starttime;

    @Column(name = "finishtime")
    private java.util.Date finishtime;

    @Column(name = "totaltime")
    private Long totaltime;

    @Column(name = "loss_id")
    private Long lossId;

    @Column(name = "factory_id")
    private Long factoryId;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getProductionId() {
        return this.productionId;
    }

    public void setProductionId(Long productionId) {
        this.productionId = productionId;
    }

    public Long getMachineRegisterId() {
        return this.machineRegisterId;
    }

    public void setMachineRegisterId(Long machineRegisterId) {
        this.machineRegisterId = machineRegisterId;
    }

    public String getMachine() {
        return this.machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getProductionEvent() {
        return this.productionEvent;
    }

    public void setProductionEvent(String productionEvent) {
        this.productionEvent = productionEvent;
    }

    public java.util.Date getStarttime() {
        return this.starttime;
    }

    public void setStarttime(java.util.Date starttime) {
        this.starttime = starttime;
    }

    public java.util.Date getFinishtime() {
        return this.finishtime;
    }

    public void setFinishtime(java.util.Date finishtime) {
        this.finishtime = finishtime;
    }

    public Long getTotaltime() {
        return this.totaltime;
    }

    public void setTotaltime(Long totaltime) {
        this.totaltime = totaltime;
    }

    public Long getLossId() {
        return this.lossId;
    }

    public void setLossId(Long lossId) {
        this.lossId = lossId;
    }

    public Long getFactoryId() {
        return this.factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }
}
