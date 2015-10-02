package br.edu.ifpb.db.myplaces.dao.neo4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author joaomarcos
 */
public class UserLikeDao {

    private final Neo4jConnectionUtil connectionUtil;

    public UserLikeDao() {
        this.connectionUtil = new Neo4jConnectionUtil();
    }

    public void likePost(String email, ObjectId postID) {
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            String userIdentifier = email.split("@")[0];
            sb.append("MERGE (").append(userIdentifier).append(":Person{email:\"").append(email);
            sb.append("\", name:\"").append(userIdentifier).append("\"})\n");
            sb.append("MERGE (p:Post{id:\"").append(postID).append("\"})\n");
            sb.append("MERGE (").append(userIdentifier).append(")-[:LIKE]->(p)");

            stat.execute(sb.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Driver Problem", ex);
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Query error", ex);
        } finally {
            try {
                connectionUtil.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Error on to close connection", ex);
            }
        }
    }

    public void dislikePost(String email, ObjectId postID) {
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("{email:\"").append(email);
            sb.append("\"})-[r:LIKE]->({id:\"").append(postID).append("\"}) ");
            sb.append("DELETE r");
            stat.execute(sb.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Driver Problem", ex);
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Query error", ex);
        } finally {
            try {
                connectionUtil.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Error on to close connection", ex);
            }
        }
    }
   
    public List<String> findUsersThatLikeIt(ObjectId postID) {
        List<String> listOfEmails = new ArrayList<>();
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (n)-[:LIKE]->({id:\"").append(postID).append("\"}) ");
            sb.append("RETURN n.email");
            ResultSet result = stat.executeQuery(sb.toString());
            while (result.next()) {
                listOfEmails.add(result.getString("n.email"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Driver Problem", ex);
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Query error", ex);
        } finally {
            try {
                connectionUtil.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Error on to close connection", ex);
            }
        }
        return listOfEmails;
    }

    public boolean isLikedFor(ObjectId postID, String email) {
        boolean isLiked = false;
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("{email:\"").append(email);
            sb.append("\"})-[r:LIKE]->({id:\"").append(postID).append("\"}) ");
            sb.append("RETURN r");
            ResultSet result = stat.executeQuery(sb.toString());
            if (result.next()) {
                isLiked = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Driver Problem", ex);
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Query error", ex);
        } finally {
            try {
                connectionUtil.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Error on to close connection", ex);
            }
        }
        return isLiked;
    }
    
    @Deprecated
    public int numberOfLikes(ObjectId postId){
        int nLikes = 0;
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (n)-[:LIKE]->({id:\"").append(postId).append("\"}) ");
            sb.append("RETURN count(n) as numberOfLikes");
            ResultSet result = stat.executeQuery(sb.toString());
            result.next();
            nLikes = result.getInt("numberOfLikes");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Driver Problem", ex);
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Query error", ex);
        } finally {
            try {
                connectionUtil.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(RelationshipDao.class.getName()).log(Level.SEVERE, "Error on to close connection", ex);
            }
        }
        return nLikes;
    }
}
