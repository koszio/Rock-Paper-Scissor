package server.integration;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import server.model.Player;

/*
This class handles all the database queries thus called Data Access Object (DAO)
 */
public class PlayerDAO {

    private final EntityManagerFactory emFactory;
    private final ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<>();

    public PlayerDAO() {
        emFactory = Persistence.createEntityManagerFactory("projectPU");
    }

    /*
    Registers the client in the database
     */
    public void registerClient(Player player) {
        try {
            EntityManager entityManager = beginTransaction();
            entityManager.persist(player);
        } finally {
            commitTransaction();
        }
    }

    /*
    Deletes the player from the database
     */
    public void deletePlayer(Player player) {
        EntityManager entityManager = beginTransaction();
        entityManager.remove(entityManager.merge(player));
        commitTransaction();
    }

    /*
    Finds a specific player in the database
     */
    public Player findPlayer(String username) {
        try {
            EntityManager em = beginTransaction();
            try {
                Query query = em.createQuery("SELECT u FROM Player u WHERE u.username=:username");
                query.setParameter("username", username);
                return (Player) query.getSingleResult();
            } catch (NoResultException noSuchName) {
                return null;
            }
        } finally {
            commitTransaction();
        }

    }

    /*
    Returns a list of all current players
     */
    public List<Player> listPlayers() {

        EntityManager em = beginTransaction();
        Query query = em.createQuery("SELECT p FROM Player p");
        List<Player> players;

        try {
            players = query.getResultList();
        } catch (NoResultException e) {
            players = new ArrayList<>();
        }

        commitTransaction();
        return players;
    }

    /*
    Updates players information
     */
    public void updateInfo(Player player) {
        EntityManager entityManager = beginTransaction();
        entityManager.merge(player);
        commitTransaction();
    }

    /*
    Dataccess methods
     */
    private EntityManager beginTransaction() {
        EntityManager em = emFactory.createEntityManager();
        threadLocalEntityManager.set(em);
        EntityTransaction transaction = em.getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        return em;
    }

    private void commitTransaction() {
        threadLocalEntityManager.get().getTransaction().commit();
    }
}
