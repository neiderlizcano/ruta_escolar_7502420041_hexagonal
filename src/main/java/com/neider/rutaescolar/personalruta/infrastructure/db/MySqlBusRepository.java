package com.neider.rutaescolar.personalruta.infrastructure.db;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.model.EstadoBus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlBusRepository implements BusRepository {

    @Override
    public Bus guardar(Bus bus) {
        if (bus.getId() == null) {
            return insertar(bus);
        } else {
            return actualizar(bus);
        }
    }

    private Bus insertar(Bus bus) {
        String sql = "INSERT INTO bus (placa, capacidad, estado) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bus.getPlaca());
            ps.setInt(2, bus.getCapacidad()); 
            ps.setString(3, bus.getEstado().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    bus.setId(rs.getInt(1));
                }
            }

            return bus;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar bus", e);
        }
    }

    private Bus actualizar(Bus bus) {
        String sql = "UPDATE bus SET placa = ?, capacidad = ?, estado = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bus.getPlaca());
            ps.setInt(2, bus.getCapacidad());
            ps.setString(3, bus.getEstado().name());
            ps.setInt(4, bus.getId());

            ps.executeUpdate();
            return bus;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar bus", e);
        }
    }

    @Override
    public Optional<Bus> buscarPorId(Integer id) {
        String sql = "SELECT id, placa, capacidad, estado FROM bus WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Bus b = mapearBus(rs);
                    return Optional.of(b);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar bus por id", e);
        }
    }

    @Override
    public List<Bus> listarTodos() {
        String sql = "SELECT id, placa, capacidad, estado FROM bus";
        List<Bus> lista = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearBus(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar buses", e);
        }
        return lista;
    }

    @Override
    public void eliminarPorId(Integer id) {
        String sql = "DELETE FROM bus WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar bus", e);
        }
    }

    private Bus mapearBus(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String placa = rs.getString("placa");
        int capacidad = rs.getInt("capacidad");
        EstadoBus estado = EstadoBus.valueOf(rs.getString("estado"));

        return new Bus(id, placa, capacidad, estado);
    }
}
