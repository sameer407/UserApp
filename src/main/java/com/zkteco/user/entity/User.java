package com.zkteco.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
//import javax.validation.constraints.*;
//import com.zkteco.user.dto.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "\"user\"")
public class User {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", length = 50, nullable = false)
	private String userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "Address")
	private String address;

	@Column(name = "email_ID")
	private String email_Id;

	@Column(name = "UserCode")
	private String userCode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedate;

	private String gender;

	public boolean isPresent() {

		return true;
	}

}
