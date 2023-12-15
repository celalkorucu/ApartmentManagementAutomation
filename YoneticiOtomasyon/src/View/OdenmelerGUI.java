package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Model.Borc;
import Model.Odenmeler;
import Model.Yonetici;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class OdenmelerGUI extends JFrame {

	private JPanel contentPane;
	private JTable table_odenmeler;
	static Yonetici yonetici = new Yonetici();

	DefaultTableModel odenmeler_model = new DefaultTableModel();
	Object[] odenmeler_data;

	Borc borc = new Borc();

	Odenmeler odenmeler = new Odenmeler();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OdenmelerGUI frame = new OdenmelerGUI(yonetici);
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
	public OdenmelerGUI(Yonetici yonetici) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Object[] col_odenmeler = new Object[7];
		col_odenmeler[0] = "D.N";
		col_odenmeler[1] = "Ad";
		col_odenmeler[2] = "Soyad";
		col_odenmeler[3] = "Ödenme Tarih Ve Zamaný";
		col_odenmeler[4] = "Ay";
		col_odenmeler[5] = "Yýl";
		col_odenmeler[6] = "Aidat";

		odenmeler_model.setColumnIdentifiers(col_odenmeler);

		odenmeler_data = new Object[7];

		if (odenmeler.odenmeler_list().size() != 0) {
			for (int i = 0; i < odenmeler.odenmeler_list().size(); i++) {
				odenmeler_data[0] = odenmeler.odenmeler_list().get(i).getKisi_daire_no();
				odenmeler_data[1] =  odenmeler.odenmeler_list().get(i).getKisi_ad();
				odenmeler_data[2] =  odenmeler.odenmeler_list().get(i).getKisi_soyad();
				odenmeler_data[3] =  odenmeler.odenmeler_list().get(i).getGuncel_tarih();
				odenmeler_data[4] =  odenmeler.odenmeler_list().get(i).getBorc_ay();
				odenmeler_data[5] =  odenmeler.odenmeler_list().get(i).getBorc_yil();
				odenmeler_data[6] = odenmeler.odenmeler_list().get(i).getAidat();

				odenmeler_model.addRow(odenmeler_data);

			}
		}

		int scroll_uzunluk = odenmeler.odenmeler_list().size() * 30 + 25;
		if (scroll_uzunluk > 400) {
			scroll_uzunluk = 400;
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 120, 800, scroll_uzunluk);
		contentPane.add(scrollPane);

		table_odenmeler = new JTable(odenmeler_model);
		table_odenmeler.setEnabled(false);
		table_odenmeler.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		scrollPane.setViewportView(table_odenmeler);
		
		



		table_odenmeler.setRowHeight(30);
		
		DefaultTableCellRenderer center_renderer = new DefaultTableCellRenderer();
		center_renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table_odenmeler.getColumnModel().getColumn(0).setCellRenderer(center_renderer);
		table_odenmeler.getColumnModel().getColumn(1).setCellRenderer(center_renderer);
		table_odenmeler.getColumnModel().getColumn(2).setCellRenderer(center_renderer);
		table_odenmeler.getColumnModel().getColumn(3).setCellRenderer(center_renderer);
		table_odenmeler.getColumnModel().getColumn(4).setCellRenderer(center_renderer);
		table_odenmeler.getColumnModel().getColumn(5).setCellRenderer(center_renderer);
		table_odenmeler.getColumnModel().getColumn(6).setCellRenderer(center_renderer);
		

		
		table_odenmeler.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_odenmeler.getColumnModel().getColumn(1).setPreferredWidth(180);
		table_odenmeler.getColumnModel().getColumn(2).setPreferredWidth(180);
		table_odenmeler.getColumnModel().getColumn(3).setPreferredWidth(200);
		table_odenmeler.getColumnModel().getColumn(4).setPreferredWidth(40);
		table_odenmeler.getColumnModel().getColumn(5).setPreferredWidth(60);
		table_odenmeler.getColumnModel().getColumn(6).setPreferredWidth(100);

		JTableHeader header = table_odenmeler.getTableHeader();
		header.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));

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
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 864, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 525, 864, 2);
		contentPane.add(separator_1);
		
		JLabel lbldenenBorlarnListesi = new JLabel("\u00D6denen bor\u00E7lar\u0131n listesi ");
		lbldenenBorlarnListesi.setHorizontalAlignment(SwingConstants.CENTER);
		lbldenenBorlarnListesi.setForeground(Color.GRAY);
		lbldenenBorlarnListesi.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lbldenenBorlarnListesi.setBounds(83, 11, 714, 40);
		contentPane.add(lbldenenBorlarnListesi);
	}
}

