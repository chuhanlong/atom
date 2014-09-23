package com.chu.dao.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * null
 *
 * @author chuhanlong
 * @date 2014-7-3
 *
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = "user")
public class User implements Serializable {
    /** null */
//	@XmlAttribute
    private Integer id;

    /** null */
//	@XmlAttribute
    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}