package death_tracker;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import death_tracker.InputFrame.distance;
import death_tracker.InputFrame.location;
import death_tracker.InputFrame.rush;
import death_tracker.InputFrame.weapon;

class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int gameCount = 0;

	int closeC = 0;
	int mediumC = 0;
	int farC = 0;

	int shotgunC = 0;
	int rifleC = 0;
	int sniperC = 0;
	int otherC = 0;

	int belowC = 0;
	int sameC = 0;
	int aboveC = 0;

	int meC = 0;
	int themC = 0;
	int neitherC = 0;

	JLabel lGameCount;
	JLabel lDist;
	JLabel lWeapon;
	JLabel lLocation;
	JLabel lRush;

	Death prevDeath;

	MainFrame(String title, ArrayList<Death> list, String[] argString) {
		super(title);
		setLayout(null);

		if (argString != null) {
			gameCount = Integer.parseInt(argString[0]);

			closeC = Integer.parseInt(argString[1]);
			mediumC = Integer.parseInt(argString[2]);
			farC = Integer.parseInt(argString[3]);

			shotgunC = Integer.parseInt(argString[4]);
			rifleC = Integer.parseInt(argString[5]);
			sniperC = Integer.parseInt(argString[6]);
			otherC = Integer.parseInt(argString[7]);

			belowC = Integer.parseInt(argString[8]);
			sameC = Integer.parseInt(argString[9]);
			aboveC = Integer.parseInt(argString[10]);

			meC = Integer.parseInt(argString[11]);
			themC = Integer.parseInt(argString[12]);
			neitherC = Integer.parseInt(argString[13]);
		}

		JFrame frm = this;

		lGameCount = new JLabel("Games Stored: " + gameCount);
		lGameCount.setLocation(10, 10);
		lGameCount.setSize(100, 30);
		add(lGameCount);

		lDist = new JLabel(
				"<html>Distance: <br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Close)"
								: new String("" + (int) ((double) closeC / gameCount * 100) + "% (Close)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Medium)"
								: new String("" + (int) ((double) mediumC / gameCount * 100) + "% (Medium)"))
						+ "<br>&emsp;&emsp;" + new String((gameCount == 0) ? "0% (Far)"
								: new String("" + (int) ((double) farC / gameCount * 100) + "% (Far)"))
						+ "</html>");
		lDist.setLocation(10, 50);
		lDist.setSize(300, 60);
		add(lDist);

		lWeapon = new JLabel(
				"<html>Weapon: <br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Shotgun)"
								: new String("" + (int) ((double) shotgunC / gameCount * 100) + "% (Shotgun)"))
						+ "<br>&emsp;&emsp;"

						+ new String((gameCount == 0) ? "0% (Rifle)"
								: new String("" + (int) ((double) rifleC / gameCount * 100) + "% (Rifle)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Sniper)"
								: new String("" + (int) ((double) sniperC / gameCount * 100) + "% (Sniper)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Other)"
								: new String("" + (int) ((double) otherC / gameCount * 100) + "% (Other)"))
						+ "</html>");
		lWeapon.setLocation(10, 120);
		lWeapon.setSize(300, 90);
		add(lWeapon);

		lLocation = new JLabel(
				"<html>Location: <br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Below)"
								: new String("" + (int) ((double) belowC / gameCount * 100) + "% (Below)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Same)"
								: new String("" + (int) ((double) sameC / gameCount * 100) + "% (Same)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Above)"
								: new String("" + (int) ((double) aboveC / gameCount * 100) + "% (Above)"))
						+ "</html>");
		lLocation.setLocation(10, 220);
		lLocation.setSize(300, 60);
		add(lLocation);

		lRush = new JLabel(
				"<html>Who Rushed: <br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Me)"
								: new String("" + (int) ((double) meC / gameCount * 100) + "% (Me)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Them)"
								: new String("" + (int) ((double) themC / gameCount * 100) + "% (Them)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Neither)"
								: new String("" + (int) ((double) neitherC / gameCount * 100) + "% (Neither)"))
						+ "</html>");
		lRush.setLocation(10, 295);
		lRush.setSize(300, 60);
		add(lRush);

		JButton bStore = new JButton("Store Game");
		bStore.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				InputFrame frame = new InputFrame("Store Game", list, MainFrame.this);
				frame.setSize(300, 300);
				frame.setLocation((int) frm.getLocation().getX(), (int) frm.getLocation().getY()+170);
				frame.setVisible(true);
			}
		});
		bStore.setLocation(25, 380);
		bStore.setSize(230, 40);
		add(bStore);

		JButton bSave = new JButton("Save");
		bSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String text = gameCount + "," + closeC + "," + mediumC + "," + farC + "," + shotgunC + "," + rifleC
						+ "," + sniperC + "," + otherC + "," + belowC + "," + sameC + "," + aboveC + "," + meC + ","
						+ themC + "," + neitherC;

				try {
					File file = new File("fortnite_death_stats.txt");

					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(text);
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		try {
			Image img = ImageIO.read(getClass().getResource("saveIcon.png"));
			Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			bSave.setIcon(new ImageIcon(newImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bSave.setLocation(165, 10);
		bSave.setSize(110, 30);
		add(bSave);

		JButton bUndo = new JButton("Undo");
		bUndo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				undo(prevDeath);

				prevDeath = null;
			}
		});
		try {
			Image img = ImageIO.read(getClass().getResource("undo.png"));
			Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			bUndo.setIcon(new ImageIcon(newImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bUndo.setLocation(165, 50);
		bUndo.setSize(110, 30);
		add(bUndo);

		JButton bReset = new JButton("Reset");
		bReset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String option = JOptionPane.showInputDialog(frm,
						"CAUTION: This will remove ALL currently stored games and all\nstats will be reset back to 0.\n\n\nPlease type in the word 'reset' if you wish to continue.");

				if (option != null && option.equals("reset")) {
					gameCount = 0;

					closeC = 0;
					mediumC = 0;
					farC = 0;

					shotgunC = 0;
					rifleC = 0;
					sniperC = 0;
					otherC = 0;

					belowC = 0;
					sameC = 0;
					aboveC = 0;

					meC = 0;
					themC = 0;
					neitherC = 0;

					updateFrame(null);
				}
			}
		});
		try {
			Image img = ImageIO.read(getClass().getResource("restart.png"));
			Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			bReset.setIcon(new ImageIcon(newImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bReset.setLocation(165, 90);
		bReset.setSize(110, 30);
		add(bReset);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(frm, "Are you sure you want to exit without saving?", "Exit Without Saving",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);

				}
			}
		});
		
		
		JLabel lImage = new JLabel();
		int imageSize = 400;
		try {
			Image img = ImageIO.read(getClass().getResource("fortnite.png"));
			Image newImage = img.getScaledInstance(imageSize, imageSize, Image.SCALE_DEFAULT);
			lImage.setIcon(new ImageIcon(newImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		lImage.setLocation(0, 100);
		lImage.setSize(imageSize, imageSize);
		add(lImage);
		
		getContentPane().setBackground(new Color(224,255,255));

		setResizable(false);
	}

	public void updateFrame(Death d) {
		if (d != null) {
			gameCount++;

			distance dist = d.getDistance();
			weapon w = d.getWeapon();
			location l = d.getLocation();
			rush r = d.getRush();

			if (dist.equals(distance.CLOSE)) {
				closeC++;
			} else if (dist.equals(distance.MEDIUM)) {
				mediumC++;
			} else {
				farC++;
			}

			if (w.equals(weapon.SHOTGUN)) {
				shotgunC++;
			} else if (w.equals(weapon.RIFLE)) {
				rifleC++;
			} else if (w.equals(weapon.SNIPER)) {
				sniperC++;
			} else {
				otherC++;
			}

			if (l.equals(location.BELOW)) {
				belowC++;
			} else if (l.equals(location.SAME)) {
				sameC++;
			} else {
				aboveC++;
			}

			if (r.equals(rush.ME)) {
				meC++;
			} else if (r.equals(rush.THEM)) {
				themC++;
			} else {
				neitherC++;
			}
		}

		lGameCount.setText("Games Stored: " + gameCount);
		lDist.setText(
				"<html>Distance: <br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Close)"
								: new String("" + (int) ((double) closeC / gameCount * 100) + "% (Close)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Medium)"
								: new String("" + (int) ((double) mediumC / gameCount * 100) + "% (Medium)"))
						+ "<br>&emsp;&emsp;" + new String((gameCount == 0) ? "0% (Far)"
								: new String("" + (int) ((double) farC / gameCount * 100) + "% (Far)"))
						+ "</html>");
		lWeapon.setText(
				"<html>Weapon: <br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Shotgun)"
								: new String("" + (int) ((double) shotgunC / gameCount * 100) + "% (Shotgun)"))
						+ "<br>&emsp;&emsp;"

						+ new String((gameCount == 0) ? "0% (Rifle)"
								: new String("" + (int) ((double) rifleC / gameCount * 100) + "% (Rifle)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Sniper)"
								: new String("" + (int) ((double) sniperC / gameCount * 100) + "% (Sniper)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Other)"
								: new String("" + (int) ((double) otherC / gameCount * 100) + "% (Other)"))
						+ "</html>");
		lLocation
				.setText("<html>Location: <br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Below)"
								: new String("" + (int) ((double) belowC / gameCount * 100) + "% (Below)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Same)"
								: new String("" + (int) ((double) sameC / gameCount * 100) + "% (Same)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Above)"
								: new String("" + (int) ((double) aboveC / gameCount * 100) + "% (Above)"))
						+ "</html>");
		lRush.setText(
				"<html>Who Rushed: <br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Me)"
								: new String("" + (int) ((double) meC / gameCount * 100) + "% (Me)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Them)"
								: new String("" + (int) ((double) themC / gameCount * 100) + "% (Them)"))
						+ "<br>&emsp;&emsp;"
						+ new String((gameCount == 0) ? "0% (Neither)"
								: new String("" + (int) ((double) neitherC / gameCount * 100) + "% (Neither)"))
						+ "</html>");

		prevDeath = d;
	}

	private void undo(Death d) {
		if (d != null) {
			gameCount--;

			distance dist = d.getDistance();
			weapon w = d.getWeapon();
			location l = d.getLocation();
			rush r = d.getRush();

			if (dist.equals(distance.CLOSE)) {
				closeC--;
			} else if (dist.equals(distance.MEDIUM)) {
				mediumC--;
			} else {
				farC--;
			}

			if (w.equals(weapon.SHOTGUN)) {
				shotgunC--;
			} else if (w.equals(weapon.RIFLE)) {
				rifleC--;
			} else if (w.equals(weapon.SNIPER)) {
				sniperC--;
			} else {
				otherC--;
			}

			if (l.equals(location.BELOW)) {
				belowC--;
			} else if (l.equals(location.SAME)) {
				sameC--;
			} else {
				aboveC--;
			}

			if (r.equals(rush.ME)) {
				meC--;
			} else if (r.equals(rush.THEM)) {
				themC--;
			} else {
				neitherC--;
			}

			lGameCount.setText("Games Stored: " + gameCount);
			lDist.setText(
					"<html>Distance: <br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Close)"
									: new String("" + (int) ((double) closeC / gameCount * 100) + "% (Close)"))
							+ "<br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Medium)"
									: new String("" + (int) ((double) mediumC / gameCount * 100) + "% (Medium)"))
							+ "<br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Far)"
									: new String("" + (int) ((double) farC / gameCount * 100) + "% (Far)"))
							+ "</html>");
			lWeapon.setText(
					"<html>Weapon: <br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Shotgun)"
									: new String("" + (int) ((double) shotgunC / gameCount * 100) + "% (Shotgun)"))
							+ "<br>&emsp;&emsp;"

							+ new String((gameCount == 0) ? "0% (Rifle)"
									: new String("" + (int) ((double) rifleC / gameCount * 100) + "% (Rifle)"))
							+ "<br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Sniper)"
									: new String("" + (int) ((double) sniperC / gameCount * 100) + "% (Sniper)"))
							+ "<br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Other)"
									: new String("" + (int) ((double) otherC / gameCount * 100) + "% (Other)"))
							+ "</html>");
			lLocation
					.setText("<html>Location: <br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Below)"
									: new String("" + (int) ((double) belowC / gameCount * 100) + "% (Below)"))
							+ "<br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Same)"
									: new String("" + (int) ((double) sameC / gameCount * 100) + "% (Same)"))
							+ "<br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Above)"
									: new String("" + (int) ((double) aboveC / gameCount * 100) + "% (Above)"))
							+ "</html>");
			lRush.setText(
					"<html>Who Rushed: <br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Me)"
									: new String("" + (int) ((double) meC / gameCount * 100) + "% (Me)"))
							+ "<br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Them)"
									: new String("" + (int) ((double) themC / gameCount * 100) + "% (Them)"))
							+ "<br>&emsp;&emsp;"
							+ new String((gameCount == 0) ? "0% (Neither)"
									: new String("" + (int) ((double) neitherC / gameCount * 100) + "% (Neither)"))
							+ "</html>");
		} else {
			JOptionPane.showMessageDialog(MainFrame.this, "Unable to undo at this point.", "Undo Alert",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}

class InputFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum distance {
		CLOSE, MEDIUM, FAR;
	}

	public enum weapon {
		SHOTGUN, RIFLE, SNIPER, OTHER;
	}

	public enum location {
		BELOW, SAME, ABOVE;
	}

	public enum rush {
		ME, THEM, NEITHER;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	InputFrame(String title, ArrayList<Death> list, MainFrame main) {
		super(title);
		setLayout(null);
		JFrame frm = this;

		JLabel l1 = new JLabel("Distance: ");
		l1.setLocation(10, 10);
		l1.setSize(100, 25);
		add(l1);

		distance[] dArray = { distance.CLOSE, distance.MEDIUM, distance.FAR };
		JComboBox cDistance = new JComboBox(dArray);
		cDistance.setLocation(170, 10);
		cDistance.setSize(100, 25);
		cDistance.setBackground(Color.WHITE);
		add(cDistance);

		JLabel l2 = new JLabel("Weapon: ");
		l2.setLocation(10, 50);
		l2.setSize(100, 25);
		add(l2);

		weapon[] wArray = { weapon.SHOTGUN, weapon.RIFLE, weapon.SNIPER, weapon.OTHER, };
		JComboBox cWeapon = new JComboBox(wArray);
		cWeapon.setLocation(170, 50);
		cWeapon.setSize(100, 25);
		cWeapon.setBackground(Color.WHITE);
		add(cWeapon);

		JLabel l3 = new JLabel("Location: ");
		l3.setLocation(10, 90);
		l3.setSize(100, 25);
		add(l3);

		location[] lArray = { location.BELOW, location.SAME, location.ABOVE };
		JComboBox cLocation = new JComboBox(lArray);
		cLocation.setLocation(170, 90);
		cLocation.setSize(100, 25);
		cLocation.setBackground(Color.WHITE);
		add(cLocation);

		JLabel l4 = new JLabel("Who Rushed: ");
		l4.setLocation(10, 130);
		l4.setSize(100, 25);
		add(l4);

		rush[] rArray = { rush.ME, rush.THEM, rush.NEITHER };
		JComboBox cRush = new JComboBox(rArray);
		cRush.setLocation(170, 130);
		cRush.setSize(100, 25);
		cRush.setBackground(Color.WHITE);
		add(cRush);

		JButton bStore = new JButton("Store"); // construct a JButton
		bStore.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				list.add(new Death((distance) cDistance.getSelectedItem(), (weapon) cWeapon.getSelectedItem(),
						(location) cLocation.getSelectedItem(), (rush) cRush.getSelectedItem()));

				main.updateFrame(list.get(list.size() - 1));

				frm.dispose();
			}
		});
		bStore.setLocation(25, 210);
		bStore.setSize(230, 40);
		add(bStore);
		
		getContentPane().setBackground(new Color(224,255,255));
	}
}

public class DeathTrackerMain {

	public static void main(String[] args) {
		ArrayList<Death> list = new ArrayList<Death>();
		String[] argString = null;

		try {
			InputStream is = new FileInputStream("fortnite_death_stats.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();

			argString = line.split(",");

			is.close();
			buf.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		MainFrame frm = new MainFrame("Fortnite Death Tracker", list, argString);

		frm.setSize(300, 470);
		frm.setVisible(true);
	}
}