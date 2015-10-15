package br.edu.ifpb.db.myplaces.dao.neo4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaomarcos
 */
public class RelationshipDao {

    private final Neo4jConnectionUtil connectionUtil;

    public RelationshipDao() {
        this.connectionUtil = new Neo4jConnectionUtil();
    }

    public void follow(String from, String toFollow) {
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            String fromIdentifier = from.split("@")[0];
            String toFollowIdentifier = toFollow.split("@")[0];
            sb.append("MERGE (").append(fromIdentifier).append(":Person{email:\"").append(from);
            sb.append("\", name:\"").append(fromIdentifier).append("\"})\n");
            sb.append("MERGE (").append(toFollowIdentifier).append(":Person{email:\"").append(toFollow);
            sb.append("\", name:\"").append(toFollowIdentifier).append("\"})\n");
            sb.append("MERGE (").append(fromIdentifier).append(")-[:FOLLOW]->(").append(toFollowIdentifier).append(")");
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

    public void unfollow(String email, String emailToUnfollow) {
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("{email:\"").append(email);
            sb.append("\"})-[r:FOLLOW]->({email:\"").append(emailToUnfollow).append("\"}) ");
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

    /**
     * Return a list of email's (on max 10) that the user can to know, based in
     * your following list. If not found email's, the empty list is returned
     * @param email
     * @return
     */
    public List<String> suggestPersonsFor(String email) {
        List<String> listOfEmails = new ArrayList<>();
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("u {email:\"").append(email);
            sb.append("\"})-[:FOLLOW]->()-[:FOLLOW]->(o) WHERE o.email <> u.email AND NOT (u)-[:FOLLOW]->(o) RETURN o.email LIMIT 10");
            ResultSet result = stat.executeQuery(sb.toString());
            while (result.next()) {
                listOfEmails.add(result.getString("o.email"));
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

    public boolean isFollower(String email, String emailToCheck) {
        boolean isFollower = false;
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("{email:\"").append(email);
            sb.append("\"})-[r:FOLLOW]->({email:\"").append(emailToCheck);
            sb.append("\"}) RETURN r");
            ResultSet result = stat.executeQuery(sb.toString());
            if(result.next()){                
                isFollower = true;
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
        return isFollower;
    }

    public int numberOfFollwers(String email) {
        int numberOfFollowers = 0;
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("{email:\"").append(email);
            sb.append("\"})<-[:FOLLOW]-(n) return  count(n) as numberOfFollowers");
            ResultSet result = stat.executeQuery(sb.toString());
            result.next();
            numberOfFollowers = result.getInt("numberOfFollowers");
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
        return numberOfFollowers;
    }
    
    public List<String> following(String email){
        List<String> listOfEmails = new ArrayList<>();
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("{email:\"").append(email);
            sb.append("\"})-[:FOLLOW]->(n) return n.email");
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
}
