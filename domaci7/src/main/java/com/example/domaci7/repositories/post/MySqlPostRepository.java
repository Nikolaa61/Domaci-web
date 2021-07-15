package com.example.domaci7.repositories.post;

import com.example.domaci7.entities.Post;
import com.example.domaci7.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPostRepository extends MySqlAbstractRepository implements PostRepository{
    @Override
    public Post addPost(Post post) {
        System.out.println("USAP U MSSQL ADD POST");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO postovi (Autor, Title, Content, Date) " +
                            "VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, post.getAutor());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                post.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }

    @Override
    public List<Post> allPosts() {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from postovi");
            while (resultSet.next()) {
                /// Post(String autor, String title, String content, Date date, int id) {
                posts.add(new Post(
                        resultSet.getString("Autor"), resultSet.getString("Title"),
                        resultSet.getString("Content"), new java.util.Date(resultSet.getDate("Date").getTime()),
                        resultSet.getInt("ID")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return posts;
    }

    @Override
    public Post findPost(Integer id) {
        Post post = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM postovi where ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String autor = resultSet.getString("Autor");
                String title = resultSet.getString("Title");
                String content = resultSet.getString("Content");
                Date date = resultSet.getDate("Date");
                int postID = resultSet.getInt("id");
                post = new Post(autor, title, content, date, postID);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }

}
