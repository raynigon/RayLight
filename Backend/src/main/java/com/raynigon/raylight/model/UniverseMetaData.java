package com.raynigon.raylight.model;

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
@Table(name="universemetadata")
public class UniverseMetaData {

    @Id
    @Column(name="id")
    public int id;

    @Column(name="output")
    public String output;
}
