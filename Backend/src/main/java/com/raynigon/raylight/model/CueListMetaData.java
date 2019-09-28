package com.raynigon.raylight.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cuelist")
public class CueListMetaData {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    @Column(name="name")
    public String name;

    @Column(name="mode")
    public CueListMode mode;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="cuelist")
    public List<CueMetaData> cues;
}
