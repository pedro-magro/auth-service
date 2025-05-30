package br.com.nailDesigner.auth_service.Models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private UUID id;

	    @Column(unique = true, nullable = false)
	    private String username;

	    @Column(nullable = false)
	    private String password;
	    
	    @Column(unique = true, nullable = false)
	    private String email;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Role role;
	    
	 // Getters, Setters, Constructors...
	    
	    

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	    }

	    public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
	    public String getPassword() {
	        return this.password;
	    }

	    @Override
	    public String getUsername() {
	        return this.username;
	    }

	    // Para simplificar, vamos retornar true para os métodos abaixo
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
	    
	    public UUID getId() {
	        return id;
	    }
	    // Adicione os demais Getters e Setters para todos os campos
	    
}
