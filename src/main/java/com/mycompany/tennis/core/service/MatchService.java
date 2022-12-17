package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.dao.MatchDaoImpl;
import com.mycompany.tennis.core.entity.Match;
// import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
// import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {

    // private ScoreRepositoryImpl scoreRepositoryImpl;
    // private MatchRepositoryImpl matchRepositoryImpl;
    private MatchDaoImpl matchDaoImpl;

    public MatchService() {
        // this.scoreRepositoryImpl = new ScoreRepositoryImpl();
        // this.matchRepositoryImpl = new MatchRepositoryImpl();
        matchDaoImpl = new MatchDaoImpl();
    }

    public void enregistrerNouveauMatch(Match m) {
        // this.matchRepositoryImpl.create(m);
        // this.scoreRepositoryImpl.create(m.getScore());
        matchDaoImpl.createMatchWithScore(m);
    }
}
