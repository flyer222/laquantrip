package com.huaxin.travel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

//import com.baidu.bae.api.util.BaeEnv;


public class DbUtil {
	static Logger logger = Logger.getLogger("java");
    static String driver = "com.mysql.jdbc.Driver"; 
    /* 
    String url = "jdbc:mysql://127.0.0.1:3306/travel";
    String username = "root"; 
    String password = "123456";
    */
    
    
    String databaseName = Config.MYSQLNAME;
	String host = Config.MYSQLHOST;
	String port = Config.MYSQLPORT;
	String username = Config.USER;
	String password = Config.PWD;
	String driverName = "com.mysql.jdbc.Driver";
	String dbUrl = "jdbc:mysql://";
	String serverName = host + ":" + port + "/";
  	String url = dbUrl + serverName + databaseName;
  	
     
  
    
    static{
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			 logger.log(Level.ERROR, "Could not load driver");
        }
    }
    
    
    public Connection getConn(){
    	
            try {
				 Connection conn = DriverManager.getConnection(url, username, password); 
				 return conn;
			} catch (SQLException e) {
				logger.fatal("could not get connection");
				e.printStackTrace();
			}
            return null;
    }
    
    //  x = qr.update(conn, "DELETE FROM person WHERE id = ?", id); 
    public int executeSql(String sql,Object... params){
    	
    	int x=-1;
    	Connection conn = getConn();
        QueryRunner qr = new QueryRunner();
        logger.log(Level.INFO, "executeSql "+sql);
        try { 
        	x = qr.update(conn, sql,params);
        	return x;
	    } catch (SQLException e) {
	    	 	logger.log(Level.ERROR, "executeSql "+sql +" "+e.getMessage());
	            e.printStackTrace(); 
	    } finally { 
	            DbUtils.closeQuietly(conn);
	    } 
	    return x; 
    }
    
    public <T> T load(long id,Class clz) { 
        Connection conn = getConn(); 
        QueryRunner qr = new QueryRunner(); 
        try { 
                T person = (T) qr.query(conn, "SELECT * FROM "+ clz.getSimpleName()+"  where id = ? ", new BeanHandler(clz), id); 
                return person;
        } catch (SQLException e) { 
                e.printStackTrace(); 
                logger.log(Level.ERROR, "load "+clz.getName()+" "+e.getMessage());
        }  finally { 
	            DbUtils.closeQuietly(conn);
	    } 
        return null; 
    } 

    
    public <T> List<T> search(String sql, Class clz){
    	
    	 Connection conn = getConn(); 
         QueryRunner qr = new QueryRunner(); 
         logger.log(Level.INFO, "searchSql "+sql);
         try { 
        	 	List<T> list = (List) qr.query(conn, sql, new BeanListHandler(clz)); 
                 return list;
         } catch (SQLException e) { 
                 e.printStackTrace(); 
                 logger.log(Level.ERROR, "search "+sql +" "+e.getMessage());
         }  finally { 
	            DbUtils.closeQuietly(conn);
 	    } 
         return null; 
    }
    
    

    public <T> List<T> search(String sql,Class clz, Object... params ){
    	Connection conn = getConn(); 
        QueryRunner qr = new QueryRunner(); 
        logger.log(Level.INFO, "executeSql witch class"+ clz + "  "+sql);
        try { 
       	 	List<T> list = (List) qr.query(conn, sql , new BeanListHandler(clz),params); 
       	 	return list;
        } catch (SQLException e) {
        	    logger.log(Level.ERROR, "search "+sql +" "+e.getMessage());
                e.printStackTrace(); 
        }  finally { 
	            DbUtils.closeQuietly(conn);
	    } 
        return null; 
    }
    
    // ResultSetHandler rsh = new ArrayListHandler();   
    // new MapListHandler();
    
    
    public List getSingleColumn(String sql,Object... params){
    	
    	Connection conn = getConn(); 
        QueryRunner qr = new QueryRunner(); 
      logger.log(Level.INFO, "getSingleColumn "+sql);
        try { 
       	 	List<Map> list = (List)qr.query(conn, sql , new ArrayListHandler(),params); 
       	 	return list;
        } catch (SQLException e) { 
                e.printStackTrace(); 
        }  finally { 
	            DbUtils.closeQuietly(conn);
	    } 
        return null; 
    }
    
    public List<Map> search(String sql, Object... params ){
    	
    	Connection conn = getConn(); 
        QueryRunner qr = new QueryRunner(); 
        logger.log(Level.INFO, "search "+sql);
        try { 
       	 	List<Map> list = (List)qr.query(conn, sql , new MapListHandler(),params); 
       	 	return list;
        } catch (SQLException e) { 
                e.printStackTrace(); 
        }  finally { 
	            DbUtils.closeQuietly(conn);
	    } 
        return null; 
    }
    
    public Integer getLastId(String tableName){
    	Integer id=null;
    	  Connection conn = getConn(); 
          QueryRunner qr = new QueryRunner(); 
          try { 
        	  id = (Integer) qr.query(conn, "SELECT max(id) from "+tableName, new ScalarHandler(1)); 
        	  return id;
          } catch (SQLException e) { 
                  e.printStackTrace(); 
          }  finally { 
  	            DbUtils.closeQuietly(conn);
  	    } 
          return null; 
    }
    
    public Integer getMaxValue(String sql,Object... params)
    {
    	Integer id=null;
  	  	Connection conn = getConn(); 
        QueryRunner qr = new QueryRunner(); 
        try { 
      	   id = (Integer) qr.query(conn, sql, new ScalarHandler(1),params); 
      	  return id;
        } catch (SQLException e) { 
                e.printStackTrace(); 
        }  finally { 
	            DbUtils.closeQuietly(conn);
	    } 
        return null;
    }
}
