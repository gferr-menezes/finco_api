package br.com.finco.finco_api.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finco.finco_api.user.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    
}
