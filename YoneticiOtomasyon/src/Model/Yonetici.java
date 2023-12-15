package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.DBConnection;

public class Yonetici {

	private int id;
	private String ad;
	private String soyad;
	private String tel_no;
	private String sifre;
	private int alacak_borc;
	private int aidat;

	DBConnection conn = new DBConnection();

	public Yonetici(int id, String ad, String soyad, String tel_no, String sifre, int alacak_borc, int aidat) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.tel_no = tel_no;
		this.sifre = sifre;
		this.alacak_borc = alacak_borc;
		this.aidat = aidat;
	}

	public Yonetici() {

	}

	public Yonetici dogrula(String sifre) {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT*FROM yonetici";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getString("yonetici_sifre").equals(sifre)) {
					Yonetici yonetici = new Yonetici(rs.getInt("id"), rs.getString("yonetici_ad"),
							rs.getString("yonetici_soyad"), rs.getString("yonetici_tel_no"),
							rs.getString("yonetici_sifre"), rs.getInt("yonetici_alacak"), rs.getInt("aylik_aidat"));

					return yonetici;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
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

	// Mevcut Aidatý Dönderen Metod
	public int aidat_mevcut() {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT*FROM yonetici";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {

				return rs.getInt("aylik_aidat");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return 0;
	}

	// Aidat Güncelleyen Metod

	public boolean aidat_guncel(int guncel_aidat) {

		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "UPDATE yonetici SET aylik_aidat=?";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, guncel_aidat);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
				preparedStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					con.close();
					preparedStatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}

			}

		}

		return false;

	}

	// Güncel aidatý dondren metod

	public int return_aidat() {
		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT*FROM yonetici";
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {

				return rs.getInt("aylik_aidat");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			} 

		}

		return 0;

	}

	// Alýnacak Borc Ekleme

	public void alinacak_borc_ekle() {
		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "UPDATE yonetici SET yonetici_alacak=" + "yonetici_alacak+" +return_aidat();

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
				preparedStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}
	
	public void alinacak_borc_cikar(int aidat) {
		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "UPDATE yonetici SET yonetici_alacak=" + "yonetici_alacak-" +aidat;

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
				preparedStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	// Yonetici alacaðý donderen metod

	public int yonetici_alacak_borc() {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT*FROM yonetici";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			int alacak = 0;
			while (rs.next()) {
				alacak = rs.getInt("yonetici_alacak");
			}

			return alacak;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getTel_no() {
		return tel_no;
	}

	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public int getAlacak_borc() {
		return alacak_borc;
	}

	public void setAlacak_borc(int alacak_borc) {
		this.alacak_borc = alacak_borc;
	}

	public int getAidat() {
		return aidat;
	}

	public void setAidat(int aidat) {
		this.aidat = aidat;
	}

}
