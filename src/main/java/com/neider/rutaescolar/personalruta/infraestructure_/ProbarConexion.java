package com.neider.rutaescolar.personalruta.infraestructure_;

import com.neider.rutaescolar.personalruta.infraestructure.db.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class ProbarConexion {

    public static void main(String[] args) {
        System.out.println("Probando conexión a la base de datos...");

        try (Connection conn = DatabaseConfig.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexión EXITOSA a ruta_escolar_act_und4");
            } else {
                System.out.println("Conexión NO establecida");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
