package com.raynigon.raylight.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cue")
public class CueMetaData {

    @Id
    @Column(name="id")
    public int id;

    @Column(name="name")
    public String name;

    @ManyToOne
    @JoinColumn(name="cuelist")
    public CueListMetaData cuelist;
}
