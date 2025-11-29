package com.neider.rutaescolar.personalruta.infraestructure.db;

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
        if (asistente == null) {
            throw new IllegalArgumentException("No se puede guardar un asistente nulo.");
        }

        return (asistente.getId() == null)
                ? insertar(asistente)
                : actualizar(asistente);
    }

    private Asistente insertar(Asistente asistente) {
        String sql = "INSERT INTO asistente "
                + "(nombre, apellido, telefono, estado) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, asistente.getNombre());
            ps.setString(2, asistente.getApellido());
            ps.setString(3, asistente.getTelefono());
            ps.setString(4, asistente.getEstado().name());

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
        String sql = "UPDATE asistente SET "
                + "nombre = ?, apellido = ?, "
                + "telefono = ?, estado = ? "
                + "WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, asistente.getNombre());
            ps.setString(2, asistente.getApellido());
            ps.setString(3, asistente.getTelefono());
            ps.setString(4, asistente.getEstado().name());
            ps.setInt(5, asistente.getId());

            ps.executeUpdate();
            return asistente;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar asistente con id " + asistente.getId(), e);
        }
    }

    @Override
    public Optional<Asistente> buscarPorId(Integer id) {
        String sql = "SELECT id, nombre, apellido, telefono, estado "
                + "FROM asistente WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

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
            throw new RuntimeException("Error al buscar asistente por id " + id, e);
        }
    }

    @Override
    public List<Asistente> listarTodos() {
        String sql = "SELECT id, nombre, apellido, telefono, estado FROM asistente";
        List<Asistente> lista = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar asistente con id " + id, e);
        }
    }

    private Asistente mapearAsistente(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String telefono = rs.getString("telefono");
        EstadoTrabajador estado = EstadoTrabajador.valueOf(rs.getString("estado"));

        return new Asistente(id, nombre, apellido, telefono, estado);
    }
}
