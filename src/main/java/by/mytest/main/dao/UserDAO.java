package by.mytest.main.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import by.mytest.main.model.User;


@Repository
public interface UserDAO {
	List<User> getUserList();

	void saveUser(User user);

	User getUser(String loginUse);

	void deleteUser(String login);
}
