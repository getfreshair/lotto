package com.sadang.lotto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author yang/2020.01.29
 *
 */
@Entity
@Table(name = "member")
public class Member {
	
	@Id
	@Column(name = "member_id", columnDefinition = "serial")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "member_email", columnDefinition="VARCHAR(55)")
	private String email;
	
	public Integer getId() {
		return id;
	}
	
    public String getEmail() {
        return email;
    }
    
}
