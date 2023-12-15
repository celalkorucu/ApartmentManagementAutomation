package Model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import Helper.DBConnection;

public class Borc {

	private int id;
	private int kisi_id;
	private int kisi_daire_no;
	private String kisi_ad;
	private String kisi_soyad;
	private String borc_ay;
	private String borc_yil;
	private int borc_aidat;
	private String tip;

	DBConnection conn = new DBConnection();

	Kisi kisi = new Kisi();

	public Borc(int id, int kisi_id, int kisi_daire_no, String kisi_ad, String kisi_soyad, String borc_ay,
			String borc_yil, int borc_aidat, String tip) {
		super();
		this.id = id;
		this.kisi_id = kisi_id;
		this.kisi_daire_no = kisi_daire_no;
		this.kisi_ad = kisi_ad;
		this.kisi_soyad = kisi_soyad;
		this.borc_ay = borc_ay;
		this.borc_yil = borc_yil;
		this.borc_aidat = borc_aidat;
		this.tip = tip;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Borc() {

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * O ay için borç eklenmemiþ kiþileri listeleyen metod
	 */

	public ArrayList<Kisi> borc_eklenecek_liste() {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;
		Connection con2 = conn.connDB();
		Statement s = null;
		ResultSet r = null;

		ArrayList<Kisi> list = new ArrayList<>();

		String query_1 = "SELECT * FROM kisiler";

		ZonedDateTime time = ZonedDateTime.now();
		try {
			st = con.createStatement();
			rs = st.executeQuery(query_1);

			while (rs.next()) {
				int id = rs.getInt("id");

				String query_2 = "SELECT * FROM borclar WHERE kisi_id=" + id;

				// SELECT * FROM borclar WHERE (kisi_id=98 AND borc_ay=2 AND borc_yil=2023)
				s = con2.createStatement();
				r = s.executeQuery(query_2);
				boolean control = true;
				while (r.next()) {

					// --------------------------------------------------------------------

					if (r.getInt("borc_ay") == time.getMonthValue() && r.getInt("borc_yil") == time.getYear()) {

						control = false;
					}

					// ---------------------------------------------------------------------
				}
				if (control == true) {

					Kisi kisi = new Kisi(rs.getInt("id"), rs.getInt("kisi_daire_no"), rs.getString("kisi_ad"),
							rs.getString("kisi_soyad"), rs.getString("kisi_tel_no"), rs.getInt("borc"));
					list.add(kisi);
				}

			}

			return list;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con2.close();
				con.close();
				st.close();
				rs.close();
				r.close();
				s.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return null;

	}

	// Borç Ekle Metod

	public boolean borc_ekle(Kisi kisi, int ay, int yil, int guncel_aidat) {

		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO borclar (kisi_id,kisi_daire_no,kisi_ad,kisi_soyad,borc_ay,borc_yil,borc_aidat) VALUES (?,?,?,?,?,?,?)";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, kisi.getId());
			preparedStatement.setInt(2, kisi.getDaire_no());
			preparedStatement.setString(3, kisi.getAd());
			preparedStatement.setString(4, kisi.getSoyad());
			preparedStatement.setInt(5, ay);
			preparedStatement.setInt(6, yil);
			preparedStatement.setInt(7, guncel_aidat);

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
			} finally {
				try {
					con.close();
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return false;

	}

	// Seçilen kiþinin ödenmemiþ borçlar listesi

	public ArrayList<Borc> odenmemis_borc_liste(int daire_no, String kisi_ad, String kisi_soyad) {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		ArrayList<Borc> list = new ArrayList<>();

		String query = "SELECT*FROM borclar";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {

				if (rs.getInt("kisi_daire_no") == daire_no && rs.getString("kisi_ad").equals(kisi_ad)
						&& rs.getString("kisi_soyad").equals(kisi_soyad) && rs.getString("tip").equals("a")) {

					Borc borc = new Borc(rs.getInt("id"), rs.getInt("kisi_id"), rs.getInt("kisi_daire_no"),
							rs.getString("kisi_ad"), rs.getString("kisi_soyad"), rs.getString("borc_ay"),
							rs.getString("borc_yil"), rs.getInt("borc_aidat"), rs.getString("tip"));

					list.add(borc);
				}

			}

			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

		return null;
	}

	// Odenmemiþ Borçlar
	public ArrayList<Borc> odenmemis_borc_list() {
		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Borc> list = new ArrayList<>();
		Borc borc;
		String query = "SELECT*FROM borclar WHERE tip=" + "'a'";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {

				borc = new Borc(rs.getInt("id"), rs.getInt("kisi_id"), rs.getInt("kisi_daire_no"),
						rs.getString("kisi_ad"), rs.getString("kisi_soyad"), rs.getString("borc_ay"),
						rs.getString("borc_yil"), rs.getInt("borc_aidat"), rs.getString("tip"));

				list.add(borc);
			}
			return list;
		}

		catch (SQLException e) {

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

	// Parametre olarak verilen ay,yýl,daire ya bakarak borcun id sini donderen
	// metod

	public int return_borc_id(int daire_no, String ay, String yil) {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT*FROM borclar";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			int id = 0;

			while (rs.next()) {
				if (rs.getInt("kisi_daire_no") == daire_no && rs.getString("borc_ay").equals(ay)
						&& rs.getString("borc_yil").equals(yil)) {

					id = rs.getInt("id");

				}
			}
			return id;
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

	// Verilen id'ye göre borcun tipini 'p' yapan metod

	public boolean update_borc_tip_p(int id) {
		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "UPDATE borclar SET tip='p' WHERE id=" + id;

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				preparedStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return false;

	}

	// Tipi 'p' olan borçlarý listeleyen metod

	public ArrayList<Borc> odenmis_borc_list() {
		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		Borc borc;
		ArrayList<Borc> list = new ArrayList<>();

		String query = "SELECT*FROM borclar";

		try {

			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {

				if (rs.getString("tip").equals("p")) {

					borc = new Borc(rs.getInt("id"), rs.getInt("kisi_id"), rs.getInt("kisi_daire_no"),
							rs.getString("kisi_ad"), rs.getString("kisi_soyad"), rs.getString("borc_ay"),
							rs.getString("borc_yil"), rs.getInt("borc_aidat"), rs.getString("tip"));

					list.add(borc);
				}
			}
			return list;
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

		return null;

	}

	// Verilen id'ye göre Borç döndüren metod

	public Borc return_borc(int id) {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT*FROM borclar";
		Borc borc = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt("id") == id) {

					borc = new Borc(rs.getInt("id"), rs.getInt("kisi_id"), rs.getInt("kisi_daire_no"),
							rs.getString("kisi_ad"), rs.getString("kisi_soyad"), rs.getString("borc_ay"),
							rs.getString("borc_yil"), rs.getInt("borc_aidat"), rs.getString("tip"));

				}
			}
			return borc;

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
	
	//Girilen kisinin tüm borçlar toplamý
	public int kisi_tum_borclari(Kisi kisi) {
		
		Connection con = conn.connDB();
		Statement st = null ;
		ResultSet rs = null ;
		
		String query = "SELECT*FROM borclar WHERE tip='a'";
		
		try {
			
			int toplam_borc = 0 ; 
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				if(rs.getInt("kisi_id") == kisi.getId()) {
					toplam_borc = toplam_borc+rs.getInt("borc_aidat");
				}
			}
			return toplam_borc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return 0 ; 
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

	public int getBorc_aidat() {
		return borc_aidat;
	}

	public void setBorc_aidat(int borc_aidat) {
		this.borc_aidat = borc_aidat;
	}

}
