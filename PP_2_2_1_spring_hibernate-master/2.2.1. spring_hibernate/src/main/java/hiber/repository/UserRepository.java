package hiber.repository;

import hiber.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public User getUserByCarModelAndSeries(String model, int series) {
        String hql = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";
        return entityManager.createQuery(hql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }
}
