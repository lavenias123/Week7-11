package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projects.entity.Project;
import projects.exception.DbException;
import provided.util.DaoBase;

public class ProjectDao extends DaoBase {
	
	// creating constants (table names)
	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";
	private static final String STEP_TABLE = "step";

	public Project insertProject(Project project) {
		// @formatter:off
		// creating a prepared SQL statement
		String sql = ""
				+ "INSERT INTO " + PROJECT_TABLE + " "
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?) ";
		// @formatter:on
		
		// trying to establish a connection to the DB
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				// setting params inside the prepared statement
				setParameter(stmt, 1, project.getProjectName(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class);
				
				stmt.executeUpdate();
				
				// assign the last auto-incremented projectId to this var
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				commitTransaction(conn);
				
				project.setProjectId(projectId);
				return project;
				
				
				
			} // send prepareStatement
			catch(SQLException e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			} // catch error and cancel prepareStatement
		} // try to establish a connection
		catch(SQLException e) {
			throw new DbException(e);
		} // catch
	
	
	} // insertProject

}
