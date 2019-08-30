package com.raynigon.raylight.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="controlpanel")
public class ControlPanel {

    @Id
    @Column(name="id")
    public String id;

    @Column(name="name")
    public String name;

    @Column(name="changed")
    public ZonedDateTime changed;

    @Column(name="data")
    public String data;
}
