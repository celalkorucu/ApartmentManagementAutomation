package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	Connection con = null;

	public Connection connDB() {

		try {
			this.con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/yonetim?user=root");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return con;

	}

}
