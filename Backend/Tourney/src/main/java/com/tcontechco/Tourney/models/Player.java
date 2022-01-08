package com.tcontechco.Tourney.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tcontechco.Tourney.DTOs.RegisterPlayerDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "username",unique = true)
    private String username;

    @NonNull
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    private Set<? extends GrantedAuthority> grantedAuthorities;
    @Transient
    @JsonIgnore
    private boolean isAccountNonExpired;
    @Transient
    @JsonIgnore
    private  boolean isAccountNonLocked;
    @Transient
    @JsonIgnore
    private  boolean isCredentialsNonExpired;
    @Transient
    @JsonIgnore
    private  boolean isEnabled;

    @NonNull
    @Column
    private String email;


    @OneToMany(mappedBy = "player")
    @JsonIgnore
    private Set<TPlayer> tPlayers = new HashSet<>();

    public Player(Player player){
        this.playerId = player.getPlayerId();
        this.username = player.getUsername();
        this.email = player.getEmail();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.password = "Protected for you";
    }
    public Player(String username, String email, String firstName, String lastName, String password, Set<? extends  GrantedAuthority> grantedAuthorities,boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled){
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public  Player(RegisterPlayerDTO playerDTO){
        username = playerDTO.getUsername();
        password = playerDTO.getPassword();
        email = playerDTO.getEmail();
        firstName = playerDTO.getFirstName();
        lastName = playerDTO.getLastName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
