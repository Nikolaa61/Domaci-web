package com.example.domaci7.repositories.komentar;

import com.example.domaci7.entities.Komentar;
import com.example.domaci7.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlKomentarRepository extends MySqlAbstractRepository implements KomentarRepository {

    @Override
    public Komentar addKomentar(Komentar komentar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};
            System.out.println("USAO!");
            preparedStatement = connection.prepareStatement("INSERT INTO komentari (Tekst, Autor, ID_posta) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, komentar.getTekst());
            preparedStatement.setString(2, komentar.getAutor());
            preparedStatement.setInt(3, komentar.getIdPosta());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
//
//            if (resultSet.next()) {
//                subject.setId(resultSet.getInt(1));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentar;
    }

    @Override
    public List<Komentar> allKoments(Integer idPosta) {
        System.out.println("ID POSTA :" + idPosta);
        List<Komentar> komentars = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from komentari where ID_posta = " + idPosta);

            while (resultSet.next()) {
                komentars.add(new Komentar(resultSet.getString("Autor"), resultSet.getString("Tekst"), resultSet.getInt("ID_posta")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        System.out.println(komentars);
        return komentars;
    }

}
