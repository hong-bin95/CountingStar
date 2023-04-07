package com.ssafy.countingstar.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.countingstar.model.db.Database;
import com.ssafy.countingstar.model.dto.ConstellationDTO;

public class ConstellationDAO {
	private static ConstellationDAO instance;
	private Connection connection;

	private ConstellationDAO() {
		connection = Database.getInstance().getConnection();
	}

	public static ConstellationDAO getInstance() {
		if (instance == null) {
			instance = new ConstellationDAO();
		}
		return instance;
	}

	public void addConstellation(ConstellationDTO constellation) {
		try {
			String sql = "INSERT INTO constellation (name, observe_month) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, constellation.getName());
			statement.setString(2, constellation.getObserve_month());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateConstellation(ConstellationDTO constellation) {
		try {
			String sql = "UPDATE constellation SET name = ?, observe_month = ? WHERE constellation_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, constellation.getName());
			statement.setString(2, constellation.getObserve_month());
			statement.setInt(3, constellation.getConstellation_id());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteConstellation(int constellation_id) {
		try {
			String sql = "DELETE FROM constellation WHERE constellation_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, constellation_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ConstellationDTO> getAllConstellations() {
		List<ConstellationDTO> constellations = new ArrayList<>();
		try {
			String sql = "SELECT * FROM constellation";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Integer constellation_id = result.getInt("constellation_id");
				String name = result.getString("name");
				String observe_month = result.getString("observe_month");
				ConstellationDTO constellation = new ConstellationDTO(constellation_id, name, observe_month);
				constellations.add(constellation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return constellations;
	}

	public ConstellationDTO getConstellationById(int constellation_id) {
		ConstellationDTO constellation = null;
		try {
			String sql = "SELECT * FROM constellation WHERE constellation_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, constellation_id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String name = result.getString("name");
				String observe_month = result.getString("observe_month");
				constellation = new ConstellationDTO(constellation_id, name, observe_month);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return constellation;
	}
}