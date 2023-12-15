package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Daire {

	private int id;
	private int daire_no;
	private String tip;

	DBConnection conn = new DBConnection();

	public Daire(int id, int daire_no, String tip) {
		super();
		this.id = id;
		this.daire_no = daire_no;
		this.tip = tip;
	}

	public Daire() {

	}

	public Daire(int daire_no) {
		super();
		this.daire_no = daire_no;
	}

	// Bu metod dairelerin tip'i "bos" olanlarý listeleyen bir metod

	public ArrayList<Daire> bos_daire_liste() {
		ArrayList<Daire> list = new ArrayList<>();
		Connection con = conn.connDB();
		ResultSet rs = null;
		Statement st = null;

		String query = "SELECT * FROM daireno";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getString("daire_tip").equals("bos")) {

					Daire daire = new Daire(rs.getInt("id"), rs.getInt("daire_no"), rs.getString("daire_tip"));
					list.add(daire);
				}
			}
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return null;

	}

	// Bu metod dairelerin tip'i "dolu" olanlarý listeleyen bir metod

	public ArrayList<Daire> dolu_daire_liste() {

		ArrayList<Daire> list = new ArrayList<>();
		Connection con = conn.connDB();
		ResultSet rs = null;
		Statement st = null;

		String query = "SELECT * FROM daireno";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getString("daire_tip").equals("dolu")) {

					Daire daire = new Daire(rs.getInt("id"), rs.getInt("daire_no"), rs.getString("daire_tip"));
					list.add(daire);
				}
			}
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return null;

	}

	// Bu Metod girilen daire no'yu 'dolu' yapar

	public void update_daire_tip_dolu(Daire daire) {

		Connection con = conn.connDB();

		PreparedStatement preparedStatement = null;

		String query = "UPDATE daireno SET daire_tip=? WHERE daire_no=?";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "dolu");
			preparedStatement.setInt(2, daire.getDaire_no());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			try {
				con.close();
				preparedStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	public void update_daire_tip_bos(Daire daire) {

		Connection con = conn.connDB();

		PreparedStatement preparedStatement = null;

		String query = "UPDATE daireno SET daire_tip=? WHERE daire_no=?";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "bos");
			preparedStatement.setInt(2, daire.getDaire_no());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			try {
				con.close();
				preparedStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDaire_no() {
		return daire_no;
	}

	public void setDaire_no(int daire_no) {
		this.daire_no = daire_no;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
