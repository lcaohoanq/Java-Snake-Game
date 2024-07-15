package modules.user;

import constants.ErrorMessages;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.StoredProcedureQuery;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;

public class UserDAO {

    private static UserDAO instance;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<String> selectFirstNameAndScore() {
        List<String> resultList = new ArrayList<>();
        EntityManager entityManager = getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            StoredProcedureQuery query = session.createStoredProcedureQuery(
                "proc_select_first_name_score");

            List<Object[]> results = query.getResultList();
            for (Object[] result : results) {
                resultList.add(result[0] + " " + result[1]);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ErrorMessages.ERROR_SELECT_FIRST_NAME_AND_SCORE + e.getMessage());
        } finally {
            session.close();
        }
        return resultList;
    }

    public UserEntity selectEmailAndPasswordByEmail(String email) {
        UserEntity user = null;
        EntityManager entityManager = getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query<UserEntity> query =  session.createQuery(
                "FROM UserEntity WHERE email = :email", UserEntity.class);

            query.setParameter("email", email);
            query.setMaxResults(1);
            List<UserEntity> results = query.getResultList(); // Get directly as UserEntity
            if (!results.isEmpty()) {
                user = results.get(0); // No need for manual mapping
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ErrorMessages.ERROR_SELECT_EMAIL_AND_PASSWORD + e.getMessage());
        } finally {
            session.close();
        }
        return user;
    }

    public UserEntity selectEmailAndScoreByEmail(String email) {
        UserEntity user = null;
        EntityManager entityManager = getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            StoredProcedureQuery query = session.createStoredProcedureQuery(
                    "proc_select_email_score_by_email")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            query.setParameter(1, email);
            query.setMaxResults(1);
            List<Object[]> results = query.getResultList();
            if (!results.isEmpty()) {
                Object[] result = results.get(0);
                user = new UserEntity((String) result[0], (Integer) result[1]);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ErrorMessages.ERROR_SELECT_USERNAME_AND_SCORE + e.getMessage());
        } finally {
            session.close();
        }
        return user;
    }

    public int setSafeUpdate() {
        EntityManager entityManager = getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = null;
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            StoredProcedureQuery query = session.createStoredProcedureQuery("proc_set_safe_update");

            rowsAffected = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rowsAffected;
    }

    public int updateEmailScore(String email, String score) {
        EntityManager entityManager = getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = null;
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            StoredProcedureQuery query = session.createStoredProcedureQuery(
                    "proc_update_score_by_email")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);

            query.setParameter(1, email);
            query.setParameter(2, score);
            rowsAffected = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rowsAffected;
    }

    public int insertMail(String email, String phoneNumber, String firstName, String lastName,
        String password) {
        EntityManager entityManager = getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = null;
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            StoredProcedureQuery query = session.createStoredProcedureQuery(
                    "proc_insert_user_new(?,?,?,?,?)")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN);

            query.setParameter(1, email);
            query.setParameter(2, phoneNumber);
            query.setParameter(3, firstName);
            query.setParameter(4, lastName);
            query.setParameter(5, password);
            rowsAffected = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rowsAffected;
    }

    public static void main(String[] args) {
//        UserDAO.getInstance().insertMail("hoangclw@gmail.com", "", "Hoang", "Luu", "123456");
//        UserDAO.getInstance().selectFirstNameAndScore().forEach(System.out::println);
        System.out.println(
            UserDAO.getInstance().selectEmailAndPasswordByEmail("hoanglcse181513@fpt.edu.vn").getPassword());
    }
}
