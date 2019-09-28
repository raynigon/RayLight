package com.raynigon.raylight.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raynigon.raylight.model.helper.MetaCueActionConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="cue")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "cuelist")
public class CueMetaData {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    @Column(name="name")
    public String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="cuelist")
    public CueListMetaData cuelist;

    @Column(name="actions")
    @Convert(converter = MetaCueActionConverter.class)
    public List<CueAction> actions;
}
