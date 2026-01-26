package com.mycompany.iwishserver.Services;


import com.mycompany.iwishserver.DAO.FriendshipDAO;
import com.mycompany.iwishserver.DAO.UserDAO;
import com.mycompany.iwishserver.Models.User;

import java.util.List;

public class FriendsService {
    private FriendshipDAO friendshipDAO = new FriendshipDAO();
    private UserDAO userDAO = new UserDAO();

    ///////////////////// A. Get all friends ////////////////////////
    public List<Integer> getAllFriends(int userId) {
        // Function number 7 in friendshipDAO
        return friendshipDAO.getAllFriends(userId);
    }

    ///////////////////// B. Unfriend method ////////////////////////
    public boolean unfriend(int friendId,  int userId) {
        // Function number 4 in friendshipDAO
        return friendshipDAO.deleteFriendshipByUserIds(userId, friendId);
    }
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
}
