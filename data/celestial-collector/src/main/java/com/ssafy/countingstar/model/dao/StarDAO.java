package com.ssafy.countingstar.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.countingstar.model.db.Database;
import com.ssafy.countingstar.model.dto.StarDTO;

public class StarDAO {
	private static StarDAO instance;
	private Connection connection;

	private StarDAO() {
		connection = Database.getInstance().getConnection();
	}

	public static StarDAO getInstance() {
		if (instance == null) {
			instance = new StarDAO();
		}
		return instance;
	}

	public void addStar(StarDTO star) {
		try {
			String sql = "INSERT INTO star (star_id, name, code_detail_id, constellation_id) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, star.getStar_id());
			statement.setString(2, star.getName());
			statement.setInt(3, star.getCode_detail_id());
			statement.setObject(4, star.getConstellation_id());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStar(StarDTO star) {
		try {
			String sql = "UPDATE star SET name = ?, code_detail_id = ?, constellation_id = ? WHERE star_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, star.getName());
			statement.setInt(2, star.getCode_detail_id());
			statement.setObject(3, star.getConstellation_id());
			statement.setInt(4, star.getStar_id());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStar(int star_id) {
		try {
			String sql = "DELETE FROM star WHERE star_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, star_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}