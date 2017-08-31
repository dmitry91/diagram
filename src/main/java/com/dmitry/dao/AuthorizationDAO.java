package com.dmitry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dmitry.util.DataConnect;

public class AuthorizationDAO {

	/**
	 * Authorization user on system
	 * @param email user login
	 * @param password user password
	 * @return boolean result
	 */
	public static boolean validate(String email, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			assert con != null;
			ps = con.prepareStatement("Select email, password from User where email = ? and password = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}

	/**
	 * registration new user
	 * @param email user login
	 * @param password user password
	 * @return boolean result
	 */
	public static boolean registration(String email, String password){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			assert con != null;
			ps = con.prepareStatement("INSERT INTO  user (email, password) VALUES(?,?)");
			ps.setString(1, email);
			ps.setString(2, password);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}