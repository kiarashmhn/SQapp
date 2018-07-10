package com.example.demo.Services;

import com.example.demo.Models.*;
import com.example.demo.Repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class OwnerService {
    @PersistenceContext
    EntityManager entityManager;
    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Iterable<Owner> findAll() {
        Iterable<Owner> users = ownerRepository.findAll();
        return users;
    }

    public void createOwner(Owner owner) throws Exception {
        //save md5
        //Owner owner1 = new Owner(owner.getName(),owner.getUserName(), MD5.getMD5(owner.getPassWord()),owner.getEmail());
        Owner owner1 = new Owner(owner.getName(), owner.getUserName(), owner.getPassWord(), owner.getEmail());
        ownerRepository.save(owner1);
    }

    public Boolean checkPassWord(String userName, String password) { // no MD5, because it doesn't use
        Iterable<Owner> owners = ownerRepository.findAll();
        for (Owner x : owners)
            if (x.getPassWord().equals(password) && x.getUserName().equals(userName))
                return true;
        return false;
    }

    public Owner findByUserName(String username) {
        Iterable<Owner> owners = ownerRepository.findAll();
        for (Owner x : owners)
            if (x.getUserName().equals(username))
                return x;
        return null;
    }

    public void createReceipt(String ownerUserName,Long userId,Long planId) {
        Query query = entityManager.createQuery("select p from plan p where p.id=:planId");
        query.setParameter("planId",planId);
        Plan plan = (Plan) query.getSingleResult();
        Owner owner = (Owner) entityManager.createQuery("select  o from owner o where o.userName=:ownerUserName").setParameter("ownerUserName",ownerUserName).getSingleResult();
        Club club = owner.getClub();
        User user = (User) entityManager.createQuery("select u from user u where u.id= :userId").setParameter("userId",userId).getSingleResult();
        Receipt receipt = new Receipt();
        receipt.setPrice(plan.getPrice());
        receipt.setClubAddress(club.getAddress());
        receipt.setClubName(club.getName());
        receipt.setDate(plan.getDate());
        receipt.setTime(plan.getTime());
        List list = owner.getReceipts();
        list.add(receipt);
        owner.setReceipts(list);
        plan.setStatus(1);
        ownerRepository.save(owner);
    }

    public void createTransaction(String ownerUserName,Long userId, Long planId) {
        Query query = entityManager.createQuery("select p from plan p where p.id=:planId");
        query.setParameter("planId",planId);
        Plan plan = (Plan) query.getSingleResult();
        Owner owner = (Owner) entityManager.createQuery("select  o from owner o where o.userName=:ownerUserName").setParameter("ownerUserName",ownerUserName).getSingleResult();
        Club club = owner.getClub();
        User user = (User) entityManager.createQuery("select u from user u where u.id= :userId").setParameter("userId",userId).getSingleResult();
        Transaction transaction = new Transaction();
        transaction.setDate(java.time.LocalDate.now());
        transaction.setTime(java.time.LocalTime.now());
        Double c = owner.getCredit();
        int n = plan.getPrice();
        int m = transaction.getBalance();
        owner.setCredit(c + n);
        transaction.setBalance(m + n);
        transaction.setTransactionMoney(plan.getPrice());
        List list = owner.getTransactions();
        list.add(transaction);
        owner.setTransactions(list);
        ownerRepository.save(owner);
    }
}
