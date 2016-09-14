package deal;

import java.math.BigDecimal;

public class DealObject {
	private String buyerEmail;
	private String sellerEmail;
	private String buyerName;
	private String sellerName;
	private String buyerContact;
	private String sellerContact;
	private BigDecimal price;
	private String eatery;
	private String time;
	private int buyerRating;
	private int sellerRating;
	private String buyerComment;
	private String sellerComment;
	
	public DealObject(String be, String se, String bn, String sn, String bc, String sc, BigDecimal p, String l, String t, int br, int sr, String bt, String st) {
		buyerEmail = be;
		sellerEmail = se;
		buyerName = bn;
		sellerName = sn;
		buyerContact = bc;
		sellerContact = sc;
		price = p;
		eatery = l;
		time = t;
		buyerRating = br;
		sellerRating = sr;
		buyerComment = bt;
		sellerComment = st;
	}
}
