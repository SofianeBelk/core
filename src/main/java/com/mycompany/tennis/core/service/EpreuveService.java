package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;

public class EpreuveService {

    private EpreuveRepositoryImpl epreuveRepositoryImpl;

    public EpreuveService() {
        epreuveRepositoryImpl = new EpreuveRepositoryImpl();
    }

    public Epreuve getEpreuve(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepositoryImpl.getById(id);
            System.out
                    .println("l'épreuve séléctionnée se déroule en " + epreuve.getAnnee());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return epreuve;
    }
}
