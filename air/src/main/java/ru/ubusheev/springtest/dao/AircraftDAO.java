package ru.ubusheev.springtest.dao;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.Propagation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ubusheev.springtest.HibernateSession;
import ru.ubusheev.springtest.model.Aircraft;


import java.util.List;


@Component
public class AircraftDAO {

    public Aircraft findById(int id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        return session.get(Aircraft.class, id);
    }

    public List<Aircraft> findAll(){
        Session session = HibernateSession.getSessionFactory().openSession();
        return session.createQuery("From Aircraft").list();
    }


    public void save(Aircraft aircraft){
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(aircraft);
        session.getTransaction().commit();
    }


    public void saveOrUpdate(Aircraft aircraft){
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(aircraft);
        session.getTransaction().commit();
    }

    public void delete(int id){
        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();
        Aircraft aircraft = (Aircraft) session.load(Aircraft.class, id);
        session.delete(aircraft);
        session.getTransaction().commit();

    }


    public int exist(Aircraft aircraft){
        Session session = HibernateSession.getSessionFactory().openSession();
        StringBuffer querystr = new StringBuffer("select a.id from Aircraft a where a.depCode = :depcode and a.desCode = :descode and a.outbounddate = :outbounddate");
        if(aircraft.isInbounddateExist())
            querystr.append(" and a.inbounddate = :inbounddate");
        Query query = session.createQuery(querystr.toString())
                .setParameter("depcode", aircraft.getDepCode())
                .setParameter("descode", aircraft.getDesCode())
                .setParameter("outbounddate", aircraft.getOutbounddate());
        if(aircraft.isInbounddateExist())
            query.setParameter("inbounddate", aircraft.getInbounddate());
        return query.uniqueResult() != null ? (int) query.getSingleResult() : 0;
    }



}
