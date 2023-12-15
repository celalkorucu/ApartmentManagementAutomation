package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Helper.Mesaj;
import Model.Borc;
import Model.Kisi;
import Model.Odenmeler;
import Model.Yonetici;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.ZonedDateTime;

import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorcOdeGUI extends JFrame {

	private JPanel contentPane;
	private JTable table_borclar;

	Mesaj mesaj = new Mesaj();

	DefaultTableModel borclar_model = new DefaultTableModel();
	Object borc_data[];

	Borc borc = new Borc();
	static Yonetici yonetici = new Yonetici();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorcOdeGUI frame = new BorcOdeGUI(yonetici);
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
	public BorcOdeGUI(Yonetici yonetici) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Object[] col_borc = new Object[6];
		col_borc[0] = "D.N";
		col_borc[1] = "Ad";
		col_borc[2] = "Soyad";
		col_borc[3] = "Ay";
		col_borc[4] = "Yýl";
		col_borc[5] = "Aidat";
		borclar_model.setColumnIdentifiers(col_borc);

		borc_data = new Object[6];
		if (borc.odenmemis_borc_list().size() != 0) {
			for (int i = 0; i < borc.odenmemis_borc_list().size(); i++) {

				borc_data[0] = borc.odenmemis_borc_list().get(i).getKisi_daire_no();
				borc_data[1] = borc.odenmemis_borc_list().get(i).getKisi_ad();
				borc_data[2] = borc.odenmemis_borc_list().get(i).getKisi_soyad();
				borc_data[3] = borc.odenmemis_borc_list().get(i).getBorc_ay();
				borc_data[3] = borc.odenmemis_borc_list().get(i).getBorc_ay();
				borc_data[4] = borc.odenmemis_borc_list().get(i).getBorc_yil();
				borc_data[5] = borc.odenmemis_borc_list().get(i).getBorc_aidat();

				borclar_model.addRow(borc_data);
			}
		}

		int scroll_uzunluk = borc.odenmemis_borc_list().size() * 40 + 25;
		if (scroll_uzunluk > 365) {
			scroll_uzunluk = 365;
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 130, 700, scroll_uzunluk);
		contentPane.add(scrollPane);

		table_borclar = new JTable(borclar_model);
		table_borclar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_borclar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));

		JTableHeader header = table_borclar.getTableHeader();
		header.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		scrollPane.setViewportView(table_borclar);

		table_borclar.setRowHeight(40);
		table_borclar.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_borclar.getColumnModel().getColumn(1).setPreferredWidth(200);
		table_borclar.getColumnModel().getColumn(2).setPreferredWidth(200);
		table_borclar.getColumnModel().getColumn(3).setPreferredWidth(50);
		table_borclar.getColumnModel().getColumn(4).setPreferredWidth(100);
		table_borclar.getColumnModel().getColumn(5).setPreferredWidth(100);

		DefaultTableCellRenderer center_renderer = new DefaultTableCellRenderer();
		center_renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table_borclar.getColumnModel().getColumn(0).setCellRenderer(center_renderer);
		table_borclar.getColumnModel().getColumn(1).setCellRenderer(center_renderer);
		table_borclar.getColumnModel().getColumn(2).setCellRenderer(center_renderer);
		table_borclar.getColumnModel().getColumn(3).setCellRenderer(center_renderer);
		table_borclar.getColumnModel().getColumn(4).setCellRenderer(center_renderer);
		table_borclar.getColumnModel().getColumn(5).setCellRenderer(center_renderer);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 864, 2);
		contentPane.add(separator);

		JLabel lblNewLabel_4 = new JLabel("");
		ZonedDateTime time = ZonedDateTime.now();

		if (time.getHour() < 10 && time.getMinute() < 10) {
			lblNewLabel_4.setText("0" + time.getHour() + ":0" + time.getMinute());
		} else if (time.getHour() >= 10 && time.getMinute() < 10) {
			lblNewLabel_4.setText(time.getHour() + ":0" + time.getMinute());
		} else {
			lblNewLabel_4.setText(time.getHour() + ":" + time.getMinute());
		}

		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(784, 11, 80, 40);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(new ImageIcon("Yonetim Saat.png"));
		lblNewLabel_5.setBounds(729, 19, 25, 25);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel(new ImageIcon("Yonetim Tarih.png"));
		lblNewLabel_5_1.setBounds(729, 70, 25, 25);
		contentPane.add(lblNewLabel_5_1);

		JLabel lblNewLabel_3 = new JLabel("");
		if (time.getDayOfMonth() < 10 && time.getMonthValue() < 10) {
			lblNewLabel_3.setText("0" + time.getDayOfMonth() + ".0" + time.getMonthValue() + "." + time.getYear());
		} else if (time.getDayOfMonth() >= 10 && time.getMonthValue() < 10) {
			lblNewLabel_3.setText(time.getDayOfMonth() + ".0" + time.getMonthValue() + "." + time.getYear());
		} else {
			lblNewLabel_3.setText(time.getDayOfMonth() + "." + time.getMonthValue() + "." + time.getYear());
		}

		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(774, 62, 100, 40);
		contentPane.add(lblNewLabel_3);

		JButton btn_borc_ode = new JButton("\u00D6de");
		btn_borc_ode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table_borclar.getSelectedRow();

				System.out.println("Satýr : " + row);
				if (row >= 0) {

					int daire_no = (Integer) table_borclar.getValueAt(row, 0);

					String ay = (String) table_borclar.getValueAt(row, 3);

					String yil = (String) table_borclar.getValueAt(row, 4);

					String ad = (String) table_borclar.getValueAt(row, 1);

					String soyad = (String) table_borclar.getValueAt(row, 2);

					int aidat = (Integer) table_borclar.getValueAt(row, 5);

					int borc_id = borc.return_borc_id(daire_no, ay, yil);

					if (mesaj.soru_mesaj(ad + " " + soyad + " adlý kiþinin " + yil + " yýlýnýn " + ay
							+ ". ayýndaki borcunu (" + aidat + ") ödemek istediðinize emin misiniz ?") == 0) {

						boolean control = borc.update_borc_tip_p(borc_id);
						update_borc_model();
						int scroll_uzunluk = borc.odenmemis_borc_list().size() * 40 + 25;
						if (scroll_uzunluk > 365) {
							scroll_uzunluk = 365;
						}
						scrollPane.setBounds(40, 130, 700, scroll_uzunluk);
						if (control) {
							mesaj.normal_mesaj("baþarýlý");

							// Zamaný odenmeler GUI ye veren metod
							ZonedDateTime time = ZonedDateTime.now();
							String tarih = time.getHour() + ":" + time.getMinute() + " - " + time.getDayOfMonth() + "."
									+ time.getMonthValue() + "." + time.getYear();

							Odenmeler odenme = new Odenmeler();

							odenme.odenme_ekle(borc.return_borc(borc_id), tarih);

							// Kiþinin borcundan düþecek metod

						
							
							yonetici.alinacak_borc_cikar(aidat);

						} else {
							mesaj.normal_mesaj("hata");
						}
					}

				}

			}
		});
		btn_borc_ode.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_borc_ode.setBounds(750, 127, 124, 40);
		contentPane.add(btn_borc_ode);

		JButton btn_geri = new JButton(new ImageIcon("Yonetim Geri.png"));
		btn_geri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				YoneticiGUI yGUI = new YoneticiGUI(yonetici);
				yGUI.setVisible(true);
				dispose();
			}
		});
		btn_geri.setBounds(40, 11, 40, 40);
		contentPane.add(btn_geri);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 525, 864, 2);
		contentPane.add(separator_1);

		JLabel lblBordemekIin = new JLabel(
				"Bor\u00E7 \u00F6demek i\u00E7in listeden borcu  se\u00E7ip '\u00F6de' butonuna bas\u0131n\u0131z ");
		lblBordemekIin.setHorizontalAlignment(SwingConstants.CENTER);
		lblBordemekIin.setForeground(Color.GRAY);
		lblBordemekIin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblBordemekIin.setBounds(90, 11, 714, 40);
		contentPane.add(lblBordemekIin);

	}

	public void update_borc_model() {

		DefaultTableModel clear = (DefaultTableModel) table_borclar.getModel();
		clear.setRowCount(0);

		if (borc.odenmemis_borc_list().size() != 0) {
			for (int i = 0; i < borc.odenmemis_borc_list().size(); i++) {

				borc_data[0] = borc.odenmemis_borc_list().get(i).getKisi_daire_no();
				borc_data[1] = borc.odenmemis_borc_list().get(i).getKisi_ad();
				borc_data[2] = borc.odenmemis_borc_list().get(i).getKisi_soyad();
				borc_data[3] = borc.odenmemis_borc_list().get(i).getBorc_ay();
				borc_data[3] = borc.odenmemis_borc_list().get(i).getBorc_ay();
				borc_data[4] = borc.odenmemis_borc_list().get(i).getBorc_yil();
				borc_data[5] = borc.odenmemis_borc_list().get(i).getBorc_aidat();

				borclar_model.addRow(borc_data);
			}
		}

	}

}
