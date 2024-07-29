package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CarDaoImp implements CarDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    @Transactional
    public void addCar(Car car) {
        entityManager.persist(car);
    }
    @Transactional
    @Override
    public Car getCarById(Long id) {
        return sessionFactory.openSession().get(Car.class,id);
    }
    @Transactional
    @Override
    public List<Car> listCars() {
        return sessionFactory.openSession().createQuery("from Car", Car.class).list();
    }
}
