package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Odenmeler {

	private int id;
	private int kisi_id;
	private int kisi_daire_no;
	private int borc_id;
	private String kisi_ad;
	private String kisi_soyad;
	private String guncel_tarih;
	private String borc_ay;
	private String borc_yil;

	private int aidat;

	DBConnection conn = new DBConnection();

	public Odenmeler(int id, int borc_id, int kisi_id, int kisi_daire_no, String kisi_ad, String kisi_soyad,
			String guncel_tarih, String borc_ay, String borc_yil, int aidat) {
		super();
		this.id = id;
		this.kisi_id = kisi_id;
		this.kisi_daire_no = kisi_daire_no;
		this.kisi_ad = kisi_ad;
		this.kisi_soyad = kisi_soyad;
		this.guncel_tarih = guncel_tarih;
		this.borc_ay = borc_ay;
		this.borc_yil = borc_yil;
		this.aidat = aidat;
		this.borc_id = borc_id;
	}

	public int getBorc_id() {
		return borc_id;
	}

	public void setBorc_id(int borc_id) {
		this.borc_id = borc_id;
	}

	public Odenmeler() {

	}

	// Odenmeler Borc ekle
	public boolean odenme_ekle(Borc borc, String tarih) {

		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO odenmeler (borc_id,kisi_id,kisi_daire_no,kisi_ad,kisi_soyad,guncel_tarih,borc_ay,borc_yil,aidat)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			System.out.println("metod" + borc.getKisi_ad());
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, borc.getId());
			preparedStatement.setInt(2, borc.getKisi_id());
			preparedStatement.setInt(3, borc.getKisi_daire_no());
			preparedStatement.setString(4, borc.getKisi_ad());
			preparedStatement.setString(5, borc.getKisi_soyad());
			preparedStatement.setString(6, tarih);
			preparedStatement.setString(7, borc.getBorc_ay());
			preparedStatement.setString(8, borc.getBorc_yil());
			preparedStatement.setInt(9, borc.getBorc_aidat());

			preparedStatement.executeUpdate();

			return true;

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
		return false;

	}

	// Odenen borclar Liste
	public ArrayList<Odenmeler> odenmeler_list() {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Odenmeler> list = new ArrayList<>();
		String query = "SELECT* FROM odenmeler";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				Odenmeler odenmeler = new Odenmeler(rs.getInt("id"), rs.getInt("borc_id"), rs.getInt("kisi_id"),
						rs.getInt("kisi_daire_no"), rs.getString("kisi_ad"), rs.getString("kisi_soyad"),
						rs.getString("guncel_tarih"), rs.getString("borc_ay"), rs.getString("borc_yil"),
						rs.getInt("aidat"));

				list.add(odenmeler);
			}

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKisi_id() {
		return kisi_id;
	}

	public void setKisi_id(int kisi_id) {
		this.kisi_id = kisi_id;
	}

	public int getKisi_daire_no() {
		return kisi_daire_no;
	}

	public void setKisi_daire_no(int kisi_daire_no) {
		this.kisi_daire_no = kisi_daire_no;
	}

	public String getKisi_ad() {
		return kisi_ad;
	}

	public void setKisi_ad(String kisi_ad) {
		this.kisi_ad = kisi_ad;
	}

	public String getKisi_soyad() {
		return kisi_soyad;
	}

	public void setKisi_soyad(String kisi_soyad) {
		this.kisi_soyad = kisi_soyad;
	}

	public String getGuncel_tarih() {
		return guncel_tarih;
	}

	public void setGuncel_tarih(String guncel_tarih) {
		this.guncel_tarih = guncel_tarih;
	}

	public String getBorc_ay() {
		return borc_ay;
	}

	public void setBorc_ay(String borc_ay) {
		this.borc_ay = borc_ay;
	}

	public String getBorc_yil() {
		return borc_yil;
	}

	public void setBorc_yil(String borc_yil) {
		this.borc_yil = borc_yil;
	}

	public int getAidat() {
		return aidat;
	}

	public void setAidat(int aidat) {
		this.aidat = aidat;
	}
}
