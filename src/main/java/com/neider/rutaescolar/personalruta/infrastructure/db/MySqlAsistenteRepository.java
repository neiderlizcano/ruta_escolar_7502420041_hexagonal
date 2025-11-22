package com.neider.rutaescolar.personalruta.infrastructure.db;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlAsistenteRepository implements AsistenteRepository {

    @Override
    public Asistente guardar(Asistente asistente) {
        if (asistente.getId() == null) {
            return insertar(asistente);
        } else {
            return actualizar(asistente);
        }
    }

    private Asistente insertar(Asistente asistente) {
        String sql = "INSERT INTO asistente " +
                "(nombres, apellidos, documento, telefono, estado) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, asistente.getNombres());
            ps.setString(2, asistente.getApellidos());
            ps.setString(3, asistente.getDocumento());
            ps.setString(4, asistente.getTelefono());
            ps.setString(5, asistente.getEstado().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    asistente.setId(rs.getInt(1));
                }
            }

            return asistente;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar asistente", e);
        }
    }

    private Asistente actualizar(Asistente asistente) {
        String sql = "UPDATE asistente SET " +
                "nombres = ?, apellidos = ?, documento = ?, " +
                "telefono = ?, estado = ? " +
                "WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, asistente.getNombres());
            ps.setString(2, asistente.getApellidos());
            ps.setString(3, asistente.getDocumento());
            ps.setString(4, asistente.getTelefono());
            ps.setString(5, asistente.getEstado().name());
            ps.setInt(6, asistente.getId());

            ps.executeUpdate();
            return asistente;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar asistente", e);
        }
    }

    @Override
    public Optional<Asistente> buscarPorId(Integer id) {
        String sql = "SELECT id, nombres, apellidos, documento, telefono, estado " +
                "FROM asistente WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Asistente a = mapearAsistente(rs);
                    return Optional.of(a);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar asistente por id", e);
        }
    }

    @Override
    public List<Asistente> listarTodos() {
        String sql = "SELECT id, nombres, apellidos, documento, telefono, estado FROM asistente";
        List<Asistente> lista = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearAsistente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar asistentes", e);
        }
        return lista;
    }

    @Override
    public void eliminarPorId(Integer id) {
        String sql = "DELETE FROM asistente WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar asistente", e);
        }
    }

    private Asistente mapearAsistente(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nombres = rs.getString("nombres");
        String apellidos = rs.getString("apellidos");
        String documento = rs.getString("documento");
        String telefono = rs.getString("telefono");
        EstadoTrabajador estado = EstadoTrabajador.valueOf(rs.getString("estado"));

        return new Asistente(id, nombres, apellidos, documento, telefono, estado);
    }
}
