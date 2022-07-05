import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	static int win;
	static int lose;
	static int total;
	static String str = "";
	static int count = 0;
	static int loginSuccess = 0;

	public void select() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. 드라이버 로딩

			System.out.println("hello");
			String url = "jdbc:mysql://localhost/sixstones";// 2. 연결하기

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("연결 성공");
			String sql = "select count(*) as total, (select count(*) from winLose where winLose = 1) as win,(select count(*) from winLose where winLose = 2) as lose from winLose; ";

			rs = stmt.executeQuery(sql);// 5. 쿼리 수행

			if (rs.next()) {
				total = rs.getInt(1);
				win = rs.getInt(2);
				lose = rs.getInt(3);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void insert(int winLose) {
		try { // 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/sixstones";// 2. 연결하기
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "INSERT INTO  winLose(winLose) VALUES (?)";// 3. SQL 쿼리 준비
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, winLose);// 4. 데이터 binding

			int count = pstmt.executeUpdate();// 5. 쿼리 실행 및 결과 처리
			if (count == 0) {
				System.out.println("데이터 입력 실패");
			} else {
				System.out.println("데이터 입력 성공");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}