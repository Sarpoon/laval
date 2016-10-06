package domain.invoice;

import java.text.DecimalFormat;

public class InvoiceDescripter {
	private Invoice invoice;

	public InvoiceDescripter(Invoice invoice) {
		this.invoice = invoice;
	}

	public String printInvoice() {
		String invoicePrint = invoiceHeader();
		for (InvoiceLine line : invoice.getInvoiceLines()) { // FIXME
			invoicePrint = invoicePrint + line.describe() + "\n";
		}
		invoicePrint = invoiceFooter(invoicePrint);
		return invoicePrint;
	}

	private String invoiceFooter(String invoicePrint) {
		double subTotalValue = invoice.getSubTotal().getPriceValue();
		String subTotalString = new DecimalFormat("##.##")
				.format(subTotalValue);

		double discountValue = invoice.getDiscount().getPriceValue();
		String discountString = new DecimalFormat("##.##")
				.format(discountValue);

		double totalValue = subTotalValue - discountValue;
		String totalString = new DecimalFormat("##.##").format(totalValue);

		invoicePrint = invoicePrint
				+ "--------------------------------------\n";
		invoicePrint = invoicePrint + "Sous total : \t \t" + subTotalString
				+ " $ \n";
		invoicePrint = invoicePrint + "Escompte : \t \t" + discountString
				+ " $ \n";
		invoicePrint = invoicePrint + "Grand total : \t \t" + totalString
				+ " $ \n";
		invoicePrint = invoicePrint
				+ "--------------------------------------\n";
		return invoicePrint;
	}

	private String invoiceHeader() {
		String invoicePrint = "-----------FRUITERIE ABC-----------\n";
		invoicePrint = invoicePrint + "101 rue du jardin\n";
		invoicePrint = invoicePrint + "Quebec, Qc, G1A 2BC\n";
		invoicePrint = invoicePrint
				+ "--------------------------------------\n";
		return invoicePrint;
	}
}
