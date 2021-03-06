package com.example.demo.Services;

import com.example.demo.Models.*;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service

public class UserService {
    @PersistenceContext
    EntityManager entityManager;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //User user = new User("g","g","g","g");
        //this.userRepository.save(user);
    }

    public Iterable<User> findAll() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    public void createUser(User user) throws Exception {
        //save md5
        //Owner owner1 = new Owner(owner.getName(),owner.getUserName(), MD5.getMD5(owner.getPassWord()),owner.getEmail());
        User user1 = new User(user.getName(), user.getUserName(), user.getPassWord(), user.getEmail());
        userRepository.save(user1);
    }

    public Boolean checkPassWord(String userName, String password) { // no MD5, because it doesn't use
        Iterable<User> users = userRepository.findAll();
        if(users!=null){
        for (User x : users)
            if (x.getPassWord().equals(password) && x.getUserName().equals(userName))
                return true;}
        return false;
    }

    public User findByUserName(String username) {
        Iterable<User> users = userRepository.findAll();
            for (User x : users)
                if (x.getUserName().equals(username))
                    return x;
        return null;
    }
    public void updateUser(User user,User user1){
        if(user.getEmail()!=null)
        user1.setEmail(user.getEmail());
        if(user.getName()!=null)
        user1.setName(user.getName());
        if (user.getReceiptList()!=null)
        user1.setReceiptList(user.getReceiptList());
        if(user.getTransaction()!=null)
        user1.setTransaction(user.getTransaction());
        userRepository.save(user1);
    }
    public void createReceipt(String ownerUserName,Long userId, Long planId){
        Query query = entityManager.createQuery("select p from plan p where p.id=:planId");
        query.setParameter("planId",planId);
        Plan plan = (Plan) query.getSingleResult();
        Owner owner = (Owner) entityManager.createQuery("select  o from owner o where o.userName=:ownerUserName").setParameter("ownerUserName",ownerUserName).getSingleResult();
        Club club = owner.getClub();
        User user = (User) entityManager.createQuery("select u from user u where u.id= :userId").setParameter("userId",userId).getSingleResult();
        Receipt receipt=new Receipt();
        receipt.setPrice(plan.getPrice());
        receipt.setClubAddress(club.getAddress());
        receipt.setClubName(club.getName());
        receipt.setDate(plan.getDate());
        receipt.setTime(plan.getTime());
        List list = user.getReceiptList();
        list.add(receipt);
        user.setReceiptList(list);
        userRepository.save(user);
    }
    public void createTransaction(String ownerUserName,Long userId,Long planId){
        Query query = entityManager.createQuery("select p from plan p where p.id=:planId");
        query.setParameter("planId",planId);
        Plan plan = (Plan) query.getSingleResult();
        Owner owner = (Owner) entityManager.createQuery("select  o from owner o where o.userName=:ownerUserName").setParameter("ownerUserName",ownerUserName).getSingleResult();
        Club club = owner.getClub();
        User user = (User) entityManager.createQuery("select u from user u where u.id= :userId").setParameter("userId",userId).getSingleResult();
        Transaction transaction=new Transaction();
        transaction.setDate(java.time.LocalDate.now());
        transaction.setTime(java.time.LocalTime.now());
        Double c= user.getCredit();
        int n=plan.getPrice();
        int m=transaction.getBalance();
        user.setCredit(c-n);
        transaction.setBalance(m-n);
        transaction.setTransactionMoney(plan.getPrice());
        List list = user.getTransaction();
        list.add(transaction);
        user.setTransaction(list);
        userRepository.save(user);


    }
}
