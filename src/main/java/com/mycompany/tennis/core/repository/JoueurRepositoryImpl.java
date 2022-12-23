package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
        System.out.println("Joueur créer");
    }

    // Pourquoi faire ca en repository ? est ce que c'est vraiment son role ?
    // peut-etre que c'est le role de dao vu
    // que c'est un appel avec un getId et on doit juste ajouter une ligne a savoir
    // REP : dans le service ...
    public void renomme(Long id, String nouveauNom) {
        Session session = null;
        Joueur j = new Joueur();
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            j = session.get(Joueur.class, id);
            j.setNom(nouveauNom);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    }

    public void update(Joueur joueur) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement statement = conn
                    .prepareStatement("UPDATE JOUEUR SET NOM = ?, PRENOM= ?, SEXE = ? WHERE ID = ?");

            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3, joueur.getSexe().toString());
            statement.setLong(4, joueur.getId());

            statement.executeUpdate();

            System.out.println("Joueur Modifier");
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

    public void delete(Long id) {

        Joueur joueur = getById(id);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.remove(joueur);

        System.out.println("Le joueur avec l'identifiant " + joueur.getId() + " a été supprimer.");

    }

    // Utiliser la session courrante au lieu d'en créer une
    public Joueur getById(Long id) {
        // fonctionnement de hibernate
        Session session = null;
        Joueur j = new Joueur();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        j = session.get(Joueur.class, id);
        System.out.println("Joueur lu avec comme prénom " + j.getPrenom());
        return j;
    }

    public List<Joueur> list() {
        Connection conn = null;
        List<Joueur> maListe = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT ID,NOM,PRENOM FROM JOUEUR");

            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Joueur j = new Joueur();
                j.setId(res.getLong("ID"));
                j.setNom(res.getString("NOM"));
                j.setPrenom(res.getString("PRENOM"));
                j.setSexe(res.getString("SEXE").charAt(0));
                maListe.add(j);
            }

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

        return maListe;
    }
}
