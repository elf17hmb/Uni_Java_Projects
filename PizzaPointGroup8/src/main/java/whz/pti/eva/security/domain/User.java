package whz.pti.eva.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import whz.pti.eva.BaseEntity;


@Entity
@Table(name = "secuser")
public class User extends BaseEntity<Long>{

	@Column(name = "loginName", nullable = false, unique = true)
    private String loginName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    private Role role;

	public Long getId() {
        return super.getId();
    }
	
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                "loginName=" + loginName +
                ", email='" + email.replaceFirst("@.*", "@***") +
                ", password='" + passwordHash.substring(0, 10) +
                ", role=" + role +
                '}';
    }
}