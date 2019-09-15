package com.raynigon.raylight.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cuelist")
public class CueListMetaData {

    @Id
    @Column(name="id")
    public int id;

    @Column(name="name")
    public String name;

    @Column(name="mode")
    public CueListMode mode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="cuelist")
    public List<CueMetaData> cues;
}
