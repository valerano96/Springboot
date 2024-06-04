package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class ComputerJDBCTemp {
    
	
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    public int insertComputer(String marca ,String tipologia, String modello, String descrizione, int qnt,  String urlImage, double prezzo) {
        String query = "INSERT INTO magazzino (marca , tipologia, modello, descrizione, qnt, url, prezzo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplateObject.update(query, marca, tipologia,  modello,  descrizione, qnt, urlImage, prezzo);
    }
    
    

    
    public ArrayList<Computer> ritornaComputer(){
    	ResultSet rs1 = null;
    	
    	
            String query = "SELECT * FROM magazzino";
            return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<Computer>>() {
                @Override
                public ArrayList<Computer> extractData(ResultSet rs) throws SQLException {
                	ArrayList <Computer> listaComputer = new ArrayList<>();
                    while (rs.next()) {
                        Computer pc = new Computer();
                       
                        pc.setId(rs.getInt("id"));
                        pc.setMarca(rs.getString("marca"));
                        pc.setTipologia(rs.getString("tipologia"));
                        pc.setModello(rs.getString("modello"));
                        pc.setDescrizione(rs.getString("descrizione"));
                        pc.setQnt(rs.getInt("qnt"));
                        pc.setUrlImage(rs.getString("url"));
                        pc.setPrezzo(rs.getDouble("prezzo"));
                        
                        listaComputer.add(pc);
                    }
                    return listaComputer;
                }
            });
        }
    public int updatePezzi(int pezzi, int id) {
        String query = "UPDATE magazzino SET qnt = qnt+? WHERE id = ?";
        return jdbcTemplateObject.update(query, pezzi, id);
    }
    public int updatePezzi2(int pezzi, int id) {
        String query = "UPDATE magazzino SET qnt = qnt - ? WHERE id = ?";
        return jdbcTemplateObject.update(query, pezzi, id);
    }
       // Metodo per eseguire query DDL
    public void executeDDLQuery(String query) {
        jdbcTemplateObject.execute(query);
    }
}
