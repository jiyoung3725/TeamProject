package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Spliterator;

public class ConnectionProvider {
	//JDBC ����̹��� �޸𸮷� �ε��ϰ� DB������ ������ Ŀ�ؼ� ��ü�� ��ȯ�ϴ� Ŭ���� �޼��� �����
	public static Connection getConnecton() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","C##yein","yein");
		} catch (Exception e) {System.out.println("getConnection() ���� �߻� : "+e.getMessage());}
		return conn;
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {System.out.println("close() ���� �߻� : "+e.getMessage());}
	}
	public static void close(Statement stmt, Connection conn) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {System.out.println("close() ���� �߻� : "+e.getMessage());}
	}
}
