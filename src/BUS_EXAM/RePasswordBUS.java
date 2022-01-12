/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.UserDAO;

/**
 *
 * @author ASUS
 */
public class RePasswordBUS {

    public int rePass(String before, String after, String reAfter) {
        UserDAO user = new UserDAO();
        if (before.equals(user.getPassword(LoginBUS.isUser.getMaUser()))) {
            if (after.equals(reAfter)) {
                user.setPassword(LoginBUS.isUser.getMaUser(), after);
                return 2;
            }
            return 1;
        }
        return 0;
    }

}
