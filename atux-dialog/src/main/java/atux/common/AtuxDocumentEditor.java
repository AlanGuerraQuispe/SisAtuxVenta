package atux.common;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class AtuxDocumentEditor extends PlainDocument {

	public static final String ALPHA = " abcdefghijklmnoopqrstuvwxyzooooooooooABCDEFGHIJKLMNoOPQRSTUVWXYZoooooooooo'";

	public static final String CHAR_SPECIAL = "/#-(){}[]<>.,";

	public static final String NUMERIC = "0123456789";

	public static final String LOGIN_CHARS = "abcdefghijklmnoopqrstuvwxyzABCDEFGHIJKLMNoOPQRSTUVWXYZ$_";

	public static final String EMAIL = "abcdefghijklmnoopqrstuvwxyzABCDEFGHIJKLMNoOPQRSTUVWXYZ@_.1234567890";

	public static final String FLOAT = NUMERIC + ".";

	public static final String ALPHA_NUMERIC = ALPHA + NUMERIC;

	public static final String ALPHA_SPECIAL = ALPHA + CHAR_SPECIAL;

	public static final String ALPHA_NUMERIC_SPECIAL = ALPHA_NUMERIC + CHAR_SPECIAL;

	public static final int NONECASE = 0;

	public static final int LOWERCASE = 1;

	public static final int UPPERCASE = 2;

	protected String acceptedChars;

	protected boolean negativeAccepted;

	protected int typeCase;

	private int limit;

	public AtuxDocumentEditor() {
		this(0, ALPHA_NUMERIC_SPECIAL, UPPERCASE);
	}

	public AtuxDocumentEditor(int pLimit) {
		this(pLimit, ALPHA_NUMERIC_SPECIAL, UPPERCASE);
	}

	public AtuxDocumentEditor(String pAcepptedchars) {
		this(0, pAcepptedchars, UPPERCASE);
	}

	public AtuxDocumentEditor(int pLimit, String pAcepptedchars) {
		this(pLimit, pAcepptedchars, UPPERCASE);
	}

	public AtuxDocumentEditor(int pLimit, String pAcceptedchars, int pTypeCase) {
			this.limit = pLimit;
			this.acceptedChars = pAcceptedchars;
			this.typeCase = pTypeCase;
		}


	public void insertString(int pOffset, String pStr, AttributeSet pAttr) throws BadLocationException {

		if (pStr == null) {
			return;
		}

		if ((getLength() + pStr.length()) <= limit || limit == 0) {
			if (typeCase == UPPERCASE) {
				pStr = pStr.toUpperCase();
			} else if (typeCase == LOWERCASE) {
				pStr = pStr.toLowerCase();
			}

			for (int i = 0; i < pStr.length(); i++) {
				if (acceptedChars.indexOf(String.valueOf(pStr.charAt(i))) == -1) {
					return;
				}
			}

			if (acceptedChars.equals(FLOAT) || (acceptedChars.equals(FLOAT + "-") && negativeAccepted)) {
				if (pStr.indexOf(".") != -1) {
					if (getText(0, getLength()).indexOf(".") != -1) {
						return;
					}
				}
			}

			if (negativeAccepted && pStr.indexOf("-") != -1) {
				if (pStr.indexOf("-") != 0 || pOffset != 0) {
					return;
				}
			}

			super.insertString(pOffset, pStr, pAttr);
		}
	}

	public void setNegativeAccepted(boolean pNegativeaccepted) {
		if (acceptedChars.equals(NUMERIC) || acceptedChars.equals(FLOAT) || acceptedChars.equals(ALPHA_NUMERIC)) {
			negativeAccepted = pNegativeaccepted;
			acceptedChars += "-";
		}
	}

	public void setMaxLenght(int pLimit) {
		this.limit = pLimit;
	}

	public int getMaxLenght() {
		return limit;
	}

	public void setAcceptedChars(String pAcceptedChars) {
		this.acceptedChars = pAcceptedChars;
	}

	public String getAcceptedChars() {
		return acceptedChars;
	}

	public void setTypeCase(int pTypeCase) {
		this.typeCase = pTypeCase;
	}

	public int getTypeCase() {
		return this.typeCase;
	}

}