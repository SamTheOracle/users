package com.oracolo.findmycar.users.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.oracolo.findmycar.users.entity.User;

@ApplicationScoped
public class UserDao extends BaseDao<User>{

	public List<User> getUserWithChatIdNull(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(cb.isNull(root.get("chatId")));
		TypedQuery<User> query = em.createQuery(cq);
		query.setMaxResults(100);
		return query.getResultList();
	}

	public Optional<User> getUserByUniqueKey(String uniqueKey) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(cb.equal(root.get("uniqueKey"),uniqueKey));
		return em.createQuery(cq).getResultStream().findFirst();
	}

	public List<User> getAllUserByUniqueKeys(List<String> uniqueKeys) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(root.get("uniqueKey").in(uniqueKeys));
		return em.createQuery(cq).getResultList();
	}

	public List<User> getUserByQuery(String uniqueKey) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		Predicate uniqueKeyPredicate = cb.equal(root.get("uniqueKey"),uniqueKey);

		cq.where(cb.and(uniqueKeyPredicate));
		return em.createQuery(cq).getResultList();
	}
}
