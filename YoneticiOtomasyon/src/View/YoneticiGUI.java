package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Model.Borc;
import Model.Kisi;
import Model.Yonetici;

import javax.swing.JLabel;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Helper.Mesaj;

import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Font;

public class YoneticiGUI extends JFrame {

	private JPanel contentPane;
	static Yonetici yonetici;
	private JTable table_tum_sakinler;
	Mesaj mesaj = new Mesaj();
	DefaultTableModel kisiler_model = new DefaultTableModel();

	Object[] kisi_data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YoneticiGUI frame = new YoneticiGUI(yonetici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public YoneticiGUI(Yonetici yonetici) {
		setResizable(false);
		setTitle("YONET\u0130C\u0130 OTOMASYON");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		kisiler_model = new DefaultTableModel();
		Object col_kisi[] = new Object[5];
		col_kisi[0] = "D.No";
		col_kisi[1] = "Ad";
		col_kisi[2] = "Soyad";
		col_kisi[3] = "Tel. No";
		col_kisi[4] = "Borç";

		kisiler_model.setColumnIdentifiers(col_kisi);

		kisi_data = new Object[5];

		Kisi kisi = new Kisi();
		Borc borc = new Borc();
		if (kisi.return_kisiler().size() != 0) {
			for (int i = 0; i < kisi.return_kisiler().size(); i++) {
				kisi_data[0] = kisi.return_kisiler().get(i).getDaire_no();
				kisi_data[1] = kisi.return_kisiler().get(i).getAd();
				kisi_data[2] = kisi.return_kisiler().get(i).getSoyad();
				kisi_data[3] = kisi.return_kisiler().get(i).getTel_no();
				kisi_data[4] = borc.kisi_tum_borclari(kisi.return_kisiler().get(i));

				kisiler_model.addRow(kisi_data);

			}
		}

		JButton btn_geri = new JButton(new ImageIcon("Yonetim Geri.png"));
		btn_geri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int control = mesaj.soru_mesaj("emin");
				if (control == 0) {
					GirisGUI gGUI = new GirisGUI();
					gGUI.setVisible(true);
					dispose();
				}
			}
		});
		btn_geri.setBounds(40, 11, 40, 40);
		contentPane.add(btn_geri);

		JButton btn_borc_ekle = new JButton(new ImageIcon("Yonetim Borc2 (2).png"));
		btn_borc_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borc borc = new Borc();

				if (borc.borc_eklenecek_liste().size() != 0) {
					BorcEkleGUI beGUI = new BorcEkleGUI(yonetici);
					beGUI.setVisible(true);
					dispose();
				} else {
					mesaj.normal_mesaj("Bu ay için herkese borç yazýlmýþtýr !!");
				}
			}
		});
		btn_borc_ekle.setBackground(Color.BLACK);
		btn_borc_ekle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_borc_ekle.setBounds(40, 120, 225, 100);
		contentPane.add(btn_borc_ekle);

		JButton btn_borc_ode = new JButton(new ImageIcon("Yonetim Borc.png"));
		btn_borc_ode.setBackground(Color.BLACK);
		btn_borc_ode.setForeground(Color.BLACK);
		btn_borc_ode.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_borc_ode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borc borc = new Borc();
				if (borc.odenmemis_borc_list().size() != 0) {
					BorcOdeGUI boGUI = new BorcOdeGUI(yonetici);
					boGUI.setVisible(true);
					dispose();
				} else {
					mesaj.normal_mesaj("Borcu olan kimse yoktur");
				}
			}
		});
		btn_borc_ode.setBounds(40, 250, 225, 100);
		contentPane.add(btn_borc_ode);

		JButton btn_aidat = new JButton(new ImageIcon("Yonetim Aidat.png"));
		btn_aidat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borc borc = new Borc();

			

				if (borc.borc_eklenecek_liste().size() > 0) {
					int control = mesaj.soru_mesaj(
							"Borç eklenecek kýsmýnda bu ay için henüz\nborç eklenmemiþ kiþiler var yine de\ndevam etmek istiyormusunuz ?\nAidat deðiþikliði yaptýðýnýz takdirde\nyeni miktara göre borç eklemesi yapabilirsiniz !");
				
					if(control == 0) {
						AidatGuncelleGUI aGUI = new AidatGuncelleGUI(yonetici);
						aGUI.setVisible(true);
						dispose();
					}
				
				}
				
				else {
					
					AidatGuncelleGUI aGUI = new AidatGuncelleGUI(yonetici);
					aGUI.setVisible(true);
					dispose();
					
				}
			}
		});
		btn_aidat.setBackground(Color.BLACK);
		btn_aidat.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_aidat.setBounds(40, 380, 225, 100);
		contentPane.add(btn_aidat);

		JButton btn_odeme_gecmisi = new JButton(new ImageIcon("Yonetim Odeme Gecmis.png"));
		btn_odeme_gecmisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				OdenmelerGUI oGUI = new OdenmelerGUI(yonetici);
				oGUI.setVisible(true);
				dispose();
			}
		});
		btn_odeme_gecmisi.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_odeme_gecmisi.setBounds(620, 120, 225, 100);
		contentPane.add(btn_odeme_gecmisi);

		JButton btn_kisi_ekle = new JButton(new ImageIcon("Yonetim Ekle.png"));
		btn_kisi_ekle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_kisi_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EkleGUI eGUI = new EkleGUI(yonetici);
				eGUI.setVisible(true);
				dispose();
			}
		});
		btn_kisi_ekle.setBounds(620, 250, 225, 100);
		contentPane.add(btn_kisi_ekle);

		JButton btn_tum_borclar = new JButton(new ImageIcon("Yonetim Tum Borc.png"));
		btn_tum_borclar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Borc borc = new Borc();
				if (borc.odenmemis_borc_list().size() != 0) {
					TumBorclarGUI tGUI = new TumBorclarGUI(yonetici);
					tGUI.setVisible(true);
					dispose();
				} else {
					mesaj.normal_mesaj("Borcu olan kimse yoktur");
				}
			}
		});
		btn_tum_borclar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_tum_borclar.setBounds(620, 380, 225, 100);
		contentPane.add(btn_tum_borclar);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setForeground(Color.WHITE);
		separator.setBounds(10, 100, 864, 10);
		contentPane.add(separator);

		int scroll_uzunluk = kisi.return_kisiler().size() * 50 + 23;
		if (scroll_uzunluk > 360) {
			scroll_uzunluk = 360;
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 120, 335, scroll_uzunluk);
		contentPane.add(scrollPane);

		table_tum_sakinler = new JTable(kisiler_model);
		table_tum_sakinler.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		table_tum_sakinler.setEnabled(false);
		table_tum_sakinler.setBackground(Color.WHITE);
		scrollPane.setViewportView(table_tum_sakinler);

		JTableHeader header = table_tum_sakinler.getTableHeader();

		header.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		table_tum_sakinler.setRowHeight(50);
		table_tum_sakinler.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_tum_sakinler.getColumnModel().getColumn(1).setPreferredWidth(70);
		table_tum_sakinler.getColumnModel().getColumn(2).setPreferredWidth(70);
		table_tum_sakinler.getColumnModel().getColumn(3).setPreferredWidth(110);
		table_tum_sakinler.getColumnModel().getColumn(4).setPreferredWidth(80);

		DefaultTableCellRenderer center_renderer = new DefaultTableCellRenderer();
		center_renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table_tum_sakinler.getColumnModel().getColumn(0).setCellRenderer(center_renderer);
		table_tum_sakinler.getColumnModel().getColumn(1).setCellRenderer(center_renderer);
		table_tum_sakinler.getColumnModel().getColumn(2).setCellRenderer(center_renderer);
		table_tum_sakinler.getColumnModel().getColumn(3).setCellRenderer(center_renderer);
		table_tum_sakinler.getColumnModel().getColumn(4).setCellRenderer(center_renderer);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.RED);
		separator_1.setBounds(10, 525, 864, 10);
		contentPane.add(separator_1);

		JLabel lblNewLabel = new JLabel(new ImageIcon());
		lblNewLabel.setBounds(42, 120, 46, 100);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Güncel Aidat : " + yonetici.aidat_mevcut() + " TL");
		lblNewLabel_1.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(645, 11, 200, 40);
		contentPane.add(lblNewLabel_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(620, 49, 225, 2);
		contentPane.add(separator_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(620, 11, 225, 2);
		contentPane.add(separator_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("Güncel Alacak : " + yonetici.yonetici_alacak_borc() + " TL");
		lblNewLabel_1_1.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1_1.setBounds(645, 49, 200, 40);
		contentPane.add(lblNewLabel_1_1);

		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setBounds(620, 87, 225, 2);
		contentPane.add(separator_2_2);
	}
}
