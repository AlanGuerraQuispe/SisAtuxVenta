package com.michaelbaranov.microba.calendar.ui.basic;

import com.michaelbaranov.microba.calendar.resource.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class ClassicCalendarPanel extends JPanel implements
		PropertyChangeListener {

	public static final String PROPERTY_NAME_DATE = "date";

	public static final String PROPERTY_NAME_LOCALE = "locale";

	public static final String PROPERTY_NAME_ZONE = "zone";

	private Locale locale;

	private TimeZone zone;

	private Calendar calendar;

	private JButton prevButton;

	private JButton nextButton;

	private JLabel selectedDateLabel;

	private DateFormat format;

	private Set focusableComponents = new HashSet();

	private JButton fastPrevButton;

	private JButton fastNextButton;

	public ClassicCalendarPanel(Date aDate, Locale aLocale, TimeZone zone) {
		this.locale = aLocale;
		this.zone = zone;

		prevButton = new JButton();
		nextButton = new JButton();

		fastPrevButton = new JButton();
		fastNextButton = new JButton();

//        prevButton.setPreferredSize(new Dimension(15,15));
//        nextButton.setPreferredSize(new Dimension(10,10));
//        fastPrevButton.setPreferredSize(new Dimension(10,10));
//        fastNextButton.setPreferredSize(new Dimension(10,10));

        setupCalendarButton(nextButton, "/images/cal-next-month.png");
        setupCalendarButton(prevButton, "/images/cal-prev-month.png");
        setupCalendarButton(fastNextButton, "/images/cal-next-year.png");
        setupCalendarButton(fastPrevButton, "/images/cal-prev-year.png");


		prevButton.setMargin(new Insets(0, 0, 0, 0));
		nextButton.setMargin(new Insets(0, 0, 0, 0));
		fastPrevButton.setMargin(new Insets(0, 0, 0, 0));
		fastNextButton.setMargin(new Insets(0, 0, 0, 0));

		Dimension psz = nextButton.getPreferredSize();
		Dimension npsz = new Dimension(psz.height, psz.height);

		nextButton.setPreferredSize(npsz);
		prevButton.setPreferredSize(npsz);

		selectedDateLabel = new JLabel();
		selectedDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectedDateLabel.setFont(selectedDateLabel.getFont().deriveFont(
				Font.BOLD));
		setLayout(new GridBagLayout());
		add(fastPrevButton, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 3, 0), 0, 0));
		add(prevButton, new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 3, 0), 0, 0));
		add(selectedDateLabel, new GridBagConstraints(2, 0, 1, 1, 1.0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 3, 3, 3), 0, 0));
		add(nextButton, new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 3, 0), 0, 0));
		add(fastNextButton, new GridBagConstraints(4, 0, 1, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 3, 0), 0, 0));

		nextButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Date old = calendar.getTime();
				calendar.add(Calendar.MONTH, 1);
				firePropertyChange(PROPERTY_NAME_DATE, old, getDate());
				reflectData();
			}
		});
		prevButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Date old = calendar.getTime();
				calendar.add(Calendar.MONTH, -1);
				firePropertyChange(PROPERTY_NAME_DATE, old, getDate());
				reflectData();
			}
		});
		fastNextButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Date old = calendar.getTime();
				calendar.add(Calendar.YEAR, 1);
				firePropertyChange(PROPERTY_NAME_DATE, old, getDate());
				reflectData();
			}
		});
		fastPrevButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Date old = calendar.getTime();
				calendar.add(Calendar.YEAR, -1);
				firePropertyChange(PROPERTY_NAME_DATE, old, getDate());
				reflectData();
			}
		});

		this.addPropertyChangeListener(this);

		focusableComponents.add(prevButton);
		focusableComponents.add(nextButton);
		focusableComponents.add(fastNextButton);
		focusableComponents.add(fastPrevButton);

		createLocaleAndZoneSensitive();
		calendar.setTime(aDate);
		reflectData();
	}

    private void setupCalendarButton(JButton button, String imagePath){
        ImageIcon image = new ImageIcon(Resource.class.getResource(imagePath));
        button.setIcon(image);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(image.getIconWidth(),
                                              image.getIconHeight()));
    }

	private void createLocaleAndZoneSensitive() {
		if (calendar != null) {
			Date old = calendar.getTime();
			calendar = Calendar.getInstance(zone, locale);
			calendar.setTime(old);
		} else
			calendar = Calendar.getInstance(zone, locale);

		format = new SimpleDateFormat("MMMMM yyyy", locale);
		format.setTimeZone(zone);

		setPreferredLabelSize();

	}

	private void setPreferredLabelSize() {
		Calendar c = Calendar.getInstance(zone, locale);
		c.setTime(getDate());

		JLabel l = new JLabel();
		l.setFont(selectedDateLabel.getFont());

		int maxWidth = Integer.MIN_VALUE;
		for (int i = 0; i <= c.getActualMaximum(Calendar.MONTH); i++) {
			c.set(Calendar.MONTH, i);
			String text = format.format(c.getTime());
			l.setText(text);
			int w = l.getPreferredSize().width;
			if (w > maxWidth)
				maxWidth = w;
		}
		Dimension d = l.getPreferredSize();
		d.width = maxWidth + 10;
		selectedDateLabel.setMinimumSize(d);
		selectedDateLabel.setPreferredSize(d);
		this.revalidate();
	}

	private void reflectData() {
		selectedDateLabel.setText(format.format(calendar.getTime()));
	}

	public Date getDate() {
		return calendar.getTime();
	}

	public void setDate(Date date) {
		Date old = getDate();
		calendar.setTime(date);
		firePropertyChange(PROPERTY_NAME_DATE, old, date);
		reflectData();
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		Locale old = this.locale;
		this.locale = locale;
		createLocaleAndZoneSensitive();
		firePropertyChange(PROPERTY_NAME_LOCALE, old, locale);
		reflectData();
	}

	public TimeZone getZone() {
		return zone;
	}

	public void setZone(TimeZone zone) {
		TimeZone old = this.zone;
		this.zone = zone;
		createLocaleAndZoneSensitive();
		firePropertyChange(PROPERTY_NAME_ZONE, old, locale);
		reflectData();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("focusable")) {
			Boolean value = (Boolean) evt.getNewValue();
			prevButton.setFocusable(value.booleanValue());
			nextButton.setFocusable(value.booleanValue());
			fastNextButton.setFocusable(value.booleanValue());
			fastPrevButton.setFocusable(value.booleanValue());
		}
		if (evt.getPropertyName().equals("enabled")) {
			Boolean value = (Boolean) evt.getNewValue();
			prevButton.setEnabled(value.booleanValue());
			nextButton.setEnabled(value.booleanValue());
			fastNextButton.setEnabled(value.booleanValue());
			fastPrevButton.setEnabled(value.booleanValue());
		}

	}

	public Collection getFocusableComponents() {
		return focusableComponents;
	}

	public void addMonth(int m) {
		int modM = m > 0 ? m : -m;
		int sign = m > 0 ? 1 : -1;
		Date old = calendar.getTime();
		for (int i = 0; i < modM; i++) {
			calendar.add(Calendar.MONTH, sign);
		}
		firePropertyChange(PROPERTY_NAME_DATE, old, getDate());
		reflectData();

	}

}
