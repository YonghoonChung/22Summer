import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryFunctions {
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	static String str = "";
	static int count = 0;
	static int loginSuccess = 0;
	
	public void select() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. 드라이버 로딩

			String url = "jdbc:mysql://localhost/login";// 2. 연결하기

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("연결 성공");
			String sql = "SELECT username, userid FROM tb_user;";

			rs = stmt.executeQuery(sql);// 5. 쿼리 수행

			while (rs.next()) { // 6. 실행결과 출력하기
				String name = rs.getString(1);
				String userid = rs.getString(2);
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
	public void select(String dC) {//중복확인
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. 드라이버 로딩

			String url = "jdbc:mysql://localhost/login";// 2. 연결하기

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("연결 성공");
			String sql = "select count(*) from tb_user where userid = '"+dC+"';";
			// select count(*) from tb_user where username = 'Yonghoon';

			rs = stmt.executeQuery(sql);// 5. 쿼리 수행
			if(rs.next()) {
				count = rs.getInt(1);
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
	public void select(String userName, String userEmail) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. 드라이버 로딩

			String url = "jdbc:mysql://localhost/login";// 2. 연결하기

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("연결 성공");
			String sql = "SELECT userid, userpw FROM tb_user WHERE username = '"+userName+"' and useremail = '"+userEmail+"';";
			//select userid, userpw from tb_user where username = 'Yonghoon' and useremail = 'skdk@gmail.com';

			rs = stmt.executeQuery(sql);// 5. 쿼리 수행
			if(!rs.next()) {
				str = "";
			}
			else {
				String name = "";
				String userid = "";
				name = rs.getString(1);
				userid = rs.getString(2);
				str = name + " " + userid;
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
	public void selectLogin(String text, String text2) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. 드라이버 로딩

			String url = "jdbc:mysql://localhost/login";// 2. 연결하기

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("연결 성공");
			String sql = "select count(*) from tb_user where userid = '"+text+"' and userpw = '"+text2+"';";

			rs = stmt.executeQuery(sql);// 5. 쿼리 수행
			if(rs.next()) {
				loginSuccess = rs.getInt(1);
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
	public void insertLoginLog(String text) {
		try { // 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/login";// 2. 연결하기
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "INSERT INTO tb_admin (userid)"
					+ " VALUES (?)";// 3. SQL 쿼리 준비
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, text);// 4. 데이터 binding

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
	public void insert(String userid, String userpw, String username, String useremail, String usergender) {
		try { // 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/login";// 2. 연결하기
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "INSERT INTO tb_user (userid, userpw, username, useremail, usergender)"
					+ " VALUES (?,?,?,?,?)";// 3. SQL 쿼리 준비
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userid);// 4. 데이터 binding
			pstmt.setString(2, userpw);
			pstmt.setString(3, username);
			pstmt.setString(4, useremail);
			pstmt.setString(5, usergender);

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
