package com.neider.rutaescolar.personalruta.infrastructure.db;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlConductorRepository implements ConductorRepository {

    @Override
    public Conductor guardar(Conductor conductor) {
        if (conductor.getId() == null) {
            return insertar(conductor);
        } else {
            return actualizar(conductor);
        }
    }

    private Conductor insertar(Conductor conductor) {
        String sql = "INSERT INTO conductor " +
                "(nombres, apellidos, documento, nro_licencia, categoria_lic, telefono, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, conductor.getNombres());
            ps.setString(2, conductor.getApellidos());
            ps.setString(3, conductor.getDocumento());
            ps.setString(4, conductor.getNroLicencia());
            ps.setString(5, conductor.getCategoriaLicencia());
            ps.setString(6, conductor.getTelefono());
            ps.setString(7, conductor.getEstado().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    conductor.setId(rs.getInt(1));
                }
            }

            return conductor;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar conductor", e);
        }
    }

    private Conductor actualizar(Conductor conductor) {
        String sql = "UPDATE conductor SET " +
                "nombres = ?, apellidos = ?, documento = ?, " +
                "nro_licencia = ?, categoria_lic = ?, telefono = ?, estado = ? " +
                "WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, conductor.getNombres());
            ps.setString(2, conductor.getApellidos());
            ps.setString(3, conductor.getDocumento());
            ps.setString(4, conductor.getNroLicencia());
            ps.setString(5, conductor.getCategoriaLicencia());
            ps.setString(6, conductor.getTelefono());
            ps.setString(7, conductor.getEstado().name());
            ps.setInt(8, conductor.getId());

            ps.executeUpdate();
            return conductor;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar conductor", e);
        }
    }

    @Override
    public Optional<Conductor> buscarPorId(Integer id) {
        String sql = "SELECT id, nombres, apellidos, documento, " +
                "nro_licencia, categoria_lic, telefono, estado " +
                "FROM conductor WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Conductor c = mapearConductor(rs);
                    return Optional.of(c);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar conductor por id", e);
        }
    }

    @Override
    public List<Conductor> listarTodos() {
        String sql = "SELECT id, nombres, apellidos, documento, " +
                "nro_licencia, categoria_lic, telefono, estado FROM conductor";
        List<Conductor> lista = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearConductor(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar conductores", e);
        }
        return lista;
    }

    @Override
    public void eliminarPorId(Integer id) {
        String sql = "DELETE FROM conductor WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar conductor", e);
        }
    }

    private Conductor mapearConductor(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nombres = rs.getString("nombres");
        String apellidos = rs.getString("apellidos");
        String documento = rs.getString("documento");
        String nroLicencia = rs.getString("nro_licencia");
        String categoriaLic = rs.getString("categoria_lic");
        String telefono = rs.getString("telefono");
        EstadoTrabajador estado = EstadoTrabajador.valueOf(rs.getString("estado"));

        return new Conductor(id, nombres, apellidos, documento,
                nroLicencia, categoriaLic, telefono, estado);
    }
}
