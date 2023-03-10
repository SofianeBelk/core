package com.mycompany.tennis.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.DataSource;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;

public class MatchDaoImpl {

    public void createMatchWithScore(Match match) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, match.getEpreuve().getId());
            statement.setLong(2, match.getVainqueur().getId());
            statement.setLong(3, match.getFinaliste().getId());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                match.setId(rs.getLong(1));
            }

            System.out.println("Match créé ");

            // Partie 2

            PreparedStatement statement2 = conn.prepareStatement(
                    "INSERT INTO SCORE_VAINQUEUR (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            Score score = match.getScore();
            statement2.setLong(1, score.getMatch().getId());
            statement2.setLong(2, score.getSet1());
            statement2.setLong(3, score.getSet2());

            if (score.getSet3() == null) {
                statement2.setNull(4, Types.TINYINT);
            } else {
                statement2.setLong(4, score.getSet3());
            }

            if (score.getSet4() == null) {
                statement2.setNull(5, Types.TINYINT);
            } else {
                statement2.setLong(5, score.getSet4());
            }

            if (score.getSet5() == null) {
                statement2.setNull(6, Types.TINYINT);
            } else {
                statement2.setLong(6, score.getSet5());
            }

            statement2.executeUpdate();

            ResultSet rs2 = statement.getGeneratedKeys();

            if (rs2.next()) {
                score.setId(rs2.getLong(1));
            }
            conn.commit();
            System.out.println("Score créé ");
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if (conn != null) {
                    conn.rollback();
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
