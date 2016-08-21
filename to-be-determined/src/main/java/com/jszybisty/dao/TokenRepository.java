package com.jszybisty.dao;

import com.jszybisty.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jakub on 8/21/2016.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByToken(String token);
}
