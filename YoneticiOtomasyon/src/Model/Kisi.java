package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.PartialResultException;
import javax.naming.spi.DirStateFactory.Result;

import Helper.DBConnection;

public class Kisi {

	private int id;
	private int daire_no;
	private String ad;
	private String soyad;
	private String tel_no;
	private int borc;

	DBConnection conn = new DBConnection();

	public Kisi(int id, int daire_no, String ad, String soyad, String tel_no, int borc) {
		super();
		this.id = id;
		this.daire_no = daire_no;
		this.ad = ad;
		this.soyad = soyad;
		this.tel_no = tel_no;
		this.borc = borc;
	}

	public Kisi(int daire_no, String ad, String soyad, String tel_no) {
		super();
		this.daire_no = daire_no;
		this.ad = ad;
		this.soyad = soyad;
		this.tel_no = tel_no;
	}
	

	public Kisi(int daire_no, String ad, String soyad) {
		super();
		this.daire_no = daire_no;
		this.ad = ad;
		this.soyad = soyad;
	
	}

	public Kisi() {

	}

	public boolean kisiyi_ekle(int daire_no, String ad, String soyad, String tel_no) {

		String query = "INSERT INTO kisiler (kisi_daire_no,kisi_ad,kisi_soyad,kisi_tel_no) VALUES (?,?,?,?)";

		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, daire_no);
			preparedStatement.setString(2, ad);
			preparedStatement.setString(3, soyad);
			preparedStatement.setString(4, tel_no);
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

	// Kiþi Ekleme Metodu

	public boolean add_kisi(Kisi kisi) {

		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO kisiler(kisi_daire_no,kisi_ad,kisi_soyad,kisi_tel_no) VALUES (?,?,?,?)";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, kisi.getDaire_no());
			preparedStatement.setString(2, kisi.getAd());
			preparedStatement.setString(3, kisi.getSoyad());
			preparedStatement.setString(4, kisi.getTel_no());
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
			}

		}

		return false;
	}

	// daire no'su verilen kiþiye eriþme metodu

	public Kisi kisi_daire_no(int daire_no) {
		Kisi kisi = null;
		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT * FROM kisiler";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt("kisi_daire_no") == daire_no) {
					kisi = new Kisi(rs.getInt("id"), rs.getInt("kisi_daire_no"), rs.getString("kisi_ad"),
							rs.getString("kisi_soyad"), rs.getString("kisi_tel_no"), rs.getInt("borc"));
				}

			}

			return kisi;
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

	// Parametre olarak verilen kiþiyi id'ye göre siler

	public boolean kisi_deleted(int daire_no) {

		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "DELETE FROM kisiler WHERE kisi_daire_no=" + daire_no;

		try {

			preparedStatement = con.prepareStatement(query);

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

	// id'si verilenin kiþisini dönderen metod

	public Kisi return_kisi_daire_no(int daire_no) {
		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;

		Kisi kisi = new Kisi();
		String query = "SELECT*FROM kisiler";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt("kisi_daire_no") == daire_no) {
					kisi = new Kisi(rs.getInt("id"), rs.getInt("kisi_daire_no"), rs.getString("kisi_ad"),
							rs.getString("kisi_soyad"), rs.getString("kisi_tel_no"), rs.getInt("borc"));
					return kisi;

				}

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

		return null;

	}

	// Tüm kiþileri listeleyen metod

	public ArrayList<Kisi> return_kisiler() {

		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Kisi> list = new ArrayList<>();
		Kisi kisi;
		String query = "SELECT * FROM kisiler";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {

				kisi = new Kisi(rs.getInt("id"), rs.getInt("kisi_daire_no"), rs.getString("kisi_ad"),
						rs.getString("kisi_soyad"), rs.getString("kisi_tel_no"), rs.getInt("borc"));

				list.add(kisi);

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}
	
	//Kisinin toplam borcuna borc ekleyen metod
	
/*	public void toplam_borc_ekle(Kisi kisi , int aidat) {
		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null;

		String query = "UPDATE kisiler SET borc="+"borc+"+aidat+" WHERE id="+kisi.getId();

		try {

			preparedStatement = con.prepareStatement(query);
		
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
			System.out.println("HATAAAAAA");

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
	*/
//borcu eksiltme metodu
	/*public void borc_eksiltme(Kisi kisi ,int dusulecek_borc) {
		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null ;
		
		String query = "UPDATE kisiler SET borc=borc-"+dusulecek_borc+" WHERE id="+kisi.getId();
	               
		
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	*/
	
	
	//Kisi borc update
	public void update_kisi_tum_borc(Kisi kisi) {
		
		Connection con = conn.connDB();
		PreparedStatement preparedStatement = null ;
		
		Borc borc = new Borc();
		int kisinin_tum_borcu = borc.kisi_tum_borclari(kisi);
		
		System.out.println(kisinin_tum_borcu);
		
		
		String query = "UPDATE kisiler SET borc="+kisinin_tum_borcu +" WHERE id ="+kisi.getId();
		
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				preparedStatement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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

	public int getBorc() {
		return borc;
	}

	public void setBorc(int borc) {
		this.borc = borc;
	}

}
