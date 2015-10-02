package br.edu.ifpb.db.myplaces.dao.neo4j;

import br.edu.ifpb.db.myplaces.entitys.Place;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaomarcos
 */
public class UserPlaceDao {

    private final Neo4jConnectionUtil connectionUtil;

    public UserPlaceDao() {
        this.connectionUtil = new Neo4jConnectionUtil();
    }

    public void registerNewPlace(Place place, String email, LocalDateTime time) {
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();            
            String userIdentifier = email.split("@")[0];
            sb.append("MERGE (").append(userIdentifier).append(":Person{email:\"").append(email);
            sb.append("\", name:\"").append(userIdentifier).append("\"})\n");
            sb.append("MERGE (p:Place{").append("description:\"").append(place.getDescription());
            sb.append("\", lat:").append(place.getLat()).append(", lng:").append(place.getLng());
            sb.append("})\n");
            sb.append("MERGE (").append(userIdentifier).append(")-[:VISITED{time:\"").append(time).append("\"}]->(p)");
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

    public List<Place> listPlaces(String email) {
        List<Place> places = new ArrayList<>();
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("{email:\"").append(email);
            sb.append("\"})-[r:VISITED]->(n) RETURN n ORDER BY r.time");
            ResultSet result = stat.executeQuery(sb.toString());
            Gson gson = new Gson();
            while (result.next()) {
                places.add(gson.fromJson(result.getString("n"), Place.class));
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
        return places;
    }

    public List<Place> suggestedPlaces(String email) {
        List<Place> places = new ArrayList<>();
        try (Statement stat = connectionUtil.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("MATCH (").append("{email:\"").append(email);
            sb.append("\"})-[:FOLLOW]->(u)-[r:VISITED]->(n) RETURN n ORDER BY r.time LIMIT 5");
            ResultSet result = stat.executeQuery(sb.toString());
            Gson gson = new Gson();
            while (result.next()) {
                places.add(gson.fromJson(result.getString("n"), Place.class));
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
        return places;
    }
}
