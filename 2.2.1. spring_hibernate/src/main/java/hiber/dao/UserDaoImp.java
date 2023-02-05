package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User findUserByCarModelAndSeries(String model, int series) {
      String hql = "from Car where model=:model and series=:series";
      Query query = sessionFactory.getCurrentSession().createQuery(hql, Car.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      Car car = (Car)query.getSingleResult();
      return car.getUser();
   }

}
