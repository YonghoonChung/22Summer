import java.lang.reflect.Array;
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
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. ����̹� �ε�

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("���� ����");
			String sql = "SELECT username, userid FROM tb_user;";

			rs = stmt.executeQuery(sql);// 5. ���� ����

			while (rs.next()) { // 6. ������ ����ϱ�
				String name = rs.getString(1);
				String userid = rs.getString(2);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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

	public void select(String dC) {// �ߺ�Ȯ��
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. ����̹� �ε�

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("���� ����");
			String sql = "select count(*) from tb_user where userid = '" + dC + "';";
			// select count(*) from tb_user where username = 'Yonghoon';

			rs = stmt.executeQuery(sql);// 5. ���� ����
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. ����̹� �ε�

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("���� ����");
			String sql = "SELECT userid, userpw FROM tb_user WHERE username = '" + userName + "' and useremail = '"
					+ userEmail + "';";
			// select userid, userpw from tb_user where username = 'Yonghoon' and useremail
			// = 'skdk@gmail.com';

			rs = stmt.executeQuery(sql);// 5. ���� ����
			if (!rs.next()) {
				str = "";
			} else {
				String name = "";
				String userid = "";
				name = rs.getString(1);
				userid = rs.getString(2);
				str = name + " " + userid;
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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

	public String[] selectLogin(String text, String text2) {
		String arr[] = new String[4];
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. ����̹� �ε�

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("���� ����");
			String sql = "select username,usergender,useremail,userid from tb_user where userid = '" + text
					+ "' and userpw = '" + text2 + "';";

			rs = stmt.executeQuery(sql);// 5. ���� ����
			if (rs.next()) {
				loginSuccess = 1;
				arr[0] = rs.getString(1);
				arr[1] = rs.getString(2);
				arr[2] = rs.getString(3);
				arr[3] = rs.getString(4);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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
		return arr;
	}
	public int selectLoginLog() {
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. ����̹� �ε�

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("���� ����");
			String sql = "select count(*) from tb_admin where DATE(regidate) = CURDATE();";

			rs = stmt.executeQuery(sql);// 5. ���� ����
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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
		return result;
	}
	public int countUsers() {
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. ����̹� �ε�

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("���� ����");
			String sql = "select distinct count(*) from tb_user;";

			rs = stmt.executeQuery(sql);// 5. ���� ����
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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
		return result;
	}

	public void insertLoginLog(String text) {
		try { // 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "INSERT INTO tb_admin (userid)" + " VALUES (?)";// 3. SQL ���� �غ�
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, text);// 4. ������ binding

			int count = pstmt.executeUpdate();// 5. ���� ���� �� ��� ó��
			if (count == 0) {
				System.out.println("������ �Է� ����");
			} else {
				System.out.println("������ �Է� ����");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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
		try { // 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "INSERT INTO tb_user (userid, userpw, username, useremail, usergender)"
					+ " VALUES (?,?,?,?,?)";// 3. SQL ���� �غ�
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userid);// 4. ������ binding
			pstmt.setString(2, userpw);
			pstmt.setString(3, username);
			pstmt.setString(4, useremail);
			pstmt.setString(5, usergender);

			int count = pstmt.executeUpdate();// 5. ���� ���� �� ��� ó��
			if (count == 0) {
				System.out.println("������ �Է� ����");
			} else {
				System.out.println("������ �Է� ����");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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

	public void update(String userid, String userpw) {
		try { // 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "update tb_user SET userpw ='"+userpw+"' where userid = '"+userid+"' ;";																																	// �غ�
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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
	public void delete(String userid) {
		try { // 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "DELETE from tb_user where userid = '"+userid+"';";																																	// �غ�
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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

	public void truncate() {
		try { // 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "truncate car;";																																	// �غ�
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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

	public void truncate(String string) {
		try { // 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/login";// 2. �����ϱ�
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "truncate "+string+";";																																	// �غ�
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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