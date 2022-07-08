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
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. ����̹� �ε�

			System.out.println("hello");
			String url = "jdbc:mysql://localhost/sixstones";// 2. �����ϱ�

			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			System.out.println("연결 성공");
			String sql = "select count(*) as total, (select count(*) from winLose where winLose = 1) as win,(select count(*) from winLose where winLose = 2) as lose from winLose; ";

			rs = stmt.executeQuery(sql);// 5. ���� ����

			if (rs.next()) {
				total = rs.getInt(1);
				win = rs.getInt(2);
				lose = rs.getInt(3);
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

	public void insert(int winLose) {
		try { // 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/sixstones";// 2. �����ϱ�
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "INSERT INTO  winLose(winLose) VALUES (?)";// 3. SQL ���� �غ�
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, winLose);// 4. ������ binding

			int count = pstmt.executeUpdate();// 5. ���� ���� �� ��� ó��
			if (count == 0) {
//				System.out.println("������ �Է� ����");
			} else {
//				System.out.println("������ �Է� ����");
			}
		} catch (ClassNotFoundException e) {
//			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
//			System.out.println("���� " + e);
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